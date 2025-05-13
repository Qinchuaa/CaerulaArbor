package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class GetStraSilenceProcedure {
	public static double execute(LevelAccessor world) {
		return CaerulaArborModVariables.MapVariables.get(world).strategy_silence;
	}
}
