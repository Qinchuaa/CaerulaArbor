package com.apocalypse.caerulaarbor.client.model.item;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.item.LegendarySpearItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LegendarySpearItemModel extends GeoModel<LegendarySpearItem> {

    @Override
    public ResourceLocation getAnimationResource(LegendarySpearItem animatable) {
        return CaerulaArborMod.loc("animations/legendary_spear.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(LegendarySpearItem animatable) {
        return CaerulaArborMod.loc("geo/legendary_spear.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LegendarySpearItem animatable) {
        return CaerulaArborMod.loc("textures/item/legendary_spear.png");
    }
}
