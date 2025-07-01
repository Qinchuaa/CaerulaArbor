package com.apocalypse.caerulaarbor.entity.model;

import com.apocalypse.caerulaarbor.entity.DeepSeaSliderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SliderFishModel extends GeoModel<DeepSeaSliderEntity> {
	@Override
	public ResourceLocation getAnimationResource(DeepSeaSliderEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/slidingfish.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(DeepSeaSliderEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/slidingfish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DeepSeaSliderEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
