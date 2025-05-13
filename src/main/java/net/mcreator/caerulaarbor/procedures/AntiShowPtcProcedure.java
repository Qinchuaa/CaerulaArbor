package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;

public class AntiShowPtcProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return !(entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).kingShowPtc;
	}
}
