package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import net.minecraft.world.entity.Entity;

public class GetKingiconProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return (entity.getCapability(ModCapabilities.PLAYER_VARIABLE).orElse(new PlayerVariable())).player_king_suit;
	}
}
