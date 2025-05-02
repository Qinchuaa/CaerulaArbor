package net.mcreator.caerulaarbor.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.entity.SplasherAbyssalEntity;

public class SplasherAbyssalModel extends GeoModel<SplasherAbyssalEntity> {
	@Override
	public ResourceLocation getAnimationResource(SplasherAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/splasher.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SplasherAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/splasher.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SplasherAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
