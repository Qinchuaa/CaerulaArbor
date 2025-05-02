package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrCromcontraProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.crimson_treaty").getString() + ": " + Component.translatable("item.caerula_arbor.crimson_treaty.description_0").getString();
	}
}
