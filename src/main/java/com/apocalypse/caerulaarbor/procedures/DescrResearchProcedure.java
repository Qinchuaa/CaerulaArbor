package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrResearchProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.relic_cursed_research").getString() + ": " + Component.translatable("item.caerula_arbor.relic_cursed_research.description_0").getString();
	}
}
