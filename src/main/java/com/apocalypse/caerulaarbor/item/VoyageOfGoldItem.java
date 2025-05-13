
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.procedures.GainRelicVOYGOLDProcedure;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import java.util.List;

public class VoyageOfGoldItem extends Item {
	public VoyageOfGoldItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.caerula_arbor.voyage_of_gold.description_0"));
		list.add(Component.translatable("item.caerula_arbor.voyage_of_gold.description_1"));
		list.add(Component.translatable("item.caerula_arbor.voyage_of_gold.description_2"));
		list.add(Component.translatable("item.caerula_arbor.voyage_of_gold.description_3"));
		list.add(Component.translatable("item.caerula_arbor.voyage_of_gold.description_4"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		GainRelicVOYGOLDProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getObject());
		return ar;
	}
}
