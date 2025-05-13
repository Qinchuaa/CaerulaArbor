package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrOmnikeyProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.omni_key").getString() + ": " + Component.translatable("item.caerula_arbor.omni_key.description_0").getString();
	}
}
