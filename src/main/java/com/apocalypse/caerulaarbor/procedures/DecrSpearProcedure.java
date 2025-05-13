package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DecrSpearProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.kings_spear").getString() + ": " + Component.translatable("item.caerula_arbor.kings_spear.description_0").getString();
	}
}
