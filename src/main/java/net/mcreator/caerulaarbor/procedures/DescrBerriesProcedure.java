package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrBerriesProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.canned_cherry").getString() + ": " + Component.translatable("item.caerula_arbor.canned_cherry.description_0").getString();
	}
}
