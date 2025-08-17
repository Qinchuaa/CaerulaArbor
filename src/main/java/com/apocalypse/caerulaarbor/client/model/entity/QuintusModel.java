package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.QuintusEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class QuintusModel extends GeoModel<QuintusEntity> {
    @Override
    public ResourceLocation getAnimationResource(QuintusEntity entity) {
        return CaerulaArborMod.loc("animations/quintus.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(QuintusEntity entity) {
        return CaerulaArborMod.loc("geo/quintus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(QuintusEntity entity) {
        return CaerulaArborMod.loc("textures/entity/quintus.png");
    }
}
