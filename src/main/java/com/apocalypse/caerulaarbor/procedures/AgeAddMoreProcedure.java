package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class AgeAddMoreProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double expand = 0;
		if ((blockstate.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip1 ? blockstate.getValue(_getip1) : -1) < 30) {
			{
				int _value = (int) ((blockstate.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip3 ? blockstate.getValue(_getip3) : -1) + 8);
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		}
	}
}
