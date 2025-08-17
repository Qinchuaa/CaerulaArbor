package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.QuintusEntity;
import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

public class BishopFishModel extends GeoModel<QuintusEntity> {
	@Override
	public ResourceLocation getAnimationResource(QuintusEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/quintus.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(QuintusEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/quintus.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(QuintusEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entity/" + entity.getTexture() + ".png");
	}

}
