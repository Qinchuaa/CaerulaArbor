package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.map.MapVariables;
import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.init.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public class UpgradeBreedProcedure {
    public static void execute(Level world) {
        double stra;
        String num = "";
        String prefix = "";
        var mapVar = MapVariables.get(world);
        stra = mapVar.strategyBreed;
        if (stra < 4) {
            if (mapVar.evoPointBreed >= Math.pow(stra + 1, 3) * GameplayConfig.EVOLUTION_POINT_COEFFICIENT.get()) {
                for (var player : world.players()) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        var advancement = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("caerula_arbor:to_experience_evolution"));
                        var progress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
                        if (!progress.isDone()) {
                            for (String criteria : progress.getRemainingCriteria())
                                serverPlayer.getAdvancements().award(advancement, criteria);
                        }
                    }
                }
                mapVar.strategyBreed = stra + 1;
                mapVar.evoPointBreed = 0;

                stra = mapVar.strategyBreed;
                if (stra == 1) {
                    num = "I";
                    prefix = "§p";
                } else if (stra == 2) {
                    num = "II";
                    prefix = "§b";
                } else if (stra == 3) {
                    num = "III";
                    prefix = "§9";
                } else if (stra == 4) {
                    num = "IV";
                    prefix = "§1";
                }
                if (GameplayConfig.ENABLE_EVOLUTION_SOUND.get()) {
                    for (var player : world.players()) {
                        if (stra >= 3) {
                            if (!world.isClientSide()) {
                                world.playSound(null, player.blockPosition(), ModSounds.BREED2.get(), SoundSource.NEUTRAL, 4, 1);
                            } else {
                                world.playLocalSound(player.blockPosition(), ModSounds.BREED2.get(), SoundSource.NEUTRAL, 4, 1, false);
                            }
                        } else if (stra > 0) {
                            if (!world.isClientSide()) {
                                world.playSound(null, player.blockPosition(), ModSounds.BREED1.get(), SoundSource.NEUTRAL, 4, 1);
                            } else {
                                world.playLocalSound(player.blockPosition(), ModSounds.BREED1.get(), SoundSource.NEUTRAL, 4, 1, false);
                            }
                        }
                    }
                }
                if (!world.isClientSide() && world.getServer() != null)
                    world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((prefix + Component.translatable("item.caerula_arbor.sample_breed.description_5").getString() + num)), false);
            }
        } else {
            for (var player : world.players()) {
                if (player instanceof ServerPlayer serverPlayer) {
                    var advancement = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("caerula_arbor:to_terminate_evolution"));
                    var progress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
                    if (!progress.isDone()) {
                        for (String criteria : progress.getRemainingCriteria()) {
                            serverPlayer.getAdvancements().award(advancement, criteria);
                        }
                    }
                }
            }
            mapVar.evoPointBreed = 1;
        }
        mapVar.syncData(world);
    }
}
