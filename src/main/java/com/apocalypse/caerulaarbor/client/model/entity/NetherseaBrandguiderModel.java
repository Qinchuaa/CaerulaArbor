package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.NetherseaBrandguiderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NetherseaBrandguiderModel extends GeoModel<NetherseaBrandguiderEntity> {

    @Override
    public ResourceLocation getAnimationResource(NetherseaBrandguiderEntity entity) {
        return CaerulaArborMod.loc("animations/nethersea_brandguider.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NetherseaBrandguiderEntity entity) {
        return CaerulaArborMod.loc("geo/nethersea_brandguider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NetherseaBrandguiderEntity entity) {
        return CaerulaArborMod.loc("textures/entity/nethersea_brandguider.png");
    }
}
