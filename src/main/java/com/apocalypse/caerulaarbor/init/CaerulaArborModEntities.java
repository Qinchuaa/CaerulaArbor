
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.entity.UmbrellaAbyssalEntity;
import com.apocalypse.caerulaarbor.entity.SuperSliderEntity;
import com.apocalypse.caerulaarbor.entity.SplasherAbyssalEntity;
import com.apocalypse.caerulaarbor.entity.SliderFishEntity;
import com.apocalypse.caerulaarbor.entity.ShooterFishEntity;
import com.apocalypse.caerulaarbor.entity.RunFishEntity;
import com.apocalypse.caerulaarbor.entity.RouteShaperEntity;
import com.apocalypse.caerulaarbor.entity.RouteFractalEntity;
import com.apocalypse.caerulaarbor.entity.ReaperFishEntity;
import com.apocalypse.caerulaarbor.entity.PunctureFishEntity;
import com.apocalypse.caerulaarbor.entity.PregnantFishEntity;
import com.apocalypse.caerulaarbor.entity.PredatorAbyssalEntity;
import com.apocalypse.caerulaarbor.entity.GuideAbyssalEntity;
import com.apocalypse.caerulaarbor.entity.FlyFishEntity;
import com.apocalypse.caerulaarbor.entity.FleefishBulletEntity;
import com.apocalypse.caerulaarbor.entity.FleeFishEntity;
import com.apocalypse.caerulaarbor.entity.FishSplashEntity;
import com.apocalypse.caerulaarbor.entity.FishShootEntity;
import com.apocalypse.caerulaarbor.entity.FakerggShootEntity;
import com.apocalypse.caerulaarbor.entity.FakeOffspringEntity;
import com.apocalypse.caerulaarbor.entity.CreeperFishEntity;
import com.apocalypse.caerulaarbor.entity.CrackerAbyssalEntity;
import com.apocalypse.caerulaarbor.entity.CollectorProkaryoteEntity;
import com.apocalypse.caerulaarbor.entity.ChiselerFishEntity;
import com.apocalypse.caerulaarbor.entity.BoneFishEntity;
import com.apocalypse.caerulaarbor.entity.BaselayerAbyssalEntity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CaerulaArborModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CaerulaArborMod.MODID);
	public static final RegistryObject<EntityType<RunFishEntity>> RUN_FISH = register("run_fish",
			EntityType.Builder.<RunFishEntity>of(RunFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(RunFishEntity::new)

					.sized(0.4f, 0.5f));
	public static final RegistryObject<EntityType<SliderFishEntity>> SLIDER_FISH = register("slider_fish",
			EntityType.Builder.<SliderFishEntity>of(SliderFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SliderFishEntity::new)

					.sized(0.5f, 0.8f));
	public static final RegistryObject<EntityType<SuperSliderEntity>> SUPER_SLIDER = register("super_slider",
			EntityType.Builder.<SuperSliderEntity>of(SuperSliderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SuperSliderEntity::new)

					.sized(0.3f, 0.8f));
	public static final RegistryObject<EntityType<ShooterFishEntity>> SHOOTER_FISH = register("shooter_fish",
			EntityType.Builder.<ShooterFishEntity>of(ShooterFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ShooterFishEntity::new)

					.sized(0.6f, 1.2f));
	public static final RegistryObject<EntityType<FishShootEntity>> FISH_SHOOT = register("fish_shoot",
			EntityType.Builder.<FishShootEntity>of(FishShootEntity::new, MobCategory.MISC).setCustomClientFactory(FishShootEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.3f, 0.3f));
	public static final RegistryObject<EntityType<FlyFishEntity>> FLY_FISH = register("fly_fish",
			EntityType.Builder.<FlyFishEntity>of(FlyFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(FlyFishEntity::new)

					.sized(0.6f, 0.9f));
	public static final RegistryObject<EntityType<ReaperFishEntity>> REAPER_FISH = register("reaper_fish",
			EntityType.Builder.<ReaperFishEntity>of(ReaperFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ReaperFishEntity::new)

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

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			RunFishEntity.init();
			SliderFishEntity.init();
			SuperSliderEntity.init();
			ShooterFishEntity.init();
			FlyFishEntity.init();
			ReaperFishEntity.init();
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
		event.put(RUN_FISH.get(), RunFishEntity.createAttributes().build());
		event.put(SLIDER_FISH.get(), SliderFishEntity.createAttributes().build());
		event.put(SUPER_SLIDER.get(), SuperSliderEntity.createAttributes().build());
		event.put(SHOOTER_FISH.get(), ShooterFishEntity.createAttributes().build());
		event.put(FLY_FISH.get(), FlyFishEntity.createAttributes().build());
		event.put(REAPER_FISH.get(), ReaperFishEntity.createAttributes().build());
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
