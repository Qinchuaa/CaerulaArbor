
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.init.ModItems;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class GoldenStormItem extends Item {
    public GoldenStormItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.8f).alwaysEat().build()));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 30;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.golden_storm.description_0"));
        list.add(Component.translatable("item.caerula_arbor.golden_storm.description_1"));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        super.finishUsingItem(stack, world, entity);

        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0));
        }

        entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(cap -> {
            cap.relic_util_ORANGE = true;
            cap.syncPlayerVariables(entity);
        });

        if (stack.isEmpty()) {
            return new ItemStack(ModItems.PAPER_BAG.get());
        } else {
            if (entity instanceof Player player && !player.getAbilities().instabuild) {
                if (!player.getInventory().add(new ItemStack(ModItems.PAPER_BAG.get()))) {
                    player.drop(new ItemStack(ModItems.PAPER_BAG.get()), false);
                }
            }
            return stack;
        }
    }
}
