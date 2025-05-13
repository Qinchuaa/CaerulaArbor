package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrHeartProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.caerula_heart").getString() + ": " + Component.translatable("item.caerula_arbor.caerula_heart.description_0").getString();
	}
}
