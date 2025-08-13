package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.block.PoolOfProcreationBlock;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
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
        horizontalBlock(ModBlocks.TIDE_OBSERVATION_STATION.get(), new ModelFile.UncheckedModelFile(modLoc("block/tide_observation_station")));

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

        blockWithItem(ModBlocks.NETHERSEA_BRICKS);
        stairsBlock((StairBlock) ModBlocks.NETHERSEA_BRICK_STAIRS.get(), blockTexture(ModBlocks.NETHERSEA_BRICKS.get()));
        slabBlock((SlabBlock) ModBlocks.NETHERSEA_BRICK_SLAB.get(), blockTexture(ModBlocks.NETHERSEA_BRICKS.get()), CaerulaArborMod.loc("block/nethersea_bricks"));
        wallBlock((WallBlock) ModBlocks.NETHERSEA_BRICK_WALL.get(), blockTexture(ModBlocks.NETHERSEA_BRICKS.get()));
        blockWithItem(ModBlocks.NETHERSEA_TILES);

        blockWithItem(ModBlocks.SAL_VIENTO_SAND);
        simpleBlockWithItem(ModBlocks.SAL_VIENTO_SANDSTONE.get(), models().cubeBottomTop("sal_viento_sandstone", CaerulaArborMod.loc("block/sal_viento_sandstone"),
                CaerulaArborMod.loc("block/sal_viento_sandstone_bottom"), CaerulaArborMod.loc("block/sal_viento_sandstone_top")));
        stairsBlock((StairBlock) ModBlocks.SAL_VIENTO_SANDSTONE_STAIRS.get(), blockTexture(ModBlocks.SAL_VIENTO_SANDSTONE.get()), CaerulaArborMod.loc("block/sal_viento_sandstone_bottom"),
                CaerulaArborMod.loc("block/sal_viento_sandstone_top"));
        slabBlock((SlabBlock) ModBlocks.SAL_VIENTO_SANDSTONE_SLAB.get(), blockTexture(ModBlocks.SAL_VIENTO_SANDSTONE.get()), CaerulaArborMod.loc("block/sal_viento_sandstone"),
                CaerulaArborMod.loc("block/sal_viento_sandstone_bottom"), CaerulaArborMod.loc("block/sal_viento_sandstone_top"));
        wallBlock((WallBlock) ModBlocks.SAL_VIENTO_SANDSTONE_WALL.get(), blockTexture(ModBlocks.SAL_VIENTO_SANDSTONE.get()));
        simpleBlockWithItem(ModBlocks.CHISELED_SAL_VIENTO_SANDSTONE.get(), models().cubeColumn("chiseled_sal_viento_sandstone", CaerulaArborMod.loc("block/chiseled_sal_viento_sandstone"),
                CaerulaArborMod.loc("block/sal_viento_sandstone_top")));
        wallBlock((WallBlock) ModBlocks.CHISELED_SAL_VIENTO_SANDSTONE_WALL.get(), blockTexture(ModBlocks.CHISELED_SAL_VIENTO_SANDSTONE.get()));
        blockWithItem(ModBlocks.SMOOTH_SAL_VIENTO_SANDSTONE);
        stairsBlock((StairBlock) ModBlocks.SMOOTH_SAL_VIENTO_SANDSTONE_STAIRS.get(), blockTexture(ModBlocks.SMOOTH_SAL_VIENTO_SANDSTONE.get()), CaerulaArborMod.loc("block/smooth_sal_viento_sandstone"),
                CaerulaArborMod.loc("block/smooth_sal_viento_sandstone"));
        slabBlock((SlabBlock) ModBlocks.SMOOTH_SAL_VIENTO_SANDSTONE_SLAB.get(), blockTexture(ModBlocks.SMOOTH_SAL_VIENTO_SANDSTONE.get()), CaerulaArborMod.loc("block/smooth_sal_viento_sandstone"),
                CaerulaArborMod.loc("block/smooth_sal_viento_sandstone"), CaerulaArborMod.loc("block/smooth_sal_viento_sandstone"));
        simpleBlockWithItem(ModBlocks.SQUARE_PATTERN_SAL_VIENTO_SANDSTONE.get(), models().cubeAll("square_pattern_sal_viento_sandstone", CaerulaArborMod.loc("block/sal_viento_sandstone_top")));
        stairsBlock((StairBlock) ModBlocks.SQUARE_PATTERN_SAL_VIENTO_SANDSTONE_STAIRS.get(), CaerulaArborMod.loc("block/sal_viento_sandstone_top"));
        slabBlock((SlabBlock) ModBlocks.SQUARE_PATTERN_SAL_VIENTO_SANDSTONE_SLAB.get(), blockTexture(ModBlocks.SQUARE_PATTERN_SAL_VIENTO_SANDSTONE.get()), CaerulaArborMod.loc("block/sal_viento_sandstone_top"));
        simpleBlockWithItem(ModBlocks.CUT_SAL_VIENTO_SANDSTONE.get(), models().cubeColumn("cut_sal_viento_sandstone", CaerulaArborMod.loc("block/cut_sal_viento_sandstone"),
                CaerulaArborMod.loc("block/sal_viento_sandstone_top")));
        stairsBlock((StairBlock) ModBlocks.CUT_SAL_VIENTO_SANDSTONE_STAIRS.get(), blockTexture(ModBlocks.CUT_SAL_VIENTO_SANDSTONE.get()), CaerulaArborMod.loc("block/cut_sal_viento_sandstone"),
                CaerulaArborMod.loc("block/sal_viento_sandstone_top"));
        slabBlock((SlabBlock) ModBlocks.CUT_SAL_VIENTO_SANDSTONE_SLAB.get(), blockTexture(ModBlocks.CUT_SAL_VIENTO_SANDSTONE.get()), CaerulaArborMod.loc("block/cut_sal_viento_sandstone"),
                CaerulaArborMod.loc("block/sal_viento_sandstone_bottom"), CaerulaArborMod.loc("block/sal_viento_sandstone_top"));
        wallBlock((WallBlock) ModBlocks.CUT_SAL_VIENTO_SANDSTONE_WALL.get(), blockTexture(ModBlocks.CUT_SAL_VIENTO_SANDSTONE.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.SAL_VIENTO_PILLAR.get(), CaerulaArborMod.loc("block/sal_viento_pillar_side"), CaerulaArborMod.loc("block/sal_viento_pillar_top"));
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
