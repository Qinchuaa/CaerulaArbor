package com.apocalypse.caerulaarbor.network;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.Relic;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CaerulaArborModVariables {
    public static File crowd_will = new File("");

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        CaerulaArborMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
        CaerulaArborMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
    }

    @SubscribeEvent
    public static void init(RegisterCapabilitiesEvent event) {
        event.register(PlayerVariables.class);
    }

    @Mod.EventBusSubscriber
    public static class EventBusVariableHandlers {
        @SubscribeEvent
        public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
            if (!event.getEntity().level().isClientSide())
                event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()).syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
            if (!event.getEntity().level().isClientSide())
                event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()).syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
            if (!event.getEntity().level().isClientSide())
                event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()).syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void clonePlayer(PlayerEvent.Clone event) {
            event.getOriginal().revive();
            PlayerVariables original = event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables());
            PlayerVariables clone = event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables());
            clone.light = original.light;
            clone.lives = original.lives;
            clone.maxLive = original.maxLive;
            clone.shield = original.shield;
            clone.disoclusion = original.disoclusion;
            clone.show_stats = original.show_stats;
            clone.kingShowPtc = original.kingShowPtc;
            clone.player_util_RAINBOW = original.player_util_RAINBOW;
            clone.player_util_AROMATIC = original.player_util_AROMATIC;

            for (var relic : Relic.values()) {
                if (relic.gained(original)) {
                    relic.set(clone, relic.get(original));
                }
            }

//            clone.relic_cursed_EMELIGHT = original.relic_cursed_EMELIGHT;
//            clone.relic_cursed_GLOWBODY = original.relic_cursed_GLOWBODY;
//            clone.relic_cursed_RESEARCH = original.relic_cursed_RESEARCH;
            clone.relic_king_CROWN = original.relic_king_CROWN;
            clone.relic_king_ARMOR = original.relic_king_ARMOR;
            clone.relic_king_SPEAR = original.relic_king_SPEAR;
            clone.relic_king_EXTENSION = original.relic_king_EXTENSION;
            clone.relic_king_CRYSTAL = original.relic_king_CRYSTAL;
            clone.relic_hand_THORNS = original.relic_hand_THORNS;
            clone.relic_hand_STRANGLE = original.relic_hand_STRANGLE;
            clone.relic_hand_FERTILITY = original.relic_hand_FERTILITY;
            clone.relic_hand_SPEED = original.relic_hand_SPEED;
            clone.relic_hand_BARREN = original.relic_hand_BARREN;
            clone.relic_hand_SWIPE = original.relic_hand_SWIPE;
//            clone.relic_SARKAZ_KING_ARTIFACT = original.relic_SARKAZ_KING_ARTIFACT;
            clone.relic_hand_FIREWORK = original.relic_hand_FIREWORK;
//            clone.relic_SARKAZ_KING_FLAG = original.relic_SARKAZ_KING_FLAG;
            clone.relic_hand_ENGRAVE = original.relic_hand_ENGRAVE;
//            clone.relic_SARKAZ_KING_BED = original.relic_SARKAZ_KING_BED;
            clone.relic_SURVIVOR = original.relic_SURVIVOR;
            clone.relic_TREATY = original.relic_TREATY;
//            clone.relic_SARKAZ_KING_RYLFATE = original.relic_SARKAZ_KING_RYLFATE;
            clone.relic_util_MEATCAN = original.relic_util_MEATCAN;
            clone.relic_util_SEAGRASS = original.relic_util_SEAGRASS;
            clone.relic_util_ORANGE = original.relic_util_ORANGE;
            clone.relic_util_COFFEE = original.relic_util_COFFEE;
            clone.relic_util_BERRIES = original.relic_util_BERRIES;
            clone.relic_util_MUSICBOX = original.relic_util_MUSICBOX;
            clone.relic_util_IRIS = original.relic_util_IRIS;
            clone.relic_util_FLUTE = original.relic_util_FLUTE;
            clone.relic_util_VOYGOLD = original.relic_util_VOYGOLD;
            clone.relic_util_DURIN = original.relic_util_DURIN;
            clone.relic_util_TOPONYM = original.relic_util_TOPONYM;
            clone.relic_util_KETTLE = original.relic_util_KETTLE;
            clone.relic_legend_CHITIN = original.relic_legend_CHITIN;
            clone.relic_util_ALLEY = original.relic_util_ALLEY;
            clone.relic_util_BATBED = original.relic_util_BATBED;
            clone.relic_util_LONGEVITY = original.relic_util_LONGEVITY;
            clone.relic_util_OMNIKEY = original.relic_util_OMNIKEY;
            clone.relic_util_score = original.relic_util_score;
            clone.relic_util_RESCISSION = original.relic_util_RESCISSION;
            clone.relic_util_STARE = original.relic_util_STARE;
            clone.relic_hand_SWORD = original.relic_hand_SWORD;
