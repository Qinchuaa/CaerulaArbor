package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.configuration.CaerulaConfigsConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

import java.util.ArrayList;

public class UpgradeSilenceProcedure {
	public static void execute(LevelAccessor world, Entity entity, double point) {
		if (entity == null)
			return;
		double stra = 0;
		String num = "";
		String prefix = "";
		stra = CaerulaArborModVariables.MapVariables.get(world).strategy_silence;
		if (IfCanSilenceProcedure.execute(world)) {
			CaerulaArborModVariables.MapVariables.get(world).evo_point_silence = CaerulaArborModVariables.MapVariables.get(world).evo_point_silence + point;
			CaerulaArborModVariables.MapVariables.get(world).syncData(world);
			if (stra < 4) {
				if (CaerulaArborModVariables.MapVariables.get(world).evo_point_silence >= Math.pow(stra + 1, 3) * (double) CaerulaConfigsConfiguration.COEFFICIENT.get() * 4) {
					for (Entity entityiterator : new ArrayList<>(world.players())) {
						if (entity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("caerula_arbor:she_coming"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
					CaerulaArborModVariables.MapVariables.get(world).strategy_silence = stra + 1;
					CaerulaArborModVariables.MapVariables.get(world).syncData(world);
					stra = CaerulaArborModVariables.MapVariables.get(world).strategy_silence;
					CaerulaArborModVariables.MapVariables.get(world).evo_point_silence = 0;
					CaerulaArborModVariables.MapVariables.get(world).syncData(world);
					if (stra == 1) {
						num = "I";
						prefix = "\u00A7p";
					} else if (stra == 2) {
						num = "II";
						prefix = "\u00A7p";
					} else if (stra == 3) {
						num = "III";
						prefix = "\u00A7c";
					} else if (stra == 4) {
						num = "IV";
						prefix = "\u00A74";
					}
					if (CaerulaConfigsConfiguration.EVOSOUND.get()) {
						for (Entity entityiterator : new ArrayList<>(world.players())) {
							if (stra == 1) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:silence1")),
												SoundSource.NEUTRAL, 4, 1);
									} else {
										_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:silence1")), SoundSource.NEUTRAL, 4, 1,
												false);
									}
								}
								if (entityiterator instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_6").getString())), true);
							} else if (stra == 2) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:silence2")),
												SoundSource.NEUTRAL, 4, 1);
									} else {
										_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:silence2")), SoundSource.NEUTRAL, 4, 1,
												false);
									}
								}
								if (entityiterator instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_7").getString())), true);
							} else if (stra == 3) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:silence3")),
												SoundSource.NEUTRAL, 4, 1);
									} else {
										_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:silence3")), SoundSource.NEUTRAL, 4, 1,
												false);
									}
								}
								if (entityiterator instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_8").getString())), true);
							} else if (stra == 4) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:silence4")),
												SoundSource.NEUTRAL, 4, 1);
									} else {
										_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:silence4")), SoundSource.NEUTRAL, 4, 1,
												false);
									}
								}
								if (entityiterator instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_9").getString())), true);
							}
						}
					}
					if (!world.isClientSide() && world.getServer() != null)
						world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((prefix + "" + Component.translatable("item.caerula_arbor.language_key.description_4").getString() + num)), false);
				}
			} else {
				CaerulaArborModVariables.MapVariables.get(world).evo_point_silence = 0;
				CaerulaArborModVariables.MapVariables.get(world).syncData(world);
				for (Entity entityiterator : new ArrayList<>(world.players())) {
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("caerula_arbor:hymn_of_land"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
		} else {
			CaerulaArborModVariables.MapVariables.get(world).strategy_silence = 0;
			CaerulaArborModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
