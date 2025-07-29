
package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.capability.map.MapVariables;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class RedOvaryBlock extends OceanOvaryBlock implements SimpleWaterloggedBlock {

	public RedOvaryBlock() {
		Properties.of().sound(SoundType.SCULK_SENSOR)
				.strength(6f, 18f)
				.lightLevel(s -> 4)
				.requiresCorrectToolForDrops()
				.speedFactor(0.9f)
				.jumpFactor(0.9f)
				.noOcclusion()
				.pushReaction(PushReaction.BLOCK)
				.hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true)
				.isRedstoneConductor((bs, br, bp) -> false);
		this.registerDefaultState(this.stateDefinition.any());
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return super.propagatesSkylightDown(state, reader, pos);
    }

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return super.getLightBlock(state, worldIn, pos);
    }

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 60);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		world.scheduleTick(pos, this, 40);
		int chance = blockstate.getValue(COUNT);
		int new_count = chance;
		if(Mth.nextDouble(world.random,0,1) < chance * 0.005) {
			if (GiveBirth(world, blockstate, pos, EliteChance(world))) new_count = 0;
			else new_count++;
		}
		blockstate.setValue(COUNT,new_count);
	}

	@Override
	public double EliteChance(Level level){
		int breed = MapVariables.get(level).strategyBreed;
		if(breed >= 4)return 0.7;
		if(breed >= 2)return 0.65;
		return 0.5;
	}
}
