package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrSwordProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.hand_sword").getString() + ": " + Component.translatable("item.caerula_arbor.hand_sword.description_0").getString();
	}
}
