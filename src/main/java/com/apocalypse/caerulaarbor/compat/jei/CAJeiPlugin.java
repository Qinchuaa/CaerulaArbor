package com.apocalypse.caerulaarbor.compat.jei;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.init.ModItems;
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
public class CAJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return CaerulaArborMod.loc("jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IVanillaRecipeFactory factory = registration.getJeiHelpers().getVanillaRecipeFactory();
        registration.addRecipes(RecipeTypes.ANVIL, getAnvilRecipes(factory));
    }

    private static List<IJeiAnvilRecipe> getAnvilRecipes(IVanillaRecipeFactory factory) {
        List<IJeiAnvilRecipe> anvilRecipes = new ArrayList<>();

        anvilRecipes.add(factory.createAnvilRecipe(new ItemStack(Items.IRON_HELMET), List.of(new ItemStack(ModItems.RELIC_CROWN.get())),
                List.of(new ItemStack(ModItems.WEARABLE_CROWN_HELMET.get())), CaerulaArborMod.loc("wearable_crown_helmet")));
        anvilRecipes.add(factory.createAnvilRecipe(new ItemStack(Items.IRON_CHESTPLATE), List.of(new ItemStack(ModItems.KINGS_ARMOUR.get())),
                List.of(new ItemStack(ModItems.WEARABLE_CHEST_CHESTPLATE.get())), CaerulaArborMod.loc("wearable_chestplate")));
        anvilRecipes.add(factory.createAnvilRecipe(new ItemStack(ModItems.SOLO_MUSIC_BOX.get()), List.of(new ItemStack(Items.COPPER_INGOT)),
                List.of(new ItemStack(ModItems.MUSIC_BOX_FIXED.get())), CaerulaArborMod.loc("music_box_fixed")));

        return anvilRecipes;
    }
}
