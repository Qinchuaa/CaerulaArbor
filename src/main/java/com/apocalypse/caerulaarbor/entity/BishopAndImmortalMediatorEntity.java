package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.entity.base.LinkedMonster;
import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.nbt.CompoundTag;
import javax.annotation.Nullable;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;

/**
 * Bishop 与 Immortal 专用的中介实体，仅允许 Bishop 与 Immortal 建立连接。
 * 继承 MediatorEntity，复用其链接统计与自删除逻辑。
 */
public class BishopAndImmortalMediatorEntity extends MediatorEntity {

    private TidelinkedBishopEntity bishopRef;
    private TidelinkedImmortalEntity immortalRef;
    private boolean bishopIsDying = false;
    private boolean immortalIsDying = false;
    private boolean bishopRebornTriggered = false;
    private boolean immortalRebornTriggered = false;

    public BishopAndImmortalMediatorEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.BISHOP_IMMORTAL.get(), world);
    }

    public BishopAndImmortalMediatorEntity(EntityType<BishopAndImmortalMediatorEntity> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        
        return MediatorEntity.createAttributes();
    }

    @Override
    public boolean allowsLinking(@NotNull LinkedMonster monster) {
        // 仅允许 Bishop 与 Immortal 建立有效链接
        if (this.isRemoved()) return false;
        return monster instanceof TidelinkedBishopEntity || monster instanceof TidelinkedImmortalEntity;
    }

    // 新增：中介对重生许可的明确判断
    @Override
    public boolean allowsReborn(@NotNull LinkedMonster monster) {
        if (!this.allowsLinking(monster)) return false;
        if (this.bishopIsDying && this.immortalIsDying) return false;
        return true;
    }

    @Override
    protected boolean isGameSpawningProhibited() {
       
        return false;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
        net.minecraft.server.level.ServerLevel serverLevel = world.getLevel();
        var bishopType = ModEntities.TIDELINKED_BISHOP.get();
        var immortalType = ModEntities.TIDELINKED_IMMORTAL.get();

        var bishop = bishopType.create(serverLevel);
        var immortal = immortalType.create(serverLevel);

        if (bishop != null) {
            bishop.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            bishop.finalizeSpawn(world, difficulty, MobSpawnType.MOB_SUMMONED, null, null);
            serverLevel.addFreshEntity(bishop);
            this.bishopRef = bishop;
        }
        if (immortal != null) {
            immortal.moveTo(this.getX() + 1.0, this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            immortal.finalizeSpawn(world, difficulty, MobSpawnType.MOB_SUMMONED, null, null);
            serverLevel.addFreshEntity(immortal);
            this.immortalRef = immortal;
        }
        if (bishop != null && immortal != null) {
            bishop.linkWith(immortal);
        }
        this.bishopIsDying = false;
        this.immortalIsDying = false;
        this.bishopRebornTriggered = false;
        this.immortalRebornTriggered = false;
        return super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.bishopIsDying && this.immortalIsDying) {
            killMonster(this.bishopRef);
            killMonster(this.immortalRef);
        }
    }


    /**
     * 新接口：更新某一方的 isDying 状态，并根据规则决定杀死双方或启动其重生。
     * @return 是否满足“双方同时濒死而被直接杀死”的条件
     */
    public boolean updateDyingState(LinkedMonster monster, boolean isDying) {
        if (monster instanceof TidelinkedBishopEntity b) {
            this.bishopIsDying = isDying;
            this.bishopRef = b;
        } else if (monster instanceof TidelinkedImmortalEntity i) {
            this.immortalIsDying = isDying;
            this.immortalRef = i;
        }
        if (this.bishopIsDying && this.immortalIsDying) {
            killMonster(this.bishopRef);
            killMonster(this.immortalRef);
            return true;
        }
        return false;
    }

    private void killMonster(LinkedMonster monster) {
        if (monster != null && monster.isAlive()) {
            // 在执行击杀前设置抑制标记，防止实体在死亡流程中再次通知中介
            monster.setSuppressMediatorNotification(true);
            // 使用 remove(KILLED) 直接移除，避免 LivingEntity.kill() 的递归路径
            monster.remove(net.minecraft.world.entity.Entity.RemovalReason.KILLED);
        }
    }
}