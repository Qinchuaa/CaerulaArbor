
package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class BlockBatbedBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BlockBatbedBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(0.5f, 1f).jumpFactor(1.3f).noOcclusion().pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false));
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
                    Shapes.or(box(0, 0, 0, 16, 12, 2), box(0, 0, 2, 2, 12, 16), box(14, 0, 2, 16, 12, 16), box(2, 0, 2, 14, 2, 16), box(2, 2, 2, 14, 5, 16));
            case EAST ->
                    Shapes.or(box(14, 0, 0, 16, 12, 16), box(0, 0, 0, 14, 12, 2), box(0, 0, 14, 14, 12, 16), box(0, 0, 2, 14, 2, 14), box(0, 2, 2, 14, 5, 14));
            case WEST ->
                    Shapes.or(box(0, 0, 0, 2, 12, 16), box(2, 0, 14, 16, 12, 16), box(2, 0, 0, 16, 12, 2), box(2, 0, 2, 16, 2, 14), box(2, 2, 2, 16, 5, 14));
            default ->
                    Shapes.or(box(0, 0, 14, 16, 12, 16), box(14, 0, 0, 16, 12, 14), box(0, 0, 0, 2, 12, 14), box(2, 0, 0, 14, 2, 14), box(2, 2, 0, 14, 5, 14));
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
            var facing = blockstate.getValue(BlockStateProperties.FACING).getOpposite();
            var targetBlockState = world.getBlockState(pos.offset(facing.getStepX(), 0, facing.getStepZ()));
            return targetBlockState.canBeReplaced() || targetBlockState.getBlock() == ModBlocks.BATBED_UPPER.get();
        }
        return super.canSurvive(blockstate, worldIn, pos);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 20);

        var facing = blockstate.getValue(BlockStateProperties.FACING);
        var oppositeFacing = blockstate.getValue(BlockStateProperties.FACING).getOpposite();
        var offsetPos = pos.offset(oppositeFacing.getStepX(), 0, oppositeFacing.getStepZ());

        if (world.getBlockState(offsetPos).canBeReplaced()) {
            world.setBlock(offsetPos, ModBlocks.BATBED_UPPER.get().defaultBlockState().setValue(BlockStateProperties.FACING, facing), 3);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);

        var state = world.getBlockState(pos);
        var facing = state.getValue(BlockStateProperties.FACING).getOpposite();
        var targetPos = pos.relative(facing);

        if (world.getBlockState(targetPos).getBlock() != ModBlocks.BATBED_UPPER.get()) {
            dropResources(world.getBlockState(pos), world, pos, null);
            world.destroyBlock(pos, false);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        var center = pos.getCenter();
        for (var entity : world.getEntitiesOfClass(Mob.class, new AABB(center, center).inflate(32), e -> true)) {
            if ((entity instanceof Bat || entity.getMobType() == MobType.UNDEAD)
                    && new Vec3(x + 0.5, y + 1.0, z + 0.5).distanceTo(entity.position()) > 1
                    && Math.random() < 0.2
            ) {
                entity.getNavigation().moveTo(
                        x + Mth.nextDouble(RandomSource.create(), -3, 4),
                        y,
                        z + Mth.nextDouble(RandomSource.create(), -3, 4),
                        1
                );
            }
        }

        world.scheduleTick(pos, this, 20);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
        var state = world.getBlockState(pos);
        var facing = state.getValue(BlockStateProperties.FACING).getOpposite();
        world.destroyBlock(pos.offset(facing.getStepX(), 0, facing.getStepZ()), false);

        return super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void wasExploded(Level world, BlockPos pos, Explosion e) {
        super.wasExploded(world, pos, e);

        var state = world.getBlockState(pos);
        var facing = state.getValue(BlockStateProperties.FACING).getOpposite();
        world.destroyBlock(pos.offset(facing.getStepX(), 0, facing.getStepZ()), false);
    }
}
