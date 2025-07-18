package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.level.LevelAccessor;

public class GetBarGrowProcedure {
	public static double execute(LevelAccessor world) {
		double rate;
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_grow >= 4) {
			return 18;
		}
		rate = CaerulaArborModVariables.MapVariables.get(world).evo_point_grow / (Math.pow(CaerulaArborModVariables.MapVariables.get(world).strategy_grow + 1, 3) * GameplayConfig.EVOLUTION_POINT_COEFFICIENT.get());
		if (rate > 1) {
			rate = 1;
		}
		return Math.round(18 * rate);
	}
}
