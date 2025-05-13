package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class LightIsWavingProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return 50 <= (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_light
				&& (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_light < 85;
	}
}
