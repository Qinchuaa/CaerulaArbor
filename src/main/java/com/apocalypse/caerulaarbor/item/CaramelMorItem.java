
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.init.ModAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class CaramelMorItem extends Item {
	public CaramelMorItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(3).saturationMod(2f).build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		var san = entity.getAttribute(ModAttributes.SANITY.get());
		if (san != null) {
			san.setBaseValue(san.getBaseValue() + 15);
		}
		return retval;
	}
}
