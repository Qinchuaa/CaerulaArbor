package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.entity.ai.goal.SeaMonsterAttackableTargetGoal;
import com.apocalypse.caerulaarbor.entity.base.SeaMonster;
import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
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
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class RetchingBroodmotherEntity extends SeaMonster implements RangedAttackMob {

    public RetchingBroodmotherEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.RETCHING_BROODMOTHER.get(), world);
    }

    public RetchingBroodmotherEntity(EntityType<RetchingBroodmotherEntity> type, Level world) {
        super(type, world);
        xpReward = 6;
        setNoAi(false);
        setMaxUpStep(0.6f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, false, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, SnowGolem.class, false, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Villager.class, false, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Illusioner.class, false, false));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Pillager.class, false, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Vindicator.class, false, false));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Witch.class, false, false));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Piglin.class, false, false));
        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, PiglinBrute.class, false, false));
        this.targetSelector.addGoal(11, new NearestAttackableTargetGoal<>(this, ZombifiedPiglin.class, false, false));
        this.targetSelector.addGoal(12, new SeaMonsterAttackableTargetGoal<>(this, Player.class, false, false));
        this.goalSelector.addGoal(13, new RandomStrollGoal(this, 0.4));
        this.goalSelector.addGoal(14, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 80, 18f));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.EMPTY;
    }

    @Override
    public void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
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
        super.baseTick();

        if (!this.isAggressive() && Math.random() < 0.001) {
            this.triggerAnim("random", "random");
        }
        this.refreshDimensions();
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity target, float flval) {
        FakerggShootEntity.shoot(this, target);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 68);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        return builder;
    }

    private PlayState movementPredicate(AnimationState<?> event) {
        if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.retching_broodmother.move"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.retching_broodmother.idle"));
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
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.retching_broodmother.attack"));
        }
        return PlayState.CONTINUE;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            this.remove(RetchingBroodmotherEntity.RemovalReason.KILLED);
            this.dropExperience();
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "movement", 3, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 3, this::attackingPredicate));
        data.add(new AnimationController<>(this, "random", 3, event -> PlayState.STOP)
                .triggerableAnim("random", RawAnimation.begin().thenPlay("animation.retching_broodmother.random").thenLoop("animation.retching_broodmother.idle")));
    }
}
