package com.apocalypse.caerulaarbor.event;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.init.*;
import com.apocalypse.caerulaarbor.item.relic.IRelic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerCriticalHit(CriticalHitEvent event) {
        if (!event.isVanillaCritical()) return;
        var player = event.getEntity();

        var cap = ModCapabilities.getPlayerVariables(player);
        cap.relics.forEach((relic, integer) -> {
            if (!relic.gained(player)) return;
            var relicItem = relic.item;
            if (relicItem instanceof IRelic iRelic) {
                int level = iRelic.onCriticalHit(player, integer);
                if (integer != level) {
                    relic.set(player, level);
                }
            }
        });
    }

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        var player = event.getEntity();

        var cap = ModCapabilities.getPlayerVariables(player);
        cap.relics.forEach((relic, integer) -> {
            if (!relic.gained(player)) return;
            var relicItem = relic.item;
            if (relicItem instanceof IRelic iRelic) {
                int level = iRelic.onAttack(player, integer);
                if (integer != level) {
                    relic.set(player, level);
                }
            }
        });
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        var player = event.player;
        var cap = ModCapabilities.getPlayerVariables(player);
        cap.relics.forEach((relic, integer) -> {
            if (relic.item instanceof IRelic iRelic) {
                int level = iRelic.onPlayerTick(player, integer);
                if (integer != level) {
                    relic.set(player, level);
                }
            }
        });

        if (player.tickCount % 5 == 0) {
            handleRejectionTick(player, cap);
        }
    }

    private static void handleRejectionTick(Player player, PlayerVariable cap) {
        // 排异反应-造血障碍
        if (cap.isRejectionInvoked(PlayerVariable.Rejection.HEMOPOIETIC_INHIBITION)) {
            float damage = player.getMaxHealth() * 0.005f;
            if (player.getHealth() <= damage) return;

            boolean flag = player.hurt(ModDamageTypes.causeHemopoieticDisorderDamage(player.level().registryAccess(), null), damage);
            if (!flag) return;
            player.invulnerableTime = 0;
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (!(entity instanceof Player player)) return;
        Level level = player.level();

        var cap = ModCapabilities.getPlayerVariables(player);

        if (!level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) && !level.isClientSide) {
            for (var relic : Relic.values()) {
                relic.remove(player);
            }
            cap.syncPlayerVariables(player);
        }

        var source = event.getSource();
        if (source.is(DamageTypes.GENERIC_KILL)) return;

        var pos = player.getOnPos();

        boolean shouldCancel = false;

        double lightCost = 0;

        if (cap.shield > 0) {
            cap.shield -= 1;

            sendSoundAndParticles(level, pos, ModParticleTypes.SHIELDLOSS.get());
            shouldCancel = true;
        } else if (cap.life > 0) {
            cap.life -= 1;

            sendSoundAndParticles(level, pos, ModParticleTypes.LIFELOSS.get());
            shouldCancel = true;

            if (source.is(DamageTypes.LIGHTNING_BOLT) || source.is(DamageTypes.FELL_OUT_OF_WORLD) || source.is(DamageTypes.OUTSIDE_BORDER) || source.is(DamageTypes.CRAMMING)) {
                lightCost = 15;
            } else if (source.is(DamageTypes.WITHER) || source.is(DamageTypes.WITHER_SKULL) || source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.FIREWORKS) || source.is(DamageTypes.SONIC_BOOM)
                    || source.is(DamageTypes.DRAGON_BREATH) || source.is(DamageTypes.LAVA) || source.is(DamageTypes.FALLING_ANVIL) || source.is(DamageTypes.FALLING_STALACTITE)
                    || source.is(DamageTypes.STALAGMITE)) {
                lightCost = 10;
            } else {
                lightCost = 5;
            }
        } else {
            lightCost = 50;
        }

        cap.light = Math.max(0, cap.light - lightCost);
        cap.syncPlayerVariables(player);

        if (shouldCancel && level.getGameRules().getBoolean(ModGameRules.TARGET_LIFE_FUNCTION)) {
            event.setCanceled(true);
            player.setHealth(player.getMaxHealth() * 0.5f);
            ModCapabilities.getSanityInjury(player).heal(1000);
        }
    }

    private static void sendSoundAndParticles(Level level, BlockPos pos, ParticleOptions particleOptions) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, pos, ModSounds.TARGET_DAMAGED.get(), SoundSource.PLAYERS, 1, 1);
            serverLevel.playSound(null, pos, SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, 1);
            serverLevel.sendParticles(particleOptions, pos.getX(), pos.getY() + 0.95, pos.getZ(), 72, 0.75, 0.55, 0.75, 0.2);
        } else {
            level.playLocalSound(pos, ModSounds.TARGET_DAMAGED.get(), SoundSource.PLAYERS, 1, 1, false);
            level.playLocalSound(pos, SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, 1, false);
        }
    }

    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
        var player = event.getEntity();
        if (event.wakeImmediately() || event.updateLevel()) return;
        var cap = ModCapabilities.getPlayerVariables(player);
        cap.light = Mth.clamp(cap.light + Mth.nextInt(RandomSource.create(), 1, 3), 0, 100);
        cap.syncPlayerVariables(player);
    }

    @SubscribeEvent
    public static void onPlayerAttacked(LivingAttackEvent event) {
        if (event.getSource() != null && event.getSource().is(ModTags.DamageTypes.BYPASS_NEURODEGENERATION)) return;
        if (event.getEntity() instanceof Player player) {
            var variables = ModCapabilities.getPlayerVariables(player);
            if (variables.isRejectionInvoked(PlayerVariable.Rejection.NEURODEGENERATION)) {
                double rate = 0.08;
                int time = 160;
                float temp = player.level().getBiome(player.getOnPos()).get().getBaseTemperature();
                if (temp >= 1.8) {
                    rate = 0.02;
                    time = 40;
                }
                if (temp <= 0.1) {
                    rate = 0.12;
                    time = 240;
                }
                if (Math.random() < rate && !player.level().isClientSide) {
                    player.addEffect(new MobEffectInstance(ModMobEffects.FROZEN.get(), time, 0, false, false, true));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.isEndConquered()) return;
        var player = event.getEntity();
        if (player.level().isClientSide) return;

        var variables = ModCapabilities.getPlayerVariables(player);
        if (variables.isRejectionInvoked(PlayerVariable.Rejection.NEURODEGENERATION)) {
            player.addEffect(new MobEffectInstance(ModMobEffects.FROZEN.get(), 300, 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onBreakBlock(BlockEvent.BreakEvent event) {
        var player = event.getPlayer();
        if (player == null) return;
        if (player.isCreative() || player.isSpectator()) return;
        if (!event.getState().is(ModTags.Blocks.NETHERSEA_BLOCK)) return;

        var pos = event.getPos();
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 6, 0.75, 0.75, 0.75, 0.1);
        }

        var cap = ModCapabilities.getPlayerVariables(player);
        if (cap.seabornization > 3) return;
        ModCapabilities.getSanityInjury(player).hurt(Mth.nextInt(RandomSource.create(), 16, 32));
    }

    /**
     * 玩家食用配置中存在的食物，恢复灯火
     */
    @SubscribeEvent
    public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        var entity = event.getEntity();
        if (!(entity instanceof Player player)) return;

        var cap = ModCapabilities.getPlayerVariables(player);
        var list = GameplayConfig.LIGHTS_RECOVERY_FOOD_LIST.get();
        var item = event.getItem();
        var id = ForgeRegistries.ITEMS.getKey(item.getItem());
        if (id == null) return;

        for (var food : list) {
            String str = food.trim().replace(" ", "");
            var properties = str.split(",");
            if (properties.length != 3) continue;
            if (!id.toString().equals(properties[0])) continue;
            cap.light = Math.min(100, cap.light + Mth.nextInt(RandomSource.create(), Integer.parseInt(properties[1]), Integer.parseInt(properties[2])));
            cap.syncPlayerVariables(entity);
        }
    }
}
