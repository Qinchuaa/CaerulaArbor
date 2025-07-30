package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.block.entity.PoolOfProcreationBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CaerulaArborMod.MODID);

    public static final RegistryObject<BlockEntityType<PoolOfProcreationBlockEntity>> POOL_OF_PROCREATION =
            BLOCK_ENTITIES.register("pool_of_procreation",
                    () -> BlockEntityType.Builder.of(PoolOfProcreationBlockEntity::new, ModBlocks.POOL_OF_PROCREATION.get(), ModBlocks.NOURISHED_POOL_OF_PROCREATION.get()).build(null));
}
