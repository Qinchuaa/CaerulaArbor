package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.BasinSeaReaperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BasinSeaReaperModel extends GeoModel<BasinSeaReaperEntity> {

    @Override
    public ResourceLocation getAnimationResource(BasinSeaReaperEntity entity) {
        return CaerulaArborMod.loc("animations/basin_sea_reaper.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(BasinSeaReaperEntity entity) {
        return CaerulaArborMod.loc("geo/basin_sea_reaper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BasinSeaReaperEntity entity) {
        return CaerulaArborMod.loc("textures/entity/basin_sea_reaper.png");
    }
}
