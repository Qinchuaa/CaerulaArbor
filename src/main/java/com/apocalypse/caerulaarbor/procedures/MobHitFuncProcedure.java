package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.configuration.CaerulaConfigsConfiguration;
import com.apocalypse.caerulaarbor.entity.*;
import com.apocalypse.caerulaarbor.init.*;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MobHitFuncProcedure {
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource(), event.getEntity(), event.getSource().getDirectEntity(), event.getSource().getEntity(),
                    event.getAmount());
        }
    }

    public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
        execute(null, world, x, y, z, damagesource, entity, immediatesourceentity, sourceentity, amount);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
        if (damagesource == null || entity == null || immediatesourceentity == null || sourceentity == null)
            return;

        double amplifi;
        double rate;
        double sklp;
        double limithard;
        if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModMobEffects.INVULNERABLE.get())) {
            if (event != null && event.isCancelable()) {
                event.setCanceled(true);
            }
        }
        if (entity instanceof PredatorAbyssalEntity livEnt4) {
            rate = 0.8;
            if (CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting >= 3) {
                rate = 0.9;
            }
            if (!(livEnt4.hasEffect(ModMobEffects.DIZZY.get()) || livEnt4.hasEffect(ModMobEffects.FROZEN.get()) || livEnt4.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) || entity instanceof LivingEntity _livEnt5 && _livEnt5.hasEffect(MobEffects.SLOW_FALLING)) && !(damagesource.is(DamageTypes.GENERIC_KILL) || damagesource.is(DamageTypes.FELL_OUT_OF_WORLD) || damagesource.is(DamageTypes.SONIC_BOOM) || damagesource.is(DamageTypes.WITHER))) {
                if (Math.random() < rate) {
                    if (entity instanceof PredatorAbyssalEntity) {
                        ((PredatorAbyssalEntity) entity).setAnimation("animation.predator.miss");
                    }
                    if (world instanceof ServerLevel _level)
                        _level.sendParticles(ModParticleTypes.MISS.get(), x, y, z, 16, 1, 1, 1, 0.1);
                    if (event != null && event.isCancelable()) {
                        event.setCanceled(true);
                    }
                }
            }
        }
        if (CaerulaArborModVariables.MapVariables.get(world).strategy_grow >= 3) {
            if (sourceentity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring"))) && !damagesource.is(DamageTypes.MAGIC)) {
                CaerulaArborMod.queueServerWork(10, () -> {
                    entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), immediatesourceentity, sourceentity),
                            (float) (amount * 0.2 * (CaerulaArborModVariables.MapVariables.get(world).strategy_grow - 2)));
                    if (world instanceof ServerLevel _level)
                        _level.sendParticles(ParticleTypes.ENCHANTED_HIT, x, (y + 1), z, 48, 1.5, 1.5, 1.5, 0.2);
                });
            }
        }
        if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring"))) && !sourceentity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
            if (CaerulaArborModVariables.MapVariables.get(world).strategy_migration > 0) {
                for (Entity entityiterator : world.getEntities(entity,
                        new AABB((x - (8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24)), (y - 16), (z - (8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24)),
                                (x + 8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24), (y + 16), (z + 8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24)))) {
                    if (entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
                        if (entityiterator == sourceentity) {
                            continue;
                        }
                        if (entityiterator instanceof Mob _entity) {
                            _entity.getNavigation().moveTo(x, y, z,
                                    (_entity.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED) ? _entity.getAttribute(Attributes.MOVEMENT_SPEED).getValue() : 0) * 1.2);
                        }
                        if (entityiterator instanceof Mob _entity && sourceentity instanceof LivingEntity _ent)
                            _entity.setTarget(_ent);
                    }
                }
            }
        }
        if (entity instanceof Player) {
            if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).player_oceanization >= 3) {
                if (CaerulaArborModVariables.MapVariables.get(world).strategy_migration > 0) {
                    for (Entity entityiterator : world.getEntities(entity,
                            new AABB((x - (8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24)), (y - 16), (z - (8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24)),
                                    (x + 8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24), (y + 16), (z + 8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24)))) {
                        if (entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
                            if (entityiterator == sourceentity) {
                                continue;
                            }
                            if (entityiterator instanceof Mob _entity) {
                                _entity.getNavigation().moveTo(x, y, z,
                                        _entity.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED) ? _entity.getAttribute(Attributes.MOVEMENT_SPEED).getValue() : 0);
                            }
                            if (entityiterator instanceof Mob _entity && sourceentity instanceof LivingEntity _ent)
                                _entity.setTarget(_ent);
                        }
                    }
                }
            }
        }
        if (sourceentity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
            if (world.getLevelData().getGameRules().getBoolean(ModGameRules.NATURAL_EVOLUTION)) {
                CaerulaArborModVariables.MapVariables.get(world).evo_point_grow = CaerulaArborModVariables.MapVariables.get(world).evo_point_grow + amount * 0.025;
                CaerulaArborModVariables.MapVariables.get(world).syncData(world);
                UpgradeGrowProcedure.execute(world);
                UpgradeSilenceProcedure.execute(world, entity, amount * 0.025);
            }
        }
        if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
            if (sourceentity instanceof ServerPlayer _player) {
                Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("caerula_arbor:encounter_from_the_ocean"));
                AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                if (!_ap.isDone()) {
                    for (String criteria : _ap.getRemainingCriteria())
                        _player.getAdvancements().award(_adv, criteria);
                }
            }
            if (world.getLevelData().getGameRules().getBoolean(ModGameRules.NATURAL_EVOLUTION)) {
                CaerulaArborModVariables.MapVariables.get(world).evo_point_subsisting = CaerulaArborModVariables.MapVariables.get(world).evo_point_subsisting + amount * 0.025;
                CaerulaArborModVariables.MapVariables.get(world).syncData(world);
                UpgradeSubsisProcedure.execute(world);
                UpgradeSilenceProcedure.execute(world, entity, amount * 0.025);
            }
            if (entity.isPassenger() && CaerulaConfigsConfiguration.BREAKABLE.get() && world.getLevelData().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                (entity.getVehicle()).hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity), 8);
            }
            if (CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting >= 3 && Math.random() < 0.5) {
                if (CaerulaConfigsConfiguration.BREAKABLE.get() && world.getLevelData().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                    if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanspawn"))) && DetectForTrailProcedure.execute(world, x, y, z)) {
                        new Object() {
                            void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, 1, 1);
                                    } else {
                                        _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, 1, 1, false);
                                    }
                                }
                                final int tick2 = ticks;
                                CaerulaArborMod.queueServerWork(tick2, () -> {
                                    if (timedlooptotal > timedloopiterator + 1) {
                                        timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
                                    }
                                });
                            }
                        }.timedLoop(0, 3, 5);
                        if (entity instanceof LivingEntity _entity)
                            _entity.setHealth((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + 8);
                    }
                }
            }
            if (CaerulaArborModVariables.MapVariables.get(world).strategy_silence > 0) {
                if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:bossoffspring"))) && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanspawn")))) {
                    if (Math.random() < -0.01 + 0.02 * CaerulaArborModVariables.MapVariables.get(world).strategy_silence && !(sourceentity == entity)) {
                        if ((entity instanceof LivingEntity _livingEntity50 && _livingEntity50.getAttributes().hasAttribute(ModAttributes.SUMMONABLE.get())
                                ? _livingEntity50.getAttribute(ModAttributes.SUMMONABLE.get()).getBaseValue()
                                : 0) == 1) {
                            {
                                Entity _ent = entity;
                                if (!_ent.level().isClientSide() && _ent.getServer() != null) {
                                    _ent.getServer().getCommands().performPrefixedCommand(
                                            new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(),
                                                    _ent.getDisplayName(), _ent.level().getServer(), _ent),
                                            ("summon " + ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString() + " ~" + Mth.nextDouble(RandomSource.create(), -1, 1) + " ~ ~" + Mth.nextDouble(RandomSource.create(), -1, 1)));
                                }
                            }
                            if (world instanceof ServerLevel _level)
                                _level.sendParticles(ParticleTypes.CLOUD, x, y, z, 64, 1.5, 2, 1.5, 0.1);
                            if (CaerulaArborModVariables.MapVariables.get(world).strategy_silence >= 3) {
                                if (Math.random() < 0.5) {
                                    if (world instanceof ServerLevel server) {
                                        var type = switch (Mth.nextInt(RandomSource.create(), 0, 8)) {
                                            case 0 -> ModEntities.BASELAYER_ABYSSAL;
                                            case 1 -> ModEntities.CRACKER_ABYSSAL;
                                            case 2 -> ModEntities.CREEPER_FISH;
                                            case 3 -> ModEntities.GUIDE_ABYSSAL;
                                            case 4 -> ModEntities.PUNCTURE_FISH;
                                            case 5 -> ModEntities.REAPER_FISH;
                                            case 6 -> ModEntities.UMBRELLA_ABYSSAL;
                                            case 7 -> ModEntities.PREGNANT_FISH;
                                            case 8 -> ModEntities.FLEE_FISH;
                                            default -> null;
                                        };

                                        if (type != null) {
                                            var entityToSpawn = ((RegistryObject<? extends EntityType<?>>) type).get().spawn((ServerLevel) world, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z), MobSpawnType.MOB_SUMMONED);
                                            if (entityToSpawn != null) {
                                                entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
                                            }
                                        }

                                        server.sendParticles(ParticleTypes.CLOUD, x, y, z, 32, 1, 1, 1, 0.1);
                                    }
                                }
                            }
                            if (entity instanceof LivingEntity _livingEntity56 && _livingEntity56.getAttributes().hasAttribute(ModAttributes.SUMMONABLE.get()))
                                _livingEntity56.getAttribute(ModAttributes.SUMMONABLE.get()).setBaseValue(0);
                        }
                    }
                }
                if (CaerulaArborModVariables.MapVariables.get(world).strategy_silence >= 3) {
                    if (Math.random() < -0.4 + CaerulaArborModVariables.MapVariables.get(world).strategy_silence * 0.2) {
                        sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.THORNS)),
                                (float) ((entity instanceof LivingEntity _livingEntity57 && _livingEntity57.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity57.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0) * 0.5));
                    }
                }
            }
            if (entity instanceof LivingEntity _livingEntity60 && _livingEntity60.getAttributes().hasAttribute(ModAttributes.EVOLVED.get()))
                _livingEntity60.getAttribute(ModAttributes.EVOLVED.get()).setBaseValue(1);
        }
        if (sourceentity instanceof CrackerAbyssalEntity livEnt) {
            amplifi = livEnt.hasEffect(ModMobEffects.REEF_CRACKER.get()) ? livEnt.getEffect(ModMobEffects.REEF_CRACKER.get()).getAmplifier() : 0;
            if (livEnt.hasEffect(ModMobEffects.REEF_CRACKER.get())) {
                if (amplifi < 14) {
                    LivingEntity _entity = (LivingEntity) sourceentity;
                    if (!_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(ModMobEffects.REEF_CRACKER.get(), 120, (int) (amplifi + 1), false, false));
                } else {
                    LivingEntity _entity = (LivingEntity) sourceentity;
                    if (!_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(ModMobEffects.REEF_CRACKER.get(), 120, 14, false, false));
                }
            } else {
                LivingEntity _entity = (LivingEntity) sourceentity;
                if (!_entity.level().isClientSide())
                    _entity.addEffect(new MobEffectInstance(ModMobEffects.REEF_CRACKER.get(), 120, 0, false, false));
            }
        }
        if (sourceentity instanceof BoneFishEntity) {
            GiveLessArmorProcedure.execute(entity, 1);
        }
        if (sourceentity instanceof FakeOffspringEntity) {
            GiveLessArmorProcedure.execute(entity, 2);
        }
        if (sourceentity instanceof RouteShaperEntity) {
            sklp = sourceentity instanceof RouteShaperEntity _datEntI ? _datEntI.getEntityData().get(RouteShaperEntity.DATA_skillp) : 0;
            if (sourceentity instanceof RouteShaperEntity _datEntSetI)
                _datEntSetI.getEntityData().set(RouteShaperEntity.DATA_skillp, (int) (sklp + 1));
            if (sklp + 1 >= 8) {
                SummonFractalProcedure.execute(world, x, y, z);
                if (sourceentity instanceof RouteShaperEntity _datEntSetI)
                    _datEntSetI.getEntityData().set(RouteShaperEntity.DATA_skillp, 0);
            }
        }
        if (sourceentity instanceof RouteFractalEntity) {
            sklp = sourceentity instanceof RouteFractalEntity _datEntI ? _datEntI.getEntityData().get(RouteFractalEntity.DATA_skillp) : 0;
            if (sourceentity instanceof RouteFractalEntity _datEntSetI)
                _datEntSetI.getEntityData().set(RouteFractalEntity.DATA_skillp, (int) (sklp + 1));
            if (sklp + 1 >= 6) {
                SummonFractalProcedure.execute(world, x, y, z);
                if (sourceentity instanceof RouteFractalEntity _datEntSetI)
                    _datEntSetI.getEntityData().set(RouteFractalEntity.DATA_skillp, 0);
            }
        }
    }
}
