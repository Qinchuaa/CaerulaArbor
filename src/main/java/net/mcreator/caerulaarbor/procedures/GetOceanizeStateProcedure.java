package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;

public class GetOceanizeStateProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_oceanization == 3) {
			return Component.translatable("item.caerula_arbor.language_key.description_13").getString();
		} else if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_oceanization == 2) {
			return Component.translatable("item.caerula_arbor.language_key.description_12").getString();
		} else if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_oceanization == 1) {
			return Component.translatable("item.caerula_arbor.language_key.description_11").getString();
		}
		return Component.translatable("item.caerula_arbor.language_key.description_10").getString();
	}
}
