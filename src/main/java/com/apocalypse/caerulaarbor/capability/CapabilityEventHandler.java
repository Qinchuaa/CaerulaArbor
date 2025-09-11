package com.apocalypse.caerulaarbor.capability;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.anchor.AnchorRecord;
import com.apocalypse.caerulaarbor.capability.map.MapVariables;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import com.apocalypse.caerulaarbor.capability.sanity.SanityInjuryCapability;
import com.apocalypse.caerulaarbor.network.ModNetwork;
import com.apocalypse.caerulaarbor.network.message.receive.SavedDataSyncMessage;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = CaerulaArborMod.MODID)
public class CapabilityEventHandler {
    @SubscribeEvent
    public static void attachLevelCapabilities(AttachCapabilitiesEvent<Level> event) {
        if (event.getObject() instanceof ServerLevel) {
            LazyOptional<AnchorRecord> optional = LazyOptional.of(AnchorRecord::new);
            ICapabilityProvider provider = new ICapabilityProvider() {
                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    return ModCapabilities.ANCHOR_RECORD.orEmpty(cap, optional.cast());
                }
            };
            event.addCapability(AnchorRecord.ID, provider);
        }
    }

    @SubscribeEvent
    public static void registerEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof LivingEntity livingEntity) {
            event.addCapability(SanityInjuryCapability.ID, createProvider(LazyOptional.of(() -> new SanityInjuryCapability(livingEntity)), ModCapabilities.SANITY_INJURY));
        }
        if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
            event.addCapability(PlayerVariable.ID, createProvider(LazyOptional.of(PlayerVariable::new), ModCapabilities.PLAYER_VARIABLE));
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            event.getEntity().getCapability(ModCapabilities.PLAYER_VARIABLE, null).orElse(new PlayerVariable()).syncPlayerVariables(event.getEntity());

            var mapVariables = MapVariables.get(event.getEntity().level());
            ModNetwork.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(mapVariables));
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
        if (!event.getEntity().level().isClientSide())
            event.getEntity().getCapability(ModCapabilities.PLAYER_VARIABLE, null).orElse(new PlayerVariable()).syncPlayerVariables(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!event.getEntity().level().isClientSide())
            event.getEntity().getCapability(ModCapabilities.PLAYER_VARIABLE, null).orElse(new PlayerVariable()).syncPlayerVariables(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        Player player = event.getEntity();
        Player oldPlayer = event.getOriginal();
        oldPlayer.revive();

        handleSanityCap(player, oldPlayer);
        handlePlayerVariables(player, oldPlayer, event.isWasDeath());
    }

    private static void handleSanityCap(Player player, Player oldPlayer) {
        var oldInjury = ModCapabilities.getSanityInjury(oldPlayer);
        var newInjury = ModCapabilities.getSanityInjury(player);
        newInjury.deserializeNBT(oldInjury.serializeNBT());
    }

    private static void handlePlayerVariables(Player player, Player oldPlayer, boolean isWasDeath) {
        PlayerVariable original = oldPlayer.getCapability(ModCapabilities.PLAYER_VARIABLE, null).orElse(new PlayerVariable());
        PlayerVariable clone = player.getCapability(ModCapabilities.PLAYER_VARIABLE, null).orElse(new PlayerVariable());
        clone.light = original.light;
        clone.life = original.life;
        clone.maxLive = original.maxLive;
        clone.shield = original.shield;
        clone.rejection = original.rejection;
        clone.seabornization = original.seabornization;

        for (var relic : Relic.values()) {
            if (relic.gained(original)) {
                relic.set(clone, relic.get(original));
            }
        }
    }

    public static <S extends Tag, T extends INBTSerializable<S>> ICapabilitySerializable<S> createProvider(LazyOptional<T> instance, Capability<T> capability) {
        return new ICapabilitySerializable<>() {
            @Override
            public @NotNull <C> LazyOptional<C> getCapability(@NotNull Capability<C> cap, @Nullable Direction side) {
                return capability.orEmpty(cap, instance.cast());
            }

            @Override
            public S serializeNBT() {
                return instance.orElseThrow(NullPointerException::new).serializeNBT();
            }

            @Override
            public void deserializeNBT(S nbt) {
                instance.orElseThrow(NullPointerException::new).deserializeNBT(nbt);
            }
        };
    }
}
