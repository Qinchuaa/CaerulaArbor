package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.DeepSeaSliderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DeepSeaSliderModel extends GeoModel<DeepSeaSliderEntity> {

    @Override
    public ResourceLocation getAnimationResource(DeepSeaSliderEntity entity) {
        return CaerulaArborMod.loc("animations/deep_sea_slider.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(DeepSeaSliderEntity entity) {
        return CaerulaArborMod.loc("geo/deep_sea_slider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DeepSeaSliderEntity entity) {
        return CaerulaArborMod.loc("textures/entity/deep_sea_slider.png");
    }
}
