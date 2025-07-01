package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.ReaperFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ReaperFishModel extends GeoModel<ReaperFishEntity> {
	@Override
	public ResourceLocation getAnimationResource(ReaperFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/reaperfish.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ReaperFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/reaperfish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ReaperFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
