package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrHandBarrenProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.hand_of_barren").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_barren.description_0").getString();
	}
}
