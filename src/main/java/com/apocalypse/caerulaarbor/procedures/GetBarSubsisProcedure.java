package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.level.LevelAccessor;

public class GetBarSubsisProcedure {
	public static double execute(LevelAccessor world) {
		double rate;
		var mapVar = CaerulaArborModVariables.MapVariables.get(world);
		if (mapVar.strategySubsisting >= 4) {
			return 18;
		}
		rate = mapVar.evo_point_subsisting / (Math.pow(mapVar.strategySubsisting + 1, 3) * GameplayConfig.EVOLUTION_POINT_COEFFICIENT.get());
		if (rate > 1) {
			rate = 1;
		}
		return Math.round(18 * rate);
	}
}
