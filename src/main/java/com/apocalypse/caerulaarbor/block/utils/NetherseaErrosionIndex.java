package com.apocalypse.caerulaarbor.block.utils;

import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class NetherseaErrosionIndex {
    public Map<UnifiedBlockAndTag, Block> index = Map.ofEntries(
            record(BlockTags.LOGS, ModBlocks.NETHERSEA_LOG.get()),
            record(BlockTags.PLANKS, ModBlocks.NETHERSEA_PLANKS.get()),
            record(BlockTags.LEAVES, ModBlocks.NETHERSEA_LEAVES.get())
    );

    public boolean recorded(Block block){
        return index.containsKey(new UnifiedBlockAndTag(block));
    }

    public boolean recorded(TagKey<Block> block){
        return index.containsKey(new UnifiedBlockAndTag(block));
    }

    @Nullable
    public Block get(Block block){
        if (!recorded(block)) return null;
        return index.get(new UnifiedBlockAndTag(block));
    }

    @Nullable
    public Block get(TagKey<Block> block){
        if (!recorded(block)) return null;
        return index.get(new UnifiedBlockAndTag(block));
    }

    @Nullable
    public Block get(BlockState state){
        Block block = state.getBlock();
        return this.get(block);
    }

    private Map.Entry<UnifiedBlockAndTag, Block> record(Block block1 , Block block2){
        return Map.entry(new UnifiedBlockAndTag(block1), block2);
    }

    private Map.Entry<UnifiedBlockAndTag, Block> record(TagKey<Block> block1 , Block block2){
        return Map.entry(new UnifiedBlockAndTag(block1), block2);
    }
}
