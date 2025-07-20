package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.SuperSliderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SuperSliderModel extends GeoModel<SuperSliderEntity> {

    @Override
    public ResourceLocation getAnimationResource(SuperSliderEntity entity) {
        return CaerulaArborMod.loc("animations/deep_sea_slider.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(SuperSliderEntity entity) {
        return CaerulaArborMod.loc("geo/deep_sea_slider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SuperSliderEntity entity) {
        return CaerulaArborMod.loc("textures/entity/deep_sea_slider.png");
    }
}
