package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.PathShaperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PathShaperModel extends GeoModel<PathShaperEntity> {

    @Override
    public ResourceLocation getAnimationResource(PathShaperEntity entity) {
        return CaerulaArborMod.loc("animations/path_shaper.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(PathShaperEntity entity) {
        return CaerulaArborMod.loc("geo/path_shaper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PathShaperEntity entity) {
        return CaerulaArborMod.loc("textures/entity/path_shaper.png");
    }
}
