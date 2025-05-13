package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrLongevityProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.proof_of_longevity").getString() + ": " + Component.translatable("item.caerula_arbor.proof_of_longevity.des_1").getString();
	}
}
