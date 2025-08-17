package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.entity.ai.Skill;
import com.apocalypse.caerulaarbor.entity.ai.goal.SeaMonsterAttackableTargetGoal;
import com.apocalypse.caerulaarbor.entity.base.SkilledSeaMonster;
import com.apocalypse.caerulaarbor.init.ModAttributes;
import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;

//TODO 主教鱼
@Mod.EventBusSubscriber
public class QuintusEntity extends SkilledSeaMonster {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(QuintusEntity.class, EntityDataSerializers.BOOLEAN);

    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.NOTCHED_10);

    public QuintusEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.QUINTUS.get(), world);
    }

    public QuintusEntity(EntityType<QuintusEntity> type, Level world) {
        super(type, world);
        xpReward = 64;
        this.addSkill(Skill.Builder.of().init(200).max(200).build());
        this.addSkill(Skill.Builder.of().init(0).max(1200).build());
        setNoAi(false);
        setMaxUpStep(2f);
        setPersistenceRequired();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0, true) {
            @Override
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return 144;
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
        this.targetSelector.addGoal(13, new SeaMonsterAttackableTargetGoal<>(this, Player.class, false, false));
        this.goalSelector.addGoal(14, new RandomLookAroundGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.GUARDIAN_AMBIENT;
    }

    @Override
    public @NotNull SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.GUARDIAN_HURT;
    }

    @Override
    public @NotNull SoundEvent getDeathSound() {
        return SoundEvents.GUARDIAN_DEATH;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.FALL) || source.is(DamageTypes.DROWN)
                || source.is(DamageTypes.LIGHTNING_BOLT) || source.is(DamageTypes.FALLING_ANVIL))
            return false;
        if (this.skillReady(0)) {
            this.bigTide();
        }
        return super.hurt(source, amount);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();
    }

    @Override
    public EntityDimensions getDimensions(Pose p_33597_) {
        return super.getDimensions(p_33597_).scale((float) 5);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0);
        builder = builder.add(Attributes.MAX_HEALTH, 560);
        builder = builder.add(Attributes.ARMOR, 8);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 9);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 10);
        builder = builder.add(ModAttributes.SANITY_RATE.get(), 10);
        return builder;
    }

    private PlayState movementPredicate(AnimationState<QuintusEntity> event) {
        if (this.isDeadOrDying()) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.bishop.die"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.bishop.idle"));
    }

    private PlayState attackingPredicate(AnimationState<QuintusEntity> event) {
        if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
            this.swinging = true;
            this.lastSwing = level().getGameTime();
        }
        if (this.swinging && this.lastSwing + 20L <= level().getGameTime()) {
            this.swinging = false;
        }
        if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().forceAnimationReset();
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.bishop.attack"));
        }
        return PlayState.CONTINUE;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 40) {
            this.remove(RemovalReason.KILLED);
            this.dropExperience();
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "movement", 2, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 2, this::attackingPredicate));
        data.add(new AnimationController<>(this, "skill", 0, event -> PlayState.STOP)
                .triggerableAnim("skill", RawAnimation.begin()
                        .thenPlay(animLoc("skill"))
                        .thenLoop(animLoc("idle"))));
    }

    private void bigTide() {
        int p = 25;
        float perc = this.getHealth() / this.getMaxHealth();
        if (perc < 0.33) p = 15;
        else if (perc < 0.67) p = 5;
        resetSkill(0, p * 20);
        triggerSound(SoundEvents.AMBIENT_UNDERWATER_EXIT);
        triggerAnim("skill", "skill");
        for (int i = 1; i <= 10; i++) {
            int I = i;
            CaerulaArborMod.queueServerWork(2 * i, () -> rim(2 * I));
        }
        Vec3 pos = this.position();
        AABB aabb = new AABB(pos.add(-20, -4, -20), pos.add(20, 8, 20));
        List<LivingEntity> ents = this.level().getEntitiesOfClass(LivingEntity.class, aabb, e -> isLegalTarget(e) && this.distanceTo(e) <= 20);
        ents.forEach(this::pushAndDamage);
    }

    private void triggerSound(SoundEvent sound) {
        Level level = this.level();
        if (level.isClientSide()) {
            level.playLocalSound(this.blockPosition(), sound, SoundSource.HOSTILE, 4, 1, false);
        } else {
            level.playSound(this, this.blockPosition(), sound, SoundSource.HOSTILE, 4, 1);
        }
    }

    private void rim(double R) {
        if (this.level() instanceof ServerLevel sLevel) {
            for (int i = 0; i < 360; i += 3) {
                double x = this.getX() + R * Math.cos(i * Mth.DEG_TO_RAD),
                        y = this.getY(),
                        z = this.getZ() + R * Math.sin(i * Mth.DEG_TO_RAD);
                sLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                        x, y, z, 16, 0.15, 0.15, 0.15, 0.1);
            }
        }
    }

    private void pushAndDamage(LivingEntity ent) {
        Vec3 vec = ent.position().add(this.position().scale(-1));
        double dx = 0, dy = 0.25, dz = 0;
        if (vec.x != 0) dx = 1.5 / vec.x;
        if (vec.z != 0) dz = 1.5 / vec.z;
        ent.push(dx, dy, dz);
        float damage = (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 1.5);
        double rate = this.getAttributeValue(ModAttributes.SANITY_RATE.get());
        ent.hurt(this.level().damageSources().magic(), damage);
        ModCapabilities.getSanityInjury(ent).hurt(damage * rate);
    }

    @SubscribeEvent
    public static void onEntityAttacked(LivingHurtEvent event) {
        var source = event.getSource();
        var attacker = source.getEntity();
        if (!(attacker instanceof QuintusEntity quintusEntity)) return;
        float perc = quintusEntity.getHealth() / quintusEntity.getMaxHealth();
        float rate = 1;
        if (perc < 0.33) rate = 1.4f;
        else if (perc < 0.67) rate = 1.8f;
        event.setAmount(event.getAmount() * rate);
    }
}
