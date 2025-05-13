package com.apocalypse.caerulaarbor.procedures;

import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.api.distmarker.Dist;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

import javax.annotation.Nullable;

import java.io.IOException;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.DEDICATED_SERVER})
public class FileHandlerProcedure {
	@SubscribeEvent
	public static void init(FMLDedicatedServerSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		try {
			CaerulaArborModVariables.crowd_will.getParentFile().mkdirs();
			CaerulaArborModVariables.crowd_will.createNewFile();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
