package com.apocalypse.caerulaarbor.procedures;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.level.LevelAccessor;

public class EvoLvlMigraProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments) {
//		MapVariables.get(world).strategyMigration = IntegerArgumentType.getInteger(arguments, "lvl");
//		MapVariables.get(world).syncData(world);
//		if (DoubleArgumentType.getDouble(arguments, "lvl") >= 3) {
//			if (world instanceof Level _level) {
//				if (!_level.isClientSide()) {
//					_level.playSound(null, BlockPos.containing(x, y, z), ModSounds.MIGRATION2.get(), SoundSource.NEUTRAL, 4, 1);
//				} else {
//					_level.playLocalSound(x, y, z, ModSounds.MIGRATION2.get(), SoundSource.NEUTRAL, 4, 1, false);
//				}
//			}
//		} else if (DoubleArgumentType.getDouble(arguments, "lvl") > 0) {
//			if (world instanceof Level _level) {
//				if (!_level.isClientSide()) {
//					_level.playSound(null, BlockPos.containing(x, y, z), ModSounds.MIGRATION1.get(), SoundSource.NEUTRAL, 4, 1);
//				} else {
//					_level.playLocalSound(x, y, z, ModSounds.MIGRATION1.get(), SoundSource.NEUTRAL, 4, 1, false);
//				}
//			}
//		}
//		if (DoubleArgumentType.getDouble(arguments, "lvl") < 4) {
//			MapVariables.get(world).strategySilence = 0;
//			MapVariables.get(world).syncData(world);
//		}
	}
}
