package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class ShowThermoProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Temperature:" + world.getBiome(BlockPos.containing(x, y, z)).value().getBaseTemperature() * 100f)), false);
	}
}
