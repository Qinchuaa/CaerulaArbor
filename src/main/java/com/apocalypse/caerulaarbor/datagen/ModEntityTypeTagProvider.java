package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.init.ModEntities;
import com.apocalypse.caerulaarbor.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends EntityTypeTagsProvider {

    public ModEntityTypeTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, CaerulaArborMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.EntityTypes.SEA_BORN).add(
                ModEntities.SHELL_SEA_RUNNER.get(),
                ModEntities.DEEP_SEA_SLIDER.get(),
                ModEntities.RIDGE_SEA_SPITTER.get(),
                ModEntities.FLOATING_SEA_DRIFTER.get(),
                ModEntities.BASIN_SEA_REAPER.get(),
                ModEntities.POCKET_SEA_CRAWLER.get(),
                ModEntities.PRIMAL_SEA_PIERCER.get(),
                ModEntities.NETHERSEA_FOUNDER.get(),
                ModEntities.NETHERSEA_PREDATOR.get(),
                ModEntities.NETHERSEA_BRANDGUIDER.get(),
                ModEntities.NETHERSEA_SPEWER.get(),
                ModEntities.NETHERSEA_SWARMCALLER.get(),
                ModEntities.NETHERSEA_REEFBREAKER.get(),
                ModEntities.UNICELLULAR_PREDATOR.get(),
                ModEntities.BONE_SEA_DRIFTER.get(),
                ModEntities.OCEAN_STONECUTTER.get(),
                ModEntities.RETCHING_BROODMOTHER.get(),
                ModEntities.SKIMMING_SEA_DRIFTER.get()
        ).addTag(ModTags.EntityTypes.SEA_BORN_BOSS).addTag(ModTags.EntityTypes.SEA_BORN_CREATURE);

        this.tag(ModTags.EntityTypes.SEA_BORN_CREATURE).add(
                ModEntities.BALEFUL_BROODLING.get(),
                ModEntities.PATHSHAPER_FRACTAL.get()
        );

        this.tag(ModTags.EntityTypes.SEA_BORN_BOSS).add(
                ModEntities.SUPER_SLIDER.get(),
                ModEntities.PATH_SHAPER.get()
        );
    }
}
