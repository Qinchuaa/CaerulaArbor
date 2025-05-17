package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.level.LevelAccessor;

public class IfCanSilenceProcedure {
	public static boolean execute(LevelAccessor world) {
		return CaerulaArborModVariables.MapVariables.get(world).strategy_grow >= 4 && CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting >= 4 && CaerulaArborModVariables.MapVariables.get(world).strategy_breed >= 4
				&& CaerulaArborModVariables.MapVariables.get(world).strategy_migration >= 4 && CaerulaArborModVariables.MapVariables.get(world).silence_enabled;
	}
}
