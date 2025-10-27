package com.apocalypse.caerulaarbor.entity.base;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class LinkedMonster extends MultiPhaseMonster{

    public LinkedMonster another;
    public SimpleParticleType linkedParticleType = ParticleTypes.FIREWORK;

    public boolean isParticleStarter = true;
    // 新增：持久化链接的 UUID，用于跨存档重建连接
    private UUID anotherUUID = null;

    public LinkedMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.finalPhase = 64;
        this.setInfinitePhase();
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("linkedAnother", another.getUUID().toString());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if(pCompound.contains("linkedAnother") && this.level() instanceof ServerLevel sLevel){
            Entity entity = sLevel.getEntity(UUID.fromString(pCompound.getString("linkedAnother")));
            if(entity instanceof LinkedMonster _lkd) another = _lkd;
        }
    }

    public void linkWith(LinkedMonster another){
        if(another == null || !another.isAlive()) return;
        this.another = another;
        another.another = this;
        // 持久化记录对方 UUID，用于重载后重建链接
        this.anotherUUID = another.getUUID();
        another.anotherUUID = this.getUUID();
        if(this.isParticleStarter != this.another.isParticleStarter){
            this.isParticleStarter = true;
            this.another.isParticleStarter = false;
        }
    }

    public boolean isValidLink(){
        if(another == null || !another.isAlive()) return false;
        return !another.isReborning();
    }

    private void linkedParticle(SimpleParticleType particle){
        if(!isParticleStarter)return;
        Level level = this.level();
        if(!isValidLink())return;
        Vec3 thisWaist = this.position().add(0,this.getBbHeight()/2,0);
        Vec3 line = thisWaist.add(another.position().add(0,another.getBbHeight()/2,0).reverse()).reverse();
        double dist = line.length();
        if(dist >= 64) return;
        if(level instanceof ServerLevel sLevel){
            for(double dDist=0.25; dDist < dist; dDist += 0.25){
                Vec3 posVec = thisWaist.add(line).scale(dDist/dist);
                sLevel.sendParticles(particle, posVec.x, posVec.y, posVec.z, 1, 0, 0, 0,0);
            }
        }
    }


    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.anotherUUID != null) {
            tag.putUUID("linkedPartnerUUID", this.anotherUUID);
        }
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID("linkedPartnerUUID")) {
            this.anotherUUID = tag.getUUID("linkedPartnerUUID");
        }
    }


    @Override
    public boolean extraRebornCondition(){
        // 只有存在有效链接时才允许进入复活流程
        return this.isValidLink();
    }

    @Override
    public void baseTick(){
        // 尝试在服务端重建链接（跨存档/重进世界）
        if (!this.level().isClientSide() && this.another == null && this.anotherUUID != null && this.level() instanceof ServerLevel sLevel) {
            // 优先使用服务端通过 UUID 获取实体（如可用）
            var partner = sLevel.getEntity(this.anotherUUID);
            if (partner instanceof LinkedMonster lm && lm.isAlive()) {
                this.linkWith(lm);
            } else {
                // 兼容：若直接获取失败，在附近扫描匹配 UUID 的 LinkedMonster
                var candidates = sLevel.getEntitiesOfClass(LinkedMonster.class, this.getBoundingBox().inflate(128));
                for (var c : candidates) {
                    if (this.anotherUUID.equals(c.getUUID())) {
                        this.linkWith(c);
                        break;
                    }
                }
            }
        }

        if(this.isParticleStarter && this.tickCount % 5 == 0) linkedParticle(linkedParticleType);
        super.baseTick();
    }
}
