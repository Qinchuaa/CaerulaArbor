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
        event.registerEntityRenderer(ModEntities.RIDGE_SEA_SPITTER.get(), RidgeSeaSpitterRenderer::new);
        event.registerEntityRenderer(ModEntities.FISH_SHOOT.get(), FishShootRenderer::new);
        event.registerEntityRenderer(ModEntities.FLOATING_SEA_DRIFTER.get(), FloatingSeaDrifterRenderer::new);
        event.registerEntityRenderer(ModEntities.BASIN_SEA_REAPER.get(), BasinSeaReaperRenderer::new);
        event.registerEntityRenderer(ModEntities.POCKET_SEA_CRAWLER.get(), PocketSeaCrawlerRenderer::new);
        event.registerEntityRenderer(ModEntities.PRIMAL_SEA_PIERCER.get(), PrimalSeaPiercerRenderer::new);
        event.registerEntityRenderer(ModEntities.NETHERSEA_FOUNDER.get(), NetherseaFounderRenderer::new);
        event.registerEntityRenderer(ModEntities.NETHERSEA_PREDATOR.get(), NetherseaPredatorRenderer::new);
        event.registerEntityRenderer(ModEntities.NETHERSEA_BRANDGUIDER.get(), NetherseaBrandguiderRenderer::new);
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
