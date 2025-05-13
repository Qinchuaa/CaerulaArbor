
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.procedures.OceanizedPlayerProcedure;
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
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import com.apocalypse.caerulaarbor.init.CaerulaArborModItems;
import com.apocalypse.caerulaarbor.init.CaerulaArborModEntities;

public class CollectorProkaryoteEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(CollectorProkaryoteEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(CollectorProkaryoteEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(CollectorProkaryoteEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public CollectorProkaryoteEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(CaerulaArborModEntities.COLLECTOR_PROKARYOTE.get(), world);
	}

	public CollectorProkaryoteEntity(EntityType<CollectorProkaryoteEntity> type, Level world) {
		super(type, world);
		xpReward = 3;
		setNoAi(false);
		setMaxUpStep(0.6f);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0);
		this.moveControl = new MoveControl(this) {
			@Override
			public void tick() {
				if (CollectorProkaryoteEntity.this.isInWater())
					CollectorProkaryoteEntity.this.setDeltaMovement(CollectorProkaryoteEntity.this.getDeltaMovement().add(0, 0.005, 0));
				if (this.operation == MoveControl.Operation.MOVE_TO && !CollectorProkaryoteEntity.this.getNavigation().isDone()) {
					double dx = this.wantedX - CollectorProkaryoteEntity.this.getX();
					double dy = this.wantedY - CollectorProkaryoteEntity.this.getY();
					double dz = this.wantedZ - CollectorProkaryoteEntity.this.getZ();
					float f = (float) (Mth.atan2(dz, dx) * (double) (180 / Math.PI)) - 90;
					float f1 = (float) (this.speedModifier * CollectorProkaryoteEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
					CollectorProkaryoteEntity.this.setYRot(this.rotlerp(CollectorProkaryoteEntity.this.getYRot(), f, 10));
					CollectorProkaryoteEntity.this.yBodyRot = CollectorProkaryoteEntity.this.getYRot();
					CollectorProkaryoteEntity.this.yHeadRot = CollectorProkaryoteEntity.this.getYRot();
					if (CollectorProkaryoteEntity.this.isInWater()) {
						CollectorProkaryoteEntity.this.setSpeed((float) CollectorProkaryoteEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
						float f2 = -(float) (Mth.atan2(dy, (float) Math.sqrt(dx * dx + dz * dz)) * (180 / Math.PI));
						f2 = Mth.clamp(Mth.wrapDegrees(f2), -85, 85);
						CollectorProkaryoteEntity.this.setXRot(this.rotlerp(CollectorProkaryoteEntity.this.getXRot(), f2, 5));
						float f3 = Mth.cos(CollectorProkaryoteEntity.this.getXRot() * (float) (Math.PI / 180.0));
						CollectorProkaryoteEntity.this.setZza(f3 * f1);
						CollectorProkaryoteEntity.this.setYya((float) (f1 * dy));
					} else {
						CollectorProkaryoteEntity.this.setSpeed(f1 * 0.05F);
					}
				} else {
					CollectorProkaryoteEntity.this.setSpeed(0);
					CollectorProkaryoteEntity.this.setYya(0);
					CollectorProkaryoteEntity.this.setZza(0);
				}
			}
		};
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "prokaryote");
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
	protected PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.8, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, BoneFishEntity.class, false, false));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Pufferfish.class, false, false));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, GlowSquid.class, false, false));
		this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, Squid.class, false, false));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, TropicalFish.class, false, false));
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal(this, Salmon.class, false, false));
		this.targetSelector.addGoal(9, new NearestAttackableTargetGoal(this, Player.class, false, false) {
			@Override
			public boolean canUse() {
				double x = CollectorProkaryoteEntity.this.getX();
				double y = CollectorProkaryoteEntity.this.getY();
				double z = CollectorProkaryoteEntity.this.getZ();
				Entity entity = CollectorProkaryoteEntity.this;
				Level world = CollectorProkaryoteEntity.this.level();
				return super.canUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
			}

			@Override
			public boolean canContinueToUse() {
				double x = CollectorProkaryoteEntity.this.getX();
				double y = CollectorProkaryoteEntity.this.getY();
				double z = CollectorProkaryoteEntity.this.getZ();
				Entity entity = CollectorProkaryoteEntity.this;
				Level world = CollectorProkaryoteEntity.this.level();
				return super.canContinueToUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
			}
		});
		this.goalSelector.addGoal(10, new RandomSwimmingGoal(this, 0.8, 40));
		this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
	}

	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}

	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		this.spawnAtLocation(new ItemStack(CaerulaArborModItems.BROKEN_OCEAN_CELL.get()));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.salmon.ambient"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.salmon.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.salmon.death"));
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
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
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
	public EntityDimensions getDimensions(Pose p_33597_) {
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

	public static void init() {
		SpawnPlacements.register(CaerulaArborModEntities.COLLECTOR_PROKARYOTE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER)));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.8);
		builder = builder.add(Attributes.MAX_HEALTH, 12);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 4);
		builder = builder.add(Attributes.FOLLOW_RANGE, 12);
		builder = builder.add(ForgeMod.SWIM_SPEED.get(), 0.8);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			return event.setAndContinue(RawAnimation.begin().thenLoop("animation.collector.idle"));
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
		if (this.swinging && this.lastSwing + 20L <= level().getGameTime()) {
			this.swinging = false;
		}
		if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			event.getController().forceAnimationReset();
			return event.setAndContinue(RawAnimation.begin().thenPlay("animation.collector.attack"));
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
			this.remove(CollectorProkaryoteEntity.RemovalReason.KILLED);
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
