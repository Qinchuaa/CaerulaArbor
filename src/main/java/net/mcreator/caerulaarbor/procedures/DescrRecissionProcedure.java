package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrRecissionProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.rescission").getString() + ": " + Component.translatable("item.caerula_arbor.rescission.description_0").getString();
	}
}
