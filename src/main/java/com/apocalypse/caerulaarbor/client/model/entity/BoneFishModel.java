package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.BoneFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

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