//            clone.relic_cursed_HEART = original.relic_cursed_HEART;


            clone.player_king_suit = original.player_king_suit;
            clone.player_demon_suit = original.player_demon_suit;
            clone.player_oceanization = original.player_oceanization;
            if (!event.isWasDeath()) {
                clone.chitin_knife_selected = original.chitin_knife_selected;
            }
        }

        @SubscribeEvent
        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            if (!event.getEntity().level().isClientSide()) {
                SavedData mapdata = MapVariables.get(event.getEntity().level());
                SavedData worlddata = WorldVariables.get(event.getEntity().level());
                if (mapdata != null)
                    CaerulaArborMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(0, mapdata));
                if (worlddata != null)
                    CaerulaArborMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
            }
        }

        @SubscribeEvent
        public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
            if (!event.getEntity().level().isClientSide()) {
                SavedData worlddata = WorldVariables.get(event.getEntity().level());
                if (worlddata != null)
                    CaerulaArborMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
            }
        }
    }

    public static class WorldVariables extends SavedData {
        public static final String DATA_NAME = "caerula_arbor_worldvars";

        public static WorldVariables load(CompoundTag tag) {
            WorldVariables data = new WorldVariables();
            data.read(tag);
            return data;
        }

        public void read(CompoundTag nbt) {
        }

        @Override
        public @NotNull CompoundTag save(@NotNull CompoundTag nbt) {
            return nbt;
        }

        public void syncData(LevelAccessor world) {
            this.setDirty();
            if (world instanceof Level level && !level.isClientSide())
                CaerulaArborMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
        }

        static WorldVariables clientSide = new WorldVariables();

        public static WorldVariables get(LevelAccessor world) {
            if (world instanceof ServerLevel level) {
                return level.getDataStorage().computeIfAbsent(WorldVariables::load, WorldVariables::new, DATA_NAME);
            } else {
                return clientSide;
            }
        }
    }

    public static class MapVariables extends SavedData {
        public static final String DATA_NAME = "caerula_arbor_mapvars";
        public double evo_point_grow = 0;
        public double evo_point_subsisting = 0;
        public double evo_point_breed = 0;
        public double evo_point_migration = 0;
        public double strategy_grow = 0;
        public double strategy_subsisting = 0;
        public double strategy_breed = 0;
        public double strategy_migration = 0;
        public double strategy_silence = 0;
        public double evo_point_silence = 0;
        public boolean silence_enabled = false;

        public static MapVariables load(CompoundTag tag) {
            MapVariables data = new MapVariables();
            data.read(tag);
            return data;
        }

        public void read(CompoundTag nbt) {
            evo_point_grow = nbt.getDouble("evo_point_grow");
            evo_point_subsisting = nbt.getDouble("evo_point_subsisting");
            evo_point_breed = nbt.getDouble("evo_point_breed");
            evo_point_migration = nbt.getDouble("evo_point_migration");
            strategy_grow = nbt.getDouble("strategy_grow");
            strategy_subsisting = nbt.getDouble("strategy_subsisting");
            strategy_breed = nbt.getDouble("strategy_breed");
            strategy_migration = nbt.getDouble("strategy_migration");
            strategy_silence = nbt.getDouble("strategy_silence");
            evo_point_silence = nbt.getDouble("evo_point_silence");
            silence_enabled = nbt.getBoolean("silence_enabled");
        }

        @Override
        public @NotNull CompoundTag save(CompoundTag nbt) {
            nbt.putDouble("evo_point_grow", evo_point_grow);
            nbt.putDouble("evo_point_subsisting", evo_point_subsisting);
            nbt.putDouble("evo_point_breed", evo_point_breed);
            nbt.putDouble("evo_point_migration", evo_point_migration);
            nbt.putDouble("strategy_grow", strategy_grow);
            nbt.putDouble("strategy_subsisting", strategy_subsisting);
            nbt.putDouble("strategy_breed", strategy_breed);
            nbt.putDouble("strategy_migration", strategy_migration);
            nbt.putDouble("strategy_silence", strategy_silence);
            nbt.putDouble("evo_point_silence", evo_point_silence);
            nbt.putBoolean("silence_enabled", silence_enabled);
            return nbt;
        }

        public void syncData(LevelAccessor world) {
            this.setDirty();
            if (world instanceof Level && !world.isClientSide())
                CaerulaArborMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
        }

        static MapVariables clientSide = new MapVariables();

        public static MapVariables get(LevelAccessor world) {
            if (world instanceof ServerLevelAccessor serverLevelAcc) {
                return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(MapVariables::load, MapVariables::new, DATA_NAME);
            } else {
                return clientSide;
            }
        }
    }

    public static class SavedDataSyncMessage {
        private final int type;
        private SavedData data;

        public SavedDataSyncMessage(FriendlyByteBuf buffer) {
            this.type = buffer.readInt();
            CompoundTag nbt = buffer.readNbt();
            if (nbt != null) {
                this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
                if (this.data instanceof MapVariables mapVariables)
                    mapVariables.read(nbt);
                else if (this.data instanceof WorldVariables worldVariables)
                    worldVariables.read(nbt);
            }
        }

        public SavedDataSyncMessage(int type, SavedData data) {
            this.type = type;
            this.data = data;
        }

        public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
            buffer.writeInt(message.type);
            if (message.data != null)
                buffer.writeNbt(message.data.save(new CompoundTag()));
        }

        public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
                    if (message.type == 0)
                        MapVariables.clientSide = (MapVariables) message.data;
                    else
                        WorldVariables.clientSide = (WorldVariables) message.data;
                }
            });
            context.setPacketHandled(true);
        }
    }

    public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    @Mod.EventBusSubscriber
    private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
        @SubscribeEvent
        public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
                event.addCapability(new ResourceLocation("caerula_arbor", "player_variables"), new PlayerVariablesProvider());
        }

        private final PlayerVariables playerVariables = new PlayerVariables();
        private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

        @Override
        public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
            return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
        }

        @Override
        public Tag serializeNBT() {
            return playerVariables.writeNBT();
        }

        @Override
        public void deserializeNBT(Tag nbt) {
            playerVariables.readNBT(nbt);
        }
    }

    // TODO 把这坨清理了
    public static class PlayerVariables {
        public double light = 100.0;
        public double lives = 6.0;
        public double maxLive = 6.0;
        public double shield = 0;
        public int disoclusion = 0;
        public boolean show_stats = false;
        public boolean relic_king_CROWN = false;
        public boolean relic_king_ARMOR = false;
        public boolean relic_king_SPEAR = false;
        public boolean relic_king_EXTENSION = false;
        public boolean kingShowPtc = true;
        public boolean relic_king_CRYSTAL = false;
        public boolean relic_hand_THORNS = false;
        public boolean relic_hand_STRANGLE = false;
        public boolean relic_hand_FERTILITY = false;
        public boolean relic_hand_SPEED = false;
        public boolean relic_hand_BARREN = false;
        public boolean relic_hand_SWIPE = false;
        public boolean relic_hand_FIREWORK = false;
        public double relic_hand_ENGRAVE = -1.0;
        public double relic_SURVIVOR = -1.0;
        public boolean relic_TREATY = false;
        public boolean relic_util_MEATCAN = false;
        public boolean relic_util_SEAGRASS = false;
        public boolean relic_util_ORANGE = false;
        public boolean relic_util_COFFEE = false;
        public boolean relic_util_BERRIES = false;
        public boolean player_util_RAINBOW = false;
        public boolean player_util_AROMATIC = false;
        public boolean relic_util_MUSICBOX = false;
        public boolean relic_util_IRIS = false;
        public boolean relic_util_FLUTE = false;
        public boolean relic_util_VOYGOLD = false;
        public boolean relic_util_DURIN = false;
        public boolean relic_util_TOPONYM = false;
        public boolean relic_util_KETTLE = false;
        public boolean relic_legend_CHITIN = false;
        public ItemStack chitin_knife_selected = ItemStack.EMPTY;
        public boolean relic_util_ALLEY = false;
        public boolean relic_util_BATBED = false;
        public boolean relic_util_LONGEVITY = false;
        public boolean relic_util_OMNIKEY = false;
        public boolean relic_util_score = false;
        public boolean relic_util_RESCISSION = false;
        public boolean relic_util_STARE = false;
        public boolean relic_hand_SWORD = false;
        public double player_king_suit = 0;
        public double player_demon_suit = 0;
        public double player_oceanization = 0;

        public HashMap<Relic, Integer> relics = new HashMap<>();

        public void syncPlayerVariables(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer)
                CaerulaArborMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
        }

        public Tag writeNBT() {
            CompoundTag nbt = new CompoundTag();
            nbt.putDouble("player_light", light);
            nbt.putDouble("player_lives", lives);
            nbt.putDouble("player_maxlive", maxLive);
            nbt.putDouble("player_shield", shield);
            nbt.putInt("disoclusion", disoclusion);
            nbt.putBoolean("show_stats", show_stats);
            nbt.putBoolean("kingShowPtc", kingShowPtc);
            nbt.putBoolean("player_util_RAINBOW", player_util_RAINBOW);
            nbt.putBoolean("player_util_AROMATIC", player_util_AROMATIC);

            for (var relic : Relic.values()) {
                if (relic.gained(this)) {
                    nbt.putInt(relic.name(), relic.get(this));
                }
            }

//            nbt.putBoolean("relic_cursed_EMELIGHT", relic_cursed_EMELIGHT);
//            nbt.putBoolean("relic_cursed_GLOWBODY", relic_cursed_GLOWBODY);
//            nbt.putBoolean("relic_cursed_RESEARCH", relic_cursed_RESEARCH);
            nbt.putBoolean("relic_king_CROWN", relic_king_CROWN);
            nbt.putBoolean("relic_king_ARMOR", relic_king_ARMOR);
            nbt.putBoolean("relic_king_SPEAR", relic_king_SPEAR);
            nbt.putBoolean("relic_king_EXTENSION", relic_king_EXTENSION);
            nbt.putBoolean("relic_king_CRYSTAL", relic_king_CRYSTAL);
            nbt.putBoolean("relic_hand_THORNS", relic_hand_THORNS);
            nbt.putBoolean("relic_hand_STRANGLE", relic_hand_STRANGLE);
            nbt.putBoolean("relic_hand_FERTILITY", relic_hand_FERTILITY);
            nbt.putBoolean("relic_hand_SPEED", relic_hand_SPEED);
            nbt.putBoolean("relic_hand_BARREN", relic_hand_BARREN);
            nbt.putBoolean("relic_hand_SWIPE", relic_hand_SWIPE);
//            nbt.putBoolean("relic_SARKAZ_KING_ARTIFACT", relic_SARKAZ_KING_ARTIFACT);
            nbt.putBoolean("relic_hand_FIREWORK", relic_hand_FIREWORK);
//            nbt.putBoolean("relic_SARKAZ_KING_FLAG", relic_SARKAZ_KING_FLAG);
            nbt.putDouble("relic_hand_ENGRAVE", relic_hand_ENGRAVE);
//            nbt.putBoolean("relic_SARKAZ_KING_BED", relic_SARKAZ_KING_BED);
            nbt.putDouble("relic_SURVIVOR", relic_SURVIVOR);
            nbt.putBoolean("relic_TREATY", relic_TREATY);
//            nbt.putBoolean("relic_SARKAZ_KING_RYLFATE", relic_SARKAZ_KING_RYLFATE);
            nbt.putBoolean("relic_util_MEATCAN", relic_util_MEATCAN);
            nbt.putBoolean("relic_util_SEAGRASS", relic_util_SEAGRASS);
            nbt.putBoolean("relic_util_ORANGE", relic_util_ORANGE);
            nbt.putBoolean("relic_util_COFFEE", relic_util_COFFEE);
            nbt.putBoolean("relic_util_BERRIES", relic_util_BERRIES);
            nbt.putBoolean("relic_util_MUSICBOX", relic_util_MUSICBOX);
            nbt.putBoolean("relic_util_IRIS", relic_util_IRIS);
            nbt.putBoolean("relic_util_FLUTE", relic_util_FLUTE);
            nbt.putBoolean("relic_util_VOYGOLD", relic_util_VOYGOLD);
            nbt.putBoolean("relic_util_DURIN", relic_util_DURIN);
            nbt.putBoolean("relic_util_TOPONYM", relic_util_TOPONYM);
            nbt.putBoolean("relic_util_KETTLE", relic_util_KETTLE);
            nbt.putBoolean("relic_legend_CHITIN", relic_legend_CHITIN);
            nbt.putBoolean("relic_util_ALLEY", relic_util_ALLEY);
            nbt.putBoolean("relic_util_BATBED", relic_util_BATBED);
            nbt.putBoolean("relic_util_LONGEVITY", relic_util_LONGEVITY);
            nbt.putBoolean("relic_util_OMNIKEY", relic_util_OMNIKEY);
            nbt.putBoolean("relic_util_score", relic_util_score);
            nbt.putBoolean("relic_util_RESCISSION", relic_util_RESCISSION);
            nbt.putBoolean("relic_util_STARE", relic_util_STARE);
            nbt.putBoolean("relic_hand_SWORD", relic_hand_SWORD);
//            nbt.putBoolean("relic_cursed_HEART", relic_cursed_HEART);


            nbt.put("chitin_knife_selected", chitin_knife_selected.save(new CompoundTag()));
            nbt.putDouble("player_king_suit", player_king_suit);
            nbt.putDouble("player_demon_suit", player_demon_suit);
            nbt.putDouble("player_oceanization", player_oceanization);
            return nbt;
        }

        public void readNBT(Tag tag) {
            CompoundTag nbt = (CompoundTag) tag;
            light = nbt.getDouble("player_light");
            lives = nbt.getDouble("player_lives");
            maxLive = nbt.getDouble("player_maxlive");
            shield = nbt.getDouble("player_shield");
            disoclusion = nbt.getInt("disoclusion");
            show_stats = nbt.getBoolean("show_stats");
            kingShowPtc = nbt.getBoolean("kingShowPtc");
            player_util_RAINBOW = nbt.getBoolean("player_util_RAINBOW");
            player_util_AROMATIC = nbt.getBoolean("player_util_AROMATIC");

            for (var relic : Relic.values()) {
                if (nbt.contains(relic.name())) {
                    relic.set(this, nbt.getInt(relic.name()));
                }
            }

//            relic_cursed_EMELIGHT = nbt.getBoolean("relic_cursed_EMELIGHT");
//            relic_cursed_GLOWBODY = nbt.getBoolean("relic_cursed_GLOWBODY");
//            relic_cursed_RESEARCH = nbt.getBoolean("relic_cursed_RESEARCH");
            relic_king_CROWN = nbt.getBoolean("relic_king_CROWN");
            relic_king_ARMOR = nbt.getBoolean("relic_king_ARMOR");
            relic_king_SPEAR = nbt.getBoolean("relic_king_SPEAR");
            relic_king_EXTENSION = nbt.getBoolean("relic_king_EXTENSION");
            relic_king_CRYSTAL = nbt.getBoolean("relic_king_CRYSTAL");
            relic_hand_THORNS = nbt.getBoolean("relic_hand_THORNS");
            relic_hand_STRANGLE = nbt.getBoolean("relic_hand_STRANGLE");
            relic_hand_FERTILITY = nbt.getBoolean("relic_hand_FERTILITY");
            relic_hand_SPEED = nbt.getBoolean("relic_hand_SPEED");
            relic_hand_BARREN = nbt.getBoolean("relic_hand_BARREN");
            relic_hand_SWIPE = nbt.getBoolean("relic_hand_SWIPE");
//            relic_SARKAZ_KING_ARTIFACT = nbt.getBoolean("relic_SARKAZ_KING_ARTIFACT");
            relic_hand_FIREWORK = nbt.getBoolean("relic_hand_FIREWORK");
//            relic_SARKAZ_KING_FLAG = nbt.getBoolean("relic_SARKAZ_KING_FLAG");
            relic_hand_ENGRAVE = nbt.getDouble("relic_hand_ENGRAVE");
//            relic_SARKAZ_KING_BED = nbt.getBoolean("relic_SARKAZ_KING_BED");
            relic_SURVIVOR = nbt.getDouble("relic_SURVIVOR");
            relic_TREATY = nbt.getBoolean("relic_TREATY");
//            relic_SARKAZ_KING_RYLFATE = nbt.getBoolean("relic_SARKAZ_KING_RYLFATE");
            relic_util_MEATCAN = nbt.getBoolean("relic_util_MEATCAN");
            relic_util_SEAGRASS = nbt.getBoolean("relic_util_SEAGRASS");
            relic_util_ORANGE = nbt.getBoolean("relic_util_ORANGE");
            relic_util_COFFEE = nbt.getBoolean("relic_util_COFFEE");
            relic_util_BERRIES = nbt.getBoolean("relic_util_BERRIES");
            relic_util_MUSICBOX = nbt.getBoolean("relic_util_MUSICBOX");
            relic_util_IRIS = nbt.getBoolean("relic_util_IRIS");
            relic_util_FLUTE = nbt.getBoolean("relic_util_FLUTE");
            relic_util_VOYGOLD = nbt.getBoolean("relic_util_VOYGOLD");
            relic_util_DURIN = nbt.getBoolean("relic_util_DURIN");
            relic_util_TOPONYM = nbt.getBoolean("relic_util_TOPONYM");
            relic_util_KETTLE = nbt.getBoolean("relic_util_KETTLE");
            relic_legend_CHITIN = nbt.getBoolean("relic_legend_CHITIN");
            relic_util_ALLEY = nbt.getBoolean("relic_util_ALLEY");
            relic_util_BATBED = nbt.getBoolean("relic_util_BATBED");
            relic_util_LONGEVITY = nbt.getBoolean("relic_util_LONGEVITY");
            relic_util_OMNIKEY = nbt.getBoolean("relic_util_OMNIKEY");
            relic_util_score = nbt.getBoolean("relic_util_score");
            relic_util_RESCISSION = nbt.getBoolean("relic_util_RESCISSION");
            relic_util_STARE = nbt.getBoolean("relic_util_STARE");
            relic_hand_SWORD = nbt.getBoolean("relic_hand_SWORD");
//            relic_cursed_HEART = nbt.getBoolean("relic_cursed_HEART");


            chitin_knife_selected = ItemStack.of(nbt.getCompound("chitin_knife_selected"));
            player_king_suit = nbt.getDouble("player_king_suit");
            player_demon_suit = nbt.getDouble("player_demon_suit");
            player_oceanization = nbt.getDouble("player_oceanization");
        }
    }

    public static class PlayerVariablesSyncMessage {
        private final PlayerVariables data;

        public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
            this.data = new PlayerVariables();
            this.data.readNBT(buffer.readNbt());
        }

        public PlayerVariablesSyncMessage(PlayerVariables data) {
            this.data = data;
        }

        public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
            buffer.writeNbt((CompoundTag) message.data.writeNBT());
        }

        public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                if (!context.getDirection().getReceptionSide().isServer()) {
                    PlayerVariables variables = Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables());
                    variables.light = message.data.light;
                    variables.lives = message.data.lives;
                    variables.maxLive = message.data.maxLive;
                    variables.shield = message.data.shield;
                    variables.disoclusion = message.data.disoclusion;
                    variables.show_stats = message.data.show_stats;
                    variables.kingShowPtc = message.data.kingShowPtc;
                    variables.player_util_RAINBOW = message.data.player_util_RAINBOW;
                    variables.player_util_AROMATIC = message.data.player_util_AROMATIC;

                    for (var relic : Relic.values()) {
                        relic.set(variables, message.data.relics.getOrDefault(relic, relic.defaultLevel));
                    }

