package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.configuration.CaerulaConfigsConfiguration;

import java.util.ArrayList;

public class UpgradeBreedProcedure {
	public static void execute(LevelAccessor world) {
		double stra = 0;
		String num = "";
		String prefix = "";
		stra = CaerulaArborModVariables.MapVariables.get(world).strategy_breed;
		if (stra < 4) {
			if (CaerulaArborModVariables.MapVariables.get(world).evo_point_breed >= Math.pow(stra + 1, 3) * (double) CaerulaConfigsConfiguration.COEFFICIENT.get()) {
				CaerulaArborModVariables.MapVariables.get(world).strategy_breed = stra + 1;
				CaerulaArborModVariables.MapVariables.get(world).syncData(world);
				stra = CaerulaArborModVariables.MapVariables.get(world).strategy_breed;
				CaerulaArborModVariables.MapVariables.get(world).evo_point_breed = 0;
				CaerulaArborModVariables.MapVariables.get(world).syncData(world);
				if (stra == 1) {
					num = "I";
					prefix = "\u00A7p";
				} else if (stra == 2) {
					num = "II";
					prefix = "\u00A7b";
				} else if (stra == 3) {
					num = "III";
					prefix = "\u00A79";
				} else if (stra == 4) {
					num = "IV";
					prefix = "\u00A71";
				}
				for (Entity entityiterator : new ArrayList<>(world.players())) {
					if (stra >= 3) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:breed2")), SoundSource.NEUTRAL,
										4, 1);
							} else {
								_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:breed2")), SoundSource.NEUTRAL, 4, 1, false);
							}
						}
					} else if (stra > 0) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:breed1")), SoundSource.NEUTRAL,
										4, 1);
							} else {
								_level.playLocalSound((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:breed1")), SoundSource.NEUTRAL, 4, 1, false);
							}
						}
					}
				}
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((prefix + "" + Component.translatable("item.caerula_arbor.sample_breed.description_5").getString() + num)), false);
			}
		} else {
			CaerulaArborModVariables.MapVariables.get(world).evo_point_breed = 1;
			CaerulaArborModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
