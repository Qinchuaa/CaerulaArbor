package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;

public class GetSilenceBreedProcedure {
	public static String execute(LevelAccessor world) {
		return Component.translatable(("item.caerula_arbor.sample_breed.description_" + Math.round(CaerulaArborModVariables.MapVariables.get(world).strategy_silence + 5))).getString();
	}
}
