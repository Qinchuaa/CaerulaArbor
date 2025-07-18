
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.init.ModEntities;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import com.apocalypse.caerulaarbor.procedures.OceanizedPlayerProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.DungeonHooks;
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

public class UmbrellaAbyssalEntity extends Monster implements GeoEntity {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(UmbrellaAbyssalEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(UmbrellaAbyssalEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(UmbrellaAbyssalEntity.class, EntityDataSerializers.STRING);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public String animationprocedure = "empty";

    public UmbrellaAbyssalEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.UMBRELLA_ABYSSAL.get(), world);
    }

    public UmbrellaAbyssalEntity(EntityType<UmbrellaAbyssalEntity> type, Level world) {
        super(type, world);
        xpReward = 6;
        setNoAi(false);
        setMaxUpStep(1f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "abyssal3");
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
                return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
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
        this.targetSelector.addGoal(13, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
            @Override
            public boolean canUse() {
                double x = UmbrellaAbyssalEntity.this.getX();
                double y = UmbrellaAbyssalEntity.this.getY();
                double z = UmbrellaAbyssalEntity.this.getZ();
                Level world = UmbrellaAbyssalEntity.this.level();
                return super.canUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
            }

            @Override
            public boolean canContinueToUse() {
                double x = UmbrellaAbyssalEntity.this.getX();
                double y = UmbrellaAbyssalEntity.this.getY();
                double z = UmbrellaAbyssalEntity.this.getZ();
                Level world = UmbrellaAbyssalEntity.this.level();
                return super.canContinueToUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
            }
        });
        this.goalSelector.addGoal(14, new RandomStrollGoal(this, 0.4));
        this.goalSelector.addGoal(15, new RandomLookAroundGoal(this));
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.GLOW_SQUID_AMBIENT;
    }

    @Override
    public void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.SILVERFISH_STEP, 0.15f, 1);
    }

    @Override
    public @NotNull SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return SoundEvents.GLOW_SQUID_HURT;
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
        LevelAccessor world = this.level();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        double angle;

        this.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        if (world instanceof ServerLevel server) {
            for (int index0 = 0; index0 < 8; index0++) {
                angle = Mth.nextDouble(RandomSource.create(), 0, 6.283);
                server.sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, x + 3.9 * Math.sin(angle), y + 0.4, z + 3.9 * Math.cos(angle), 2, 0.1, 0.1, 0.1, 0.1);

                angle = Mth.nextDouble(RandomSource.create(), 0, 6.283);
                server.sendParticles(ParticleTypes.ELECTRIC_SPARK, x + 3.9 * Math.sin(angle), y + 0.4, z + 3.9 * Math.cos(angle), 2, 0.1, 0.1, 0.1, 0.1);
            }

            if (CaerulaArborModVariables.MapVariables.get(world).strategy_grow >= 3) {
                for (int index1 = 0; index1 < 14; index1++) {
                    angle = Mth.nextDouble(RandomSource.create(), 0, 6.283);
                    server.sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, x + 6.9 * Math.sin(angle), y + 0.4, z + 6.9 * Math.cos(angle), 2, 0.15, 0.15, 0.15, 0.1);

                    angle = Mth.nextDouble(RandomSource.create(), 0, 6.283);
                    server.sendParticles(ParticleTypes.ELECTRIC_SPARK, x + 6.9 * Math.sin(angle), y + 0.4, z + 6.9 * Math.cos(angle), 2, 0.15, 0.15, 0.15, 0.1);
                }
            }

            if (!this.hasEffect(ModMobEffects.UMBRELLA_SETTLE.get())) {
                this.addEffect(new MobEffectInstance(ModMobEffects.UMBRELLA_SETTLE.get(), 120, 0, false, false));
            }
        }

        this.refreshDimensions();
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose p_33597_) {
        return super.getDimensions(p_33597_);
    }

    public static void init() {
        SpawnPlacements.register(ModEntities.UMBRELLA_ABYSSAL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
        DungeonHooks.addDungeonMob(ModEntities.UMBRELLA_ABYSSAL.get(), 180);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
        builder = builder.add(Attributes.MAX_HEALTH, 40);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 2);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        return builder;
    }

    private PlayState movementPredicate(AnimationState<?> event) {
        if (this.animationprocedure.equals("empty")) {
            if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

            ) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("animation.umbrella.move"));
            }
            if (this.isDeadOrDying()) {
                return event.setAndContinue(RawAnimation.begin().thenPlay("animation.umbrella.die"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.umbrella.idle"));
        }
        return PlayState.STOP;
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
            this.remove(UmbrellaAbyssalEntity.RemovalReason.KILLED);
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
        data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
        data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
