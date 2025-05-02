package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrAllayProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.allay_sculpture").getString() + ": " + Component.translatable("item.caerula_arbor.allay_sculpture.description_0").getString();
	}
}
