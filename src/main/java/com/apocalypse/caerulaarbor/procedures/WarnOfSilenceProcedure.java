package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class WarnOfSilenceProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (CaerulaArborModVariables.MapVariables.get(world).strategy_silence > 0) {
            BlockPos _pos = BlockPos.containing(x, y, z);
            BlockState _bs = world.getBlockState(_pos);
            if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(1))
                world.setBlock(_pos, _bs.setValue(_integerProp, 1), 3);
        } else {
            BlockPos _pos = BlockPos.containing(x, y, z);
            BlockState _bs = world.getBlockState(_pos);
            if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(0))
                world.setBlock(_pos, _bs.setValue(_integerProp, 0), 3);
        }
    }
}
