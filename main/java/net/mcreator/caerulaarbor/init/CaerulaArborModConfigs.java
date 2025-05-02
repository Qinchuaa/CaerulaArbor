package net.mcreator.caerulaarbor.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.mcreator.caerulaarbor.configuration.CaerulaConfigsConfiguration;
import net.mcreator.caerulaarbor.CaerulaArborMod;

@Mod.EventBusSubscriber(modid = CaerulaArborMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CaerulaArborModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CaerulaConfigsConfiguration.SPEC, "caerular_configs.toml");
		});
	}
}
