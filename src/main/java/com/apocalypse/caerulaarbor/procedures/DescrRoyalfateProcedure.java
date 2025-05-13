package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrRoyalfateProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.royal_fate").getString() + ": " + Component.translatable("item.caerula_arbor.royal_fate.description_0").getString();
	}
}
