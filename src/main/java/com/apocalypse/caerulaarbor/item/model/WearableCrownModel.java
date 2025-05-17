package com.apocalypse.caerulaarbor.item.model;

import com.apocalypse.caerulaarbor.item.WearableCrownItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

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
