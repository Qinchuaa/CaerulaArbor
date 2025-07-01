package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.FleeFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FleeFishModel extends GeoModel<FleeFishEntity> {
	@Override
	public ResourceLocation getAnimationResource(FleeFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/fleefish.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(FleeFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/fleefish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(FleeFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
