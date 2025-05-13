package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class GetDescrGrowProcedure {
	public static String execute(LevelAccessor world) {
		return Component.translatable(("item.caerula_arbor.sample_grow.description_" + Math.round(CaerulaArborModVariables.MapVariables.get(world).strategy_grow))).getString();
	}
}
