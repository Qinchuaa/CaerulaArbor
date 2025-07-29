package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.DivicellularHoarderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DivicellularHoarderModel extends GeoModel<DivicellularHoarderEntity> {
    @Override
    public ResourceLocation getAnimationResource(DivicellularHoarderEntity entity) {
        return CaerulaArborMod.loc("animations/divicellular_hoarder.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(DivicellularHoarderEntity entity) {
        return CaerulaArborMod.loc("geo/divicellular_hoarder.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DivicellularHoarderEntity entity) {
        return CaerulaArborMod.loc("textures/entity/divicellular_hoarder.png");
    }
}
