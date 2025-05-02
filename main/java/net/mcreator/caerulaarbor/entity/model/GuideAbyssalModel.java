package net.mcreator.caerulaarbor.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.entity.GuideAbyssalEntity;

public class GuideAbyssalModel extends GeoModel<GuideAbyssalEntity> {
	@Override
	public ResourceLocation getAnimationResource(GuideAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/tracerabyssal.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(GuideAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/tracerabyssal.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(GuideAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
