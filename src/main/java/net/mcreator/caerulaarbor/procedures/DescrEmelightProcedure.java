package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrEmelightProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.relic_curse_emelight").getString() + ": " + Component.translatable("item.caerula_arbor.relic_curse_emelight.description_0").getString();
	}
}
