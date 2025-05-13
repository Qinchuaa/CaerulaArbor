
package net.mcreator.caerulaarbor.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class OceanGlasspaneBlock extends IronBarsBlock {
	public OceanGlasspaneBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(0.85f, 16f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}
