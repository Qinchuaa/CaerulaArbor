package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class GwtFinishedBreedProcedure {
	public static double execute(LevelAccessor world) {
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_breed >= 4) {
			return 1;
		}
		return 0;
	}
}
