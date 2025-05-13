package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrMeatcanProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.meat_can").getString() + ": " + Component.translatable("item.caerula_arbor.meat_can.description_0").getString();
	}
}
