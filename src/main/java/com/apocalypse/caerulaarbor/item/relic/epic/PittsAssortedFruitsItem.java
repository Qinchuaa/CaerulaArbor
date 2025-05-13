package com.apocalypse.caerulaarbor.item.relic.epic;

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
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class PittsAssortedFruitsItem extends RelicItem {

    public PittsAssortedFruitsItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)
                .food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.5f)
                        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 280, 1), 1.0f)
                        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 280, 1), 1.0f)
                        .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 560, 1), 1.0f)
                        .alwaysEat().build())
        );
    }

    @Override
    public boolean isInstantUse() {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.pitts_assorted_fruits.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.pitts_assorted_fruits.des_2").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
			capability.player_util_RAINBOW = true;
			capability.syncPlayerVariables(entity);
		});

		ItemStack stack = new ItemStack(ModItems.PAPER_BAG.get());
        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            if (!player.getInventory().add(stack)) {
				player.drop(stack, false);
			}
        }
        return super.finishUsingItem(itemstack, world, entity);
    }
}
