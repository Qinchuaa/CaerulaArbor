package com.apocalypse.caerulaarbor.item.relic.epic;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HandOfPulverizationItem extends RelicItem {

    public HandOfPulverizationItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.hand_of_pulverization.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.hand_of_pulverization.des_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public @NotNull Relic getRelic() {
        return Relic.HAND_OF_PULVERIZATION;
    }

    @Override
    public int onCriticalHit(Player player, int level) {
        ItemStack stack = player.getMainHandItem();
        if (stack.is(ItemTags.AXES)) {
            if (level < 8) level++;
        }
        return level;
    }

    @Override
    public @NotNull Map<Attribute, AttributeModifier> getRelicAttributeModifiers(Player player) {
        int level = Math.max(0, Relic.getLevel(player, this.getRelic()));
        var map = super.getRelicAttributeModifiers(player);
        map.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(new UUID(this.getRelic().toString().hashCode(), 0), "Relic Item Modifier",
                0.2 * level, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return map;
    }
}
