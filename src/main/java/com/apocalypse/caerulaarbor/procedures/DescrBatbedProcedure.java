package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrBatbedProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.bat_bed").getString() + ": " + Component.translatable("item.caerula_arbor.bat_bed.description_0").getString();
	}
}
