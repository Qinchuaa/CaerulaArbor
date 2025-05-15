package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.init.ModItems;

public class EquipComplexChitinProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double suit = 0;
		suit = 0;
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == ModItems.COMPLEXCHITIN_ARMOR_BOOTS.get()) {
			suit = suit + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == ModItems.COMPLEXCHITIN_ARMOR_LEGGINGS.get()) {
			suit = suit + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == ModItems.COMPLEXCHITIN_ARMOR_CHESTPLATE.get()) {
			suit = suit + 1;
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == ModItems.COMPLEXCHITIN_ARMOR_HELMET.get()) {
			suit = suit + 1;
		}
		if (suit > 0 && !(entity instanceof LivingEntity _livEnt8 && _livEnt8.hasEffect(ModMobEffects.SANITY_PROTECT.get()))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(ModMobEffects.SANITY_PROTECT.get(), 40, (int) (suit - 1), false, false));
		}
	}
}
