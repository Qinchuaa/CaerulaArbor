
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.caerulaarbor.client.renderer.UmbrellaAbyssalRenderer;
import net.mcreator.caerulaarbor.client.renderer.SuperSliderRenderer;
import net.mcreator.caerulaarbor.client.renderer.SplasherAbyssalRenderer;
import net.mcreator.caerulaarbor.client.renderer.SliderFishRenderer;
import net.mcreator.caerulaarbor.client.renderer.ShooterFishRenderer;
import net.mcreator.caerulaarbor.client.renderer.RunFishRenderer;
import net.mcreator.caerulaarbor.client.renderer.ReaperFishRenderer;
import net.mcreator.caerulaarbor.client.renderer.PunctureFishRenderer;
import net.mcreator.caerulaarbor.client.renderer.PredatorAbyssalRenderer;
import net.mcreator.caerulaarbor.client.renderer.GuideAbyssalRenderer;
import net.mcreator.caerulaarbor.client.renderer.FlyFishRenderer;
import net.mcreator.caerulaarbor.client.renderer.FishSplashRenderer;
import net.mcreator.caerulaarbor.client.renderer.FishShootRenderer;
import net.mcreator.caerulaarbor.client.renderer.CreeperFishRenderer;
import net.mcreator.caerulaarbor.client.renderer.BaselayerAbyssalRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CaerulaArborModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(CaerulaArborModEntities.RUN_FISH.get(), RunFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.SLIDER_FISH.get(), SliderFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.SUPER_SLIDER.get(), SuperSliderRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.SHOOTER_FISH.get(), ShooterFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.FISH_SHOOT.get(), FishShootRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.FLY_FISH.get(), FlyFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.REAPER_FISH.get(), ReaperFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.CREEPER_FISH.get(), CreeperFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.PUNCTURE_FISH.get(), PunctureFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.BASELAYER_ABYSSAL.get(), BaselayerAbyssalRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.PREDATOR_ABYSSAL.get(), PredatorAbyssalRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.GUIDE_ABYSSAL.get(), GuideAbyssalRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.SPLASHER_ABYSSAL.get(), SplasherAbyssalRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.FISH_SPLASH.get(), FishSplashRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.UMBRELLA_ABYSSAL.get(), UmbrellaAbyssalRenderer::new);
	}
}
