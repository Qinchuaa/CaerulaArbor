
package com.apocalypse.caerulaarbor.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import com.apocalypse.caerulaarbor.init.ModItems;

import java.util.List;

public abstract class ComplexchitinArmorItem extends ArmorItem {
	public ComplexchitinArmorItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 42;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{4, 6, 9, 4}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 22;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_diamond"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(ModItems.COMPLEX_CHITIN.get()));
			}

			@Override
			public String getName() {
				return "complexchitin_armor";
			}

			@Override
			public float getToughness() {
				return 4f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.15f;
			}
		}, type, properties);
	}

	public static class Helmet extends ComplexchitinArmorItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.translatable("item.caerula_arbor.complexchitin_armor_helmet.description_0"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "caerula_arbor:textures/models/armor/superchitin_layer_1.png";
		}
	}

	public static class Chestplate extends ComplexchitinArmorItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.translatable("item.caerula_arbor.complexchitin_armor_chestplate.description_0"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "caerula_arbor:textures/models/armor/superchitin_layer_1.png";
		}
	}

	public static class Leggings extends ComplexchitinArmorItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.translatable("item.caerula_arbor.complexchitin_armor_leggings.description_0"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "caerula_arbor:textures/models/armor/superchitin_layer_2.png";
		}
	}

	public static class Boots extends ComplexchitinArmorItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.translatable("item.caerula_arbor.complexchitin_armor_boots.description_0"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "caerula_arbor:textures/models/armor/superchitin_layer_1.png";
		}
	}
}
