package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrArmorProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.kings_armour").getString() + ": " + Component.translatable("item.caerula_arbor.kings_armour.description_0").getString();
	}
}
