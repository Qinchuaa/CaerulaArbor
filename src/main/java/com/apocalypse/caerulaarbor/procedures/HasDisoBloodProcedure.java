package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class HasDisoBloodProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).disoclusion == 2;
	}
}
