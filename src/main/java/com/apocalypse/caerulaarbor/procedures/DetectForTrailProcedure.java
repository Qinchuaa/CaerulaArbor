package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import com.apocalypse.caerulaarbor.init.CaerulaArborModBlocks;

public class DetectForTrailProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean once = false;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		BlockState tobeeaten = Blocks.AIR.defaultBlockState();
		once = false;
		dx = -2;
		for (int index0 = 0; index0 < 5; index0++) {
			dz = -2;
			for (int index1 = 0; index1 < 5; index1++) {
				dy = -2;
				for (int index2 = 0; index2 < 4; index2++) {
					if ((world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz))).getBlock() == CaerulaArborModBlocks.SEA_TRAIL_GROWN.get()) {
						world.destroyBlock(BlockPos.containing(x + dx, y + dy, z + dz), false);
						once = true;
						break;
					}
					dy = dy + 1;
				}
				dz = dz + 1;
				if (once) {
					break;
				}
			}
			dx = dx + 1;
			if (once) {
				break;
			}
		}
		return once;
	}
}
