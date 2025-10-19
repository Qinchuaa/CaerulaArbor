package com.apocalypse.caerulaarbor.entity.base;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import com.apocalypse.caerulaarbor.entity.MediatorEntity;

public abstract class LinkedMonster extends MultiPhaseMonster{

    public LinkedMonster another;
    public SimpleParticleType linkedParticleType = ParticleTypes.FIREWORK;

    public boolean isParticleStarter = true;
    // 添加：中介通知抑制标记，防止递归通知导致栈溢出
    private boolean suppressMediatorNotification = false;

    public LinkedMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.finalPhase = 64;
        this.setInfinitePhase();
    }

    public void linkWith(LinkedMonster another){
        if(another == null || !another.isAlive()) return;
        this.another = another;
        another.another = this;
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

    // 添加：抑制标记访问器
    public void setSuppressMediatorNotification(boolean suppress) {
        this.suppressMediatorNotification = suppress;
    }

    public boolean isSuppressMediatorNotification() {
        return this.suppressMediatorNotification;
    }

    protected boolean mediatorAllowsReborn(){
        Level level = this.level();
        if (level instanceof ServerLevel sLevel) {
            var mediators = sLevel.getEntitiesOfClass(MediatorEntity.class, this.getBoundingBox().inflate(64));
            for (var mediator : mediators) {

                if (mediator.allowsReborn(this)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean extraRebornCondition(){return mediatorAllowsReborn();}

    @Override
    public void baseTick(){
        if(this.isParticleStarter && this.tickCount % 5 == 0) linkedParticle(linkedParticleType);
        super.baseTick();
    }
}
