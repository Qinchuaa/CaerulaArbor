package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, CaerulaArborMod.MODID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, CaerulaArborMod.MODID);

    public static final RegistryObject<PoiType> CANNOT_GOODENOUGH_POI = POI_TYPES.register("cannot_goodenough",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.BLOCK_RECORDER.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<VillagerProfession> CANNOT_GOODENOUGH = PROFESSIONS.register("cannot_goodenough",
            () -> new VillagerProfession("cannot_goodenough", holder -> holder.get() == CANNOT_GOODENOUGH_POI.get(), holder -> holder.get() == CANNOT_GOODENOUGH_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        PROFESSIONS.register(eventBus);
    }
}
