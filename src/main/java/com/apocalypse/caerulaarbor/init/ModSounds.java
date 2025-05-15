package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CaerulaArborMod.MODID);

    public static final RegistryObject<SoundEvent> TARGET_DAMAGED = REGISTRY.register("target_damaged", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("target_damaged")));
    public static final RegistryObject<SoundEvent> PCEANWISH = REGISTRY.register("pceanwish", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("pceanwish")));
    public static final RegistryObject<SoundEvent> FLUTESONG = REGISTRY.register("flutesong", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("flutesong")));
    public static final RegistryObject<SoundEvent> BLOODYWOLF_OPENMOUTH = REGISTRY.register("bloodywolf_openmouth", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("bloodywolf_openmouth")));
    public static final RegistryObject<SoundEvent> GROW1 = REGISTRY.register("grow1", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("grow1")));
    public static final RegistryObject<SoundEvent> GROW2 = REGISTRY.register("grow2", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("grow2")));
    public static final RegistryObject<SoundEvent> SUBSISTING1 = REGISTRY.register("subsisting1", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("subsisting1")));
    public static final RegistryObject<SoundEvent> SUBSISTING2 = REGISTRY.register("subsisting2", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("subsisting2")));
    public static final RegistryObject<SoundEvent> BREED1 = REGISTRY.register("breed1", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("breed1")));
    public static final RegistryObject<SoundEvent> BREED2 = REGISTRY.register("breed2", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("breed2")));
    public static final RegistryObject<SoundEvent> MIGRATION1 = REGISTRY.register("migration1", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("migration1")));
    public static final RegistryObject<SoundEvent> MIGRATION2 = REGISTRY.register("migration2", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("migration2")));
    public static final RegistryObject<SoundEvent> SILENCE1 = REGISTRY.register("silence1", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("silence1")));
    public static final RegistryObject<SoundEvent> SILENCE2 = REGISTRY.register("silence2", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("silence2")));
    public static final RegistryObject<SoundEvent> SILENCE3 = REGISTRY.register("silence3", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("silence3")));
    public static final RegistryObject<SoundEvent> SILENCE4 = REGISTRY.register("silence4", () -> SoundEvent.createVariableRangeEvent(CaerulaArborMod.loc("silence4")));
}
