package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrBedProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.archfiends_bed").getString() + ": " + Component.translatable("item.caerula_arbor.archfiends_bed.description_0").getString();
	}
}
