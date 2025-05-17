package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.entity.ReaperFishEntity;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class DestroyBlocksProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		double limithard = 0;
		double hardness = 0;
		boolean once = false;
		if (entity instanceof ReaperFishEntity) {
			limithard = -1;
			if (CaerulaArborModVariables.MapVariables.get(world).strategy_migration >= 2) {
				limithard = 3.5;
			} else if (CaerulaArborModVariables.MapVariables.get(world).strategy_migration >= 4) {
				limithard = 5;
			}
			if (ModGriefSettingsProcedure.execute(world) && limithard > 0) {
				if (Math.random() < 0.5) {
					once = false;
					dx = -1;
					for (int index0 = 0; index0 < 3; index0++) {
						dz = -1;
						for (int index1 = 0; index1 < 3; index1++) {
							dy = 1;
							for (int index2 = 0; index2 < 3; index2++) {
								hardness = (world.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz))).getDestroySpeed(world, BlockPos.containing(0, 0, 0));
								if (hardness <= limithard && hardness >= 0 && world.getBlockFloorHeight(BlockPos.containing(x + dx, y + dy, z + dz)) > 0) {
									if (Math.random() < 0.75) {
										{
											BlockPos _pos = BlockPos.containing(x + dx, y + dy, z + dz);
											Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
											world.destroyBlock(_pos, false);
										}
										if (world instanceof Level _level)
											_level.updateNeighborsAt(BlockPos.containing(x + dx, y + dy, z + dz), _level.getBlockState(BlockPos.containing(x + dx, y + dy, z + dz)).getBlock());
										once = true;
									}
								}
								dy = dy + 1;
							}
							dz = dz + 1;
						}
						dx = dx + 1;
					}
					if (once) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.break_block")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither.break_block")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					}
				}
			}
		}
	}
}
