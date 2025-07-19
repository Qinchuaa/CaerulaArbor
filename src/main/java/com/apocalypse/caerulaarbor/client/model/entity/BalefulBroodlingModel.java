package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.BalefulBroodlingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BalefulBroodlingModel extends GeoModel<BalefulBroodlingEntity> {

    @Override
    public ResourceLocation getAnimationResource(BalefulBroodlingEntity entity) {
        return CaerulaArborMod.loc("animations/baleful_broodling.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(BalefulBroodlingEntity entity) {
        return CaerulaArborMod.loc("geo/baleful_broodling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BalefulBroodlingEntity entity) {
        return CaerulaArborMod.loc("textures/entity/baleful_broodling.png");
    }

    @Override
    public void setCustomAnimations(BalefulBroodlingEntity animatable, long instanceId, AnimationState<BalefulBroodlingEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("ball");
        if (head == null) return;
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
