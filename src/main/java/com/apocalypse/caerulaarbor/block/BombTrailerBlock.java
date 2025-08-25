
package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.init.ModBlocks;
import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class BombTrailerBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BombTrailerBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(4f, 32f).requiresCorrectToolForDrops());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    @ParametersAreNonnullByDefault
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);

        if (world.getBestNeighborSignal(pos) <= 0) return;

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        double dx, dz, dy;
        BlockState target;
        dx = -10;
        if (world instanceof ServerLevel level) {
            for (int index0 = 0; index0 < 21; index0++) {
                level.sendParticles(ParticleTypes.SMALL_FLAME, (x + dx), (y + 0.5), (z + 10), 32, 0.5, 2, 0.5, 0.1);
                level.sendParticles(ParticleTypes.SMALL_FLAME, (x + dx), (y + 0.5), (z - 10), 32, 0.5, 2, 0.5, 0.1);
                level.sendParticles(ParticleTypes.SMALL_FLAME, (x + 10), (y + 0.5), (z + dx), 32, 0.5, 2, 0.5, 0.1);
                level.sendParticles(ParticleTypes.SMALL_FLAME, (x - 10), (y + 0.5), (z + dx), 32, 0.5, 2, 0.5, 0.1);
                dx = dx + 1;
            }
            level.sendParticles(ParticleTypes.EXPLOSION, x, y, z, 6, 3, 3, 3, 0.1);
            level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 128, 3, 3, 3, 0.1);
        }

        if (!world.isClientSide()) {
            world.playSound(null, BlockPos.containing(x, y, z), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 3.2f, 1);
        } else {
            world.playLocalSound(x, y, z, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 3.2f, 1, false);
        }

        dx = -10;
        for (int index1 = 0; index1 < 21; index1++) {
            dz = -10;
            for (int index2 = 0; index2 < 21; index2++) {
                dy = -2;
                for (int index3 = 0; index3 < 5; index3++) {
                    target = world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz));
                    if (ModBlocks.NETHERSEA_BRAND_GROWN.get() == target.getBlock()) {
                        world.destroyBlock(BlockPos.containing(x + dx, y + dy, z + dz), false);
                        if (world instanceof ServerLevel level) {
                            for (int index4 = 0; index4 < 3; index4++) {
                                ItemEntity entityToSpawn = new ItemEntity(level, (x + dx + 0.5), (y + dy + 0.5), (z + dz + 0.5), new ItemStack(ModItems.SEA_TRAIL_MOR.get()));
                                entityToSpawn.setPickUpDelay(10);
                                level.addFreshEntity(entityToSpawn);

                            }
                            level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, (x + dx), (y + dy), (z + dz), 6, 0.5, 0.5, 0.5, 0.1);
                        }
                    } else if (ModBlocks.NETHERSEA_BRAND_GROWING.get() == target.getBlock() || ModBlocks.NETHERSEA_BRAND_INIT.get() == target.getBlock()) {
                        world.destroyBlock(BlockPos.containing(x + dx, y + dy, z + dz), false);

                        if (world instanceof ServerLevel level) {
                            level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, (x + dx), (y + dy), (z + dz), 6, 0.5, 0.5, 0.5, 0.1);
                            for (int index5 = 0; index5 < 9; index5++) {
                                ItemEntity entityToSpawn = new ItemEntity(level, (x + dx + 0.5), (y + dy + 0.5), (z + dz + 0.5), new ItemStack(ModItems.SEA_TRAIL_MOR.get()));
                                entityToSpawn.setPickUpDelay(10);
                                level.addFreshEntity(entityToSpawn);
                            }
                            level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, (x + dx), (y + dy), (z + dz), 6, 0.5, 0.5, 0.5, 0.1);
                        }
                    } else if (ModBlocks.NETHERSEA_BLOCK.get() == target.getBlock()) {
                        world.destroyBlock(BlockPos.containing(x + dx, y + dy, z + dz), false);
                        if (world instanceof ServerLevel _level)
                            _level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, (x + dx), (y + dy), (z + dz), 6, 0.5, 0.5, 0.5, 0.1);
                    } else if ((world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz))).canBeReplaced()) {
                        world.destroyBlock(BlockPos.containing(x + dx, y + dy, z + dz), false);
                    }
                    dy = dy + 1;
                }
                dz = dz + 1;
            }
            dx = dx + 1;
        }
        world.setBlock(BlockPos.containing(x, y, z), Blocks.IRON_BLOCK.defaultBlockState(), 3);
    }
}
