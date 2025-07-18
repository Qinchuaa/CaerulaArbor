package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.FloatingSeaDrifterEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FloatingSeaDrifterModel extends GeoModel<FloatingSeaDrifterEntity> {

    @Override
    public ResourceLocation getAnimationResource(FloatingSeaDrifterEntity entity) {
        return CaerulaArborMod.loc("animations/floating_sea_drifter.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(FloatingSeaDrifterEntity entity) {
        return CaerulaArborMod.loc("geo/floating_sea_drifter.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FloatingSeaDrifterEntity entity) {
        return CaerulaArborMod.loc("textures/entity/floating_sea_drifter.png");
    }

    @Override
    public void setCustomAnimations(FloatingSeaDrifterEntity animatable, long instanceId, AnimationState<FloatingSeaDrifterEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("body");
        if (head == null) return;
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
