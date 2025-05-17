
package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.procedures.OpenBoilProcedure;
import com.apocalypse.caerulaarbor.procedures.SetBoilingProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockKettleBlock extends Block {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 1);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERED = BooleanProperty.create("watered");
	public static final BooleanProperty BOILING = BooleanProperty.create("boiling");
	public static final BooleanProperty NOODLED = BooleanProperty.create("noodled");

	public BlockKettleBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(0.5f, 2f).lightLevel(s -> (new Object() {
			public int getLightLevel() {
				if (s.getValue(BLOCKSTATE) == 1)
					return 0;
				return 0;
			}
		}.getLightLevel())).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERED, false).setValue(BOILING, false).setValue(NOODLED, false));
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
				default -> Shapes.or(box(2.5, 3, 2.5, 13.5, 6, 13.5), box(3.5, 6, 3.5, 12.5, 9, 12.5), box(3.5, 2, 3.5, 12.5, 3, 12.5), box(4.5, 10, 4.5, 11.5, 12, 11.5), box(5.5, 12, 5.5, 10.5, 13, 10.5), box(7.5, 13, 7.5, 8.5, 14, 8.5),
						box(4.5, 1, 4.5, 11.5, 2, 11.5), box(3.5, 0, 3.5, 12.5, 1, 12.5), box(4.5, 9, 4.5, 11.5, 10, 11.5));
				case NORTH -> Shapes.or(box(2.5, 3, 2.5, 13.5, 6, 13.5), box(3.5, 6, 3.5, 12.5, 9, 12.5), box(3.5, 2, 3.5, 12.5, 3, 12.5), box(4.5, 10, 4.5, 11.5, 12, 11.5), box(5.5, 12, 5.5, 10.5, 13, 10.5), box(7.5, 13, 7.5, 8.5, 14, 8.5),
						box(4.5, 1, 4.5, 11.5, 2, 11.5), box(3.5, 0, 3.5, 12.5, 1, 12.5), box(4.5, 9, 4.5, 11.5, 10, 11.5));
				case EAST -> Shapes.or(box(2.5, 3, 2.5, 13.5, 6, 13.5), box(3.5, 6, 3.5, 12.5, 9, 12.5), box(3.5, 2, 3.5, 12.5, 3, 12.5), box(4.5, 10, 4.5, 11.5, 12, 11.5), box(5.5, 12, 5.5, 10.5, 13, 10.5), box(7.5, 13, 7.5, 8.5, 14, 8.5),
						box(4.5, 1, 4.5, 11.5, 2, 11.5), box(3.5, 0, 3.5, 12.5, 1, 12.5), box(4.5, 9, 4.5, 11.5, 10, 11.5));
				case WEST -> Shapes.or(box(2.5, 3, 2.5, 13.5, 6, 13.5), box(3.5, 6, 3.5, 12.5, 9, 12.5), box(3.5, 2, 3.5, 12.5, 3, 12.5), box(4.5, 10, 4.5, 11.5, 12, 11.5), box(5.5, 12, 5.5, 10.5, 13, 10.5), box(7.5, 13, 7.5, 8.5, 14, 8.5),
						box(4.5, 1, 4.5, 11.5, 2, 11.5), box(3.5, 0, 3.5, 12.5, 1, 12.5), box(4.5, 9, 4.5, 11.5, 10, 11.5));
			};
		}
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(2.5, 3, 2.5, 13.5, 6, 13.5), box(3.5, 6, 3.5, 12.5, 9, 12.5), box(3.5, 2, 3.5, 12.5, 3, 12.5), box(4.5, 10, 4.5, 11.5, 12, 11.5), box(5.5, 12, 5.5, 10.5, 13, 10.5), box(7.5, 13, 7.5, 8.5, 14, 8.5),
					box(4.5, 1, 4.5, 11.5, 2, 11.5), box(3.5, 0, 3.5, 12.5, 1, 12.5), box(4.5, 9, 4.5, 11.5, 10, 11.5));
			case NORTH -> Shapes.or(box(2.5, 3, 2.5, 13.5, 6, 13.5), box(3.5, 6, 3.5, 12.5, 9, 12.5), box(3.5, 2, 3.5, 12.5, 3, 12.5), box(4.5, 10, 4.5, 11.5, 12, 11.5), box(5.5, 12, 5.5, 10.5, 13, 10.5), box(7.5, 13, 7.5, 8.5, 14, 8.5),
					box(4.5, 1, 4.5, 11.5, 2, 11.5), box(3.5, 0, 3.5, 12.5, 1, 12.5), box(4.5, 9, 4.5, 11.5, 10, 11.5));
			case EAST -> Shapes.or(box(2.5, 3, 2.5, 13.5, 6, 13.5), box(3.5, 6, 3.5, 12.5, 9, 12.5), box(3.5, 2, 3.5, 12.5, 3, 12.5), box(4.5, 10, 4.5, 11.5, 12, 11.5), box(5.5, 12, 5.5, 10.5, 13, 10.5), box(7.5, 13, 7.5, 8.5, 14, 8.5),
					box(4.5, 1, 4.5, 11.5, 2, 11.5), box(3.5, 0, 3.5, 12.5, 1, 12.5), box(4.5, 9, 4.5, 11.5, 10, 11.5));
			case WEST -> Shapes.or(box(2.5, 3, 2.5, 13.5, 6, 13.5), box(3.5, 6, 3.5, 12.5, 9, 12.5), box(3.5, 2, 3.5, 12.5, 3, 12.5), box(4.5, 10, 4.5, 11.5, 12, 11.5), box(5.5, 12, 5.5, 10.5, 13, 10.5), box(7.5, 13, 7.5, 8.5, 14, 8.5),
					box(4.5, 1, 4.5, 11.5, 2, 11.5), box(3.5, 0, 3.5, 12.5, 1, 12.5), box(4.5, 9, 4.5, 11.5, 10, 11.5));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, WATERED, BOILING, NOODLED, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERED, false).setValue(BOILING, false).setValue(NOODLED, false);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 5);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		SetBoilingProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		SetBoilingProcedure.execute(world, x, y, z);
		world.scheduleTick(pos, this, 5);
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
		InteractionResult result = OpenBoilProcedure.execute(world, x, y, z, blockstate, entity);
		return result;
	}
}
