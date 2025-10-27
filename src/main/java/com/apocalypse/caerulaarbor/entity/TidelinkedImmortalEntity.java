
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.entity.ai.Skill;
import com.apocalypse.caerulaarbor.entity.ai.goal.SeaMonsterAttackableTargetGoal;
import com.apocalypse.caerulaarbor.entity.base.LinkedMonster;
import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Pose;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerBossEvent;

public class TidelinkedImmortalEntity extends LinkedMonster implements GeoEntity {
	// Boss 血条（蓝色）：显示当前名字和血量进度
	private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.PROGRESS);
	// 是否处于“濒死”状态
	private boolean isDyingFlag = false;
	public boolean isDyingFlag(){ return this.isDyingFlag; }
	// 双杀的配对合法性（双方互相链接且均存活）
	private boolean isValidPairForKill(){
	    return this.another != null && this.another.isAlive() && this.isAlive() && this.another.another == this;
	}

	public TidelinkedImmortalEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(ModEntities.TIDELINKED_IMMORTAL.get(), world);
	}

	public TidelinkedImmortalEntity(EntityType<TidelinkedImmortalEntity> type, Level world) {
		super(type, world);
		xpReward = 6;
		this.addSkill(Skill.Builder.of().max(300).initCooldown(100).duration(50).build());
		this.addSkill(Skill.Builder.of().max(60).init(0).duration(20).noRegenerate().build());
		setNoAi(false);
		setMaxUpStep(1.5f);
		setPersistenceRequired();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.4, true) {
			@Override
			protected double getAttackReachSqr(@NotNull LivingEntity entity) {
				return 4;
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false, false));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SnowGolem.class, false, false));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Villager.class, false, false));
		this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Illusioner.class, false, false));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Pillager.class, false, false));
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Vindicator.class, false, false));
		this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Witch.class, false, false));
		this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, Piglin.class, false, false));
		this.targetSelector.addGoal(11, new NearestAttackableTargetGoal<>(this, PiglinBrute.class, false, false));
		this.targetSelector.addGoal(12, new NearestAttackableTargetGoal<>(this, ZombifiedPiglin.class, false, false));
		this.targetSelector.addGoal(13, new SeaMonsterAttackableTargetGoal<>(this, Player.class, false, false));
		this.goalSelector.addGoal(14, new RandomStrollGoal(this, 0.4));
		this.goalSelector.addGoal(15, new RandomLookAroundGoal(this));
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public @NotNull SoundEvent getHurtSound(@NotNull DamageSource ds) {
		return SoundEvents.GUARDIAN_HURT;
	}

	@Override
	public @NotNull SoundEvent getDeathSound() {
		return SoundEvents.GUARDIAN_DEATH;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.DROWN))
			return false;
		return super.hurt(source, amount);
	}

	// 每个 tick 都会调一次：刷新体型并在服务器端同步 Boss 血条进度
	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
		if (!this.level().isClientSide()) {
			this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
		}
	}

	@Override
	public @NotNull EntityDimensions getDimensions(@NotNull Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1.2);
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void startSeenByPlayer(@NotNull ServerPlayer player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(@NotNull ServerPlayer player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	// AI 循环里也会同步一次 Boss 血条（复活阶段 NoAI 时这段不会跑）
	@Override
	public void customServerAiStep() {
		super.customServerAiStep();
		this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
	}

	// 当生命降至 0 或以下时：
	// - 若对方也“濒死”，则触发：双方禁用复活并以动画方式死亡
	// - 否则若允许复活，则阻止死亡转入复活
	@Override
	public void setHealth(float pHealth){
	    if(pHealth <= 0){
	        boolean killBoth = notifyPartnerAndCheckKill(true);
	        if(!killBoth){
	            if(canReborn()){
	                super.setHealth(1);
	                this.setReborning();
	                return;
	            }
	        }else{
	            return; // 已触发双杀，直接返回避免重复流程
	        }
	    }
	    super.setHealth(pHealth);
	}
	
	// 直接通知对方并评估是否需要“双杀”
	private boolean notifyPartnerAndCheckKill(boolean isDying){
	    this.isDyingFlag = isDying;
	    if(this.another instanceof TidelinkedBishopEntity bishop){
	        if(this.isDyingFlag && bishop.isDyingFlag() && isValidPairForKill()){
	            // 双方禁用复活并强制死亡
	            this.disableRebirth();
	            bishop.disableRebirth();
	            this.forceDieWithAnimation();
	            bishop.forceDieWithAnimation();
	            return true;
	        }
	    }
	    return false;
	}

	// 复活结束：先切到“躺尸闲置”动画
	@Override
    public void endReborn(){
        this.triggerAnim("start_reborn","die_idle");
        // 复活结束，清除“濒死”标记
        notifyPartnerAndCheckKill(false);
    }





	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 75);
		builder = builder.add(Attributes.ARMOR, 10);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 8);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.65);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (event.isMoving()){
			return event.setAndContinue(RawAnimation.begin().thenLoop(animLoc("move")));
		}
		if (this.isDeadOrDying()) {
			return event.setAndContinue(RawAnimation.begin().thenPlay(animLoc("die")));
		}
		return event.setAndContinue(RawAnimation.begin().thenLoop(animLoc("idle")));
	}

	private PlayState attackingPredicate(AnimationState event) {
        if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
            this.swinging = true;
            this.lastSwing = level().getGameTime();
        }
        if (this.swinging && this.lastSwing + 7L <= level().getGameTime()) {
            this.swinging = false;
        }
        if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().forceAnimationReset();
            return event.setAndContinue(RawAnimation.begin().thenPlay(animLoc("attack")));
        }
        return PlayState.CONTINUE;
    }



	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 2, this::movementPredicate));
		data.add(new AnimationController<>(this, "attacking", 2, this::attackingPredicate));
		data.add(new AnimationController<>(this, "start_reborn", 0, event -> PlayState.STOP)
				.triggerableAnim("start_reborn", RawAnimation.begin()
					.thenPlay(animLoc("die"))
					.thenLoop(animLoc("die_loop")))
				.triggerableAnim("die_idle", RawAnimation.begin()
					.thenPlay(animLoc("die_idle"))));
	}


	// 开始复活！！！
	public void startReborn(){
		this.triggerAnim("start_reborn","start_reborn");
	}

}
