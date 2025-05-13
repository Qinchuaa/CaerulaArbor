package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrHandSpeedProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.hand_of_speed").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_speed.description_0").getString();
	}
}
