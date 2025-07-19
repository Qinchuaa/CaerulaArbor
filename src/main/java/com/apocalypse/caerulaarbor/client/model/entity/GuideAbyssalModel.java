package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.NetherseaBrandguiderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GuideAbyssalModel extends GeoModel<NetherseaBrandguiderEntity> {
    @Override
    public ResourceLocation getAnimationResource(NetherseaBrandguiderEntity entity) {
        return new ResourceLocation("caerula_arbor", "animations/nethersea_brandguider.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NetherseaBrandguiderEntity entity) {
        return new ResourceLocation("caerula_arbor", "geo/nethersea_brandguider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NetherseaBrandguiderEntity entity) {
        return new ResourceLocation("caerula_arbor", "textures/entity/nethersea_brandguider.png");
    }

}
