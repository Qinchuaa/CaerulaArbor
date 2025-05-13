package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class GetStartedSilenceProcedure {
	public static double execute(LevelAccessor world) {
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_silence >= 1) {
			return 1;
		}
		return 0;
	}
}
