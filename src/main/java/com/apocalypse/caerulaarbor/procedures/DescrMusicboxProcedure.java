package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.network.chat.Component;

public class DescrMusicboxProcedure {
	public static String execute() {
		return Component.translatable("item.caerula_arbor.solo_music_box").getString() + ": " + Component.translatable("item.caerula_arbor.solo_music_box.description_0").getString();
	}
}
