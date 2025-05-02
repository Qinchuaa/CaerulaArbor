package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DecrCrystalProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.kings_crystal").getString() + ": " + Component.translatable("item.caerula_arbor.kings_crystal.description_0").getString();
	}
}
