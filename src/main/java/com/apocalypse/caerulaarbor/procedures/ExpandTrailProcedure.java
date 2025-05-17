package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.List;

public class ExpandTrailProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		boolean valid = false;
		BlockState blocktoplace = Blocks.AIR.defaultBlockState();
		double direc = 0;
		double dx = 0;
		double dz = 0;
		double longev = 0;
		double expand = 0;
		if ((blockstate.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip1 ? blockstate.getValue(_getip1) : -1) < 64) {
			expand = 1;
			if (world.getLevelData().isThundering()) {
				expand = 2;
			}
			if (world.getBiome(BlockPos.containing(x, y, z)).value().getBaseTemperature() * 100f >= 165) {
				expand = 0.5;
			}
			{
				int _value = (int) ((blockstate.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip5 ? blockstate.getValue(_getip5) : -1) + expand);
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			valid = true;
			{
				final Vec3 _center = new Vec3((x + 0.5), (y + 0.5), (z + 0.5));
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(0.6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if ((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) > 5) {
						valid = false;
						break;
					}
				}
			}
			if (valid && (blockstate.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip10 ? blockstate.getValue(_getip10) : -1) > 29
					&& (blockstate.getBlock().getStateDefinition().getProperty("longevity") instanceof IntegerProperty _getip12 ? blockstate.getValue(_getip12) : -1) > 0) {
				if (Math.random() < 0.2) {
					dx = 0;
					dx = 1;
					direc = Mth.nextInt(RandomSource.create(), 0, 3);
					if (direc == 0) {
						dx = 0;
						dz = 1;
					} else if (direc == 1) {
						dx = 0;
						dz = -1;
					} else if (direc == 2) {
						dx = 1;
						dz = 0;
					} else {
						dx = -1;
						dz = 0;
					}
					longev = blockstate.getBlock().getStateDefinition().getProperty("longevity") instanceof IntegerProperty _getip15 ? blockstate.getValue(_getip15) : -1;
					if (Math.random() < 0.33) {
						longev = longev - 1;
					}
					blocktoplace = (new Object() {
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
					}.with(ModBlocks.SEA_TRAIL_INIT.get().defaultBlockState(), new Object() {
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
					}.getValue())), "longevity", (int) longev));
					if ((world.getBlockState(BlockPos.containing(x + dx, y, z + dz))).canBeReplaced() && ModBlocks.SEA_TRAIL_INIT.get().defaultBlockState().canSurvive(world, BlockPos.containing(x + dx, y, z + dz))) {
						world.setBlock(BlockPos.containing(x + dx, y, z + dz), blocktoplace, 3);
						world.levelEvent(2001, BlockPos.containing(x + dx, y, z + dz), Block.getId(ModBlocks.SEA_TRAIL_INIT.get().defaultBlockState()));
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.place")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.place")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					} else if ((world.getBlockState(BlockPos.containing(x + dx, y - 1, z + dz))).canBeReplaced()
							&& ((world.getBlockState(BlockPos.containing(x + dx, y, z + dz))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x + dx, y, z + dz))).getBlock() == Blocks.CAVE_AIR)
							&& ModBlocks.SEA_TRAIL_INIT.get().defaultBlockState().canSurvive(world, BlockPos.containing(x + dx, y - 1, z + dz))) {
						world.setBlock(BlockPos.containing(x + dx, y - 1, z + dz), blocktoplace, 3);
						world.levelEvent(2001, BlockPos.containing(x + dx, y - 1, z + dz), Block.getId(ModBlocks.SEA_TRAIL_INIT.get().defaultBlockState()));
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.place")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.place")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					} else if ((world.getBlockState(BlockPos.containing(x + dx, y + 1, z + dz))).canBeReplaced()
							&& ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.CAVE_AIR)
							&& ModBlocks.SEA_TRAIL_INIT.get().defaultBlockState().canSurvive(world, BlockPos.containing(x + dx, y + 1, z + dz))) {
						world.setBlock(BlockPos.containing(x + dx, y + 1, z + dz), blocktoplace, 3);
						world.levelEvent(2001, BlockPos.containing(x + dx, y + 1, z + dz), Block.getId(ModBlocks.SEA_TRAIL_INIT.get().defaultBlockState()));
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.place")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.place")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					}
				}
			}
		} else {
			if (Math.random() < 0.2 && (world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(new ResourceLocation("caerula_arbor:errodable")))) {
				world.destroyBlock(BlockPos.containing(x, y, z), false);
				world.setBlock(BlockPos.containing(x, y - 1, z), (new Object() {
					public BlockState with(BlockState _bs, Direction newValue) {
						Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
						if (_prop instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(newValue))
							return _bs.setValue(_dp, newValue);
						_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
						return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().contains(newValue.getAxis()) ? _bs.setValue(_ep, newValue.getAxis()) : _bs;
					}
				}.with(ModBlocks.SEA_TRAIL_SOLID.get().defaultBlockState(), new Object() {
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
				}.getValue())), 3);
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.break")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.break")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			}
		}
	}
}
