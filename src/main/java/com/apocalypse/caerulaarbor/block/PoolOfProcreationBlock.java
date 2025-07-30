package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.block.entity.PoolOfProcreationBlockEntity;
import com.apocalypse.caerulaarbor.init.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class PoolOfProcreationBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    private static final VoxelShape BASE_SHAPE = box(0, 0, 0, 16, 8, 16);
    private static final VoxelShape TOP_SHAPE = box(1, 8, 1, 15, 15, 15);

    public PoolOfProcreationBlock() {
        super(Properties.of().sound(SoundType.SCULK_SENSOR)
                .strength(6f, 18f)
                .lightLevel(s -> s.getValue(POWERED) ? 4 : 0)
                .requiresCorrectToolForDrops()
                .speedFactor(0.9f)
                .jumpFactor(0.9f)
                .pushReaction(PushReaction.DESTROY)
                .hasPostProcess((bs, br, bp) -> true)
                .emissiveRendering((bs, br, bp) -> true)
                .isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.or(BASE_SHAPE, TOP_SHAPE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = defaultBlockState();
        if (context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER) {
            blockState = blockState.setValue(WATERLOGGED, true);
        }
        if (context.getLevel().hasNeighborSignal(context.getClickedPos())) {
            blockState = blockState.setValue(POWERED, true);
        }
        return blockState;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        super.neighborChanged(pState, pLevel, pPos, pNeighborBlock, pNeighborPos, pMovedByPiston);
        boolean hasSignal = pLevel.hasNeighborSignal(pPos);
        if (hasSignal && !pState.getValue(POWERED)) {
            if (pLevel.getBlockEntity(pPos) instanceof PoolOfProcreationBlockEntity pBlockEntity) {
                pBlockEntity.activate();
            }
            pLevel.setBlock(pPos, pState.setValue(POWERED, true), 3);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PoolOfProcreationBlockEntity(pPos, pState, false);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (!pLevel.isClientSide) {
            return createTickerHelper(pBlockEntityType, ModBlockEntityTypes.POOL_OF_PROCREATION.get(), PoolOfProcreationBlockEntity::serverTick);
        }
        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
}
