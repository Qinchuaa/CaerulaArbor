package com.apocalypse.caerulaarbor.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import com.apocalypse.caerulaarbor.entity.SuperSliderEntity;

public class SuperSliderModel extends GeoModel<SuperSliderEntity> {
	@Override
	public ResourceLocation getAnimationResource(SuperSliderEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/slidingfish.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SuperSliderEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/slidingfish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SuperSliderEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
