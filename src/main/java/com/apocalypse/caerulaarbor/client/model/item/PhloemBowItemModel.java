package com.apocalypse.caerulaarbor.client.model.item;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.item.PhloemBowItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PhloemBowItemModel extends GeoModel<PhloemBowItem> {

	@Override
	public ResourceLocation getAnimationResource(PhloemBowItem animatable) {
		return CaerulaArborMod.loc("animations/bluebow.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(PhloemBowItem animatable) {
		return CaerulaArborMod.loc("geo/bluebow.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PhloemBowItem animatable) {
		return CaerulaArborMod.loc("textures/item/combinedarrow.png");
	}
}
