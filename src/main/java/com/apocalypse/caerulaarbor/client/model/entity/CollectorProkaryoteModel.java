package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.entity.CollectorProkaryoteEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CollectorProkaryoteModel extends GeoModel<CollectorProkaryoteEntity> {
	@Override
	public ResourceLocation getAnimationResource(CollectorProkaryoteEntity entity) {
		return new ResourceLocation("caerula_arbor", "animations/collector.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CollectorProkaryoteEntity entity) {
		return new ResourceLocation("caerula_arbor", "geo/collector.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CollectorProkaryoteEntity entity) {
		return new ResourceLocation("caerula_arbor", "textures/entities/" + entity.getTexture() + ".png");
	}

}
