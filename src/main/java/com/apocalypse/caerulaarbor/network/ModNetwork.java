package com.apocalypse.caerulaarbor.network;

import com.apocalypse.caerulaarbor.network.message.s2c.PlayerVariablesSyncMessage;
import com.apocalypse.caerulaarbor.network.message.s2c.SavedDataSyncMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.apocalypse.caerulaarbor.CaerulaArborMod.PACKET_HANDLER;
import static com.apocalypse.caerulaarbor.CaerulaArborMod.messageID;

public class ModNetwork {

    public static void register() {
        playToClient(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::encode, PlayerVariablesSyncMessage::decode, PlayerVariablesSyncMessage::handler);
        playToClient(SavedDataSyncMessage.class, SavedDataSyncMessage::encode, SavedDataSyncMessage::decode, SavedDataSyncMessage::handler);
    }

    public static <T> void playToClient(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        messageID++;
    }

    /**
     * 注册无参数、向客户端发送的消息
     */
    @SuppressWarnings("unchecked")
    public static <T> void playToClient(T instance, Consumer<Supplier<NetworkEvent.Context>> messageConsumer) {
        var type = (Class<T>) instance.getClass();
        PACKET_HANDLER.registerMessage(messageID, type, (msg, buf) -> {
        }, (buf) -> instance, (msg, ctx) -> messageConsumer.accept(ctx), Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        messageID++;
    }

    public static <T> void playToServer(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        messageID++;
    }

    /**
     * 注册无参数、向服务器发送的消息
     */
    @SuppressWarnings("unchecked")
    public static <T> void playToServer(T instance, Consumer<Supplier<NetworkEvent.Context>> messageConsumer) {
        var type = (Class<T>) instance.getClass();
        PACKET_HANDLER.registerMessage(messageID, type, (msg, buf) -> {
        }, (buf) -> instance, (msg, ctx) -> messageConsumer.accept(ctx), Optional.of(NetworkDirection.PLAY_TO_SERVER));
        messageID++;
    }
}
