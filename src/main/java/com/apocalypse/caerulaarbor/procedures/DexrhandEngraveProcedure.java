package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DexrhandEngraveProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.hand_of_engrave").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_engrave.description_0").getString();
	}
}
