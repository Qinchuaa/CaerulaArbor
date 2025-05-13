package com.apocalypse.caerulaarbor.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import com.apocalypse.caerulaarbor.item.PhloemBowItem;

public class PhloemBowItemModel extends GeoModel<PhloemBowItem> {
	@Override
	public ResourceLocation getAnimationResource(PhloemBowItem animatable) {
		return new ResourceLocation("caerula_arbor", "animations/bluebow.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(PhloemBowItem animatable) {
		return new ResourceLocation("caerula_arbor", "geo/bluebow.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PhloemBowItem animatable) {
		return new ResourceLocation("caerula_arbor", "textures/item/combinedarrow.png");
	}
}
