package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.Map;

public class TridentEnchantProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
			if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(Enchantments.SHARPNESS) > itemstack.getEnchantmentLevel(ModEnchantments.SYNESTHESIA.get())) {
				{
					Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(itemstack);
					if (_enchantments.containsKey(ModEnchantments.SYNESTHESIA.get())) {
						_enchantments.remove(ModEnchantments.SYNESTHESIA.get());
						EnchantmentHelper.setEnchantments(_enchantments, itemstack);
					}
				}
				itemstack.enchant(ModEnchantments.SYNESTHESIA.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(Enchantments.SHARPNESS));
				{
					Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY));
					if (_enchantments.containsKey(Enchantments.SHARPNESS)) {
						_enchantments.remove(Enchantments.SHARPNESS);
						EnchantmentHelper.setEnchantments(_enchantments, (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY));
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.ENCHANT, x, y, z, 72, 1.2, 2, 1.2, 0.2);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 3, 1);
					} else {
						_level.playLocalSound(x, y, z, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 3, 1, false);
					}
				}
			}
		}
	}
}
