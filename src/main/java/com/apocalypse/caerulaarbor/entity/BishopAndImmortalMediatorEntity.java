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
 * 专门服务 Bishop 和 Immortal 的“中介”实体。
 * 作用：让两者互相联动（一起死或一起复活），并复用基础中介的统计/自清理逻辑。
 */
public class BishopAndImmortalMediatorEntity extends MediatorEntity {

    // 记录 Bishop 实体的引用（方便做联动判断）
    private TidelinkedBishopEntity bishopRef;
    // 记录 Immortal 实体的引用（方便做联动判断）
    private TidelinkedImmortalEntity immortalRef;
    // 标记 Bishop 是否处于“濒死”状态（由怪物主动上报）
    private boolean bishopIsDying = false;
    // 标记 Immortal 是否处于“濒死”状态（由怪物主动上报）
    private boolean immortalIsDying = false;
    // 记录 Bishop 是否已触发复活（避免重复触发）
    private boolean bishopRebornTriggered = false;
    // 记录 Immortal 是否已触发复活（避免重复触发）
    private boolean immortalRebornTriggered = false;

    // 通过网络包构造：一般由服务端召唤中介用到
    public BishopAndImmortalMediatorEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.BISHOP_IMMORTAL.get(), world);
    }

    // 常规构造：初始化为不可见、无 AI、持久化的小工具实体
    public BishopAndImmortalMediatorEntity(EntityType<BishopAndImmortalMediatorEntity> type, Level world) {
        super(type, world);
    }

    // 属性复用基础中介：这里不做数值修改
    public static AttributeSupplier.Builder createAttributes() {
        
        return MediatorEntity.createAttributes();
    }

    @Override
    public boolean allowsLinking(@NotNull LinkedMonster monster) {
        // 只允许 Bishop/Immortal 绑定到这个中介（其他怪物一律不接）
        if (this.isRemoved()) return false;
        return monster instanceof TidelinkedBishopEntity || monster instanceof TidelinkedImmortalEntity;
    }

    // 校验：当前记录的 Bishop/Immortal 是否仍是有效互链配对
    // 是否仍是一对有效绑定（互相引用且都活着），用于判断是否能“同时处决”
	private boolean isValidPairForKill() {
        return this.bishopRef != null
                && this.immortalRef != null
                && this.bishopRef.isAlive()
                && this.immortalRef.isAlive()
                && this.bishopRef.another == this.immortalRef
                && this.immortalRef.another == this.bishopRef;
    }

    // 检测 然后清理状态
    private void resetPairState() {
        this.bishopIsDying = false;
        this.immortalIsDying = false;
        this.bishopRef = null;
        this.immortalRef = null;
        this.bishopRebornTriggered = false;
        this.immortalRebornTriggered = false;
    }

    // 是否允许某一方复活：如果两人都在濒死且仍是有效一对，则不允许（会一起死）
    @Override
    public boolean allowsReborn(@NotNull LinkedMonster monster) {
        if (!this.allowsLinking(monster)) return false;
        
        if (this.bishopIsDying && this.immortalIsDying && isValidPairForKill()) return false;
        return true;
    }

    // 不禁止随世界生成；该中介通常由逻辑召唤，但保留默认行为
    @Override
    protected boolean isGameSpawningProhibited() {
       
        return false;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
        // 说明：把两者的初始状态清空，确保不会误判濒死或复活标记
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
        // 清理失效引用的濒死标志，避免跨对误杀
        if (this.bishopIsDying && (this.bishopRef == null || !this.bishopRef.isAlive())) {
            this.bishopIsDying = false;
        }
        if (this.immortalIsDying && (this.immortalRef == null || !this.immortalRef.isAlive())) {
            this.immortalIsDying = false;
        }
        // 处决：双方都处于濒死且仍是有效绑定的一对时，直接同时处决并重置状态
        // 说明：真正的死亡处理在 killMonster 中执行（优先播放死亡动画，否则抑制中介通知后移除）
        if (this.bishopIsDying && this.immortalIsDying && isValidPairForKill()) {
            killMonster(this.bishopRef);
            killMonster(this.immortalRef);
            resetPairState();
        }
    }


    /**
     * 更新某一方的濒死状态，并做联动判断：
     * - 当两人都在濒死且仍是一对时，直接一起杀；返回 true
     * - 否则仅更新状态；返回 false
     */
    public boolean updateDyingState(LinkedMonster monster, boolean isDying) {
        if (monster instanceof TidelinkedBishopEntity b) {
            this.bishopIsDying = isDying;
            this.bishopRef = b;
        } else if (monster instanceof TidelinkedImmortalEntity i) {
            this.immortalIsDying = isDying;
            this.immortalRef = i;
        }
        // 若任一方声明非濒死，且引用已失效，则清理引用
        if (!this.bishopIsDying && this.bishopRef != null && !this.bishopRef.isAlive()) this.bishopRef = null;
        if (!this.immortalIsDying && this.immortalRef != null && !this.immortalRef.isAlive()) this.immortalRef = null;

        // 处决：双方都处于濒死且仍是有效绑定的一对时，直接同时处决并重置状态
        // 说明：真正的死亡处理在 killMonster 中执行（优先播放死亡动画，否则抑制中介通知后移除）
        if (this.bishopIsDying && this.immortalIsDying && isValidPairForKill()) {
            killMonster(this.bishopRef);
            killMonster(this.immortalRef);
            resetPairState();
            return true;
        }
        return false;
    }

    // 处决单个怪：优先播放死亡动画；非多阶段怪则抑制通知后直接移除
    private void killMonster(LinkedMonster monster) {
        if (monster != null && monster.isAlive()) {
            // 避免直接移除导致不播放死亡动画
            if (monster instanceof com.apocalypse.caerulaarbor.entity.base.MultiPhaseMonster) {
                ((com.apocalypse.caerulaarbor.entity.base.MultiPhaseMonster) monster).forceDieWithAnimation();
            } else {
                // 兜底：非多阶段怪仍抑制通知并移除
                monster.setSuppressMediatorNotification(true);
                monster.remove(net.minecraft.world.entity.Entity.RemovalReason.KILLED);
            }
        }
    }
}