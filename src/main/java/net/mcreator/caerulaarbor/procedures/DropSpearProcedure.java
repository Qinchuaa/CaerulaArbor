package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.caerulaarbor.init.CaerulaArborModItems;
import net.mcreator.caerulaarbor.init.CaerulaArborModBlocks;

public class DropSpearProcedure {
	public static InteractionResult execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level) {
			ItemEntity entityToSpawn = new ItemEntity(_level, (x + 0.5), (y + 0.75), (z + 0.5), new ItemStack(CaerulaArborModItems.KINGS_SPEAR.get()));
			entityToSpawn.setPickUpDelay(10);
			_level.addFreshEntity(entityToSpawn);
		}
		world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(CaerulaArborModBlocks.BLOCK_SPEAR.get().defaultBlockState()));
		world.setBlock(BlockPos.containing(x, y, z), Blocks.DEEPSLATE_BRICK_SLAB.defaultBlockState(), 3);
		return InteractionResult.SUCCESS;
	}
}
