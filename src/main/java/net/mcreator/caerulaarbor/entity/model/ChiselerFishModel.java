package net.mcreator.caerulaarbor.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.entity.ChiselerFishEntity;

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
