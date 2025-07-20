package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.NetherseaSwarmcallerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NetherseaSwarmcallerModel extends GeoModel<NetherseaSwarmcallerEntity> {

    @Override
    public ResourceLocation getAnimationResource(NetherseaSwarmcallerEntity entity) {
        return CaerulaArborMod.loc("animations/nethersea_swarmcaller.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NetherseaSwarmcallerEntity entity) {
        return CaerulaArborMod.loc("geo/nethersea_swarmcaller.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NetherseaSwarmcallerEntity entity) {
        return CaerulaArborMod.loc("textures/entity/nethersea_swarmcaller.png");
    }
}
