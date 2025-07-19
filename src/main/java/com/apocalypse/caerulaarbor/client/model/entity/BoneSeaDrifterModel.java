package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.BoneSeaDrifterEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BoneSeaDrifterModel extends GeoModel<BoneSeaDrifterEntity> {

    @Override
    public ResourceLocation getAnimationResource(BoneSeaDrifterEntity entity) {
        return CaerulaArborMod.loc("animations/bone_sea_drifter.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(BoneSeaDrifterEntity entity) {
        return CaerulaArborMod.loc("geo/bone_sea_drifter.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoneSeaDrifterEntity entity) {
        return CaerulaArborMod.loc("textures/entity/bone_sea_drifter.png");
    }
}
