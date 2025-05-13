package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrHandFirewprkProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.hand_of_firework").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_firework.description_0").getString();
	}
}
