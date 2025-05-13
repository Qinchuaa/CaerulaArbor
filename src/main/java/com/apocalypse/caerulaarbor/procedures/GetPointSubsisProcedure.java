package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.configuration.CaerulaConfigsConfiguration;
import net.minecraft.world.level.LevelAccessor;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class GetPointSubsisProcedure {
	public static String execute(LevelAccessor world) {
		double rate = 0;
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting >= 4) {
			return "\u00A7bFinished";
		}
		return Math.round(CaerulaArborModVariables.MapVariables.get(world).evo_point_subsisting) + "\u00A7b/"
				+ Math.round(Math.pow(CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting + 1, 3) * (double) CaerulaConfigsConfiguration.COEFFICIENT.get());
	}
}
