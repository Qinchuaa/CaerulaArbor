package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrSurvcontraProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.survivor_contract").getString() + ": " + Component.translatable("item.caerula_arbor.survivor_contract.description_0").getString();
	}
}
