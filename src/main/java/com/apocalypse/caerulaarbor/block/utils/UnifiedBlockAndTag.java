package com.apocalypse.caerulaarbor.block.utils;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

public class UnifiedBlockAndTag {
    private final Object key;

    public UnifiedBlockAndTag(Block key) {
        this.key = key;
    }

    public UnifiedBlockAndTag(TagKey<Block> key){
        this.key = key;
    }

    @Override
    public boolean equals(Object a){
        if(this == a)return true;
        if(a == null || this.getClass() != a.getClass())return false;
        if(a instanceof UnifiedBlockAndTag another) return Objects.equals(this.key, another.key);
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(key);
    }
}
