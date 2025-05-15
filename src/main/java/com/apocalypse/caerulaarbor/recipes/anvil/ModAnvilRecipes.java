package com.apocalypse.caerulaarbor.recipes.anvil;

import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModAnvilRecipes {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        if ((event.getLeft().getItem() == Items.IRON_HELMET) && (event.getRight().getItem() == ModItems.RELIC_CROWN.get())) {
            if ((event.getLeft().getCount() == 1) && (event.getRight().getCount() >= 1)) {
                event.setMaterialCost(1);
                event.setCost(5);
                event.setOutput(new ItemStack(ModItems.WEARABLE_CROWN_HELMET.get()));
            }
        }
        if ((event.getLeft().getItem() == Items.IRON_CHESTPLATE) && (event.getRight().getItem() == ModItems.KINGS_ARMOUR.get())) {
            if ((event.getLeft().getCount() == 1) && (event.getRight().getCount() >= 1)) {
                event.setMaterialCost(1);
                event.setCost(5);
                event.setOutput(new ItemStack(ModItems.WEARABLE_CHEST_CHESTPLATE.get()));
            }
        }
        if ((event.getLeft().getItem() == ModItems.SOLO_MUSIC_BOX.get()) && (event.getRight().getItem() == Items.COPPER_INGOT)) {
            if ((event.getLeft().getCount() == 1) && (event.getRight().getCount() >= 1)) {
                event.setMaterialCost(1);
                event.setCost(4);
                event.setOutput(new ItemStack(ModItems.MUSIC_BOX_FIXED.get()));
            }
        }
    }
}
