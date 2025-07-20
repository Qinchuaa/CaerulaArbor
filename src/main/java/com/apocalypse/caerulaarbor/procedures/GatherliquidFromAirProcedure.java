package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.ItemHandlerHelper;

public class GatherliquidFromAirProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (Blocks.WATER == (world.getFluidState(BlockPos.containing(x + entity.getLookAngle().x, y + entity.getLookAngle().y, z + entity.getLookAngle().z)).createLegacyBlock()).getBlock()) {
			itemstack.shrink(1);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(ModItems.CANNED_WATER.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		} else if (Blocks.LAVA == (world.getFluidState(BlockPos.containing(x + entity.getLookAngle().x, y + entity.getLookAngle().y, z + entity.getLookAngle().z)).createLegacyBlock()).getBlock()) {
			itemstack.shrink(1);
			if (entity instanceof Player _player) {
				ItemHandlerHelper.giveItemToPlayer(_player, new ItemStack(ModItems.CANNED_LAVA.get()).copyWithCount(1));
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
	}
}
