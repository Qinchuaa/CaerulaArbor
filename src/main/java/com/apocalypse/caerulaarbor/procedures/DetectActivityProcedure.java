package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.CaerulaArborModBlocks;
import com.apocalypse.caerulaarbor.init.CaerulaArborModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

public class DetectActivityProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
        double dx;
        double dy;
        double dz;
        double attr;
        if (blockstate.getBlock().getStateDefinition().getProperty("activated") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) {
            if (!((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == CaerulaArborModBlocks.ANCHOR_UPPER.get() && (new Object() {
                public Direction getDirection(BlockPos pos) {
                    BlockState _bs = world.getBlockState(pos);
                    Property<?> property = _bs.getBlock().getStateDefinition().getProperty("facing");
                    if (property != null && _bs.getValue(property) instanceof Direction _dir)
                        return _dir;
                    else if (_bs.hasProperty(BlockStateProperties.AXIS))
                        return Direction.fromAxisAndDirection(_bs.getValue(BlockStateProperties.AXIS), Direction.AxisDirection.POSITIVE);
                    else if (_bs.hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                        return Direction.fromAxisAndDirection(_bs.getValue(BlockStateProperties.HORIZONTAL_AXIS), Direction.AxisDirection.POSITIVE);
                    return Direction.NORTH;
                }
            }.getDirection(BlockPos.containing(x, y + 1, z))) == (new Object() {
                public Direction getDirection(BlockState _bs) {
                    Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
                    if (_prop instanceof DirectionProperty _dp)
                        return _bs.getValue(_dp);
                    _prop = _bs.getBlock().getStateDefinition().getProperty("axis");
                    return _prop instanceof EnumProperty<?> _ep && _ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) _bs.getValue(_ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
                }
            }.getDirection(blockstate)) && (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == CaerulaArborModBlocks.ANCHOR_LOWER.get() && (new Object() {
                public Direction getDirection(BlockPos pos) {
                    BlockState _bs = world.getBlockState(pos);
                    Property<?> property = _bs.getBlock().getStateDefinition().getProperty("facing");
                    if (property != null && _bs.getValue(property) instanceof Direction _dir)
                        return _dir;
                    else if (_bs.hasProperty(BlockStateProperties.AXIS))
                        return Direction.fromAxisAndDirection(_bs.getValue(BlockStateProperties.AXIS), Direction.AxisDirection.POSITIVE);
                    else if (_bs.hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                        return Direction.fromAxisAndDirection(_bs.getValue(BlockStateProperties.HORIZONTAL_AXIS), Direction.AxisDirection.POSITIVE);
                    return Direction.NORTH;
                }
            }.getDirection(BlockPos.containing(x, y - 1, z))) == (new Object() {
                public Direction getDirection(BlockState _bs) {
                    Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
                    if (_prop instanceof DirectionProperty _dp)
                        return _bs.getValue(_dp);
                    _prop = _bs.getBlock().getStateDefinition().getProperty("axis");
                    return _prop instanceof EnumProperty<?> _ep && _ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) _bs.getValue(_ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
                }
            }.getDirection(blockstate)))) {
                {
                    int _value = 0;
                    BlockPos _pos = BlockPos.containing(x, y, z);
                    BlockState _bs = world.getBlockState(_pos);
                    if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
                        world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
                }
                {
                    BlockPos _pos = BlockPos.containing(x, y, z);
                    BlockState _bs = world.getBlockState(_pos);
                    if (_bs.getBlock().getStateDefinition().getProperty("activated") instanceof BooleanProperty _booleanProp)
                        world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
                }
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.conduit.deactivate")), SoundSource.NEUTRAL, 2, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.conduit.deactivate")), SoundSource.NEUTRAL, 2, 1, false);
                    }
                }
            }
            for (int index0 = 0; index0 < 16; index0++) {
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ParticleTypes.NAUTILUS, (x + Mth.nextDouble(RandomSource.create(), -24, 25)), y, (z - 24), 4, 0.5, 4, 0.5, 0.1);
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ParticleTypes.NAUTILUS, (x + Mth.nextDouble(RandomSource.create(), -24, 25)), y, (z + 25), 4, 0.5, 4, 0.5, 0.1);
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ParticleTypes.NAUTILUS, (x - 24), y, (z + Mth.nextDouble(RandomSource.create(), -24, 25)), 4, 0.5, 4, 0.5, 0.1);
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ParticleTypes.NAUTILUS, (x + 25), y, (z + Mth.nextDouble(RandomSource.create(), -24, 25)), 4, 0.5, 4, 0.5, 0.1);
            }
            dx = -24;
            for (int index1 = 0; index1 < 49; index1++) {
                dy = -8;
                for (int index2 = 0; index2 < 17; index2++) {
                    dz = -24;
                    for (int index3 = 0; index3 < 49; index3++) {
                        if ((world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz))).is(BlockTags.create(new ResourceLocation("caerula_arbor:trail")))) {
                            attr = (world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz))).getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip28
                                    ? (world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz))).getValue(_getip28)
                                    : -1;
                            if (attr < 64) {
                                {
                                    int _value = (int) (attr - 4);
                                    BlockPos _pos = BlockPos.containing(x + dx, y + dy, z + dz);
                                    BlockState _bs = world.getBlockState(_pos);
                                    if (_bs.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
                                        world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
                                }
                            }
                        }
                        dz = dz + 1;
                    }
                    dy = dy + 1;
                }
                dx = dx + 1;
            }
            for (Entity entityiterator : world.getEntities(null, new AABB((x + 25), (y + 9), (z + 25), (x - 24), (y - 9), (z - 24)))) {
                if (!(entityiterator instanceof LivingEntity _livEnt30 && _livEnt30.hasEffect(CaerulaArborModMobEffects.POWER_OF_ANCHOR.get()))) {
                    if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.POWER_OF_ANCHOR.get(), 80, 0, false, false));
                }
            }
        }
    }
}
