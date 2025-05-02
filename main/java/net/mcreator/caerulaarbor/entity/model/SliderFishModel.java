package net.mcreator.caerulaarbor.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.entity.SliderFishEntity;

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
