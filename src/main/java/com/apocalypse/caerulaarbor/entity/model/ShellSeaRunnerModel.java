package com.apocalypse.caerulaarbor.entity.model;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.ShellSeaRunnerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShellSeaRunnerModel extends GeoModel<ShellSeaRunnerEntity> {

    @Override
    public ResourceLocation getAnimationResource(ShellSeaRunnerEntity entity) {
        return CaerulaArborMod.loc("animations/shell_sea_runner.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(ShellSeaRunnerEntity entity) {
        return CaerulaArborMod.loc("geo/shell_sea_runner.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShellSeaRunnerEntity entity) {
        return CaerulaArborMod.loc("textures/entity/shell_sea_runner.png");
    }
}
