package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.menu.InfoStrategyAllMenu;
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

public class OpenStraGUIProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (entity instanceof ServerPlayer _ent) {
			BlockPos _bpos = BlockPos.containing(x, y, z);
			NetworkHooks.openScreen(_ent, new MenuProvider() {
				@Override
				public @NotNull Component getDisplayName() {
					return Component.literal("InfoStrategyAll");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
					return new InfoStrategyAllMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
				}
			}, _bpos);
		}
	}
}
