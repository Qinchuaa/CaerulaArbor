package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;

import java.util.Objects;

public class NetherseaBrandGrowingBlock extends NetherseaBrandBlock {
    public NetherseaBrandGrowingBlock() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.SCULK_VEIN)
                .strength(1f, 2f)
                .lightLevel(s -> 1)
                .requiresCorrectToolForDrops()
                .friction(0.5f)
                .speedFactor(0.8f)
                .jumpFactor(0.8f)
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(GROW_AGE, 0).setValue(WATERLOGGED, false));
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState blockstate, boolean clientSide) {
        return blockstate.getValue(GROW_AGE) < MAX_AGE;
    }

    @Override
    protected void onGrown(ServerLevel level, BlockPos pos, BlockState state) {
        boolean waterlogged = getFluidState(state).getType() == Fluids.WATER;
        level.setBlock(pos, ModBlocks.NETHERSEA_BRAND_GROWN.get().defaultBlockState().setValue(WATERLOGGED, waterlogged), 3);
        level.playSound(null, pos, SoundEvents.SCULK_VEIN_STEP, SoundSource.BLOCKS, 1, 1);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return Objects.requireNonNull(super.getStateForPlacement(context))
                .setValue(GROW_AGE, 0)
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 2;
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 20);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        tryCauseSanityDamage(player);
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    12,
                    0.75, 0.75, 0.75,
                    0.1);
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    private void tryCauseSanityDamage(Player player) {
        if (ModCapabilities.getPlayerVariables(player).seabornization >= 3) return;
        ModCapabilities.getSanityInjury(player).hurt(player.getRandom().nextInt(16, 32));
    }
}
