package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.configuration.CaerulaConfigsConfiguration;

public class GetBarGrowProcedure {
	public static double execute(LevelAccessor world) {
		double rate = 0;
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_grow >= 4) {
			return 18;
		}
		rate = CaerulaArborModVariables.MapVariables.get(world).evo_point_grow / (Math.pow(CaerulaArborModVariables.MapVariables.get(world).strategy_grow + 1, 3) * (double) CaerulaConfigsConfiguration.COEFFICIENT.get());
		if (rate > 1) {
			rate = 1;
		}
		return Math.round(18 * rate);
	}
}
