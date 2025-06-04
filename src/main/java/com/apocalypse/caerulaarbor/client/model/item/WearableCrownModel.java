package com.apocalypse.caerulaarbor.client.model.item;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.item.WearableCrownItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WearableCrownModel extends GeoModel<WearableCrownItem> {

	@Override
	public ResourceLocation getAnimationResource(WearableCrownItem object) {
		return CaerulaArborMod.loc("animations/crown.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(WearableCrownItem object) {
		return CaerulaArborMod.loc("geo/crown.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(WearableCrownItem object) {
		return CaerulaArborMod.loc("textures/item/crown.png");
	}
}
