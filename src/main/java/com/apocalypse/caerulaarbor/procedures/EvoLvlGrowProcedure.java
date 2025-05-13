package com.apocalypse.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class EvoLvlGrowProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments) {
		CaerulaArborModVariables.MapVariables.get(world).strategy_grow = DoubleArgumentType.getDouble(arguments, "lvl");
		CaerulaArborModVariables.MapVariables.get(world).syncData(world);
		if (DoubleArgumentType.getDouble(arguments, "lvl") >= 3) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:grow2")), SoundSource.NEUTRAL, 4, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:grow2")), SoundSource.NEUTRAL, 4, 1, false);
				}
			}
		} else if (DoubleArgumentType.getDouble(arguments, "lvl") > 0) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:grow1")), SoundSource.NEUTRAL, 4, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:grow1")), SoundSource.NEUTRAL, 4, 1, false);
				}
			}
		}
		if (DoubleArgumentType.getDouble(arguments, "lvl") < 4) {
			CaerulaArborModVariables.MapVariables.get(world).strategy_silence = 0;
			CaerulaArborModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
