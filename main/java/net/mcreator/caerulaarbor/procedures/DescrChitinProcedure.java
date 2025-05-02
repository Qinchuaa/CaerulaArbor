package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrChitinProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.chitin_knife").getString() + ": " + Component.translatable("item.caerula_arbor.chitin_knife.description_0").getString();
	}
}
