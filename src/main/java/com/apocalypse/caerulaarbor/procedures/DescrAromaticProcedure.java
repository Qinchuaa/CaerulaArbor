package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrAromaticProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.aromatic_coffee").getString() + ": " + Component.translatable("item.caerula_arbor.aromatic_coffee.description_0").getString();
	}
}
