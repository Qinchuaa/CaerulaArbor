package com.apocalypse.caerulaarbor.entity.base;

import com.apocalypse.caerulaarbor.entity.ai.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class MultiPhaseMonster extends SkilledSeaMonster{
    public MultiPhaseMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private int curPhase = 0; //当前阶段序号
    public int finalPhase = 1;//最终阶段序号
    protected boolean infinitePhase = false;//是否无限转阶段！！！
    protected int rebornTime = 200; //复活用时
    private int rebornElapse = 0;//复活状态剩余时间
    protected int invulnerableTimeAfterReborn = 0;//转阶段后无敌时间，默认0致敬杰斯顿（喜） 

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("phase",curPhase);
        pCompound.putInt("rebornElapse",rebornElapse);
        pCompound.putInt("invTimePostReborn",invulnerableTimeAfterReborn);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        curPhase = pCompound.getInt("phase");
        rebornElapse = pCompound.getInt("rebornElapse");
        invulnerableTimeAfterReborn = pCompound.getInt("invTimePostReborn");
    }

    public int getPhase() {return curPhase;}

    public int getFinalPhase() {return finalPhase;}

    public void setInfinitePhase() {this.infinitePhase = true;}

    /**
     * 能否转阶段
     */
    public boolean canReborn() {
        return (curPhase < finalPhase || infinitePhase) && !isPermanent() && !isReborning() && extraRebornCondition();
    }

    private void forceStopReborning(){
        // 改为带动画的安全击杀，避免直接 kill() 导致瞬移除
        this.forceDieWithAnimation();
    }

    /**
     * 对外：强制以动画方式死亡，避免 remove/kill 的瞬移除
     * - 清除复活与无敌状态
     * - 通过伤害通道触发标准死亡动画
     */
    public void forceDieWithAnimation(){
        // 结束复活与无敌，确保伤害生效
        this.rebornElapse = 0;
        this.stopPermanent();
        // 通过伤害通道触发标准死亡动画
        this.hurt(this.level().damageSources().generic(), this.getMaxHealth() * 1000f);
    }


    public void disableRebirth(){
        this.rebornElapse = 0;
        this.stopPermanent();
        this.infinitePhase = false;
        this.curPhase = this.finalPhase;
    }

    /**
    * 额外转阶段条件，用来Override
    */
    public boolean extraRebornCondition(){return true;}

    public boolean isReborning() {return rebornElapse > 0;}

    protected void setReborning(){
        if(!infinitePhase) curPhase ++;
        startReborn();
        this.setPermanent(rebornTime + invulnerableTimeAfterReborn);
        rebornElapse = rebornTime;
        this.setNoAi(true);
    }

    /**
     * 开始转阶段时要做的事，比如播放个动画啥的
     */
    public abstract void startReborn();

    /**
     * 结束转阶段时要做的事，比如播放个动画啥的
     */
    public abstract void endReborn();

    @Override
    public void setHealth(float pHealth){
        if(pHealth <= 0 && canReborn()){
            this.setHealth(1);
            this.setReborning();
            return;
        }
        super.setHealth(pHealth);
    }

    @Override
    public void remove(@NotNull RemovalReason pReason){
        if((pReason == RemovalReason.KILLED || pReason == RemovalReason.DISCARDED) && canReborn()){
            this.setReborning();
            return;
        }
        super.remove(pReason);
    }

    @Override
    public void baseTick(){
        if(isReborning()){
            double healPerc = 1- (double) rebornElapse / rebornTime;
            this.setHealth((float) Math.max(1,this.getMaxHealth() * healPerc));
            
            if(--rebornElapse == 0) {
                endReborn();
                this.setNoAi(false);
            }
            
        }else if(this.isNoAi())
            this.setNoAi(false);
        super.baseTick();
    }

    @Override
    public void heal(float amount){
        if(isReborning()) return;
        super.heal(amount);
    }

    @Override
    public void travel(net.minecraft.world.phys.Vec3 vec){
        if(isReborning()){
            super.travel(net.minecraft.world.phys.Vec3.ZERO);
            return;
        }
        super.travel(vec);
    }
}
