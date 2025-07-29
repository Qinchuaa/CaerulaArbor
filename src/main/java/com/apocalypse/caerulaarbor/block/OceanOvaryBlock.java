
package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.capability.map.MapVariables;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OceanOvaryBlock extends Block implements SimpleWaterloggedBlock {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 1);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 200);
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	public OceanOvaryBlock() {
		super(Properties.of().sound(SoundType.SCULK_SENSOR)
				.strength(6f, 18f)
				.lightLevel(s -> (new Object() {
			public int getLightLevel() {
				if (s.getValue(BLOCKSTATE) == 1)
					return 0;
				return 4;
			}
		}.getLightLevel())).requiresCorrectToolForDrops()
				.speedFactor(0.9f)
				.jumpFactor(0.9f)
				.noOcclusion()
				.pushReaction(PushReaction.BLOCK)
				.hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true)
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(COUNT, 0)
				.setValue(POWERED, false)
				.setValue(WATERLOGGED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
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
				default -> box(0, 0, 0, 16, 8, 16);
				case NORTH -> box(0, 0, 0, 16, 8, 16);
				case EAST -> box(0, 0, 0, 16, 8, 16);
				case WEST -> box(0, 0, 0, 16, 8, 16);
			};
		}
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(0, 0, 0, 16, 8, 16), box(1, 8, 1, 15, 15, 15));
			case NORTH -> Shapes.or(box(0, 0, 0, 16, 8, 16), box(1, 8, 1, 15, 15, 15));
			case EAST -> Shapes.or(box(0, 0, 0, 16, 8, 16), box(1, 8, 1, 15, 15, 15));
			case WEST -> Shapes.or(box(0, 0, 0, 16, 8, 16), box(1, 8, 1, 15, 15, 15));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, COUNT, POWERED, WATERLOGGED, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(COUNT, 0).setValue(POWERED, false).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
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
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 40);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		world.scheduleTick(pos, this, 40);
		if(blockstate.getValue(BLOCKSTATE) == 1)return;
		int chance = blockstate.getValue(COUNT);
		int new_count = chance;
		if(Mth.nextDouble(world.random,0,1) < chance * 0.005){
			if(GiveBirth(world,blockstate,pos,EliteChance(world))){
				new_count = 0;
				blockstate.setValue(BLOCKSTATE,1);
			}
		} else new_count ++;
		blockstate.setValue(COUNT,new_count);
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		return retval;
	}

	public static Optional<EntityType<?>> Selection(Level level, TagKey<EntityType<?>> tagKey) {
		//TODO Deepseek力作，不知道能不能用
		Registry<EntityType<?>> entityRegistry = level.registryAccess().registryOrThrow(Registries.ENTITY_TYPE);

		// 获取标签中的所有实体类型
		List<EntityType<?>> entitiesInTag = new ArrayList<>();
		entityRegistry.getTag(tagKey).ifPresent(tag -> {
			for (Holder<EntityType<?>> holder : tag) {
				entitiesInTag.add(holder.value());
			}
		});

		// 如果标签为空返回空
		if (entitiesInTag.isEmpty()) {
			return Optional.empty();
		}

		// 随机选择一个实体类型
		RandomSource random = level.getRandom();
		EntityType<?> selected = entitiesInTag.get(random.nextInt(entitiesInTag.size()));

		return Optional.of(selected);
	}

	public double EliteChance(Level level){
		int breed = MapVariables.get(level).strategyBreed;
		if(breed >= 4)return 0.1;
		if(breed >= 2)return 0.08;
		return 0.05;
	}

	private boolean isSpacious(Level level,int x,int y,int z){
		for(BlockPos pos:BlockPos.betweenClosed(x-1,y+1,z-1,x+1,y+3,z+1)){
			if(!level.getBlockState(pos).canBeReplaced())return false;
		}
		return true;
	}

	//生怪
	public boolean GiveBirth(Level level,BlockState blockstate,BlockPos pos,double elite_chance){
		if(!isSpacious(level,pos.getX(), pos.getY(), pos.getZ()))return false;
		ResourceLocation loc = new ResourceLocation("caerula_arbor","sea_born");
		if(Mth.randomBetween(level.random,0,1) < elite_chance)
			loc = new ResourceLocation("caerula_arbor","seaborn_elite");//TODO 这个tag目前还没有
		TagKey<EntityType<?>> key = TagKey.create(Registries.ENTITY_TYPE,loc);
		if(level instanceof ServerLevel sLevel) {
			return Selection(level, key).map(entityType -> {
				return entityType.spawn(sLevel, pos, MobSpawnType.MOB_SUMMONED) != null;
			}).orElse(false);
		}
		return false;
	}
}
