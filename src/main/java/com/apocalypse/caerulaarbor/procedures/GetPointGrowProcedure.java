package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.level.LevelAccessor;

public class GetPointGrowProcedure {
	public static String execute(LevelAccessor world) {
		double rate = 0;
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_grow >= 4) {
			return "§bFinished";
		}
		return Math.round(CaerulaArborModVariables.MapVariables.get(world).evo_point_grow) + "§b/"
				+ Math.round(Math.pow(CaerulaArborModVariables.MapVariables.get(world).strategy_grow + 1, 3) * (double) GameplayConfig.EVOLUTION_POINT_COEFFICIENT.get());
	}
}
