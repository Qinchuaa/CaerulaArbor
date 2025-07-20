package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.PocketSeaCrawlerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class PocketSeaCrawlerModel extends GeoModel<PocketSeaCrawlerEntity> {

    @Override
    public ResourceLocation getAnimationResource(PocketSeaCrawlerEntity entity) {
        return CaerulaArborMod.loc("animations/pocket_sea_crawler.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(PocketSeaCrawlerEntity entity) {
        return CaerulaArborMod.loc("geo/pocket_sea_crawler.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PocketSeaCrawlerEntity entity) {
        return CaerulaArborMod.loc("textures/entity/pocket_sea_crawler.png");
    }

    @Override
    public void setCustomAnimations(PocketSeaCrawlerEntity animatable, long instanceId, AnimationState<PocketSeaCrawlerEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        if (head == null) return;
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
        head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
    }
}
