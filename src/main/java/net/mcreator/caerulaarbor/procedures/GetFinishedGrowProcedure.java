package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;

public class GetFinishedGrowProcedure {
	public static double execute(LevelAccessor world) {
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_grow >= 4) {
			return 1;
		}
		return 0;
	}
}
