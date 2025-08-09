package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Items {
        public static final TagKey<Item> RELICS = tag("relics");
        public static final TagKey<Item> HAND_RELICS = tag("relics/hand");
        public static final TagKey<Item> KING_RELICS = tag("relics/king");
        public static final TagKey<Item> CURSED_RELICS = tag("relics/cursed");
        public static final TagKey<Item> ARCHFIEND_RELICS = tag("relics/archfiend");

        public static final TagKey<Item> SELF_MENDABLE = tag("self_mendable");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(CaerulaArborMod.loc(name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> ERRODABLE = tag("errodable");
        public static final TagKey<Block> SEA_TRAIL = tag("sea_trail");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(CaerulaArborMod.loc(name));
        }
    }

    public static class EntityTypes {
        public static final TagKey<EntityType<?>> SEA_BORN = tag("sea_born");
        public static final TagKey<EntityType<?>> SEA_BORN_CREATURE = tag("sea_born_creature");
        public static final TagKey<EntityType<?>> SEA_BORN_BOSS = tag("sea_born_boss");
        public static final TagKey<EntityType<?>> SEABORN_ELITE = tag("seaborn_elite"); // TODO 改名+添加TagProvider

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, CaerulaArborMod.loc(name));
        }
    }

    public static class DamageTypes {
        public static final TagKey<DamageType> BYPASS_NEURODEGENERATION = tag("bypass_neurodegeneration");

        private static TagKey<DamageType> tag(String name) {
            return TagKey.create(Registries.DAMAGE_TYPE, CaerulaArborMod.loc(name));
        }
    }
}
