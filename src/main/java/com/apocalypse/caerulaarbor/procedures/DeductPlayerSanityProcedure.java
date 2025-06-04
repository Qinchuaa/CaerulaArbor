package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModAttributes;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class DeductPlayerSanityProcedure {
    public static void execute(Entity entity, double value) {
        if (!(entity instanceof Player player) || player.hasEffect(ModMobEffects.SANITY_IMMUNE.get())) return;

        var sanAttribute = player.getAttribute(ModAttributes.SANITY.get());
        var sanModifierAttribute = player.getAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get());

        var san = sanAttribute == null ? 0 : sanAttribute.getValue();
        var sanModifier = sanModifierAttribute == null ? 0 : sanModifierAttribute.getValue();

        if (sanAttribute != null) {
            sanAttribute.setBaseValue(Mth.clamp(san - value * sanModifier, -1, 1000));
        }
    }
}
