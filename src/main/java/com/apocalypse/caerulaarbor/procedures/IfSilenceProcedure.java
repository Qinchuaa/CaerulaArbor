package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class IfSilenceProcedure {
	public static boolean execute(LevelAccessor world) {
		return CaerulaArborModVariables.MapVariables.get(world).strategy_silence > 0;
	}
}
