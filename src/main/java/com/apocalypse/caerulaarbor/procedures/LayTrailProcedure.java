package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class LayTrailProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (ModBlocks.SEA_TRAIL_GROWN.get().defaultBlockState().canSurvive(world, BlockPos.containing(x, y, z)) && (world.getBlockState(BlockPos.containing(x, y, z))).canBeReplaced()) {
			if (GameplayConfig.ENABLE_MOB_BREAK.get() && world.getLevelData().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.place")), SoundSource.NEUTRAL, 2, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.place")), SoundSource.NEUTRAL, 2, 1, false);
					}
				}
				world.setBlock(BlockPos.containing(x, y, z), ModBlocks.SEA_TRAIL_GROWN.get().defaultBlockState(), 3);
				world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(ModBlocks.SEA_TRAIL_GROWN.get().defaultBlockState()));
			}
		}
	}
}
