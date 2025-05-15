package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.init.ModItems;
import com.apocalypse.caerulaarbor.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture,
                              CompletableFuture<TagLookup<Block>> tagLookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, providerCompletableFuture, tagLookupCompletableFuture, CaerulaArborMod.MODID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(ModTags.Items.RELICS)
                .addTags(ModTags.Items.ARCHFIEND_RELICS, ModTags.Items.KING_RELICS, ModTags.Items.CURSED_RELICS, ModTags.Items.HAND_RELICS);

        this.tag(ModTags.Items.CURSED_RELICS)
                .add(ModItems.RELIC_CURSE_EMELIGHT.get(), ModItems.RELIC_CURSED_RESEARCH.get(), ModItems.RELIC_CURSED_GLOWBODY.get());
        this.tag(ModTags.Items.HAND_RELICS)
                .add(ModItems.HAND_OF_BARREN.get(), ModItems.HAND_OF_ENGRAVE.get(), ModItems.HAND_OF_FERTILIY.get(), ModItems.HAND_OF_SPEED.get(),
                        ModItems.HAND_OF_FIREWORK.get(), ModItems.HAND_OF_SPOTLESS.get(), ModItems.HAND_OF_STRANGLE.get(), ModItems.HAND_OF_THORNS.get());
        this.tag(ModTags.Items.KING_RELICS).add(ModItems.KINGS_ARMOR.get(), ModItems.KINGS_SPEAR.get(), ModItems.KINGS_CRYSTAL.get(),
                ModItems.KINGS_EXTENSION.get(), ModItems.RELIC_CROWN.get());
        this.tag(ModTags.Items.ARCHFIEND_RELICS).add(ModItems.ARCHFIENDS_ARTIFACT.get(), ModItems.HAND_OF_FIREWORK.get(), ModItems.ARCHFIENDS_FLAG.get(),
                ModItems.ARCHFIENDS_BED.get(), ModItems.ROYAL_FATE.get());
        this.tag(ModTags.Items.SELF_MENDABLE).add(ModItems.COMPLEX_CHITIN_AXE.get(), ModItems.COMPLEX_CHITIN_HOE.get(), ModItems.COMPLEX_CHITIN_PICKAXE.get(),
                ModItems.COMPLEX_CHITIN_SHOVEL.get(), ModItems.COMPLEX_CHITIN_SWORD.get(), ModItems.LEGENDARY_SPEAR.get(), ModItems.TRAILED_WOODEN_SWORD.get(),
                ModItems.TRAILED_STONE_SWORD.get(), ModItems.TRAILED_IRON_SWORD.get(), ModItems.TRAILED_DIAMOND_SWORD.get(), ModItems.TRAILED_NETHERITE_SWORD.get(),
                ModItems.TRAILED_GOLDEN_SWORD.get(), ModItems.PHLOEM_BOW.get());
    }

    public static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }
}
