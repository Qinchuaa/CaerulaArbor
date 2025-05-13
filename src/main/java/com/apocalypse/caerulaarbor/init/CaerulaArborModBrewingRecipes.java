
package com.apocalypse.caerulaarbor.init;

import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.List;
import java.util.ArrayList;

@JeiPlugin
public class CaerulaArborModBrewingRecipes implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("caerula_arbor:brewing_recipes");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();
		List<IJeiBrewingRecipe> brewingRecipes = new ArrayList<>();
		ItemStack potion = new ItemStack(Items.POTION);
		ItemStack potion2 = new ItemStack(Items.POTION);
		List<ItemStack> ingredientStack = new ArrayList<>();
		List<ItemStack> inputStack = new ArrayList<>();
		ingredientStack.add(new ItemStack(Items.SWEET_BERRIES));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), new ItemStack(ModItems.SCREAMING_CHERRY.get())));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(ModItems.FERMENTED_OCEAN_EYE.get()));
		PotionUtils.setPotion(potion, Potions.AWKWARD);
		PotionUtils.setPotion(potion2, CaerulaArborModPotions.INST_SANITY.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(ModItems.CARAMEL_MOR.get()));
		PotionUtils.setPotion(potion, CaerulaArborModPotions.INST_SANITY.get());
		PotionUtils.setPotion(potion2, CaerulaArborModPotions.SANITY_CURE.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(Items.GLOWSTONE_DUST));
		PotionUtils.setPotion(potion, CaerulaArborModPotions.INST_SANITY.get());
		PotionUtils.setPotion(potion2, CaerulaArborModPotions.INST_SANITY_II.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		ingredientStack.add(new ItemStack(Items.GLOWSTONE_DUST));
		PotionUtils.setPotion(potion, CaerulaArborModPotions.SANITY_CURE.get());
		PotionUtils.setPotion(potion2, CaerulaArborModPotions.SANITY_CURE_II.get());
		brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), potion2.copy()));
		ingredientStack.clear();
		registration.addRecipes(RecipeTypes.BREWING, brewingRecipes);
	}
}
