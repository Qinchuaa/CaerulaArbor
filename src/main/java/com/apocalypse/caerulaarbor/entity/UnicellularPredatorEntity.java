package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.entity.base.SeaMonster;
import com.apocalypse.caerulaarbor.init.ModEntities;
import com.apocalypse.caerulaarbor.procedures.OceanizedPlayerProcedure;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class UnicellularPredatorEntity extends SeaMonster {

    public UnicellularPredatorEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.UNICELLULAR_PREDATOR.get(), world);
    }

    public UnicellularPredatorEntity(EntityType<UnicellularPredatorEntity> type, Level world) {
        super(type, world);
        xpReward = 3;
        setNoAi(false);
        setMaxUpStep(0.6f);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0);
        this.moveControl = new MoveControl(this) {
            @Override
            public void tick() {
                if (UnicellularPredatorEntity.this.isInWater())
                    UnicellularPredatorEntity.this.setDeltaMovement(UnicellularPredatorEntity.this.getDeltaMovement().add(0, 0.005, 0));
                if (this.operation == MoveControl.Operation.MOVE_TO && !UnicellularPredatorEntity.this.getNavigation().isDone()) {
                    double dx = this.wantedX - UnicellularPredatorEntity.this.getX();
                    double dy = this.wantedY - UnicellularPredatorEntity.this.getY();
                    double dz = this.wantedZ - UnicellularPredatorEntity.this.getZ();
                    float f = (float) (Mth.atan2(dz, dx) * (180 / Math.PI)) - 90;
                    float f1 = (float) (this.speedModifier * UnicellularPredatorEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                    UnicellularPredatorEntity.this.setYRot(this.rotlerp(UnicellularPredatorEntity.this.getYRot(), f, 10));
                    UnicellularPredatorEntity.this.yBodyRot = UnicellularPredatorEntity.this.getYRot();
                    UnicellularPredatorEntity.this.yHeadRot = UnicellularPredatorEntity.this.getYRot();
                    if (UnicellularPredatorEntity.this.isInWater()) {
                        UnicellularPredatorEntity.this.setSpeed((float) UnicellularPredatorEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                        float f2 = -(float) (Mth.atan2(dy, (float) Math.sqrt(dx * dx + dz * dz)) * (180 / Math.PI));
                        f2 = Mth.clamp(Mth.wrapDegrees(f2), -85, 85);
                        UnicellularPredatorEntity.this.setXRot(this.rotlerp(UnicellularPredatorEntity.this.getXRot(), f2, 5));
                        float f3 = Mth.cos(UnicellularPredatorEntity.this.getXRot() * (float) (Math.PI / 180.0));
                        UnicellularPredatorEntity.this.setZza(f3 * f1);
                        UnicellularPredatorEntity.this.setYya((float) (f1 * dy));
                    } else {
                        UnicellularPredatorEntity.this.setSpeed(f1 * 0.05F);
                    }
                } else {
                    UnicellularPredatorEntity.this.setSpeed(0);
                    UnicellularPredatorEntity.this.setYya(0);
                    UnicellularPredatorEntity.this.setZza(0);
                }
            }
        };
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level world) {
        return new WaterBoundPathNavigation(this, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.8, false) {
            @Override
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
            }
        });
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, BoneFishEntity.class, false, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Pufferfish.class, false, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, GlowSquid.class, false, false));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Squid.class, false, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, TropicalFish.class, false, false));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Salmon.class, false, false));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
            @Override
            public boolean canUse() {
                double x = UnicellularPredatorEntity.this.getX();
                double y = UnicellularPredatorEntity.this.getY();
                double z = UnicellularPredatorEntity.this.getZ();
                Level world = UnicellularPredatorEntity.this.level();
                return super.canUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
            }

            @Override
            public boolean canContinueToUse() {
                double x = UnicellularPredatorEntity.this.getX();
                double y = UnicellularPredatorEntity.this.getY();
                double z = UnicellularPredatorEntity.this.getZ();
                Level world = UnicellularPredatorEntity.this.level();
                return super.canContinueToUse() && OceanizedPlayerProcedure.execute(world, x, y, z);
            }
        });
        this.goalSelector.addGoal(10, new RandomSwimmingGoal(this, 0.8, 40));
        this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    @Override
    public @NotNull SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return SoundEvents.SALMON_HURT;
    }

    @Override
    public @NotNull SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();
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
        SpawnPlacements.register(ModEntities.UNICELLULAR_PREDATOR.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
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

    private PlayState movementPredicate(AnimationState<?> event) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.unicellular_predator.idle"));
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
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.unicellular_predator.attack"));
        }
        return PlayState.CONTINUE;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            this.remove(UnicellularPredatorEntity.RemovalReason.KILLED);
            this.dropExperience();
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "movement", 3, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 3, this::attackingPredicate));
    }
}
