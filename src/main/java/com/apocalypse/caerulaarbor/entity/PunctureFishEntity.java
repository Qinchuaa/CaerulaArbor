
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.init.ModEntities;
import com.apocalypse.caerulaarbor.procedures.OceanizedPlayerProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PunctureFishEntity extends Monster implements GeoEntity {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(PunctureFishEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(PunctureFishEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(PunctureFishEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Integer> DATA_delay = SynchedEntityData.defineId(PunctureFishEntity.class, EntityDataSerializers.INT);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean swinging;
    private boolean lastloop;
    private long lastSwing;
    public String animationprocedure = "empty";

    public PunctureFishEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.PUNCTURE_FISH.get(), world);
    }

    public PunctureFishEntity(EntityType<PunctureFishEntity> type, Level world) {
        super(type, world);
        xpReward = 6;
        setNoAi(false);
        setMaxUpStep(1.2f);
        setPersistenceRequired();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "fish3");
        this.entityData.define(DATA_delay, 40);
    }

    public void setTexture(String texture) {
        this.entityData.set(TEXTURE, texture);
    }

    public String getTexture() {
        return this.entityData.get(TEXTURE);
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.4, false) {
            @Override
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return 25;
            }

            @Override
            public boolean canUse() {
                if (!super.canUse()) return false;
                return PunctureFishEntity.this.tickCount - PunctureFishEntity.this.getLastHurtMobTimestamp() >= PunctureFishEntity.this.getEntityData().get(DATA_delay);
            }

        });
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SnowGolem.class, false, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Villager.class, false, false));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Illusioner.class, false, false));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Pillager.class, false, false));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Vindicator.class, false, false));
        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, Witch.class, false, false));
        this.targetSelector.addGoal(11, new NearestAttackableTargetGoal<>(this, Piglin.class, false, false));
        this.targetSelector.addGoal(12, new NearestAttackableTargetGoal<>(this, PiglinBrute.class, false, false));
        this.targetSelector.addGoal(13, new NearestAttackableTargetGoal<>(this, ZombifiedPiglin.class, false, false));
        this.targetSelector.addGoal(14, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
            @Override
            public boolean canUse() {
                double x = PunctureFishEntity.this.getX();
                double y = PunctureFishEntity.this.getY();
                double z = PunctureFishEntity.this.getZ();
                Level world = PunctureFishEntity.this.level();
                return super.canUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
            }

            @Override
            public boolean canContinueToUse() {
                double x = PunctureFishEntity.this.getX();
                double y = PunctureFishEntity.this.getY();
                double z = PunctureFishEntity.this.getZ();
                Level world = PunctureFishEntity.this.level();
                return super.canContinueToUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
            }
        });
        this.goalSelector.addGoal(15, new RandomStrollGoal(this, 0.4));
        this.goalSelector.addGoal(16, new RandomLookAroundGoal(this));
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.TROPICAL_FISH_AMBIENT;
    }

    @Override
    public void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.SILVERFISH_STEP, 0.15f, 1);
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return SoundEvents.PUFFER_FISH_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.PUFFER_FISH_DEATH;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.DROWN))
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Texture", this.getTexture());
        compound.putInt("Datadelay", this.entityData.get(DATA_delay));
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Texture"))
            this.setTexture(compound.getString("Texture"));
        if (compound.contains("Datadelay"))
            this.entityData.set(DATA_delay, compound.getInt("Datadelay"));
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

    public static void init() {
        SpawnPlacements.register(ModEntities.PUNCTURE_FISH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
        builder = builder.add(Attributes.MAX_HEALTH, 45);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 12);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        return builder;
    }

    private PlayState movementPredicate(AnimationState<?> event) {
        if (this.animationprocedure.equals("empty")) {
            if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

            ) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("animation.puncturefish.move"));
            }
            if (this.isDeadOrDying()) {
                return event.setAndContinue(RawAnimation.begin().thenPlay("animation.puncturefish.die"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.puncturefish.idle"));
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
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.puncturefish.attack"));
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
            this.remove(PunctureFishEntity.RemovalReason.KILLED);
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
        data.add(new AnimationController<>(this, "movement", 0, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 0, this::attackingPredicate));
        data.add(new AnimationController<>(this, "procedure", 0, this::procedurePredicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
