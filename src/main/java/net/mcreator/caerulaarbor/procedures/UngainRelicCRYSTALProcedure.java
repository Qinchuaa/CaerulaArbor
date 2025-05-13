package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.GameType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.init.CaerulaArborModItems;

public class UngainRelicCRYSTALProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack togive = ItemStack.EMPTY;
		if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(CaerulaArborModItems.COIN_OF_TRADE.get())) : false) {
			if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_king_CRYSTAL) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(CaerulaArborModItems.COIN_OF_TRADE.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
				for (int index0 = 0; index0 < 64; index0++) {
					togive = new ItemStack((ForgeRegistries.ITEMS.tags().getTag(ItemTags.create(new ResourceLocation("caerula_arbor:relic_generic"))).getRandomElement(RandomSource.create()).orElseGet(() -> Items.AIR))).copy();
					if (!(togive.getItem() == ItemStack.EMPTY.getItem())) {
						break;
					}
				}
				if (entity instanceof Player _player) {
					ItemStack _setstack = togive.copy();
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
				{
					boolean _setval = false;
					entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.relic_king_CRYSTAL = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		} else {
			if (new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayer _serverPlayer) {
						return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
					} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
						return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
								&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity)) {
				if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_king_CRYSTAL) {
					{
						boolean _setval = false;
						entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.relic_king_CRYSTAL = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		}
	}
}
