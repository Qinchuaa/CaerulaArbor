
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.CaerulaArborMod;

public class CaerulaArborModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CaerulaArborMod.MODID);
	public static final RegistryObject<SoundEvent> TARGET_DAMAGED = REGISTRY.register("target_damaged", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "target_damaged")));
	public static final RegistryObject<SoundEvent> PCEANWISH = REGISTRY.register("pceanwish", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "pceanwish")));
	public static final RegistryObject<SoundEvent> FLUTESONG = REGISTRY.register("flutesong", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "flutesong")));
	public static final RegistryObject<SoundEvent> BLOODYWOLF_OPENMOUTH = REGISTRY.register("bloodywolf_openmouth", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "bloodywolf_openmouth")));
	public static final RegistryObject<SoundEvent> GROW1 = REGISTRY.register("grow1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "grow1")));
	public static final RegistryObject<SoundEvent> GROW2 = REGISTRY.register("grow2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "grow2")));
	public static final RegistryObject<SoundEvent> SUBSISTING1 = REGISTRY.register("subsisting1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "subsisting1")));
	public static final RegistryObject<SoundEvent> SUBSISTING2 = REGISTRY.register("subsisting2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "subsisting2")));
	public static final RegistryObject<SoundEvent> BREED1 = REGISTRY.register("breed1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "breed1")));
	public static final RegistryObject<SoundEvent> BREED2 = REGISTRY.register("breed2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "breed2")));
	public static final RegistryObject<SoundEvent> MIGRATION1 = REGISTRY.register("migration1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "migration1")));
	public static final RegistryObject<SoundEvent> MIGRATION2 = REGISTRY.register("migration2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "migration2")));
	public static final RegistryObject<SoundEvent> SILENCE1 = REGISTRY.register("silence1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "silence1")));
	public static final RegistryObject<SoundEvent> SILENCE2 = REGISTRY.register("silence2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "silence2")));
	public static final RegistryObject<SoundEvent> SILENCE3 = REGISTRY.register("silence3", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "silence3")));
	public static final RegistryObject<SoundEvent> SILENCE4 = REGISTRY.register("silence4", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("caerula_arbor", "silence4")));
}
