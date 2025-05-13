package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrDurinProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.piglin_diary").getString() + ": " + Component.translatable("item.caerula_arbor.piglin_diary.description_0").getString();
	}
}
