package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrIrisProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.redstone_iris_flower").getString() + ": " + Component.translatable("item.caerula_arbor.redstone_iris_flower.description_0").getString();
	}
}
