package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.init.ModBlocks;
import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        simpleHelmetRecipe(writer, ModItems.CHITIN_HELMET.get(), ModItems.OCEAN_CHITIN.get());
        simpleChestplateRecipe(writer, ModItems.CHITIN_CHESTPLATE.get(), ModItems.OCEAN_CHITIN.get());
        simpleLeggingsRecipe(writer, ModItems.CHITIN_LEGGINGS.get(), ModItems.OCEAN_CHITIN.get());
        simpleBootsRecipe(writer, ModItems.CHITIN_BOOTS.get(), ModItems.OCEAN_CHITIN.get());

        simpleSmithing(writer, ModItems.OCEAN_TRIM_TEMPLATE.get(), ModItems.CHITIN_HELMET.get(), ModItems.COMPLEX_CHITIN.get(),
                ModItems.COMPLEX_CHITIN_HELMET.get(), RecipeCategory.COMBAT);
        simpleSmithing(writer, ModItems.OCEAN_TRIM_TEMPLATE.get(), ModItems.CHITIN_CHESTPLATE.get(), ModItems.COMPLEX_CHITIN.get(),
                ModItems.COMPLEX_CHITIN_CHESTPLATE.get(), RecipeCategory.COMBAT);
        simpleSmithing(writer, ModItems.OCEAN_TRIM_TEMPLATE.get(), ModItems.CHITIN_LEGGINGS.get(), ModItems.COMPLEX_CHITIN.get(),
                ModItems.COMPLEX_CHITIN_LEGGINGS.get(), RecipeCategory.COMBAT);
        simpleSmithing(writer, ModItems.OCEAN_TRIM_TEMPLATE.get(), ModItems.CHITIN_BOOTS.get(), ModItems.COMPLEX_CHITIN.get(),
                ModItems.COMPLEX_CHITIN_BOOTS.get(), RecipeCategory.COMBAT);

        // SEA_VIENTO 基础方块配方
        // 2x2 海蓝沙 -> 海风砂岩
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MAR_VIENTO_SANDSTONE.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', ModBlocks.MAR_VIENTO_SAND.get())
                .unlockedBy(getHasName(ModBlocks.MAR_VIENTO_SAND.get()), has(ModBlocks.MAR_VIENTO_SAND.get()))
                .save(writer);

        // 砂岩 -> 平滑砂岩（熔炉）
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.MAR_VIENTO_SANDSTONE.get()),
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SMOOTH_MAR_VIENTO_SANDSTONE.get(),
                        0.1f, 200)
                .unlockedBy(getHasName(ModBlocks.MAR_VIENTO_SANDSTONE.get()), has(ModBlocks.MAR_VIENTO_SANDSTONE.get()))
                .save(writer);

        // 砂岩 -> 平滑砂岩（高炉）
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.MAR_VIENTO_SANDSTONE.get()),
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SMOOTH_MAR_VIENTO_SANDSTONE.get(),
                        0.1f, 100)
                .unlockedBy(getHasName(ModBlocks.MAR_VIENTO_SANDSTONE.get()), has(ModBlocks.MAR_VIENTO_SANDSTONE.get()))
                .save(writer, RecipeBuilder.getDefaultRecipeId(ModBlocks.SMOOTH_MAR_VIENTO_SANDSTONE.get()) + "_blasting");

        // 切石：砂岩 -> 切制/雕纹/柱子
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.MAR_VIENTO_SANDSTONE.get()),
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.CUT_MAR_VIENTO_SANDSTONE.get())
                .unlockedBy(getHasName(ModBlocks.MAR_VIENTO_SANDSTONE.get()), has(ModBlocks.MAR_VIENTO_SANDSTONE.get()))
                .save(writer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.MAR_VIENTO_SANDSTONE.get()),
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.CHISELED_MAR_VIENTO_SANDSTONE.get())
                .unlockedBy(getHasName(ModBlocks.MAR_VIENTO_SANDSTONE.get()), has(ModBlocks.MAR_VIENTO_SANDSTONE.get()))
                .save(writer);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.MAR_VIENTO_SANDSTONE.get()),
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.MAR_VIENTO_PILLAR.get())
                .unlockedBy(getHasName(ModBlocks.MAR_VIENTO_SANDSTONE.get()), has(ModBlocks.MAR_VIENTO_SANDSTONE.get()))
                .save(writer);

        // Ocean：原木 -> 木板 x4
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.OCEAN_PLANKS.get(), 4)
                .requires(ModBlocks.OCEAN_LOG.get())
                .unlockedBy(getHasName(ModBlocks.OCEAN_LOG.get()), has(ModBlocks.OCEAN_LOG.get()))
                .save(writer);

        // 原木烧木炭（熔炉）：Ocean 原木 -> 木炭
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.OCEAN_LOG.get()),
                        RecipeCategory.MISC,
                        net.minecraft.world.item.Items.CHARCOAL,
                        0.15f, 200)
                .unlockedBy(getHasName(ModBlocks.OCEAN_LOG.get()), has(ModBlocks.OCEAN_LOG.get()))
                .save(writer, "charcoal_from_smelting_ocean_log");

        // 篝火（通用，可燃原木 + 木棍 + 木炭/煤）
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, net.minecraft.world.level.block.Blocks.CAMPFIRE)
                .pattern(" S ")
                .pattern("SCS")
                .pattern("LLL")
                .define('S', net.minecraft.world.item.Items.STICK)
                .define('L', net.minecraft.tags.ItemTags.LOGS_THAT_BURN)
                .define('C', net.minecraft.world.item.Items.CHARCOAL)
                .unlockedBy("has_logs_that_burn", has(net.minecraft.tags.ItemTags.LOGS_THAT_BURN))
                .save(writer, "campfire_charcoal_from_logs_that_burn");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, net.minecraft.world.level.block.Blocks.CAMPFIRE)
                .pattern(" S ")
                .pattern("SCS")
                .pattern("LLL")
                .define('S', net.minecraft.world.item.Items.STICK)
                .define('L', net.minecraft.tags.ItemTags.LOGS_THAT_BURN)
                .define('C', net.minecraft.world.item.Items.COAL)
                .unlockedBy("has_logs_that_burn", has(net.minecraft.tags.ItemTags.LOGS_THAT_BURN))
                .save(writer, "campfire_coal_from_logs_that_burn");

        // 篝火（显式：Ocean 原木 + 木炭）
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, net.minecraft.world.level.block.Blocks.CAMPFIRE)
                .pattern(" S ")
                .pattern("SCS")
                .pattern("LLL")
                .define('S', net.minecraft.world.item.Items.STICK)
                .define('L', ModBlocks.OCEAN_LOG.get())
                .define('C', net.minecraft.world.item.Items.CHARCOAL)
                .unlockedBy(getHasName(ModBlocks.OCEAN_LOG.get()), has(ModBlocks.OCEAN_LOG.get()))
                .save(writer, "campfire_charcoal_from_ocean_log");
    }

    private static void simpleHelmetRecipe(Consumer<FinishedRecipe> writer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("SSS")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(writer);
    }

    private static void simpleChestplateRecipe(Consumer<FinishedRecipe> writer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(writer);
    }

    private static void simpleLeggingsRecipe(Consumer<FinishedRecipe> writer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(writer);
    }

    private static void simpleBootsRecipe(Consumer<FinishedRecipe> writer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S S")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(writer);
    }

    private static void simpleSmithing(Consumer<FinishedRecipe> writer, ItemLike template, ItemLike base, ItemLike addition, Item result) {
        simpleSmithing(writer, template, base, addition, result, RecipeCategory.MISC);
    }

    private static void simpleSmithing(Consumer<FinishedRecipe> writer, ItemLike template, ItemLike base, ItemLike addition, Item result, RecipeCategory category) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(base), Ingredient.of(addition), category, result)
                .unlocks(getHasName(addition), has(addition))
                .save(writer, getItemName(result) + "_smithing");
    }
}
