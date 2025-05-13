package net.mcreator.caerulaarbor.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.entity.FakeOffspringEntity;

public class FakeOffspringModel extends GeoModel<FakeOffspringEntity> {
	@Override
	public ResourceLocation getAnimationResource(FakeOffspringEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/fakeegg.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(FakeOffspringEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/fakeegg.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(FakeOffspringEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(FakeOffspringEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("ball");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
