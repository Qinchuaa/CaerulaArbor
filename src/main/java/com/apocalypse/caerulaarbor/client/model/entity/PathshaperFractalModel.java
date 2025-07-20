package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.PathshaperFractalEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PathshaperFractalModel extends GeoModel<PathshaperFractalEntity> {

    @Override
    public ResourceLocation getAnimationResource(PathshaperFractalEntity entity) {
        return CaerulaArborMod.loc("animations/path_shaper.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(PathshaperFractalEntity entity) {
        return CaerulaArborMod.loc("geo/path_shaper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PathshaperFractalEntity entity) {
        return CaerulaArborMod.loc("textures/entity/pathshaper_fractal.png");
    }
}
