package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.world.entity.Entity;

public class HasRoyalfateProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
        return (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_archifi_RYLFATE;
	}
}
