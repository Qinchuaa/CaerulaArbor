package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.block.entity.OvaryBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CaerulaArborMod.MODID);

    public static final RegistryObject<BlockEntityType<OvaryBlockEntity>> OVARY = BLOCK_ENTITIES.register("ovary", () -> BlockEntityType.Builder.of(OvaryBlockEntity::new, ModBlocks.OCEAN_OVARY.get(), ModBlocks.RED_OVARY.get()).build(null));
}
