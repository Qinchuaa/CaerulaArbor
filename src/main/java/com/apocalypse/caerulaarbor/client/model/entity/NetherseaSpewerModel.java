package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.NetherseaSpewerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NetherseaSpewerModel extends GeoModel<NetherseaSpewerEntity> {

    @Override
    public ResourceLocation getAnimationResource(NetherseaSpewerEntity entity) {
        return CaerulaArborMod.loc("animations/nethersea_spewer.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NetherseaSpewerEntity entity) {
        return CaerulaArborMod.loc("geo/nethersea_spewer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NetherseaSpewerEntity entity) {
        return CaerulaArborMod.loc("textures/entity/nethersea_spewer.png");
    }
}
