package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.TidelinkedImmortalEntity;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

public class TidelinkedImmortalModel extends GeoModel<TidelinkedImmortalEntity> {
	@Override
	public ResourceLocation getAnimationResource(TidelinkedImmortalEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/tidelinked_immortal.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TidelinkedImmortalEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/tidelinked_immortal.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TidelinkedImmortalEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entity/tidelinked_immortal.png");
	}

	@Override
	public void setCustomAnimations(TidelinkedImmortalEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("Head");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
