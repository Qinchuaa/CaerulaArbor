package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.NetherseaFounderEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NetherseaFounderModel extends GeoModel<NetherseaFounderEntity> {

    @Override
    public ResourceLocation getAnimationResource(NetherseaFounderEntity entity) {
        return CaerulaArborMod.loc("animations/nethersea_founder.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NetherseaFounderEntity entity) {
        return CaerulaArborMod.loc("geo/nethersea_founder.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NetherseaFounderEntity entity) {
        return CaerulaArborMod.loc("textures/entity/nethersea_founder.png");
    }

    @Override
    public void setCustomAnimations(NetherseaFounderEntity animatable, long instanceId, AnimationState<NetherseaFounderEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        if (head == null) return;
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
