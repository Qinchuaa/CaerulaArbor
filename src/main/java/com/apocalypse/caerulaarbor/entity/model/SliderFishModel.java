package com.apocalypse.caerulaarbor.entity.model;

import com.apocalypse.caerulaarbor.entity.SliderFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SliderFishModel extends GeoModel<SliderFishEntity> {
	@Override
	public ResourceLocation getAnimationResource(SliderFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/slidingfish.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SliderFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/slidingfish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SliderFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
