package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrhandSpikeProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.hand_of_thorns").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_thorns.description_0").getString();
	}
}
