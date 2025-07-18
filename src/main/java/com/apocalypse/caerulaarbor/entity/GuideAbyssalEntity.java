
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.init.ModEntities;
import com.apocalypse.caerulaarbor.procedures.LayTrialProcedure;
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

import javax.annotation.ParametersAreNonnullByDefault;

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
        this(ModEntities.GUIDE_ABYSSAL.get(), world);
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
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    private boolean canPerformAttack(GuideAbyssalEntity entity) {
        return entity.tickCount - entity.getLastHurtMobTimestamp() >= entity.getEntityData().get(GuideAbyssalEntity.DATA_delay);
    }

    private <T extends LivingEntity> NearestAttackableTargetGoal<T> createNearestAttackableGoal(Class<T> goal) {
        return new NearestAttackableTargetGoal<>(this, goal, false, false) {
            @Override
            public boolean canUse() {
                return super.canUse() && canPerformAttack(GuideAbyssalEntity.this);
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && canPerformAttack(GuideAbyssalEntity.this);
            }
        };
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                return super.canUse() && canPerformAttack(GuideAbyssalEntity.this);
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && canPerformAttack(GuideAbyssalEntity.this);
            }
        });
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.35, true) {
            @Override
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return 12.25;
            }

            @Override
            public boolean canUse() {
                return super.canUse() && canPerformAttack(GuideAbyssalEntity.this);
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && canPerformAttack(GuideAbyssalEntity.this);
            }

        });
        this.targetSelector.addGoal(3, createNearestAttackableGoal(IronGolem.class));
        this.targetSelector.addGoal(4, createNearestAttackableGoal(SnowGolem.class));
        this.targetSelector.addGoal(5, createNearestAttackableGoal(Villager.class));
        this.targetSelector.addGoal(6, createNearestAttackableGoal(Illusioner.class));
        this.targetSelector.addGoal(7, createNearestAttackableGoal(Pillager.class));
        this.targetSelector.addGoal(8, createNearestAttackableGoal(Vindicator.class));
        this.targetSelector.addGoal(9, createNearestAttackableGoal(Witch.class));
        this.targetSelector.addGoal(10, createNearestAttackableGoal(Piglin.class));
        this.targetSelector.addGoal(11, createNearestAttackableGoal(PiglinBrute.class));
        this.targetSelector.addGoal(12, createNearestAttackableGoal(ZombifiedPiglin.class));
        this.targetSelector.addGoal(13, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
            @Override
            public boolean canUse() {
                double x = GuideAbyssalEntity.this.getX();
                double y = GuideAbyssalEntity.this.getY();
                double z = GuideAbyssalEntity.this.getZ();
                Level world = GuideAbyssalEntity.this.level();
                return super.canUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
            }

            @Override
            public boolean canContinueToUse() {
                double x = GuideAbyssalEntity.this.getX();
                double y = GuideAbyssalEntity.this.getY();
                double z = GuideAbyssalEntity.this.getZ();
                Level world = GuideAbyssalEntity.this.level();
                return super.canContinueToUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
            }
        });
        this.goalSelector.addGoal(14, new RandomStrollGoal(this, 0.35));
        this.goalSelector.addGoal(15, new RandomLookAroundGoal(this));
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.RAVAGER_AMBIENT;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.15f, 1);
    }

    @Override
    public @NotNull SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Override
    public @NotNull SoundEvent getDeathSound() {
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
        compound.putInt("Datalaylimit", this.entityData.get(DATA_laylimit));
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
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
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        return super.getDimensions(pose).scale(1.1f);
    }

    public static void init() {
        SpawnPlacements.register(ModEntities.GUIDE_ABYSSAL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
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

    private PlayState movementPredicate(AnimationState<?> event) {
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

    private PlayState attackingPredicate(AnimationState<?> event) {
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
        data.add(new AnimationController<>(this, "movement", 0, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 0, this::attackingPredicate));
        data.add(new AnimationController<>(this, "procedure", 0, this::procedurePredicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
