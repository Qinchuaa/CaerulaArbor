package com.apocalypse.caerulaarbor.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import com.apocalypse.caerulaarbor.entity.BoneFishEntity;

public class BoneFishModel extends GeoModel<BoneFishEntity> {
	@Override
	public ResourceLocation getAnimationResource(BoneFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/bonefish.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(BoneFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/bonefish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(BoneFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
