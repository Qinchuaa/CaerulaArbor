package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.block.entity.PoolOfProcreationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class NourishedPoolOfProcreationBlock extends PoolOfProcreationBlock {

    public NourishedPoolOfProcreationBlock() {
        super();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PoolOfProcreationBlockEntity(pPos, pState, true);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = defaultBlockState();
        if (context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER) {
            blockState = blockState.setValue(WATERLOGGED, true);
        }
        blockState = blockState.setValue(POWERED, true);
        return blockState;
    }
}
