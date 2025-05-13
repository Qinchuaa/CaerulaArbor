package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class GetOceanizeIndexProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_oceanization;
	}
}