//                    variables.relic_cursed_EMELIGHT = message.data.relic_cursed_EMELIGHT;
//                    variables.relic_cursed_GLOWBODY = message.data.relic_cursed_GLOWBODY;
//                    variables.relic_cursed_RESEARCH = message.data.relic_cursed_RESEARCH;
                    variables.relic_king_CROWN = message.data.relic_king_CROWN;
                    variables.relic_king_ARMOR = message.data.relic_king_ARMOR;
                    variables.relic_king_SPEAR = message.data.relic_king_SPEAR;
                    variables.relic_king_EXTENSION = message.data.relic_king_EXTENSION;
                    variables.relic_king_CRYSTAL = message.data.relic_king_CRYSTAL;
                    variables.relic_hand_THORNS = message.data.relic_hand_THORNS;
                    variables.relic_hand_STRANGLE = message.data.relic_hand_STRANGLE;
                    variables.relic_hand_FERTILITY = message.data.relic_hand_FERTILITY;
                    variables.relic_hand_SPEED = message.data.relic_hand_SPEED;
                    variables.relic_hand_BARREN = message.data.relic_hand_BARREN;
                    variables.relic_hand_SWIPE = message.data.relic_hand_SWIPE;
//                    variables.relic_SARKAZ_KING_ARTIFACT = message.data.relic_SARKAZ_KING_ARTIFACT;
                    variables.relic_hand_FIREWORK = message.data.relic_hand_FIREWORK;
