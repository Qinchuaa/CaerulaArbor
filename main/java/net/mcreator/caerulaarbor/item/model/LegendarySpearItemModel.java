package net.mcreator.caerulaarbor.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.item.LegendarySpearItem;

public class LegendarySpearItemModel extends GeoModel<LegendarySpearItem> {
	@Override
	public ResourceLocation getAnimationResource(LegendarySpearItem animatable) {
		return new ResourceLocation("caerula_arbor", "animations/lengdendaryspear.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(LegendarySpearItem animatable) {
		return new ResourceLocation("caerula_arbor", "geo/lengdendaryspear.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(LegendarySpearItem animatable) {
		return new ResourceLocation("caerula_arbor", "textures/item/superchitinspear.png");
	}
}
