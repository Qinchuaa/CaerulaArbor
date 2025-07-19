package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.SkimmingSeaDrifterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkimmingSeaDrifterModel extends GeoModel<SkimmingSeaDrifterEntity> {

    @Override
    public ResourceLocation getAnimationResource(SkimmingSeaDrifterEntity entity) {
        return CaerulaArborMod.loc("animations/skimming_sea_drifter.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(SkimmingSeaDrifterEntity entity) {
        return CaerulaArborMod.loc("geo/skimming_sea_drifter.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SkimmingSeaDrifterEntity entity) {
        return CaerulaArborMod.loc("textures/entity/skimming_sea_drifter.png");
    }
}
