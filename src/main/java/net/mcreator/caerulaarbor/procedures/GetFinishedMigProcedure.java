package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;

public class GetFinishedMigProcedure {
	public static double execute(LevelAccessor world) {
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_migration >= 4) {
			return 1;
		}
		return 0;
	}
}
