package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.level.LevelAccessor;

public class IfCanSilenceProcedure {
	public static boolean execute(LevelAccessor world) {
		var mapVariables = CaerulaArborModVariables.MapVariables.get(world);
		return mapVariables.strategy_grow >= 4
				&& mapVariables.strategy_subsisting >= 4
				&& mapVariables.strategy_breed >= 4
				&& mapVariables.strategy_migration >= 4
				&& mapVariables.silence_enabled;
	}
}
