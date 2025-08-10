package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.block.entity.AnchorBlockEntity;
import com.apocalypse.caerulaarbor.init.ModBlockEntityTypes;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

// TODO 给个#forge:relocation_unsupported
public class AnchorMediumBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public AnchorMediumBlock() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.NETHERITE_BLOCK)
                .strength(12f, 45f)
                .lightLevel(s -> 8)
                .pushReaction(PushReaction.BLOCK)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ACTIVATED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, ACTIVATED);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(ACTIVATED, false);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if (pDirection.getAxis() != Direction.Axis.Y || !pState.getValue(ACTIVATED)) return pState;
        if (!pNeighborState.is(
                pDirection == Direction.UP ? ModBlocks.ANCHOR_UPPER.get() : ModBlocks.ANCHOR_LOWER.get()))
            return pState.setValue(ACTIVATED, false);
        return pState;
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        if (hit.getDirection() != blockstate.getValue(FACING)) return InteractionResult.PASS;
        if (!blockstate.getValue(ACTIVATED) && !mayActivate(world, pos, blockstate))
            return InteractionResult.FAIL;
        if (world.isClientSide()) return InteractionResult.SUCCESS;
        world.playSound(null, pos,
                blockstate.getValue(ACTIVATED) ? SoundEvents.CONDUIT_DEACTIVATE : SoundEvents.CONDUIT_ACTIVATE,
                SoundSource.BLOCKS, 2, 1);
        world.setBlock(pos, blockstate.cycle(ACTIVATED), 3);
        return InteractionResult.CONSUME;
    }

    public boolean mayActivate(BlockGetter level, BlockPos pos, BlockState state) {
        Direction facing = state.getValue(FACING);
        BlockState stateAbove = level.getBlockState(pos.above());
        BlockState stateBelow = level.getBlockState(pos.below());
        return stateAbove.is(ModBlocks.ANCHOR_UPPER.get()) && stateAbove.getValue(FACING).equals(facing)
                && stateBelow.is(ModBlocks.ANCHOR_LOWER.get()) && stateBelow.getValue(FACING).equals(facing);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AnchorBlockEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (!pLevel.isClientSide) {
            return createTickerHelper(pBlockEntityType, ModBlockEntityTypes.ANCHOR.get(), AnchorBlockEntity::serverTick);
        }
        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
}