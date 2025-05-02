
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CaerulaArborModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> TARGET_LIFE_FUNCTION = GameRules.register("targetLifeFunction", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
}
