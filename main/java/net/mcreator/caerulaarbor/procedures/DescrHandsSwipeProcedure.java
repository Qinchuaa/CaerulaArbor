package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrHandsSwipeProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.hand_of_spotless").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_spotless.description_0").getString();
	}
}
