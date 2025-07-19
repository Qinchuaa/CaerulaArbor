package com.apocalypse.caerulaarbor.client.model.entity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.RouteShaperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RouteShaperModel extends GeoModel<RouteShaperEntity> {
	@Override
	public ResourceLocation getAnimationResource(RouteShaperEntity entity) {
		return CaerulaArborMod.loc("animations/routeshaper.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(RouteShaperEntity entity) {
		return CaerulaArborMod.loc("geo/routeshaper.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(RouteShaperEntity entity) {
		return CaerulaArborMod.loc("textures/entities/" + entity.getTexture() + ".png");
	}

}
