package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.ChiselerFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChiselerFishModel extends GeoModel<ChiselerFishEntity> {
	@Override
	public ResourceLocation getAnimationResource(ChiselerFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/chiseler.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ChiselerFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/chiseler.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ChiselerFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
