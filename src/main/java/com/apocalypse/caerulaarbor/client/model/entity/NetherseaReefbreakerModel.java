package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.NetherseaReefbreakerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NetherseaReefbreakerModel extends GeoModel<NetherseaReefbreakerEntity> {

    @Override
    public ResourceLocation getAnimationResource(NetherseaReefbreakerEntity entity) {
        return CaerulaArborMod.loc("animations/nethersea_reefbreaker.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NetherseaReefbreakerEntity entity) {
        return CaerulaArborMod.loc("geo/nethersea_reefbreaker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NetherseaReefbreakerEntity entity) {
        return CaerulaArborMod.loc("textures/entity/nethersea_reefbreaker.png");
    }

    @Override
    public void setCustomAnimations(NetherseaReefbreakerEntity animatable, long instanceId, AnimationState<NetherseaReefbreakerEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        if (head == null) return;
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
