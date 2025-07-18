
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import com.apocalypse.caerulaarbor.init.ModEntities;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.init.ModTags;
import com.apocalypse.caerulaarbor.procedures.OceanizedPlayerProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
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
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CrackerAbyssalEntity extends Monster implements GeoEntity {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(CrackerAbyssalEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(CrackerAbyssalEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(CrackerAbyssalEntity.class, EntityDataSerializers.STRING);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean swinging;
    private long lastSwing;
    public String animationprocedure = "empty";

    public CrackerAbyssalEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.CRACKER_ABYSSAL.get(), world);
    }

    public CrackerAbyssalEntity(EntityType<CrackerAbyssalEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        setMaxUpStep(1.2f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "abyssal4");
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
        this.goalSelector.addGoal(2, new BreakDoorGoal(this, e -> true) {
            @Override
            public boolean canUse() {
                Level world = CrackerAbyssalEntity.this.level();
                if (!super.canUse()) return false;
                return GameplayConfig.ENABLE_MOB_BREAK.get() && world.getLevelData().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
            }

            @Override
            public boolean canContinueToUse() {
                Level world = CrackerAbyssalEntity.this.level();
                if (!super.canContinueToUse()) return false;
                return GameplayConfig.ENABLE_MOB_BREAK.get() && world.getLevelData().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
            }
        });
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.4, true) {
            @Override
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return 12.25;
            }
        });
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, false, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, SnowGolem.class, false, false));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Villager.class, false, false));
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
                double x = CrackerAbyssalEntity.this.getX();
                double y = CrackerAbyssalEntity.this.getY();
                double z = CrackerAbyssalEntity.this.getZ();
                Level world = CrackerAbyssalEntity.this.level();
                return super.canUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
            }

            @Override
            public boolean canContinueToUse() {
                double x = CrackerAbyssalEntity.this.getX();
                double y = CrackerAbyssalEntity.this.getY();
                double z = CrackerAbyssalEntity.this.getZ();
                Level world = CrackerAbyssalEntity.this.level();
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
    public SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.parrot.imitate.silverfish"));
    }

    @Override
    public void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.SILVERFISH_STEP, 0.15f, 1);
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
        LevelAccessor world = this.level();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        double num;
        if (!this.hasEffect(ModMobEffects.COOLDOWN_SINAL.get())) {
            num = 0;
            {
                final Vec3 _center = new Vec3(x, y, z);
                num += world.getEntitiesOfClass(LivingEntity.class, new AABB(_center, _center).inflate(3), e -> e != this && e.getMaxHealth() >= 10)
                        .size();
            }
            if (num >= 2) {
                if (this instanceof CrackerAbyssalEntity) {
                    this.setAnimation("animation.reefcracker.spin");
                }
                if (!this.level().isClientSide())
                    this.addEffect(new MobEffectInstance(ModMobEffects.COOLDOWN_SINAL.get(), 40, 0, false, false));
                CaerulaArborMod.queueServerWork(10, () -> {
                    if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                            _level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 2, 1);
                        } else {
                            _level.playLocalSound(x, y, z, SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 2, 1, false);
                        }
                    }
                    {
                        final Vec3 _center = new Vec3(x, y, z);
                        for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3), e -> !e.getType().is(ModTags.EntityTypes.OCEAN_OFFSPRING) || this.getTarget() == e)) {
                            entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), this),
                                    (float) ((this.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? this.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0) * 1.5));

                        }
                    }
                });
            }
        }

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

        if (world.getBlockState(this.blockPosition()).getBlock() == ModBlocks.SEA_TRAIL_GROWN.get() && world instanceof ServerLevel server) {
            var effect = this.getEffect(MobEffects.INVISIBILITY);
            if (effect != null && effect.getDuration() <= 5) {
                this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20, 0));
            }
            server.sendParticles(ParticleTypes.SMOKE, x, y + 1, z, 4, 0.4, 2, 0.4, 0.01);
        }
        this.refreshDimensions();
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose p_33597_) {
        return super.getDimensions(p_33597_).scale((float) 1);
    }

    public static void init() {
        SpawnPlacements.register(ModEntities.CRACKER_ABYSSAL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.5);
        builder = builder.add(Attributes.MAX_HEALTH, 85);
        builder = builder.add(Attributes.ARMOR, 10);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 13);
        builder = builder.add(Attributes.FOLLOW_RANGE, 48);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.85);
        return builder;
    }

    private PlayState movementPredicate(AnimationState<?> event) {
        if (this.animationprocedure.equals("empty")) {
            if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

            ) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("animation.reefcracker.idle"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.reefcracker.move"));
        }
        return PlayState.STOP;
    }

    private PlayState attackingPredicate(AnimationState<?> event) {
        if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
            this.swinging = true;
            this.lastSwing = level().getGameTime();
        }
        if (this.swinging && this.lastSwing + 20L <= level().getGameTime()) {
            this.swinging = false;
        }
        if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().forceAnimationReset();
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.reefcracker.attack"));
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
            this.remove(CrackerAbyssalEntity.RemovalReason.KILLED);
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
