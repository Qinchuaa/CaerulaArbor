package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrHandfertilotyProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.hand_of_fertiliy").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_fertiliy.description_0").getString();
	}
}
