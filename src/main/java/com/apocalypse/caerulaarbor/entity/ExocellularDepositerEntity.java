
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.entity.ai.goal.SeaMonsterAttackableTargetGoal;
import com.apocalypse.caerulaarbor.entity.base.SeaMonster;
import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

//TODO 我的行数怎么是你们的两倍！？

public class ExocellularDepositerEntity extends SeaMonster {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(ExocellularDepositerEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(ExocellularDepositerEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(ExocellularDepositerEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public ExocellularDepositerEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(ModEntities.EXOCELLULAR_DEPOSITER.get(), world);
	}

	public ExocellularDepositerEntity(EntityType<ExocellularDepositerEntity> type, Level world) {
		super(type, world);
		xpReward = 4;
		setNoAi(false);
		setMaxUpStep(0.6f);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0);
		this.moveControl = new MoveControl(this) {
			@Override
			public void tick() {
				if (ExocellularDepositerEntity.this.isInWater())
					ExocellularDepositerEntity.this.setDeltaMovement(ExocellularDepositerEntity.this.getDeltaMovement().add(0, 0.005, 0));
				if (this.operation == Operation.MOVE_TO && !ExocellularDepositerEntity.this.getNavigation().isDone()) {
					double dx = this.wantedX - ExocellularDepositerEntity.this.getX();
					double dy = this.wantedY - ExocellularDepositerEntity.this.getY();
					double dz = this.wantedZ - ExocellularDepositerEntity.this.getZ();
					float f = (float) (Mth.atan2(dz, dx) * (180 / Math.PI)) - 90;
					float f1 = (float) (this.speedModifier * ExocellularDepositerEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
					ExocellularDepositerEntity.this.setYRot(this.rotlerp(ExocellularDepositerEntity.this.getYRot(), f, 10));
					ExocellularDepositerEntity.this.yBodyRot = ExocellularDepositerEntity.this.getYRot();
					ExocellularDepositerEntity.this.yHeadRot = ExocellularDepositerEntity.this.getYRot();
					if (ExocellularDepositerEntity.this.isInWater()) {
						ExocellularDepositerEntity.this.setSpeed((float) ExocellularDepositerEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
						float f2 = -(float) (Mth.atan2(dy, (float) Math.sqrt(dx * dx + dz * dz)) * (180 / Math.PI));
						f2 = Mth.clamp(Mth.wrapDegrees(f2), -85, 85);
						ExocellularDepositerEntity.this.setXRot(this.rotlerp(ExocellularDepositerEntity.this.getXRot(), f2, 5));
						float f3 = Mth.cos(ExocellularDepositerEntity.this.getXRot() * (float) (Math.PI / 180.0));
						ExocellularDepositerEntity.this.setZza(f3 * f1);
						ExocellularDepositerEntity.this.setYya((float) (f1 * dy));
					} else {
						ExocellularDepositerEntity.this.setSpeed(f1 * 0.05F);
					}
				} else {
					ExocellularDepositerEntity.this.setSpeed(0);
					ExocellularDepositerEntity.this.setYya(0);
					ExocellularDepositerEntity.this.setZza(0);
				}
			}
		};
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "exocellular_depositer");
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
		return super.getAddEntityPacket();
	}

	@Override
	protected @NotNull PathNavigation createNavigation(@NotNull Level world) {
		return new WaterBoundPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 5, false) {
			@Override
			protected double getAttackReachSqr(@NotNull LivingEntity entity) {
				return 1.5 * 1.5;
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GlowSquid.class, false, false));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Squid.class, false, false));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TropicalFish.class, false, false));
		this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Salmon.class, false, false));
		this.targetSelector.addGoal(7, new SeaMonsterAttackableTargetGoal<>(this, Player.class, false, false));
		this.goalSelector.addGoal(8, new RandomSwimmingGoal(this, 5, 40));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
	}

	@Override
	public @NotNull MobType getMobType() {
		return super.getMobType();
	}

	@Override
	public @NotNull SoundEvent getHurtSound(@NotNull DamageSource ds) {
		return SoundEvents.PUFFER_FISH_HURT;
	}

	@Override
	public @NotNull SoundEvent getDeathSound() {
		return SoundEvents.PUFFER_FISH_DEATH;
	}

	@Override
	public boolean hurt(@NotNull DamageSource source, float amount) {
		if (source.is(DamageTypes.DROWN))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
	}

	@Override
	public @NotNull EntityDimensions getDimensions(@NotNull Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return world.isUnobstructed(this);
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.6);
		builder = builder.add(Attributes.MAX_HEALTH, 18);
		builder = builder.add(Attributes.ARMOR, 3);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 4);
		builder = builder.add(Attributes.FOLLOW_RANGE, 22);
		builder = builder.add(ForgeMod.SWIM_SPEED.get(), 0.6);
		return builder;
	}

	private PlayState movementPredicate(AnimationState<?> event) {
		if (this.animationprocedure.equals("empty")) {
			if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

			) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.exocellular_depsoiter.move"));
			}
			if (this.isDeadOrDying()) {
				return event.setAndContinue(RawAnimation.begin().thenPlay("animation.exocellular_depsoiter.die"));
			}
			if (this.isInWaterOrBubble()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.exocellular_depsoiter.move"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("animation.exocellular_depsoiter.idle"));
		}
		return PlayState.STOP;
	}

	private PlayState attackingPredicate(AnimationState<?> event) {
		double d1 = this.getX() - this.xOld;
		double d0 = this.getZ() - this.zOld;
		float velocity = (float) Math.sqrt(d1 * d1 + d0 * d0);
		if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
			this.swinging = true;
			this.lastSwing = level().getGameTime();
		}
		if (this.swinging && this.lastSwing + 20L <= level().getGameTime()) {
			this.swinging = false;
		}
		if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			event.getController().forceAnimationReset();
			return event.setAndContinue(RawAnimation.begin().thenPlay("animation.exocellular_depsoiter.attack"));
		}
		return PlayState.CONTINUE;
	}

	String prevAnim = "empty";

	private PlayState procedurePredicate(AnimationState<?> event) {
		if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!this.animationprocedure.equals(prevAnim) && !this.animationprocedure.equals("empty"))) {
			if (!this.animationprocedure.equals(prevAnim))
				event.getController().forceAnimationReset();
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				this.animationprocedure = "empty";
				event.getController().forceAnimationReset();
			}
		} else if (animationprocedure.equals("empty")) {
			prevAnim = "empty";
			return PlayState.STOP;
		}
		prevAnim = this.animationprocedure;
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(RemovalReason.KILLED);
			this.dropExperience();
		SummonBones(this.level(),this.getX(),this.getY(),this.getZ());
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 2, this::movementPredicate));
		data.add(new AnimationController<>(this, "attacking", 2, this::attackingPredicate));
		data.add(new AnimationController<>(this, "procedure", 2, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	private void SummonBones(LevelAccessor world,double x,double y,double z){
		BlockPos curPos = BlockPos.containing(x, y, z);
		BlockState curStat = world.getBlockState( curPos);
		if (curStat.canBeReplaced()) {
			curStat = Blocks.BONE_BLOCK.defaultBlockState();
			world.setBlock(curPos,curStat,3);
			world.levelEvent(2001, curPos, Block.getId(curStat));
		}
		for (Direction directioniterator : Direction.values()) {
			if (Math.random() < 0.5) {
				curPos = BlockPos.containing(x + directioniterator.getStepX(), y + directioniterator.getStepY(), z + directioniterator.getStepZ());
				curStat = world.getBlockState(curPos);
				if (curStat.canBeReplaced()) {
					curStat = Blocks.BONE_BLOCK.defaultBlockState();
					world.setBlock(curPos,curStat,3);
					world.levelEvent(2001, curPos, Block.getId(curStat));
				}
			}
		}
	}
}