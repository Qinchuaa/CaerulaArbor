package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import com.apocalypse.caerulaarbor.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CaerulaArborMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                ModBlocks.SAL_VIENTO_SAND.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.SAL_VIENTO_SANDSTONE.get(),
                ModBlocks.SAL_VIENTO_SANDSTONE_STAIRS.get(),
                ModBlocks.SAL_VIENTO_SANDSTONE_SLAB.get(),
                ModBlocks.SAL_VIENTO_SANDSTONE_WALL.get(),
                ModBlocks.CHISELED_SAL_VIENTO_SANDSTONE.get(),
                ModBlocks.CHISELED_SAL_VIENTO_SANDSTONE_WALL.get(),
                ModBlocks.SMOOTH_SAL_VIENTO_SANDSTONE.get(),
                ModBlocks.SMOOTH_SAL_VIENTO_SANDSTONE_STAIRS.get(),
                ModBlocks.SMOOTH_SAL_VIENTO_SANDSTONE_SLAB.get(),
                ModBlocks.SQUARE_PATTERN_SAL_VIENTO_SANDSTONE.get(),
                ModBlocks.SQUARE_PATTERN_SAL_VIENTO_SANDSTONE_STAIRS.get(),
                ModBlocks.SQUARE_PATTERN_SAL_VIENTO_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_SAL_VIENTO_SANDSTONE.get(),
                ModBlocks.CUT_SAL_VIENTO_SANDSTONE_STAIRS.get(),
                ModBlocks.CUT_SAL_VIENTO_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_SAL_VIENTO_SANDSTONE_WALL.get(),
                ModBlocks.CRACKED_SAL_VIENTO_SANDSTONE.get(),
                ModBlocks.CRACKED_SAL_VIENTO_SANDSTONE_STAIRS.get(),
                ModBlocks.CRACKED_SAL_VIENTO_SANDSTONE_SLAB.get(),
                ModBlocks.CRACKED_SAL_VIENTO_SANDSTONE_WALL.get(),
                ModBlocks.SAL_VIENTO_PILLAR.get(),
                ModBlocks.NETHERSEA_BRICKS.get(),
                ModBlocks.NETHERSEA_BRICK_STAIRS.get(),
                ModBlocks.NETHERSEA_BRICK_SLAB.get(),
                ModBlocks.NETHERSEA_BRICK_WALL.get(),
                ModBlocks.NETHERSEA_TILES.get(),
                ModBlocks.NETHERSEA_TILE_STAIRS.get(),
                ModBlocks.NETHERSEA_TILE_SLAB.get(),
                ModBlocks.NETHERSEA_TILE_WALL.get(),
                ModBlocks.NETHERSEA_PRESSURE_PLATE.get(),
                ModBlocks.NETHERSEA_BUTTON.get()
        );

        this.tag(BlockTags.WALLS).add(
                ModBlocks.SAL_VIENTO_SANDSTONE_WALL.get(),
                ModBlocks.CHISELED_SAL_VIENTO_SANDSTONE_WALL.get(),
                ModBlocks.CUT_SAL_VIENTO_SANDSTONE_WALL.get(),
                ModBlocks.CRACKED_SAL_VIENTO_SANDSTONE_WALL.get(),
                ModBlocks.NETHERSEA_BRICK_WALL.get(),
                ModBlocks.NETHERSEA_TILE_WALL.get()
        );
        this.tag(BlockTags.PRESSURE_PLATES).add(
                ModBlocks.NETHERSEA_PRESSURE_PLATE.get()
        );
        this.tag(BlockTags.BUTTONS).add(
                ModBlocks.NETHERSEA_BUTTON.get()
        );

        this.tag(ModTags.Blocks.NETHERSEA_BLOCK).add(
                ModBlocks.NETHERSEA_BRICKS.get(),
                ModBlocks.NETHERSEA_BRICK_STAIRS.get(),
                ModBlocks.NETHERSEA_BRICK_SLAB.get(),
                ModBlocks.NETHERSEA_BRICK_WALL.get(),
                ModBlocks.NETHERSEA_TILES.get(),
                ModBlocks.NETHERSEA_TILE_STAIRS.get(),
                ModBlocks.NETHERSEA_TILE_SLAB.get(),
                ModBlocks.NETHERSEA_TILE_WALL.get(),
                ModBlocks.NETHERSEA_PRESSURE_PLATE.get(),
                ModBlocks.NETHERSEA_BUTTON.get()
        );
    }
}
