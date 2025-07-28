package com.apocalypse.caerulaarbor.capability.player;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.network.message.PlayerVariablesSyncMessage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.network.PacketDistributor;

import java.util.HashMap;

// TODO 把这坨清理了
@AutoRegisterCapability
public class PlayerVariable implements INBTSerializable<CompoundTag> {

    public static ResourceLocation ID = CaerulaArborMod.loc("player_variables");

    public double light = 100.0;
    public int life = 6;
    public int maxLive = 6;
    public int shield = 0;
    public int seabornization = 0;
    public int disoclusion = 0;
    public boolean show_stats = false;
    public boolean kingShowPtc = true;
    public ItemStack chitin_knife_selected = ItemStack.EMPTY;
    public int player_king_suit = 0;
    public int player_demon_suit = 0;

    public HashMap<Relic, Integer> relics = new HashMap<>();

    public void syncPlayerVariables(Entity entity) {
        if (entity instanceof ServerPlayer serverPlayer)
            CaerulaArborMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
    }

    public CompoundTag writeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putDouble("player_light", light);
        nbt.putInt("Life", life);
        nbt.putInt("MaxLife", maxLive);
        nbt.putInt("Shield", shield);
        nbt.putInt("disoclusion", disoclusion);
        nbt.putBoolean("show_stats", show_stats);
        nbt.putBoolean("kingShowPtc", kingShowPtc);

        for (var relic : Relic.values()) {
            if (relic.gained(this)) {
                nbt.putInt(relic.name(), relic.get(this));
            }
        }

        nbt.put("chitin_knife_selected", chitin_knife_selected.save(new CompoundTag()));
        nbt.putInt("player_king_suit", player_king_suit);
        nbt.putInt("player_demon_suit", player_demon_suit);
        nbt.putInt("Seabornization", seabornization);
        return nbt;
    }

    public void readNBT(CompoundTag tag) {
        light = tag.getDouble("player_light");
        life = tag.getInt("Life");
        maxLive = tag.getInt("MaxLife");
        shield = tag.getInt("Shield");
        disoclusion = tag.getInt("disoclusion");
        show_stats = tag.getBoolean("show_stats");
        kingShowPtc = tag.getBoolean("kingShowPtc");

        for (var relic : Relic.values()) {
            if (tag.contains(relic.name())) {
                relic.set(this, tag.getInt(relic.name()));
            }
        }

        chitin_knife_selected = ItemStack.of(tag.getCompound("chitin_knife_selected"));
        player_king_suit = tag.getInt("player_king_suit");
        player_demon_suit = tag.getInt("player_demon_suit");
        seabornization = tag.getInt("Seabornization");
    }

    @Override
    public CompoundTag serializeNBT() {
        return writeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.readNBT(nbt);
    }
}
