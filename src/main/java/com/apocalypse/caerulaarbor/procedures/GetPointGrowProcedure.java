package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.level.LevelAccessor;

public class GetPointGrowProcedure {
	public static String execute(LevelAccessor world) {
		var mapVar = CaerulaArborModVariables.MapVariables.get(world);
		if (mapVar.strategy_grow >= 4) {
			return "§bFinished";
		}
		return Math.round(mapVar.evo_point_grow) + "§b/"
				+ Math.round(Math.pow(mapVar.strategy_grow + 1, 3) * GameplayConfig.EVOLUTION_POINT_COEFFICIENT.get());
	}
}
