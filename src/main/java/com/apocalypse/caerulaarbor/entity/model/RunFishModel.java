package com.apocalypse.caerulaarbor.entity.model;

import com.apocalypse.caerulaarbor.entity.RunFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

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
