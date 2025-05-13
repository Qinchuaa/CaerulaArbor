
package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.procedures.EatGoodcakeProcedure;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CaramelCakeBlock extends Block {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 3);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final IntegerProperty EATEN = IntegerProperty.create("eaten", 0, 3);

	public CaramelCakeBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SCULK).strength(0.5f).lightLevel(s -> (new Object() {
			public int getLightLevel() {
				if (s.getValue(BLOCKSTATE) == 1)
					return 0;
				if (s.getValue(BLOCKSTATE) == 2)
					return 0;
				if (s.getValue(BLOCKSTATE) == 3)
					return 0;
				return 0;
			}
		}.getLightLevel())).friction(0.5f).speedFactor(0.8f).jumpFactor(0.9f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(EATEN, 0));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(BLOCKSTATE) == 1) {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(1, 0, 1, 8, 8, 8), box(8, 0, 8, 15, 8, 15), box(8, 0, 1, 15, 8, 8));
				case NORTH -> Shapes.or(box(8, 0, 8, 15, 8, 15), box(1, 0, 1, 8, 8, 8), box(1, 0, 8, 8, 8, 15));
				case EAST -> Shapes.or(box(1, 0, 8, 8, 8, 15), box(8, 0, 1, 15, 8, 8), box(1, 0, 1, 8, 8, 8));
				case WEST -> Shapes.or(box(8, 0, 1, 15, 8, 8), box(1, 0, 8, 8, 8, 15), box(8, 0, 8, 15, 8, 15));
			};
		}
		if (state.getValue(BLOCKSTATE) == 2) {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(1, 0, 1, 8, 8, 8), box(8, 0, 1, 15, 8, 8));
				case NORTH -> Shapes.or(box(8, 0, 8, 15, 8, 15), box(1, 0, 8, 8, 8, 15));
				case EAST -> Shapes.or(box(1, 0, 8, 8, 8, 15), box(1, 0, 1, 8, 8, 8));
				case WEST -> Shapes.or(box(8, 0, 1, 15, 8, 8), box(8, 0, 8, 15, 8, 15));
			};
		}
		if (state.getValue(BLOCKSTATE) == 3) {
			return switch (state.getValue(FACING)) {
				default -> box(1, 0, 1, 8, 8, 8);
				case NORTH -> box(8, 0, 8, 15, 8, 15);
				case EAST -> box(1, 0, 8, 8, 8, 15);
				case WEST -> box(8, 0, 1, 15, 8, 8);
			};
		}
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(1, 0, 1, 8, 8, 8), box(8, 0, 8, 15, 8, 15), box(8, 0, 1, 15, 8, 8), box(1, 0, 8, 8, 8, 15));
			case NORTH -> Shapes.or(box(8, 0, 8, 15, 8, 15), box(1, 0, 1, 8, 8, 8), box(1, 0, 8, 8, 8, 15), box(8, 0, 1, 15, 8, 8));
			case EAST -> Shapes.or(box(1, 0, 8, 8, 8, 15), box(8, 0, 1, 15, 8, 8), box(1, 0, 1, 8, 8, 8), box(8, 0, 8, 15, 8, 15));
			case WEST -> Shapes.or(box(8, 0, 1, 15, 8, 8), box(1, 0, 8, 8, 8, 15), box(8, 0, 8, 15, 8, 15), box(1, 0, 1, 8, 8, 8));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, EATEN, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(EATEN, 0);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		InteractionResult result = EatGoodcakeProcedure.execute(world, x, y, z, blockstate, entity);
		return result;
	}
}
