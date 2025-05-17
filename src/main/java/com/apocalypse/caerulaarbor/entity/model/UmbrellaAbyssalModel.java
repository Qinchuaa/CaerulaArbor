package com.apocalypse.caerulaarbor.entity.model;

import com.apocalypse.caerulaarbor.entity.UmbrellaAbyssalEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class UmbrellaAbyssalModel extends GeoModel<UmbrellaAbyssalEntity> {
	@Override
	public ResourceLocation getAnimationResource(UmbrellaAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/umbrella.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(UmbrellaAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/umbrella.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(UmbrellaAbyssalEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
