package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.PrimalSeaPiercerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PrimalSeaPiercerModel extends GeoModel<PrimalSeaPiercerEntity> {

    @Override
    public ResourceLocation getAnimationResource(PrimalSeaPiercerEntity entity) {
        return CaerulaArborMod.loc("animations/primal_sea_piercer.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(PrimalSeaPiercerEntity entity) {
        return CaerulaArborMod.loc("geo/primal_sea_piercer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PrimalSeaPiercerEntity entity) {
        return CaerulaArborMod.loc("textures/entity/primal_sea_piercer.png");
    }
}
