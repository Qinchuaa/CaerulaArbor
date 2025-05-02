package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.mcreator.caerulaarbor.item.PhloemBowItem;
import net.mcreator.caerulaarbor.init.CaerulaArborModEnchantments;

import java.util.Map;

public class BowUseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		boolean valid = false;
		valid = true;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
			if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(Enchantments.POWER_ARROWS) > itemstack.getEnchantmentLevel(CaerulaArborModEnchantments.REFLECTION.get())) {
				{
					Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(itemstack);
					if (_enchantments.containsKey(CaerulaArborModEnchantments.REFLECTION.get())) {
						_enchantments.remove(CaerulaArborModEnchantments.REFLECTION.get());
						EnchantmentHelper.setEnchantments(_enchantments, itemstack);
					}
				}
				itemstack.enchant(CaerulaArborModEnchantments.REFLECTION.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getEnchantmentLevel(Enchantments.POWER_ARROWS));
				{
					Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY));
					if (_enchantments.containsKey(Enchantments.POWER_ARROWS)) {
						_enchantments.remove(Enchantments.POWER_ARROWS);
						EnchantmentHelper.setEnchantments(_enchantments, (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY));
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.ENCHANT, x, y, z, 72, 1.2, 2, 1.2, 0.2);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.enchantment_table.use")), SoundSource.PLAYERS, 3, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.enchantment_table.use")), SoundSource.PLAYERS, 3, 1, false);
					}
				}
				valid = false;
			} else if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0
					&& !(EnchantmentHelper.getItemEnchantmentLevel(CaerulaArborModEnchantments.METABOLISM.get(), itemstack) != 0)) {
				{
					Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(itemstack);
					if (_enchantments.containsKey(CaerulaArborModEnchantments.METABOLISM.get())) {
						_enchantments.remove(CaerulaArborModEnchantments.METABOLISM.get());
						EnchantmentHelper.setEnchantments(_enchantments, itemstack);
					}
				}
				itemstack.enchant(CaerulaArborModEnchantments.METABOLISM.get(), 1);
				{
					Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY));
					if (_enchantments.containsKey(Enchantments.INFINITY_ARROWS)) {
						_enchantments.remove(Enchantments.INFINITY_ARROWS);
						EnchantmentHelper.setEnchantments(_enchantments, (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY));
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.ENCHANT, x, y, z, 72, 1.2, 2, 1.2, 0.2);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.enchantment_table.use")), SoundSource.PLAYERS, 3, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.enchantment_table.use")), SoundSource.PLAYERS, 3, 1, false);
					}
				}
				valid = false;
			}
		}
		if (valid) {
			if (!(entity instanceof Player _plrCldCheck32 && _plrCldCheck32.getCooldowns().isOnCooldown(itemstack.getItem()))) {
				if ((entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.ARROW)) : false) || new Object() {
					public boolean checkGamemode(Entity _ent) {
						if (_ent instanceof ServerPlayer _serverPlayer) {
							return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
						} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
							return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
									&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
						}
						return false;
					}
				}.checkGamemode(entity)) {
					ShootarrowProcedure.execute(world, x, y, z, entity, itemstack);
					if (itemstack.getItem() instanceof PhloemBowItem)
						itemstack.getOrCreateTag().putString("geckoAnim", "animation.bluebow.pull");
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.crossbow.quick_charge_1")), SoundSource.NEUTRAL, (float) 1.8, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.crossbow.quick_charge_1")), SoundSource.NEUTRAL, (float) 1.8, 1, false);
						}
					}
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(itemstack.getItem(), 30);
				}
			}
		}
	}
}
