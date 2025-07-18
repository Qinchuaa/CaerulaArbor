package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.init.ModEntities;
import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

public class ModEntityLootProvider extends EntityLootSubProvider {

    public ModEntityLootProvider() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(ModEntities.SHELL_SEA_RUNNER.get(),
                LootTable.lootTable().withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(ModItems.BONE_SHARD.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                )
        );
        this.add(ModEntities.DEEP_SEA_SLIDER.get(),
                LootTable.lootTable().withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(ModItems.OCEAN_PHLOEM.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .setWeight(70)
                                ).add(LootItem.lootTableItem(ModItems.OCEAN_PEDUNCLE.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                        .setWeight(30)
                                )
                )
        );
        this.add(ModEntities.SUPER_SLIDER.get(),
                LootTable.lootTable().withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(ModItems.OCEAN_PEDUNCLE.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                )
        );
        this.add(ModEntities.RIDGE_SEA_SPITTER.get(),
                LootTable.lootTable().withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(ModItems.OCEAN_FIBRE.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                )
        );
        this.add(ModEntities.FLOATING_SEA_DRIFTER.get(),
                LootTable.lootTable().withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(ModItems.OCEAN_FIBRE.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                )
        );

        this.add(ModEntities.POCKET_SEA_CRAWLER.get(),
                LootTable.lootTable().withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(ModItems.OCEAN_CRYSTAL.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                )
        );
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
    }
}
