
package net.mcreator.caerulaarbor.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Monster;
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
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mcreator.caerulaarbor.procedures.LayTrialProcedure;
import net.mcreator.caerulaarbor.procedures.CanAttackProcedure;
import net.mcreator.caerulaarbor.init.CaerulaArborModEntities;

public class GuideAbyssalEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(GuideAbyssalEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(GuideAbyssalEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(GuideAbyssalEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Integer> DATA_delay = SynchedEntityData.defineId(GuideAbyssalEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_laylimit = SynchedEntityData.defineId(GuideAbyssalEntity.class, EntityDataSerializers.INT);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public GuideAbyssalEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(CaerulaArborModEntities.GUIDE_ABYSSAL.get(), world);
	}

	public GuideAbyssalEntity(EntityType<GuideAbyssalEntity> type, Level world) {
		super(type, world);
		xpReward = 8;
		setNoAi(false);
		setMaxUpStep(1.2f);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "abyssal2");
		this.entityData.define(DATA_delay, 0);
		this.entityData.define(DATA_laylimit, 64);
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.35, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return 12.25;
			}

			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}

		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, SnowGolem.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Villager.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, Illusioner.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, Pillager.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal(this, Vindicator.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(9, new NearestAttackableTargetGoal(this, Witch.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(10, new NearestAttackableTargetGoal(this, Piglin.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(11, new NearestAttackableTargetGoal(this, PiglinBrute.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(12, new NearestAttackableTargetGoal(this, ZombifiedPiglin.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.targetSelector.addGoal(13, new NearestAttackableTargetGoal(this, Player.class, false, false) {
			@Override
			public boolean canUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canUse() && CanAttackProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = GuideAbyssalEntity.this.getX();
				double y = GuideAbyssalEntity.this.getY();
				double z = GuideAbyssalEntity.this.getZ();
				Entity entity = GuideAbyssalEntity.this;
				Level world = GuideAbyssalEntity.this.level();
				return super.canContinueToUse() && CanAttackProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(14, new RandomStrollGoal(this, 0.35));
		this.goalSelector.addGoal(15, new RandomLookAroundGoal(this));
	}

	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ravager.ambient"));
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.step")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ravager.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.puffer_fish.death"));
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.DROWN))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
		compound.putInt("Datadelay", this.entityData.get(DATA_delay));
		compound.putInt("Datalaylimit", this.entityData.get(DATA_laylimit));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
		if (compound.contains("Datadelay"))
			this.entityData.set(DATA_delay, compound.getInt("Datadelay"));
		if (compound.contains("Datalaylimit"))
			this.entityData.set(DATA_laylimit, compound.getInt("Datalaylimit"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		LayTrialProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1.1);
	}

	public static void init() {
		SpawnPlacements.register(CaerulaArborModEntities.GUIDE_ABYSSAL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35);
		builder = builder.add(Attributes.MAX_HEALTH, 80);
		builder = builder.add(Attributes.ARMOR, 15);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 14);
		builder = builder.add(Attributes.FOLLOW_RANGE, 24);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.8);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F)) && !this.isVehicle() && !this.isAggressive() && !this.isSprinting()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.tracerabyssal.move"));
			}
			if (this.isDeadOrDying()) {
				return event.setAndContinue(RawAnimation.begin().thenPlay("animation.tracerabyssal.die"));
			}
			if (this.isInWaterOrBubble()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.tracerabyssal.move"));
			}
			if (this.isShiftKeyDown()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.tracerabyssal.move"));
			}
			if (this.isSprinting()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.tracerabyssal.move"));
			}
			if (this.isVehicle() && event.isMoving()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.tracerabyssal.move"));
			}
			if (this.isAggressive() && event.isMoving() && !this.isVehicle()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.tracerabyssal.move"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("animation.tracerabyssal.idle"));
		}
		return PlayState.STOP;
	}

	private PlayState attackingPredicate(AnimationState event) {
		double d1 = this.getX() - this.xOld;
		double d0 = this.getZ() - this.zOld;
		float velocity = (float) Math.sqrt(d1 * d1 + d0 * d0);
		if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
			this.swinging = true;
			this.lastSwing = level().getGameTime();
		}
		if (this.swinging && this.lastSwing + 30L <= level().getGameTime()) {
			this.swinging = false;
		}
		if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			event.getController().forceAnimationReset();
			return event.setAndContinue(RawAnimation.begin().thenPlay("animation.tracerabyssal.attack"));
		}
		return PlayState.CONTINUE;
	}

	String prevAnim = "empty";

	private PlayState procedurePredicate(AnimationState event) {
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
			this.remove(GuideAbyssalEntity.RemovalReason.KILLED);
			this.dropExperience();
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
		data.add(new AnimationController<>(this, "movement", 3, this::movementPredicate));
		data.add(new AnimationController<>(this, "attacking", 3, this::attackingPredicate));
		data.add(new AnimationController<>(this, "procedure", 3, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
