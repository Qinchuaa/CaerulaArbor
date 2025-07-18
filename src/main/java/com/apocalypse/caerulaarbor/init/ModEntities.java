package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CaerulaArborMod.MODID);

    // Living Entities
    public static final RegistryObject<EntityType<ShellSeaRunnerEntity>> SHELL_SEA_RUNNER = register("shell_sea_runner",
            EntityType.Builder.<ShellSeaRunnerEntity>of(ShellSeaRunnerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ShellSeaRunnerEntity::new)
                    .sized(0.4f, 0.5f));
    public static final RegistryObject<EntityType<DeepSeaSliderEntity>> DEEP_SEA_SLIDER = register("deep_sea_slider",
            EntityType.Builder.<DeepSeaSliderEntity>of(DeepSeaSliderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(DeepSeaSliderEntity::new)
                    .sized(0.5f, 0.8f));
    public static final RegistryObject<EntityType<SuperSliderEntity>> SUPER_SLIDER = register("super_slider",
            EntityType.Builder.<SuperSliderEntity>of(SuperSliderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SuperSliderEntity::new)
                    .sized(1.8f, 4.8f));
    public static final RegistryObject<EntityType<RidgeSeaSpitterEntity>> RIDGE_SEA_SPITTER = register("ridge_sea_spitter",
            EntityType.Builder.<RidgeSeaSpitterEntity>of(RidgeSeaSpitterEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(RidgeSeaSpitterEntity::new)
                    .sized(0.6f, 1.2f));
    public static final RegistryObject<EntityType<FloatingSeaDrifterEntity>> FLOATING_SEA_DRIFTER = register("floating_sea_drifter",
            EntityType.Builder.<FloatingSeaDrifterEntity>of(FloatingSeaDrifterEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(FloatingSeaDrifterEntity::new)
                    .sized(0.6f, 0.9f));
    public static final RegistryObject<EntityType<BasinSeaReaperEntity>> BASIN_SEA_REAPER = register("basin_sea_reaper",
            EntityType.Builder.<BasinSeaReaperEntity>of(BasinSeaReaperEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BasinSeaReaperEntity::new)
                    .sized(1.2f, 2.8f));
    public static final RegistryObject<EntityType<CreeperFishEntity>> CREEPER_FISH = register("creeper_fish",
            EntityType.Builder.<CreeperFishEntity>of(CreeperFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CreeperFishEntity::new)
                    .sized(0.8f, 1.5f));
    public static final RegistryObject<EntityType<PunctureFishEntity>> PUNCTURE_FISH = register("puncture_fish",
            EntityType.Builder.<PunctureFishEntity>of(PunctureFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PunctureFishEntity::new)
                    .sized(0.9f, 2.7f));
    public static final RegistryObject<EntityType<BaselayerAbyssalEntity>> BASELAYER_ABYSSAL = register("baselayer_abyssal",
            EntityType.Builder.<BaselayerAbyssalEntity>of(BaselayerAbyssalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BaselayerAbyssalEntity::new)
                    .sized(0.7f, 1.5f));
    public static final RegistryObject<EntityType<PredatorAbyssalEntity>> PREDATOR_ABYSSAL = register("predator_abyssal",
            EntityType.Builder.<PredatorAbyssalEntity>of(PredatorAbyssalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PredatorAbyssalEntity::new)
                    .sized(0.6f, 1.4f));
    public static final RegistryObject<EntityType<GuideAbyssalEntity>> GUIDE_ABYSSAL = register("guide_abyssal",
            EntityType.Builder.<GuideAbyssalEntity>of(GuideAbyssalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(GuideAbyssalEntity::new)
                    .sized(0.8f, 2f));
    public static final RegistryObject<EntityType<SplasherAbyssalEntity>> SPLASHER_ABYSSAL = register("splasher_abyssal",
            EntityType.Builder.<SplasherAbyssalEntity>of(SplasherAbyssalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SplasherAbyssalEntity::new)
                    .sized(0.6f, 1.1f));
    public static final RegistryObject<EntityType<FishSplashEntity>> FISH_SPLASH = register("fish_splash",
            EntityType.Builder.<FishSplashEntity>of(FishSplashEntity::new, MobCategory.MISC).setCustomClientFactory(FishSplashEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.3f, 0.3f));
    public static final RegistryObject<EntityType<UmbrellaAbyssalEntity>> UMBRELLA_ABYSSAL = register("umbrella_abyssal",
            EntityType.Builder.<UmbrellaAbyssalEntity>of(UmbrellaAbyssalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(UmbrellaAbyssalEntity::new)
                    .sized(0.8f, 1.5f));
    public static final RegistryObject<EntityType<CrackerAbyssalEntity>> CRACKER_ABYSSAL = register("cracker_abyssal",
            EntityType.Builder.<CrackerAbyssalEntity>of(CrackerAbyssalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CrackerAbyssalEntity::new)
                    .sized(0.7f, 1.8f));
    public static final RegistryObject<EntityType<CollectorProkaryoteEntity>> COLLECTOR_PROKARYOTE = register("collector_prokaryote",
            EntityType.Builder.<CollectorProkaryoteEntity>of(CollectorProkaryoteEntity::new, MobCategory.WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3)
                    .setCustomClientFactory(CollectorProkaryoteEntity::new)
                    .sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<BoneFishEntity>> BONE_FISH = register("bone_fish",
            EntityType.Builder.<BoneFishEntity>of(BoneFishEntity::new, MobCategory.WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(24).setUpdateInterval(3).setCustomClientFactory(BoneFishEntity::new)
                    .sized(0.7f, 0.7f));
    public static final RegistryObject<EntityType<ChiselerFishEntity>> CHISELER_FISH = register("chiseler_fish",
            EntityType.Builder.<ChiselerFishEntity>of(ChiselerFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ChiselerFishEntity::new)
                    .sized(0.6f, 0.6f));
    public static final RegistryObject<EntityType<FakerggShootEntity>> FAKERGG_SHOOT = register("fakergg_shoot",
            EntityType.Builder.<FakerggShootEntity>of(FakerggShootEntity::new, MobCategory.MISC).setCustomClientFactory(FakerggShootEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.4f, 0.4f));
    public static final RegistryObject<EntityType<PregnantFishEntity>> PREGNANT_FISH = register("pregnant_fish",
            EntityType.Builder.<PregnantFishEntity>of(PregnantFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PregnantFishEntity::new)
                    .sized(0.7f, 1.1f));
    public static final RegistryObject<EntityType<FakeOffspringEntity>> FAKE_OFFSPRING = register("fake_offspring",
            EntityType.Builder.<FakeOffspringEntity>of(FakeOffspringEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(FakeOffspringEntity::new)
                    .sized(0.6f, 0.6f));
    public static final RegistryObject<EntityType<FleefishBulletEntity>> FLEEFISH_BULLET = register("fleefish_bullet",
            EntityType.Builder.<FleefishBulletEntity>of(FleefishBulletEntity::new, MobCategory.MISC).setCustomClientFactory(FleefishBulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.4f, 0.4f));
    public static final RegistryObject<EntityType<FleeFishEntity>> FLEE_FISH = register("flee_fish",
            EntityType.Builder.<FleeFishEntity>of(FleeFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(FleeFishEntity::new)
                    .sized(0.8f, 1.1f));
    public static final RegistryObject<EntityType<RouteShaperEntity>> ROUTE_SHAPER = register("route_shaper",
            EntityType.Builder.<RouteShaperEntity>of(RouteShaperEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(RouteShaperEntity::new)
                    .sized(1.8f, 4f));
    public static final RegistryObject<EntityType<RouteFractalEntity>> ROUTE_FRACTAL = register("route_fractal",
            EntityType.Builder.<RouteFractalEntity>of(RouteFractalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(RouteFractalEntity::new)
                    .sized(0.7f, 1.5f));

    // Projectiles
    public static final RegistryObject<EntityType<FishShootEntity>> FISH_SHOOT = register("fish_shoot",
            EntityType.Builder.<FishShootEntity>of(FishShootEntity::new, MobCategory.MISC).setCustomClientFactory(FishShootEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.3f, 0.3f));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITY_TYPES.register(name, () -> entityTypeBuilder.build(name));
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ShellSeaRunnerEntity.init();
            DeepSeaSliderEntity.init();
            RidgeSeaSpitterEntity.init();
            FloatingSeaDrifterEntity.init();
            BasinSeaReaperEntity.init();
            CreeperFishEntity.init();
            PunctureFishEntity.init();
            BaselayerAbyssalEntity.init();
            PredatorAbyssalEntity.init();
            GuideAbyssalEntity.init();
            SplasherAbyssalEntity.init();
            UmbrellaAbyssalEntity.init();
            CrackerAbyssalEntity.init();
            CollectorProkaryoteEntity.init();
            BoneFishEntity.init();
            ChiselerFishEntity.init();
            PregnantFishEntity.init();
            FakeOffspringEntity.init();
            FleeFishEntity.init();
            RouteShaperEntity.init();
            RouteFractalEntity.init();
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(SHELL_SEA_RUNNER.get(), ShellSeaRunnerEntity.createAttributes().build());
        event.put(DEEP_SEA_SLIDER.get(), DeepSeaSliderEntity.createAttributes().build());
        event.put(SUPER_SLIDER.get(), SuperSliderEntity.createAttributes().build());
        event.put(RIDGE_SEA_SPITTER.get(), RidgeSeaSpitterEntity.createAttributes().build());
        event.put(FLOATING_SEA_DRIFTER.get(), FloatingSeaDrifterEntity.createAttributes().build());
        event.put(BASIN_SEA_REAPER.get(), BasinSeaReaperEntity.createAttributes().build());
        event.put(CREEPER_FISH.get(), CreeperFishEntity.createAttributes().build());
        event.put(PUNCTURE_FISH.get(), PunctureFishEntity.createAttributes().build());
        event.put(BASELAYER_ABYSSAL.get(), BaselayerAbyssalEntity.createAttributes().build());
        event.put(PREDATOR_ABYSSAL.get(), PredatorAbyssalEntity.createAttributes().build());
        event.put(GUIDE_ABYSSAL.get(), GuideAbyssalEntity.createAttributes().build());
        event.put(SPLASHER_ABYSSAL.get(), SplasherAbyssalEntity.createAttributes().build());
        event.put(UMBRELLA_ABYSSAL.get(), UmbrellaAbyssalEntity.createAttributes().build());
        event.put(CRACKER_ABYSSAL.get(), CrackerAbyssalEntity.createAttributes().build());
        event.put(COLLECTOR_PROKARYOTE.get(), CollectorProkaryoteEntity.createAttributes().build());
        event.put(BONE_FISH.get(), BoneFishEntity.createAttributes().build());
        event.put(CHISELER_FISH.get(), ChiselerFishEntity.createAttributes().build());
        event.put(PREGNANT_FISH.get(), PregnantFishEntity.createAttributes().build());
        event.put(FAKE_OFFSPRING.get(), FakeOffspringEntity.createAttributes().build());
        event.put(FLEE_FISH.get(), FleeFishEntity.createAttributes().build());
        event.put(ROUTE_SHAPER.get(), RouteShaperEntity.createAttributes().build());
        event.put(ROUTE_FRACTAL.get(), RouteFractalEntity.createAttributes().build());
    }
}
