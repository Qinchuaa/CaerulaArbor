package com.apocalypse.caerulaarbor.datagen;

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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SAND.get())
                .unlockedBy(getHasName(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SAND.get()), has(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SAND.get()))
                .save(writer);
    
        // 砂岩 -> 平滑砂岩（熔炉）
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()),
                    RecipeCategory.BUILDING_BLOCKS,
                    com.apocalypse.caerulaarbor.init.ModBlocks.SMOOTH_SEA_VIENTO_SANDSTONE.get(),
                    0.1f, 200)
                .unlockedBy(getHasName(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()), has(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()))
                .save(writer);
    
        // 砂岩 -> 平滑砂岩（高炉）
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()),
                    RecipeCategory.BUILDING_BLOCKS,
                    com.apocalypse.caerulaarbor.init.ModBlocks.SMOOTH_SEA_VIENTO_SANDSTONE.get(),
                    0.1f, 100)
                .unlockedBy(getHasName(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()), has(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()))
                .save(writer);
    
        // 切石：砂岩 -> 切制/雕纹/柱子
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()),
                    RecipeCategory.BUILDING_BLOCKS,
                    com.apocalypse.caerulaarbor.init.ModBlocks.CUT_SEA_VIENTO_SANDSTONE.get())
                .unlockedBy(getHasName(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()), has(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()))
                .save(writer);
    
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()),
                    RecipeCategory.BUILDING_BLOCKS,
                    com.apocalypse.caerulaarbor.init.ModBlocks.CHISELED_SEA_VIENTO_SANDSTONE.get())
                .unlockedBy(getHasName(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()), has(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()))
                .save(writer);
    
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()),
                    RecipeCategory.BUILDING_BLOCKS,
                    com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_PILLAR.get())
                .unlockedBy(getHasName(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()), has(com.apocalypse.caerulaarbor.init.ModBlocks.SEA_VIENTO_SANDSTONE.get()))
                .save(writer);
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
