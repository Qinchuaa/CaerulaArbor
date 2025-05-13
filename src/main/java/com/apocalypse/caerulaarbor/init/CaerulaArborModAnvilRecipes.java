
package com.apocalypse.caerulaarbor.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.List;
import java.util.ArrayList;

@JeiPlugin
public class CaerulaArborModAnvilRecipes implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("caerula_arbor:anvil_recipes");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();
		List<IJeiAnvilRecipe> anvilRecipes = new ArrayList<>();
		ItemStack rightItem = ItemStack.EMPTY;
		rightItem = new ItemStack(CaerulaArborModItems.RELIC_CROWN.get());
		rightItem.setCount(1);
		anvilRecipes.add(factory.createAnvilRecipe(new ItemStack(Items.IRON_HELMET), List.of(rightItem.copy()), List.of(new ItemStack(CaerulaArborModItems.WEARABLE_CROWN_HELMET.get()))));
		rightItem = new ItemStack(CaerulaArborModItems.KINGS_ARMOUR.get());
		rightItem.setCount(1);
		anvilRecipes.add(factory.createAnvilRecipe(new ItemStack(Items.IRON_CHESTPLATE), List.of(rightItem.copy()), List.of(new ItemStack(CaerulaArborModItems.WEARABLE_CHEST_CHESTPLATE.get()))));
		rightItem = new ItemStack(Items.COPPER_INGOT);
		rightItem.setCount(1);
		anvilRecipes.add(factory.createAnvilRecipe(new ItemStack(CaerulaArborModItems.SOLO_MUSIC_BOX.get()), List.of(rightItem.copy()), List.of(new ItemStack(CaerulaArborModItems.MUSIC_BOX_FIXED.get()))));
		registration.addRecipes(RecipeTypes.ANVIL, anvilRecipes);
	}
}
