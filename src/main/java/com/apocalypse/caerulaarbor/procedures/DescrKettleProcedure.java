package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrKettleProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.kettle").getString() + ": " + Component.translatable("item.caerula_arbor.kettle.description_0").getString();
	}
}
