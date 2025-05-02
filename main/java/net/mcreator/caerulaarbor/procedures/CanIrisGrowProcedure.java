package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CanIrisGrowProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.REDSTONE_ORE || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.REDSTONE_ORE
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.DEEPSLATE_REDSTONE_ORE;
	}
}
