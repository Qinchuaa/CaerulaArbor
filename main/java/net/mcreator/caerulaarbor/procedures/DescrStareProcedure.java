package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrStareProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.guardian_stare").getString() + ": " + Component.translatable("item.caerula_arbor.guardian_stare.description_0").getString();
	}
}
