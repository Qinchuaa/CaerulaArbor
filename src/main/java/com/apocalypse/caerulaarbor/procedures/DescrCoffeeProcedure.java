package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrCoffeeProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.coffee_candy").getString() + ": " + Component.translatable("item.caerula_arbor.coffee_candy.description_0").getString();
	}
}
