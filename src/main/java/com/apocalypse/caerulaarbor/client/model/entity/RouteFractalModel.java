package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.RouteFractalEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RouteFractalModel extends GeoModel<RouteFractalEntity> {
	@Override
	public ResourceLocation getAnimationResource(RouteFractalEntity entity) {
		return CaerulaArborMod.loc("animations/routeshaper.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(RouteFractalEntity entity) {
		return CaerulaArborMod.loc("geo/routeshaper.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(RouteFractalEntity entity) {
		return CaerulaArborMod.loc("textures/entities/" + entity.getTexture() + ".png");
	}

}
