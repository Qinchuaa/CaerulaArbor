package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class GetlayerSurvcontaProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + Math.round((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_SURVIVOR);
	}
}
