package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;

public class GetStraGrowProcedure {
	public static double execute(LevelAccessor world) {
		return CaerulaArborModVariables.MapVariables.get(world).strategy_grow;
	}
}
