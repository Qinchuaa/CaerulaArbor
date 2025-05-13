package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrFluteProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.odd_flute").getString() + ": " + Component.translatable("item.caerula_arbor.odd_flute.description_0").getString();
	}
}
