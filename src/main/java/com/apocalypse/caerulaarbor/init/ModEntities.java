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
    public static final RegistryObject<EntityType<PocketSeaCrawlerEntity>> POCKET_SEA_CRAWLER = register("pocket_sea_crawler",
            EntityType.Builder.<PocketSeaCrawlerEntity>of(PocketSeaCrawlerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PocketSeaCrawlerEntity::new)
                    .sized(0.8f, 1.5f));
    public static final RegistryObject<EntityType<PrimalSeaPiercerEntity>> PRIMAL_SEA_PIERCER = register("primal_sea_piercer",
            EntityType.Builder.<PrimalSeaPiercerEntity>of(PrimalSeaPiercerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PrimalSeaPiercerEntity::new)
                    .sized(0.9f, 2.7f));
    public static final RegistryObject<EntityType<NetherseaFounderEntity>> NETHERSEA_FOUNDER = register("nethersea_founder",
            EntityType.Builder.<NetherseaFounderEntity>of(NetherseaFounderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NetherseaFounderEntity::new)
                    .sized(0.7f, 1.5f));
    public static final RegistryObject<EntityType<NetherseaPredatorEntity>> NETHERSEA_PREDATOR = register("nethersea_predator",
            EntityType.Builder.<NetherseaPredatorEntity>of(NetherseaPredatorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NetherseaPredatorEntity::new)
                    .sized(0.6f, 1.4f));
    public static final RegistryObject<EntityType<NetherseaBrandguiderEntity>> NETHERSEA_BRANDGUIDER = register("nethersea_brandguider",
            EntityType.Builder.<NetherseaBrandguiderEntity>of(NetherseaBrandguiderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NetherseaBrandguiderEntity::new)
                    .sized(0.88f, 2.2f));
    public static final RegistryObject<EntityType<NetherseaSpewerEntity>> NETHERSEA_SPEWER = register("nethersea_spewer",
            EntityType.Builder.<NetherseaSpewerEntity>of(NetherseaSpewerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NetherseaSpewerEntity::new)
                    .sized(0.6f, 1.1f));
    public static final RegistryObject<EntityType<NetherseaSwarmcallerEntity>> NETHERSEA_SWARMCALLER = register("nethersea_swarmcaller",
            EntityType.Builder.<NetherseaSwarmcallerEntity>of(NetherseaSwarmcallerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NetherseaSwarmcallerEntity::new)
                    .sized(0.8f, 1.5f));
    public static final RegistryObject<EntityType<NetherseaReefbreakerEntity>> NETHERSEA_REEFBREAKER = register("nethersea_reefbreaker",
            EntityType.Builder.<NetherseaReefbreakerEntity>of(NetherseaReefbreakerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NetherseaReefbreakerEntity::new)
                    .sized(0.7f, 1.8f));
    public static final RegistryObject<EntityType<UnicellularPredatorEntity>> UNICELLULAR_PREDATOR = register("unicellular_predator",
            EntityType.Builder.<UnicellularPredatorEntity>of(UnicellularPredatorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(UnicellularPredatorEntity::new)
                    .sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<BoneSeaDrifterEntity>> BONE_SEA_DRIFTER = register("bone_sea_drifter",
            EntityType.Builder.<BoneSeaDrifterEntity>of(BoneSeaDrifterEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(24).setUpdateInterval(3).setCustomClientFactory(BoneSeaDrifterEntity::new)
                    .sized(0.7f, 0.7f));
    public static final RegistryObject<EntityType<OceanStonecutterEntity>> OCEAN_STONECUTTER = register("ocean_stonecutter",
            EntityType.Builder.<OceanStonecutterEntity>of(OceanStonecutterEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(OceanStonecutterEntity::new)
                    .sized(0.6f, 0.6f));
    public static final RegistryObject<EntityType<RetchingBroodmotherEntity>> RETCHING_BROODMOTHER = register("retching_broodmother",
            EntityType.Builder.<RetchingBroodmotherEntity>of(RetchingBroodmotherEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(RetchingBroodmotherEntity::new)
                    .sized(0.7f, 1.1f));
    public static final RegistryObject<EntityType<BalefulBroodlingEntity>> BALEFUL_BROODLING = register("baleful_broodling",
            EntityType.Builder.<BalefulBroodlingEntity>of(BalefulBroodlingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BalefulBroodlingEntity::new)
                    .sized(0.6f, 0.6f));
    public static final RegistryObject<EntityType<SkimmingSeaDrifterEntity>> SKIMMING_SEA_DRIFTER = register("skimming_sea_drifter",
            EntityType.Builder.<SkimmingSeaDrifterEntity>of(SkimmingSeaDrifterEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SkimmingSeaDrifterEntity::new)
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
    public static final RegistryObject<EntityType<FishSplashEntity>> FISH_SPLASH = register("fish_splash",
            EntityType.Builder.<FishSplashEntity>of(FishSplashEntity::new, MobCategory.MISC).setCustomClientFactory(FishSplashEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.3f, 0.3f));
    public static final RegistryObject<EntityType<FakerggShootEntity>> FAKERGG_SHOOT = register("fakergg_shoot",
            EntityType.Builder.<FakerggShootEntity>of(FakerggShootEntity::new, MobCategory.MISC).setCustomClientFactory(FakerggShootEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.4f, 0.4f));
    public static final RegistryObject<EntityType<FleefishBulletEntity>> FLEEFISH_BULLET = register("fleefish_bullet",
            EntityType.Builder.<FleefishBulletEntity>of(FleefishBulletEntity::new, MobCategory.MISC).setCustomClientFactory(FleefishBulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.4f, 0.4f));

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
            PocketSeaCrawlerEntity.init();
            PrimalSeaPiercerEntity.init();
            NetherseaFounderEntity.init();
            NetherseaPredatorEntity.init();
            NetherseaBrandguiderEntity.init();
            NetherseaSpewerEntity.init();
            NetherseaSwarmcallerEntity.init();
            NetherseaReefbreakerEntity.init();
            UnicellularPredatorEntity.init();
            BoneSeaDrifterEntity.init();
            OceanStonecutterEntity.init();
            RetchingBroodmotherEntity.init();
            SkimmingSeaDrifterEntity.init();
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
        event.put(POCKET_SEA_CRAWLER.get(), PocketSeaCrawlerEntity.createAttributes().build());
        event.put(PRIMAL_SEA_PIERCER.get(), PrimalSeaPiercerEntity.createAttributes().build());
        event.put(NETHERSEA_FOUNDER.get(), NetherseaFounderEntity.createAttributes().build());
        event.put(NETHERSEA_PREDATOR.get(), NetherseaPredatorEntity.createAttributes().build());
        event.put(NETHERSEA_BRANDGUIDER.get(), NetherseaBrandguiderEntity.createAttributes().build());
        event.put(NETHERSEA_SPEWER.get(), NetherseaSpewerEntity.createAttributes().build());
        event.put(NETHERSEA_SWARMCALLER.get(), NetherseaSwarmcallerEntity.createAttributes().build());
        event.put(NETHERSEA_REEFBREAKER.get(), NetherseaReefbreakerEntity.createAttributes().build());
        event.put(UNICELLULAR_PREDATOR.get(), UnicellularPredatorEntity.createAttributes().build());
        event.put(BONE_SEA_DRIFTER.get(), BoneSeaDrifterEntity.createAttributes().build());
        event.put(OCEAN_STONECUTTER.get(), OceanStonecutterEntity.createAttributes().build());
        event.put(RETCHING_BROODMOTHER.get(), RetchingBroodmotherEntity.createAttributes().build());
        event.put(BALEFUL_BROODLING.get(), BalefulBroodlingEntity.createAttributes().build());
        event.put(SKIMMING_SEA_DRIFTER.get(), SkimmingSeaDrifterEntity.createAttributes().build());
        event.put(ROUTE_SHAPER.get(), RouteShaperEntity.createAttributes().build());
        event.put(ROUTE_FRACTAL.get(), RouteFractalEntity.createAttributes().build());
    }
}
