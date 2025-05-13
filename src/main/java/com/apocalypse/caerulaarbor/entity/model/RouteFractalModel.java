package com.apocalypse.caerulaarbor.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import com.apocalypse.caerulaarbor.entity.RouteFractalEntity;

public class RouteFractalModel extends GeoModel<RouteFractalEntity> {
	@Override
	public ResourceLocation getAnimationResource(RouteFractalEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/routeshaper.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(RouteFractalEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/routeshaper.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(RouteFractalEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
