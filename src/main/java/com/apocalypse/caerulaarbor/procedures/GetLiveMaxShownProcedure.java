package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import net.minecraft.world.entity.Entity;

public class GetLiveMaxShownProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
        return "/" + Math.round((entity.getCapability(ModCapabilities.PLAYER_VARIABLE).orElse(new PlayerVariable())).maxLive);
	}
}
