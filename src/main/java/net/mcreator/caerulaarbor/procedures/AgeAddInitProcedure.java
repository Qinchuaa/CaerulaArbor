package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.caerulaarbor.init.CaerulaArborModBlocks;

public class AgeAddInitProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double expand = 0;
		expand = 1;
		if (world.getLevelData().isThundering()) {
			expand = 2;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).value().getBaseTemperature() * 100f >= 165) {
			expand = 0.5;
		}
		{
			int _value = (int) ((blockstate.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip3 ? blockstate.getValue(_getip3) : -1) + expand);
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
				world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
		}
		if ((blockstate.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip6 ? blockstate.getValue(_getip6) : -1) > 29) {
			world.setBlock(BlockPos.containing(x, y, z), (new Object() {
				public BlockState with(BlockState _bs, String _property, int _newValue) {
					Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty(_property);
					return _prop instanceof IntegerProperty _ip && _prop.getPossibleValues().contains(_newValue) ? _bs.setValue(_ip, _newValue) : _bs;
				}
			}.with((new Object() {
				public BlockState with(BlockState _bs, Direction newValue) {
					Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_prop instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(newValue))
						return _bs.setValue(_dp, newValue);
					_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
					return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().contains(newValue.getAxis()) ? _bs.setValue(_ep, newValue.getAxis()) : _bs;
				}
			}.with(CaerulaArborModBlocks.SEA_TRAIL_GROWING.get().defaultBlockState(), new Object() {
				public Direction getValue() {
					Direction _dir = Direction.NORTH;
					int _num = Mth.nextInt(RandomSource.create(), 1, 4);
					if (_num == 1) {
						_dir = Direction.EAST;
					} else if (_num == 2) {
						_dir = Direction.SOUTH;
					} else if (_num == 3) {
						_dir = Direction.WEST;
					}
					return _dir;
				}
			}.getValue())), "longevity", blockstate.getBlock().getStateDefinition().getProperty("longevity") instanceof IntegerProperty _getip10 ? blockstate.getValue(_getip10) : -1)), 3);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.step")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.step")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
	}
}
