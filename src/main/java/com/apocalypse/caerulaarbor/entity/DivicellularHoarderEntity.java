package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.ai.goal.SeaMonsterAttackableTargetGoal;
import com.apocalypse.caerulaarbor.entity.base.SeaMonster;
import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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

import javax.annotation.ParametersAreNonnullByDefault;

public class DivicellularHoarderEntity extends SeaMonster {

    public boolean split;

    public DivicellularHoarderEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.DIVICELLULAR_HOARDER.get(), world);
    }

    public DivicellularHoarderEntity(EntityType<DivicellularHoarderEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        setMaxUpStep(0.6f);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0);
        this.moveControl = new MoveControl(this) {
            @Override
            public void tick() {
                if (DivicellularHoarderEntity.this.isInWater())
                    DivicellularHoarderEntity.this.setDeltaMovement(DivicellularHoarderEntity.this.getDeltaMovement().add(0, 0.005, 0));
                if (this.operation == Operation.MOVE_TO && !DivicellularHoarderEntity.this.getNavigation().isDone()) {
                    double dx = this.wantedX - DivicellularHoarderEntity.this.getX();
                    double dy = this.wantedY - DivicellularHoarderEntity.this.getY();
                    double dz = this.wantedZ - DivicellularHoarderEntity.this.getZ();
                    float f = (float) (Mth.atan2(dz, dx) * (180 / Math.PI)) - 90;
                    float f1 = (float) (this.speedModifier * DivicellularHoarderEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                    DivicellularHoarderEntity.this.setYRot(this.rotlerp(DivicellularHoarderEntity.this.getYRot(), f, 10));
                    DivicellularHoarderEntity.this.yBodyRot = DivicellularHoarderEntity.this.getYRot();
                    DivicellularHoarderEntity.this.yHeadRot = DivicellularHoarderEntity.this.getYRot();
                    if (DivicellularHoarderEntity.this.isInWater()) {
                        DivicellularHoarderEntity.this.setSpeed((float) DivicellularHoarderEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                        float f2 = -(float) (Mth.atan2(dy, (float) Math.sqrt(dx * dx + dz * dz)) * (180 / Math.PI));
                        f2 = Mth.clamp(Mth.wrapDegrees(f2), -85, 85);
                        DivicellularHoarderEntity.this.setXRot(this.rotlerp(DivicellularHoarderEntity.this.getXRot(), f2, 5));
                        float f3 = Mth.cos(DivicellularHoarderEntity.this.getXRot() * (float) (Math.PI / 180.0));
                        DivicellularHoarderEntity.this.setZza(f3 * f1);
                        DivicellularHoarderEntity.this.setYya((float) (f1 * dy));
                    } else {
                        DivicellularHoarderEntity.this.setSpeed(f1 * 0.05F);
                    }
                } else {
                    DivicellularHoarderEntity.this.setSpeed(0);
                    DivicellularHoarderEntity.this.setYya(0);
                    DivicellularHoarderEntity.this.setZza(0);
                }
            }
        };
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Split", this.split);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.split = pCompound.getBoolean("Split");
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
    @ParametersAreNonnullByDefault
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.PUFFER_FISH_FLOP, 0.15f, 1);
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
    public void baseTick() {
        if (this.level() instanceof ServerLevel _slvl)
            self_split(_slvl, this, this.getX(), this.getY(), this.getZ());
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

    public static void init() {
        SpawnPlacements.register(ModEntities.DIVICELLULAR_HOARDER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.5);
        builder = builder.add(Attributes.MAX_HEALTH, 32);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        builder = builder.add(ForgeMod.SWIM_SPEED.get(), 0.5);
        return builder;
    }

    private PlayState movementPredicate(AnimationState<DivicellularHoarderEntity> event) {
        if (event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F)) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.divicellular_hoarder.move"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.divicellular_hoarder.idle"));
    }

    private PlayState attackingPredicate(AnimationState<DivicellularHoarderEntity> event) {
        double d1 = this.getX() - this.xOld;
        double d0 = this.getZ() - this.zOld;
        if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
            this.swinging = true;
            this.lastSwing = level().getGameTime();
        }
        if (this.swinging && this.lastSwing + 10L <= level().getGameTime()) {
            this.swinging = false;
        }
        if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().forceAnimationReset();
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.divicellular_hoarder.attack"));
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
        data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 4, this::attackingPredicate));
    }

    private void self_split(ServerLevel level, Entity entity, double x, double y, double z) {
        if (entity == null)
            return;
        if (entity instanceof LivingEntity _ent && _ent.getHealth() < _ent.getMaxHealth() * 0.5 && split) {
            RandomSource source = level.random;
            Entity toSpawn = ModEntities.DIVICELLULAR_HOARDER.get().create(level);
            split = false;
            if (entity instanceof DivicellularHoarderEntity _man) {
                // TODO 手动触发动画
//                _man.setAnimation("animation.divicellular_hoarder.split");
            }
            _ent.setHealth((float) (_ent.getMaxHealth() * 0.5));
            CaerulaArborMod.queueServerWork(10, () -> {
                SoundEvent sound = SoundEvents.PUFFER_FISH_BLOW_OUT;
                if (!level.isClientSide()) {
                    level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()),
                            sound,
                            SoundSource.HOSTILE, 2, 1);
                } else {
                    level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()),
                            sound,
                            SoundSource.HOSTILE, 2, 1, false);
                        }
                        if (toSpawn instanceof DivicellularHoarderEntity _hoarder) {
                            _hoarder.split = false;
                            _hoarder.setHealth((float) (_ent.getMaxHealth() * 0.5));
                            _hoarder.setPos(x + Mth.nextDouble(source, -1, 1),
                                    y,
                                    z + Mth.nextDouble(source, -1, 1));
                            level.addFreshEntity(_hoarder);
                        }
                    }
            );
        }
    }
}
