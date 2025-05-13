package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.configuration.CaerulaConfigsConfiguration;
import net.minecraft.world.level.LevelAccessor;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class GetBarSubsisProcedure {
	public static double execute(LevelAccessor world) {
		double rate = 0;
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting >= 4) {
			return 18;
		}
		rate = CaerulaArborModVariables.MapVariables.get(world).evo_point_subsisting / (Math.pow(CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting + 1, 3) * (double) CaerulaConfigsConfiguration.COEFFICIENT.get());
		if (rate > 1) {
			rate = 1;
		}
		return Math.round(18 * rate);
	}
}
