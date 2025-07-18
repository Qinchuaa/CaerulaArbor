
package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class BatbedUpperBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BatbedUpperBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(0.5f, 1f).jumpFactor(1.3f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH ->
                    Shapes.or(box(0, 0, 14, 16, 12, 16), box(0, 0, 0, 2, 12, 14), box(14, 0, 0, 16, 12, 14), box(2, 0, 0, 14, 2, 14), box(2, 2, 0, 14, 5, 14));
            case EAST ->
                    Shapes.or(box(0, 0, 0, 2, 12, 16), box(2, 0, 0, 16, 12, 2), box(2, 0, 14, 16, 12, 16), box(2, 0, 2, 16, 2, 14), box(2, 2, 2, 16, 5, 14));
            case WEST ->
                    Shapes.or(box(14, 0, 0, 16, 12, 16), box(0, 0, 14, 14, 12, 16), box(0, 0, 0, 14, 12, 2), box(0, 0, 2, 14, 2, 14), box(0, 2, 2, 14, 5, 14));
            default ->
                    Shapes.or(box(0, 0, 0, 16, 12, 2), box(14, 0, 2, 16, 12, 16), box(0, 0, 2, 2, 12, 16), box(2, 0, 2, 14, 2, 16), box(2, 2, 2, 14, 5, 16));
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        if (worldIn instanceof LevelAccessor world) {
            Direction facing = blockstate.getValue(BlockStateProperties.FACING);
            return world.getBlockState(pos.relative(facing)).getBlock() == ModBlocks.BLOCK_BATBED.get();
        }
        return super.canSurvive(blockstate, worldIn, pos);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        return new ItemStack(ModBlocks.BLOCK_BATBED.get());
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
        var facing = world.getBlockState(pos).getValue(BlockStateProperties.FACING);
        world.destroyBlock(pos.relative(facing), false);

        return super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void wasExploded(Level world, BlockPos pos, Explosion e) {
        super.wasExploded(world, pos, e);

        var facing = world.getBlockState(pos).getValue(BlockStateProperties.FACING);
        world.destroyBlock(pos.relative(facing), false);
    }
}
