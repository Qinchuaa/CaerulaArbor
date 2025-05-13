package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrScoreProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.score").getString() + ": " + Component.translatable("item.caerula_arbor.score.description_0").getString();
	}
}
