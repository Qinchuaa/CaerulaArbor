package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
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
}
