package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.init.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagProvider extends DamageTypeTagsProvider {

    public ModDamageTypeTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, CaerulaArborMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(DamageTypeTags.BYPASSES_RESISTANCE).add(ModDamageTypes.NERVOUS_IMPAIRMENT);
        this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(ModDamageTypes.NERVOUS_IMPAIRMENT);
        this.tag(DamageTypeTags.BYPASSES_EFFECTS).add(ModDamageTypes.NERVOUS_IMPAIRMENT);
        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(ModDamageTypes.NERVOUS_IMPAIRMENT);
        this.tag(DamageTypeTags.BYPASSES_COOLDOWN).add(ModDamageTypes.NERVOUS_IMPAIRMENT);
        this.tag(DamageTypeTags.BYPASSES_SHIELD).add(ModDamageTypes.NERVOUS_IMPAIRMENT);
    }
}
