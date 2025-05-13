
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.apocalypse.caerulaarbor.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import com.apocalypse.caerulaarbor.client.renderer.UmbrellaAbyssalRenderer;
import com.apocalypse.caerulaarbor.client.renderer.SuperSliderRenderer;
import com.apocalypse.caerulaarbor.client.renderer.SplasherAbyssalRenderer;
import com.apocalypse.caerulaarbor.client.renderer.SliderFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.ShooterFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.RunFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.RouteShaperRenderer;
import com.apocalypse.caerulaarbor.client.renderer.RouteFractalRenderer;
import com.apocalypse.caerulaarbor.client.renderer.ReaperFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.PunctureFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.PregnantFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.PredatorAbyssalRenderer;
import com.apocalypse.caerulaarbor.client.renderer.GuideAbyssalRenderer;
import com.apocalypse.caerulaarbor.client.renderer.FlyFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.FleefishBulletRenderer;
import com.apocalypse.caerulaarbor.client.renderer.FleeFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.FishSplashRenderer;
import com.apocalypse.caerulaarbor.client.renderer.FishShootRenderer;
import com.apocalypse.caerulaarbor.client.renderer.FakerggShootRenderer;
import com.apocalypse.caerulaarbor.client.renderer.FakeOffspringRenderer;
import com.apocalypse.caerulaarbor.client.renderer.CreeperFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.CrackerAbyssalRenderer;
import com.apocalypse.caerulaarbor.client.renderer.CollectorProkaryoteRenderer;
import com.apocalypse.caerulaarbor.client.renderer.ChiselerFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.BoneFishRenderer;
import com.apocalypse.caerulaarbor.client.renderer.BaselayerAbyssalRenderer;

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
		event.registerEntityRenderer(CaerulaArborModEntities.CRACKER_ABYSSAL.get(), CrackerAbyssalRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.COLLECTOR_PROKARYOTE.get(), CollectorProkaryoteRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.BONE_FISH.get(), BoneFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.CHISELER_FISH.get(), ChiselerFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.FAKERGG_SHOOT.get(), FakerggShootRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.PREGNANT_FISH.get(), PregnantFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.FAKE_OFFSPRING.get(), FakeOffspringRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.FLEEFISH_BULLET.get(), FleefishBulletRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.FLEE_FISH.get(), FleeFishRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.ROUTE_SHAPER.get(), RouteShaperRenderer::new);
		event.registerEntityRenderer(CaerulaArborModEntities.ROUTE_FRACTAL.get(), RouteFractalRenderer::new);
	}
}
