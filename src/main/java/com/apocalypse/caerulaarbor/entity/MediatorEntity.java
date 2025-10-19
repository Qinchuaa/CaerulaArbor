package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;

import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.nbt.CompoundTag;
import javax.annotation.Nullable;
import com.apocalypse.caerulaarbor.entity.base.LinkedMonster;

public class MediatorEntity extends Monster {

    // --- Link monitoring attributes ---
    private double linkRadius = 64.0;           // 搜索半径，影响连接统计范围
    private int minActiveLinks = 0;             // 最低连接数阈值，低于则删除；0 表示禁用
    private int dropDeleteThreshold = 0;        // 连接减少阈值，掉线达到该数量则删除；0 表示禁用
    private int lastActiveLinks = -1;           // 上一 tick 的有效连接数，用于检测减少量
    private int graceTicks = 0;                 // 宽限期，生成后前 N tick 不触发自删除

    public MediatorEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.MEDIATOR.get(), world);
    }

    public MediatorEntity(EntityType<? extends MediatorEntity> type, Level world) {
        super(type, world);
        this.setInvisible(true);
        this.setNoAi(true);
        this.setPersistenceRequired();
        this.setHealth(1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MAX_HEALTH, 1.0);
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.0);
        builder = builder.add(Attributes.ARMOR, 0.0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 0.0);
        builder = builder.add(Attributes.FOLLOW_RANGE, 0.0);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
        return builder;
    }

 
    public double getLinkRadius() { return linkRadius; }
    public void setLinkRadius(double linkRadius) { this.linkRadius = Math.max(0.0, linkRadius); }
    public int getMinActiveLinks() { return minActiveLinks; }
    public void setMinActiveLinks(int minActiveLinks) { this.minActiveLinks = Math.max(0, minActiveLinks); }
    public int getDropDeleteThreshold() { return dropDeleteThreshold; }
    public void setDropDeleteThreshold(int dropDeleteThreshold) { this.dropDeleteThreshold = Math.max(0, dropDeleteThreshold); }
    public int getGraceTicks() { return graceTicks; }
    public void setGraceTicks(int graceTicks) { this.graceTicks = Math.max(0, graceTicks); }

    @Override
    public void baseTick() {
        super.baseTick();
    
        if (!this.level().isClientSide) {
            // 掉入虚空自动删除
            if (this.getY() < this.level().getMinBuildHeight()) {
                this.discard();
                return;
            }
            //  和平模式下自动删除
            if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
                this.discard();
                return;
            }

            // 统计当前有效连接数量
            ServerLevel sLevel = (ServerLevel) this.level();
            int currentActive = countActiveLinks(sLevel);

            // 宽限期：不进行删除判断，同时跟随更新 lastActiveLinks 防止后续误判
            if (this.tickCount < graceTicks) {
                lastActiveLinks = currentActive;
                return;
            }

            // 初始化上一值，避免刚生成时误判
            if (lastActiveLinks < 0) {
                lastActiveLinks = currentActive;
            } else {
                int drop = lastActiveLinks - currentActive;
                boolean dropTrigger = dropDeleteThreshold > 0 && drop >= dropDeleteThreshold;
                boolean minTrigger = minActiveLinks > 0 && currentActive < minActiveLinks;
                if (dropTrigger || minTrigger) {
                    this.discard();
                    return;
                }
                lastActiveLinks = currentActive;
            }
        }
    }

    private int countActiveLinks(ServerLevel sLevel) {
        int count = 0;
        for (var monster : sLevel.getEntitiesOfClass(LinkedMonster.class, this.getBoundingBox().inflate(linkRadius))) {
            if (this.allowsLinking(monster)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isInvulnerableTo(@NotNull DamageSource source) {
        return true;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void pushEntities() {
        //Ψ(￣∀￣)Ψ
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    
    protected boolean allowsByTags(LinkedMonster monster) {
        // 按标签过滤
        return true;
    }

    protected boolean allowsByTeam(LinkedMonster monster) {
        // 按团队过滤
        return true;
    }

    
    public boolean allowsLinking(LinkedMonster monster) {
        if (this.isRemoved()) return false;
        return allowsByTags(monster) && allowsByTeam(monster);
    }

  
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putDouble("linkRadius", this.linkRadius);
        tag.putInt("minActiveLinks", this.minActiveLinks);
        tag.putInt("dropDeleteThreshold", this.dropDeleteThreshold);
        tag.putInt("graceTicks", this.graceTicks);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("linkRadius")) this.linkRadius = Math.max(0.0, tag.getDouble("linkRadius"));
        if (tag.contains("minActiveLinks")) this.minActiveLinks = Math.max(0, tag.getInt("minActiveLinks"));
        if (tag.contains("dropDeleteThreshold")) this.dropDeleteThreshold = Math.max(0, tag.getInt("dropDeleteThreshold"));
        if (tag.contains("graceTicks")) this.graceTicks = Math.max(0, tag.getInt("graceTicks"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
        // 禁止基础中介通过指令与刷怪蛋直接生成
        if (reason == MobSpawnType.COMMAND || reason == MobSpawnType.SPAWN_EGG) {
            if (isGameSpawningProhibited()) {
                this.discard();
            }
        }
        return super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
    }

    protected boolean isGameSpawningProhibited() {
        return true;
    }

    @Override
    public void kill() {
        // 被 /kill 指令时，直接删除实体
        this.remove(RemovalReason.KILLED);
    }

    
   
    public boolean allowsReborn(@NotNull LinkedMonster monster) {
        return this.allowsLinking(monster);
    }
}