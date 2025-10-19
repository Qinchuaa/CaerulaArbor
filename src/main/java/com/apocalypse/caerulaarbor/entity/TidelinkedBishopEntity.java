
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.entity.ai.Skill;
import com.apocalypse.caerulaarbor.entity.ai.goal.SeaMonsterAttackableTargetGoal;
import com.apocalypse.caerulaarbor.entity.base.LinkedMonster;
import com.apocalypse.caerulaarbor.entity.bullets.FishShootEntity;
import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;

public class TidelinkedBishopEntity extends LinkedMonster implements RangedAttackMob, GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(TidelinkedBishopEntity.class, EntityDataSerializers.BOOLEAN);
	private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.GREEN, ServerBossEvent.BossBarOverlay.NOTCHED_6);

	public TidelinkedBishopEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(ModEntities.TIDELINKED_BISHOP.get(), world);
	}

	public TidelinkedBishopEntity(EntityType<TidelinkedBishopEntity> type, Level world) {
		super(type, world);
		xpReward = 32;
		rebornTime = 400;
		this.addSkill(Skill.Builder.of().max(200).initCooldown(160).duration(50).build());
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1, 60, 18f));
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
	public @NotNull MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return SoundEvents.EVOKER_AMBIENT;
	}

	@Override
	public @NotNull SoundEvent getHurtSound(@NotNull DamageSource ds) {
		return SoundEvents.EVOKER_HURT;
	}

	@Override
	public @NotNull SoundEvent getDeathSound() {
		return SoundEvents.EVOKER_DEATH;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.DROWN))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		LinkedMonster immortal = ModEntities.TIDELINKED_IMMORTAL.get().create(this.level());
        if (immortal != null) {
            immortal.setPos(this.position());
			this.linkWith(immortal);
			this.level().addFreshEntity(immortal);
        }
        return livingdata;
	}

	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
	}

	@Override
	public @NotNull EntityDimensions getDimensions(@NotNull Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1.5);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float flval) {
		FishShootEntity.shoot(this, target);
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

	@Override
	public void customServerAiStep() {
		super.customServerAiStep();
		this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
	}

	public void startReborn(){
		this.triggerAnim("start_reborn","start_reborn");
	}

	public void endReborn(){
		this.triggerAnim("end_reborn","end_reborn");
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 160);
		builder = builder.add(Attributes.ARMOR, 6);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 9);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if(this.isReborning()){
			return event.setAndContinue(RawAnimation.begin().thenLoop(animLoc("die_loop")));
		}
		if (event.isMoving()) {
			return event.setAndContinue(RawAnimation.begin().thenLoop(animLoc("move")));
		}
		if (this.isDeadOrDying()) {
			return event.setAndContinue(RawAnimation.begin().thenPlay(animLoc("die")));
		}
		return event.setAndContinue(RawAnimation.begin().thenLoop(animLoc("idle")));
	}

	private PlayState attackingPredicate(AnimationState event) {
		double d1 = this.getX() - this.xOld;
		double d0 = this.getZ() - this.zOld;
		float velocity = (float) Math.sqrt(d1 * d1 + d0 * d0);
		if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
			this.swinging = true;
			this.lastSwing = level().getGameTime();
		}
		if (this.swinging && this.lastSwing + 25L <= level().getGameTime()) {
			this.swinging = false;
		}
		if ((this.swinging || this.entityData.get(SHOOT)) && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			event.getController().forceAnimationReset();
			return event.setAndContinue(RawAnimation.begin().thenPlay(animLoc("attack")));
		}
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 2, this::movementPredicate));
		data.add(new AnimationController<>(this, "attacking", 2, this::attackingPredicate));
		data.add(new AnimationController<>(this, "start_reborn", 0, event -> PlayState.STOP)
				.triggerableAnim("start_reborn", RawAnimation.begin()
						.thenPlay(animLoc("die"))
						.thenLoop(animLoc("die_loop"))));
		data.add(new AnimationController<>(this, "stop_reborn", 0, event -> PlayState.STOP)
				.triggerableAnim("stop_reborn", RawAnimation.begin()
						.thenPlay(animLoc("die_idle"))
						.thenLoop(animLoc("idle"))));
	}
}
