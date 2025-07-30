package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.block.PoolOfProcreationBlock;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

@SuppressWarnings({"ConstantConditions", "SameParameterValue"})
public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CaerulaArborMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(ModBlocks.SCREAMING_CHERRY.get(), new ModelFile.UncheckedModelFile(modLoc("block/screaming_cherry")));

        multiStateBlock(ModBlocks.POOL_OF_PROCREATION.get(), state -> {
            if (state.getValue(PoolOfProcreationBlock.POWERED)) {
                return models().withExistingParent(name(ModBlocks.POOL_OF_PROCREATION.get()), modLoc("block/base/pool_of_procreation"))
                        .texture("0", modLoc("block/pool_of_procreation"))
                        .texture("particle", modLoc("block/pool_of_procreation"))
                        .renderType("cutout");
            } else {
                return models().withExistingParent(name(ModBlocks.POOL_OF_PROCREATION.get()) + "_dim", modLoc("block/base/pool_of_procreation"))
                        .texture("0", modLoc("block/pool_of_procreation_dim"))
                        .texture("particle", modLoc("block/pool_of_procreation_dim"))
                        .renderType("cutout");
            }
        });
        simpleBlock(ModBlocks.NOURISHED_POOL_OF_PROCREATION.get(),
                models().withExistingParent(name(ModBlocks.NOURISHED_POOL_OF_PROCREATION.get()), modLoc("block/base/pool_of_procreation"))
                        .texture("0", modLoc("block/nourished_pool_of_procreation"))
                        .texture("particle", modLoc("block/nourished_pool_of_procreation"))
                        .renderType("cutout")
        );
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(CaerulaArborMod.MODID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void multiStateBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(modelFunc.apply(state))
                        .build()
                );
    }
}
