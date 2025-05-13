package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.caerulaarbor.init.CaerulaArborModBlocks;

public class CanPutTrailProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return world.getBlockState(BlockPos.containing(x, y - 1, z)).isFaceSturdy(world, BlockPos.containing(x, y - 1, z), Direction.UP)
				&& !((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == CaerulaArborModBlocks.SEA_TRAIL_SOLID.get());
	}
}
