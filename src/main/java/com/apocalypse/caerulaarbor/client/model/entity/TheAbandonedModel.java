package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.TheAbandonedEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class TheAbandonedModel extends GeoModel<TheAbandonedEntity> {
    @Override
    public ResourceLocation getAnimationResource(TheAbandonedEntity entity) {
        return CaerulaArborMod.loc("animations/the_abandoned.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(TheAbandonedEntity entity) {
        return CaerulaArborMod.loc("geo/the_abandoned.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TheAbandonedEntity entity) {
        return CaerulaArborMod.loc("textures/entity/the_abandoned.png");
    }

    @Override
    public void setCustomAnimations(TheAbandonedEntity animatable, long instanceId, AnimationState<TheAbandonedEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("realhead");
        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
