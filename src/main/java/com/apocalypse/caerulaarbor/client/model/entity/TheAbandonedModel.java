package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.TheAbandonedEntity;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

public class TheAbandonedModel extends GeoModel<TheAbandonedEntity> {
	@Override
	public ResourceLocation getAnimationResource(TheAbandonedEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/the_abandoned.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TheAbandonedEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/the_abandoned.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TheAbandonedEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entity/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(TheAbandonedEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("realhead");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
