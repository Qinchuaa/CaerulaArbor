package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;

public class CanPutTrailProcedure {
    public static boolean execute(LevelAccessor world, double x, double y, double z) {
        var pos = BlockPos.containing(x, y - 1, z);
        var state = world.getBlockState(pos);

        return state.isFaceSturdy(world, pos, Direction.UP) && state.getBlock() != ModBlocks.SEA_TRAIL_SOLID.get();
    }
}
