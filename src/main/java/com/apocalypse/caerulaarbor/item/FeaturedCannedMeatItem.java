package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.init.ModItems;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class FeaturedCannedMeatItem extends RelicItem {

    public FeaturedCannedMeatItem() {
        super(new Item.Properties().stacksTo(64)
                .food((new FoodProperties.Builder()).nutrition(14).saturationMod(1.1f).meat()
                        .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0f)
                        .alwaysEat().build())
        );
    }

    @Override
    public boolean isInstantUse() {
        return false;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 40;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.featured_canned_meat.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.featured_canned_meat.des_2").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        super.finishUsingItem(itemstack, world, entity);

        entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.relic_util_MEATCAN = true;
            capability.syncPlayerVariables(entity);
        });

        ItemStack stack = new ItemStack(ModItems.EMPTY_CAN.get());
        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            if (!player.getInventory().add(stack)) {
                player.drop(stack, false);
            }
        }
        return itemstack;
    }
}
