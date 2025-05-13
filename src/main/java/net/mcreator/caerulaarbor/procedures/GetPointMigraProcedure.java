package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.configuration.CaerulaConfigsConfiguration;

public class GetPointMigraProcedure {
	public static String execute(LevelAccessor world) {
		double rate = 0;
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_migration >= 4) {
			return "\u00A7bFinished";
		}
		return Math.round(CaerulaArborModVariables.MapVariables.get(world).evo_point_migration) + "\u00A7b/"
				+ Math.round(Math.pow(CaerulaArborModVariables.MapVariables.get(world).strategy_migration + 1, 3) * (double) CaerulaConfigsConfiguration.COEFFICIENT.get());
	}
}
