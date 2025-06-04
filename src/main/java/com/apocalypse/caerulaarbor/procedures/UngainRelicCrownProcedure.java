package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModItems;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class UngainRelicCrownProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        ItemStack togive = ItemStack.EMPTY;

        var cap = entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables());
        if (entity instanceof Player _playerHasItem && _playerHasItem.getInventory().contains(new ItemStack(ModItems.COIN_OF_TRADE.get()))) {
            if (Relic.KING_CROWN.gained(cap)) {
                if (entity instanceof Player _player) {
                    ItemStack _stktoremove = new ItemStack(ModItems.COIN_OF_TRADE.get());
                    _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
                }
                for (int index0 = 0; index0 < 64; index0++) {
                    togive = new ItemStack((ForgeRegistries.ITEMS.tags().getTag(ItemTags.create(new ResourceLocation("caerula_arbor:relic_generic"))).getRandomElement(RandomSource.create()).orElse(Items.AIR))).copy();
                    if (!(togive.getItem() == ItemStack.EMPTY.getItem())) {
                        break;
                    }
                }
                if (entity instanceof Player _player) {
                    ItemHandlerHelper.giveItemToPlayer(_player, togive.copy());
                }
                Relic.KING_CROWN.reset(cap);
                cap.syncPlayerVariables(entity);
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
                if (Relic.KING_CROWN.gained(cap)) {
                    Relic.KING_CROWN.reset(cap);
                    cap.syncPlayerVariables(entity);
                }
            }
        }
    }
}
