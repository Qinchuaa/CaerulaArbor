package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.block.entity.NetherseaLogBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class NetherseaLogBlock extends RotatedPillarBlock implements EntityBlock {
    public NetherseaLogBlock() {
        super(Properties.of()
                .instrument(NoteBlockInstrument.BASS)
                .strength(3F, 5f)
                .sound(SoundType.WOOD)
                .speedFactor(0.9f)
                .ignitedByLava());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new NetherseaLogBlockEntity(pPos, pState);

    }



}
