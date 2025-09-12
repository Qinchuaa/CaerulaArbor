package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.FilialGenerationEntity;
import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

public class FilialGenerationModel extends GeoModel<FilialGenerationEntity> {
	@Override
	public ResourceLocation getAnimationResource(FilialGenerationEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/filial_generation.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(FilialGenerationEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/filial_generation.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(FilialGenerationEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entity/filial_generation.png");
	}

}
