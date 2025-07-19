package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.RetchingBroodmotherEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RetchingBroodmotherModel extends GeoModel<RetchingBroodmotherEntity> {

    @Override
    public ResourceLocation getAnimationResource(RetchingBroodmotherEntity entity) {
        return CaerulaArborMod.loc("animations/retching_broodmother.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(RetchingBroodmotherEntity entity) {
        return CaerulaArborMod.loc("geo/retching_broodmother.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RetchingBroodmotherEntity entity) {
        return CaerulaArborMod.loc("textures/entity/retching_broodmother.png");
    }

    @Override
    public void setCustomAnimations(RetchingBroodmotherEntity animatable, long instanceId, AnimationState<RetchingBroodmotherEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        if (head == null) return;
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
