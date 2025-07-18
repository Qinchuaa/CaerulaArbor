package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.NetherseaPredatorEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NetherseaPredatorModel extends GeoModel<NetherseaPredatorEntity> {

    @Override
    public ResourceLocation getAnimationResource(NetherseaPredatorEntity entity) {
        return CaerulaArborMod.loc("animations/nethersea_predator.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NetherseaPredatorEntity entity) {
        return CaerulaArborMod.loc("geo/nethersea_predator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NetherseaPredatorEntity entity) {
        return CaerulaArborMod.loc("textures/entity/nethersea_predator.png");
    }

    @Override
    public void setCustomAnimations(NetherseaPredatorEntity animatable, long instanceId, AnimationState<NetherseaPredatorEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        if (head == null) return;
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
