package com.apocalypse.caerulaarbor.entity.model;

import com.apocalypse.caerulaarbor.entity.PunctureFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PunctureFishModel extends GeoModel<PunctureFishEntity> {
	@Override
	public ResourceLocation getAnimationResource(PunctureFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/puncturefish.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(PunctureFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/puncturefish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PunctureFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
