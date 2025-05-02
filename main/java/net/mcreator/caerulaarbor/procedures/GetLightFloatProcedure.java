package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;

public class GetLightFloatProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return new java.text.DecimalFormat("##.##").format((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_light);
	}
}
