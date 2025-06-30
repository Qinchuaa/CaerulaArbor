package com.apocalypse.caerulaarbor.network.message;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerVariablesSyncMessage {
    private final PlayerVariable data;

    public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
        this.data = new PlayerVariable();
        this.data.readNBT(buffer.readNbt());
    }

    public PlayerVariablesSyncMessage(PlayerVariable data) {
        this.data = data;
    }

    public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
        buffer.writeNbt(message.data.writeNBT());
    }

    public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (!context.getDirection().getReceptionSide().isServer()) {
                PlayerVariable variables = Minecraft.getInstance().player.getCapability(ModCapabilities.PLAYER_VARIABLE, null).orElse(new PlayerVariable());
                variables.light = message.data.light;
                variables.lives = message.data.lives;
                variables.maxLive = message.data.maxLive;
                variables.shield = message.data.shield;
                variables.disoclusion = message.data.disoclusion;
                variables.show_stats = message.data.show_stats;
                variables.kingShowPtc = message.data.kingShowPtc;

                for (var relic : Relic.values()) {
                    relic.set(variables, message.data.relics.getOrDefault(relic, relic.defaultLevel));
                }

                variables.chitin_knife_selected = message.data.chitin_knife_selected;
                variables.player_king_suit = message.data.player_king_suit;
                variables.player_demon_suit = message.data.player_demon_suit;
                variables.player_oceanization = message.data.player_oceanization;
            }
        });
        context.setPacketHandled(true);
    }
}
