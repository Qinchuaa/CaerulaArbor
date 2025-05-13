package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrArtifactProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.archfiends_artifact").getString() + ": " + Component.translatable("item.caerula_arbor.archfiends_artifact.description_0").getString();
	}
}
