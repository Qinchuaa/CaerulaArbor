package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrSeagrassProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.bowl_seagrass").getString() + ": " + Component.translatable("item.caerula_arbor.bowl_seagrass.description_0").getString();
	}
}
