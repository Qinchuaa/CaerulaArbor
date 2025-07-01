package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.client.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderers {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SHELL_SEA_RUNNER.get(), ShellSeaRunnerRenderer::new);
        event.registerEntityRenderer(ModEntities.DEEP_SEA_SLIDER.get(), DeepSeaSliderRenderer::new);
        event.registerEntityRenderer(ModEntities.SUPER_SLIDER.get(), SuperSliderRenderer::new);
        event.registerEntityRenderer(ModEntities.SHOOTER_FISH.get(), ShooterFishRenderer::new);
        event.registerEntityRenderer(ModEntities.FISH_SHOOT.get(), FishShootRenderer::new);
        event.registerEntityRenderer(ModEntities.FLY_FISH.get(), FlyFishRenderer::new);
        event.registerEntityRenderer(ModEntities.REAPER_FISH.get(), ReaperFishRenderer::new);
        event.registerEntityRenderer(ModEntities.CREEPER_FISH.get(), CreeperFishRenderer::new);
        event.registerEntityRenderer(ModEntities.PUNCTURE_FISH.get(), PunctureFishRenderer::new);
        event.registerEntityRenderer(ModEntities.BASELAYER_ABYSSAL.get(), BaselayerAbyssalRenderer::new);
        event.registerEntityRenderer(ModEntities.PREDATOR_ABYSSAL.get(), PredatorAbyssalRenderer::new);
        event.registerEntityRenderer(ModEntities.GUIDE_ABYSSAL.get(), GuideAbyssalRenderer::new);
        event.registerEntityRenderer(ModEntities.SPLASHER_ABYSSAL.get(), SplasherAbyssalRenderer::new);
        event.registerEntityRenderer(ModEntities.FISH_SPLASH.get(), FishSplashRenderer::new);
        event.registerEntityRenderer(ModEntities.UMBRELLA_ABYSSAL.get(), UmbrellaAbyssalRenderer::new);
        event.registerEntityRenderer(ModEntities.CRACKER_ABYSSAL.get(), CrackerAbyssalRenderer::new);
        event.registerEntityRenderer(ModEntities.COLLECTOR_PROKARYOTE.get(), CollectorProkaryoteRenderer::new);
        event.registerEntityRenderer(ModEntities.BONE_FISH.get(), BoneFishRenderer::new);
        event.registerEntityRenderer(ModEntities.CHISELER_FISH.get(), ChiselerFishRenderer::new);
        event.registerEntityRenderer(ModEntities.FAKERGG_SHOOT.get(), FakerggShootRenderer::new);
        event.registerEntityRenderer(ModEntities.PREGNANT_FISH.get(), PregnantFishRenderer::new);
        event.registerEntityRenderer(ModEntities.FAKE_OFFSPRING.get(), FakeOffspringRenderer::new);
        event.registerEntityRenderer(ModEntities.FLEEFISH_BULLET.get(), FleefishBulletRenderer::new);
        event.registerEntityRenderer(ModEntities.FLEE_FISH.get(), FleeFishRenderer::new);
        event.registerEntityRenderer(ModEntities.ROUTE_SHAPER.get(), RouteShaperRenderer::new);
        event.registerEntityRenderer(ModEntities.ROUTE_FRACTAL.get(), RouteFractalRenderer::new);
    }
}
