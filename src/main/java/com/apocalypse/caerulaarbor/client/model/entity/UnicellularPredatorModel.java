package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.UnicellularPredatorEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class UnicellularPredatorModel extends GeoModel<UnicellularPredatorEntity> {

    @Override
    public ResourceLocation getAnimationResource(UnicellularPredatorEntity entity) {
        return CaerulaArborMod.loc("animations/unicellular_predator.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(UnicellularPredatorEntity entity) {
        return CaerulaArborMod.loc("geo/unicellular_predator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(UnicellularPredatorEntity entity) {
        return CaerulaArborMod.loc("textures/entity/unicellular_predator.png");
    }
}
