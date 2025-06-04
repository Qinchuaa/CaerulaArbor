package com.apocalypse.caerulaarbor.item.relic.normal;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModItems;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
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
    public int getUseDuration(@NotNull ItemStack itemstack) {
        return 40;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.featured_canned_meat.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.featured_canned_meat.des_2").withStyle(ChatFormatting.GRAY));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack pStack, Level world, LivingEntity entity) {
        Relic.modify(entity, cap -> Relic.UTIL_MEATCAN.set(entity, 1));

        ItemStack rt = new ItemStack(ModItems.EMPTY_CAN.get());
        if (super.finishUsingItem(pStack, world, entity).isEmpty()) {
            return rt;
        } else {
            if (entity instanceof Player player && !player.getAbilities().instabuild) {
                if (!player.getInventory().add(rt)) {
                    player.drop(rt, false);
                }
            }
            return pStack;
        }
    }
}
