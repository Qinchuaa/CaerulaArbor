package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import net.minecraft.world.entity.Entity;

public class HasDisoBloodProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
        return (entity.getCapability(ModCapabilities.PLAYER_VARIABLE).orElse(new PlayerVariable())).disoclusion == 2;
	}
}
