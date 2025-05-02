
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

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

import net.mcreator.caerulaarbor.entity.UmbrellaAbyssalEntity;
import net.mcreator.caerulaarbor.entity.SuperSliderEntity;
import net.mcreator.caerulaarbor.entity.SplasherAbyssalEntity;
import net.mcreator.caerulaarbor.entity.SliderFishEntity;
import net.mcreator.caerulaarbor.entity.ShooterFishEntity;
import net.mcreator.caerulaarbor.entity.RunFishEntity;
import net.mcreator.caerulaarbor.entity.ReaperFishEntity;
import net.mcreator.caerulaarbor.entity.PunctureFishEntity;
import net.mcreator.caerulaarbor.entity.PredatorAbyssalEntity;
import net.mcreator.caerulaarbor.entity.GuideAbyssalEntity;
import net.mcreator.caerulaarbor.entity.FlyFishEntity;
import net.mcreator.caerulaarbor.entity.FishSplashEntity;
import net.mcreator.caerulaarbor.entity.FishShootEntity;
import net.mcreator.caerulaarbor.entity.CreeperFishEntity;
import net.mcreator.caerulaarbor.entity.BaselayerAbyssalEntity;
import net.mcreator.caerulaarbor.CaerulaArborMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CaerulaArborModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CaerulaArborMod.MODID);
	public static final RegistryObject<EntityType<RunFishEntity>> RUN_FISH = register("run_fish",
			EntityType.Builder.<RunFishEntity>of(RunFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(RunFishEntity::new)

					.sized(0.4f, 0.5f));
	public static final RegistryObject<EntityType<SliderFishEntity>> SLIDER_FISH = register("slider_fish",
			EntityType.Builder.<SliderFishEntity>of(SliderFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SliderFishEntity::new)

					.sized(0.4f, 0.8f));
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

					.sized(0.6f, 0.6f));
	public static final RegistryObject<EntityType<ReaperFishEntity>> REAPER_FISH = register("reaper_fish",
			EntityType.Builder.<ReaperFishEntity>of(ReaperFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ReaperFishEntity::new)

					.sized(1.2f, 3f));
	public static final RegistryObject<EntityType<CreeperFishEntity>> CREEPER_FISH = register("creeper_fish",
			EntityType.Builder.<CreeperFishEntity>of(CreeperFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CreeperFishEntity::new)

					.sized(0.8f, 1.5f));
	public static final RegistryObject<EntityType<PunctureFishEntity>> PUNCTURE_FISH = register("puncture_fish",
			EntityType.Builder.<PunctureFishEntity>of(PunctureFishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PunctureFishEntity::new)

					.sized(0.9f, 2.5f));
	public static final RegistryObject<EntityType<BaselayerAbyssalEntity>> BASELAYER_ABYSSAL = register("baselayer_abyssal",
			EntityType.Builder.<BaselayerAbyssalEntity>of(BaselayerAbyssalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BaselayerAbyssalEntity::new)

					.sized(0.6f, 1.5f));
	public static final RegistryObject<EntityType<PredatorAbyssalEntity>> PREDATOR_ABYSSAL = register("predator_abyssal",
			EntityType.Builder.<PredatorAbyssalEntity>of(PredatorAbyssalEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PredatorAbyssalEntity::new)

					.sized(0.5f, 1.4f));
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
	}
}
