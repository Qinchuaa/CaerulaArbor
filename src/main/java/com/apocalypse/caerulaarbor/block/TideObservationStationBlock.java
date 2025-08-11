package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.client.screens.TideObservationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class TideObservationStationBlock extends Block {

    public static final VoxelShape SHAPE_PLATE_NORTH = Block.box(1, 0, 4, 15, 3, 16);
    public static final VoxelShape SHAPE_PLATE_EAST = Block.box(0, 0, 1, 12, 3, 15);
    public static final VoxelShape SHAPE_PLATE_SOUTH = Block.box(1, 0, 0, 15, 3, 12);
    public static final VoxelShape SHAPE_PLATE_WEST = Block.box(4, 0, 1, 16, 3, 15);

    public static final VoxelShape SHAPE_POST_NORTH = Shapes.or(Block.box(5, 3, 7, 11, 16, 10), Block.box(2, 3, 10, 14, 16, 14));
    public static final VoxelShape SHAPE_POST_EAST = Shapes.or(Block.box(6, 3, 5, 9, 16, 11), Block.box(2, 3, 2, 6, 16, 14));
    public static final VoxelShape SHAPE_POST_SOUTH = Shapes.or(Block.box(5, 3, 6, 11, 16, 9), Block.box(2, 3, 2, 14, 16, 6));
    public static final VoxelShape SHAPE_POST_WEST = Shapes.or(Block.box(7, 3, 5, 10, 16, 11), Block.box(10, 3, 2, 14, 16, 14));

    public static final VoxelShape SHAPE_COMMON_NORTH = Shapes.or(SHAPE_PLATE_NORTH, SHAPE_POST_NORTH);
    public static final VoxelShape SHAPE_COMMON_EAST = Shapes.or(SHAPE_PLATE_EAST, SHAPE_POST_EAST);
    public static final VoxelShape SHAPE_COMMON_SOUTH = Shapes.or(SHAPE_PLATE_SOUTH, SHAPE_POST_SOUTH);
    public static final VoxelShape SHAPE_COMMON_WEST = Shapes.or(SHAPE_PLATE_WEST, SHAPE_POST_WEST);

    public static final VoxelShape SHAPE_NORTH = Shapes.or(
            SHAPE_COMMON_NORTH,
            Block.box(0, 12, 1, 16, 17, 6),
            Block.box(0, 14, 6, 16, 19, 11),
            Block.box(0, 16, 11, 16, 21, 16)
    );
    public static final VoxelShape SHAPE_EAST = Shapes.or(
            SHAPE_COMMON_EAST,
            Block.box(10, 12, 0, 15, 17, 16),
            Block.box(5, 14, 0, 10, 19, 16),
            Block.box(0, 16, 0, 5, 21, 16)
    );
    public static final VoxelShape SHAPE_SOUTH = Shapes.or(
            SHAPE_COMMON_SOUTH,
            Block.box(0, 12, 10, 16, 17, 15),
            Block.box(0, 14, 5, 16, 19, 10),
            Block.box(0, 16, 0, 16, 21, 5)
    );
    public static final VoxelShape SHAPE_WEST = Shapes.or(
            SHAPE_COMMON_WEST,
            Block.box(1, 12, 0, 6, 17, 16),
            Block.box(6, 14, 0, 11, 19, 16),
            Block.box(11, 16, 0, 16, 21, 16)
    );

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public TideObservationStationBlock() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.METAL)
                .strength(3f, 24f)
                .lightLevel(s -> 8)
                .pushReaction(PushReaction.BLOCK)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return switch (pState.getValue(FACING)) {
            default -> SHAPE_COMMON_NORTH;
            case EAST -> SHAPE_COMMON_EAST;
            case SOUTH -> SHAPE_COMMON_SOUTH;
            case WEST -> SHAPE_COMMON_WEST;
        };
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            default -> SHAPE_COMMON_NORTH;
            case EAST -> SHAPE_COMMON_EAST;
            case SOUTH -> SHAPE_COMMON_SOUTH;
            case WEST -> SHAPE_COMMON_WEST;
        };
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            default -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
        };
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            Minecraft.getInstance().setScreen(new TideObservationScreen());
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }
}
