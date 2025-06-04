package com.apocalypse.caerulaarbor.init;

import net.minecraft.world.level.GameRules;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModGameRules {

    public static final GameRules.Key<GameRules.BooleanValue> TARGET_LIFE_FUNCTION = GameRules.register("targetLifeFunction", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
    public static final GameRules.Key<GameRules.BooleanValue> NATURAL_EVOLUTION = GameRules.register("naturalEvolution", GameRules.Category.MOBS, GameRules.BooleanValue.create(true));
}
