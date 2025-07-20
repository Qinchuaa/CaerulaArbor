package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.OceanStonecutterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OceanStonecutterModel extends GeoModel<OceanStonecutterEntity> {

    @Override
    public ResourceLocation getAnimationResource(OceanStonecutterEntity entity) {
        return CaerulaArborMod.loc("animations/ocean_stonecutter.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(OceanStonecutterEntity entity) {
        return CaerulaArborMod.loc("geo/ocean_stonecutter.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OceanStonecutterEntity entity) {
        return CaerulaArborMod.loc("textures/entity/ocean_stonecutter.png");
    }
}