//                    variables.relic_SARKAZ_KING_FLAG = message.data.relic_SARKAZ_KING_FLAG;
                    variables.relic_hand_ENGRAVE = message.data.relic_hand_ENGRAVE;
//                    variables.relic_SARKAZ_KING_BED = message.data.relic_SARKAZ_KING_BED;
                    variables.relic_SURVIVOR = message.data.relic_SURVIVOR;
                    variables.relic_TREATY = message.data.relic_TREATY;
//                    variables.relic_SARKAZ_KING_RYLFATE = message.data.relic_SARKAZ_KING_RYLFATE;
                    variables.relic_util_MEATCAN = message.data.relic_util_MEATCAN;
                    variables.relic_util_SEAGRASS = message.data.relic_util_SEAGRASS;
                    variables.relic_util_ORANGE = message.data.relic_util_ORANGE;
                    variables.relic_util_COFFEE = message.data.relic_util_COFFEE;
                    variables.relic_util_BERRIES = message.data.relic_util_BERRIES;
                    variables.relic_util_MUSICBOX = message.data.relic_util_MUSICBOX;
                    variables.relic_util_IRIS = message.data.relic_util_IRIS;
                    variables.relic_util_FLUTE = message.data.relic_util_FLUTE;
                    variables.relic_util_VOYGOLD = message.data.relic_util_VOYGOLD;
                    variables.relic_util_DURIN = message.data.relic_util_DURIN;
                    variables.relic_util_TOPONYM = message.data.relic_util_TOPONYM;
                    variables.relic_util_KETTLE = message.data.relic_util_KETTLE;
                    variables.relic_legend_CHITIN = message.data.relic_legend_CHITIN;
                    variables.relic_util_ALLEY = message.data.relic_util_ALLEY;
                    variables.relic_util_BATBED = message.data.relic_util_BATBED;
                    variables.relic_util_LONGEVITY = message.data.relic_util_LONGEVITY;
                    variables.relic_util_OMNIKEY = message.data.relic_util_OMNIKEY;
                    variables.relic_util_score = message.data.relic_util_score;
                    variables.relic_util_RESCISSION = message.data.relic_util_RESCISSION;
                    variables.relic_util_STARE = message.data.relic_util_STARE;
                    variables.relic_hand_SWORD = message.data.relic_hand_SWORD;
//                    variables.relic_cursed_HEART = message.data.relic_cursed_HEART;


                    variables.chitin_knife_selected = message.data.chitin_knife_selected;
                    variables.player_king_suit = message.data.player_king_suit;
                    variables.player_demon_suit = message.data.player_demon_suit;
                    variables.player_oceanization = message.data.player_oceanization;
                }
            });
            context.setPacketHandled(true);
        }
    }
}
