package net.mcreator.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrFlagProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.archfiends_flag").getString() + ": " + Component.translatable("item.caerula_arbor.archfiends_flag.description_0").getString();
	}
}
