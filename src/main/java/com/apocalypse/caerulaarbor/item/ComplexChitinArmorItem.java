package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.tiers.ModArmorMaterial;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ComplexChitinArmorItem extends ArmorItem {

    public ComplexChitinArmorItem(ArmorItem.Type type) {
        super(ModArmorMaterial.COMPLEX_CHITIN, type, new Properties().fireResistant().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.caerula_arbor.complex_chitin_armor.des").withStyle(ChatFormatting.GRAY));
    }
}
