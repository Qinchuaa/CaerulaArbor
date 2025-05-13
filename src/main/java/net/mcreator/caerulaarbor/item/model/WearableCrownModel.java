package net.mcreator.caerulaarbor.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.item.WearableCrownItem;

public class WearableCrownModel extends GeoModel<WearableCrownItem> {
	@Override
	public ResourceLocation getAnimationResource(WearableCrownItem object) {
		return new ResourceLocation("caerula_arbor", "animations/crown.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(WearableCrownItem object) {
		return new ResourceLocation("caerula_arbor", "geo/crown.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(WearableCrownItem object) {
		return new ResourceLocation("caerula_arbor", "textures/item/crown.png");
	}
}
