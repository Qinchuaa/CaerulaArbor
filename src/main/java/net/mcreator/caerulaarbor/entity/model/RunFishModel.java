package net.mcreator.caerulaarbor.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.entity.RunFishEntity;

public class RunFishModel extends GeoModel<RunFishEntity> {
	@Override
	public ResourceLocation getAnimationResource(RunFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/runfish.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(RunFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/runfish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(RunFishEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
