package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.world.inventory.EvoTreeMenu;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class OpenEvoTreeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (entity instanceof ServerPlayer _ent) {
			NetworkHooks.openScreen(_ent, new MenuProvider() {
				@Override
				public @NotNull Component getDisplayName() {
					return Component.literal("EvoTree");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
					return new EvoTreeMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(BlockPos.containing(x, y, z)));
				}
			}, BlockPos.containing(x, y, z));
		}
	}
}
