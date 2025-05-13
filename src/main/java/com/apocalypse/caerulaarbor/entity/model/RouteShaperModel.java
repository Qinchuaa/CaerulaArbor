package com.apocalypse.caerulaarbor.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import com.apocalypse.caerulaarbor.entity.RouteShaperEntity;

public class RouteShaperModel extends GeoModel<RouteShaperEntity> {
	@Override
	public ResourceLocation getAnimationResource(RouteShaperEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/routeshaper.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(RouteShaperEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/routeshaper.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(RouteShaperEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
