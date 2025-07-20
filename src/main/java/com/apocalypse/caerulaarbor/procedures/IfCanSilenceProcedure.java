package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.level.LevelAccessor;

public class IfCanSilenceProcedure {
	public static boolean execute(LevelAccessor world) {
		var mapVariables = CaerulaArborModVariables.MapVariables.get(world);
		return mapVariables.strategyGrow >= 4
				&& mapVariables.strategySubsisting >= 4
				&& mapVariables.strategyBreed >= 4
				&& mapVariables.strategyMigration >= 4
				&& mapVariables.silence_enabled;
	}
}
