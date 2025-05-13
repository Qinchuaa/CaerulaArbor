
package net.mcreator.caerulaarbor.block;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;

import net.mcreator.caerulaarbor.procedures.PokeSlightlyProcedure;

public class TrailPressurePlateBlock extends PressurePlateBlock {
	public TrailPressurePlateBlock() {
		super(Sensitivity.MOBS, BlockBehaviour.Properties.of().sound(SoundType.SCULK_CATALYST).strength(1f, 12f).dynamicShape().forceSolidOn(), BlockSetType.IRON);
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		PokeSlightlyProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
		return retval;
	}
}
