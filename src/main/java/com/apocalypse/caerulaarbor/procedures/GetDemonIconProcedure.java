package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.entity.Entity;

public class GetDemonIconProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
        return (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).player_demon_suit;
	}
}
