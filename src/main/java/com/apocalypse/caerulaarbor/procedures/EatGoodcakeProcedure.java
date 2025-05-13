package com.apocalypse.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import com.apocalypse.caerulaarbor.init.ModItems;

public class EatGoodcakeProcedure {
	public static InteractionResult execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return InteractionResult.PASS;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) < 3) {
				{
					int _value = (int) ((blockstate.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip4 ? blockstate.getValue(_getip4) : -1) + 1);
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			} else {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(ModItems.CARAMEL_CAKE_PIECE.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			return InteractionResult.SUCCESS;
		} else if (((entity instanceof LivingEntity _entity) ? _entity.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof SwordItem
				|| (entity instanceof LivingEntity _entity ? _entity.getOffhandItem() : ItemStack.EMPTY).getItem() instanceof SwordItem
				|| ((entity instanceof LivingEntity _entity) ? _entity.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof AxeItem
				|| (entity instanceof LivingEntity _entity ? _entity.getOffhandItem() : ItemStack.EMPTY).getItem() instanceof AxeItem
				|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("forge:tools/knives")))
				|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("forge:tools/knives")))) {
			for (int index0 = 0; index0 < (int) (4 - (blockstate.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip13 ? blockstate.getValue(_getip13) : -1)); index0++) {
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.5), (z + 0.5), new ItemStack(ModItems.CARAMEL_CAKE_PIECE.get()));
					entityToSpawn.setPickUpDelay(10);
					_level.addFreshEntity(entityToSpawn);
				}
			}
			world.destroyBlock(BlockPos.containing(x, y, z), false);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.sheep.shear")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.sheep.shear")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}
