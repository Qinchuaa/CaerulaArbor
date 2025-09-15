package com.apocalypse.caerulaarbor.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class MultiPhaseMonster extends SkilledSeaMonster{
    public MultiPhaseMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private int curPhase = 0; //当前阶段序号
    protected int finalPhase = 1;//最终阶段序号
    protected boolean isInfinitePhase;//是否无限转阶段！！！
    protected int rebornTime = 200; //复活用时
    private int rebornElapse = 0;//复活状态剩余时间
    protected int invulnerableTimeAfterReborn = 0;//转阶段后无敌时间，默认0致敬杰斯顿（喜）

    public int getPhase() {return curPhase;}

    public int getFinalPhase() {return finalPhase;}

    /**
     * 能否转阶段
     */
    public boolean canReborn() {return (curPhase < finalPhase || isInfinitePhase) && !isPermanent() && !isReborning() && extraRebornCondition();}

    /**
    * 额外转阶段条件，用来Override
    */
    public boolean extraRebornCondition(){return true;}

    public boolean isReborning() {return rebornElapse > 0;}

    protected void setReborning(){
        curPhase ++;
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
            super.setHealth(1);
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
}
