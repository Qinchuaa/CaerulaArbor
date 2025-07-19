package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.level.LevelAccessor;

public class GetPointSubsisProcedure {
	public static String execute(LevelAccessor world) {
		if (CaerulaArborModVariables.MapVariables.get(world).strategySubsisting >= 4) {
			return "§bFinished";
		}
		return Math.round(CaerulaArborModVariables.MapVariables.get(world).evo_point_subsisting) + "§b/"
				+ Math.round(Math.pow(CaerulaArborModVariables.MapVariables.get(world).strategySubsisting + 1, 3) * GameplayConfig.EVOLUTION_POINT_COEFFICIENT.get());
	}
}
