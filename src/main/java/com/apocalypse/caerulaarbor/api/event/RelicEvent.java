package com.apocalypse.caerulaarbor.api.event;

import com.apocalypse.caerulaarbor.capability.Relic;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class RelicEvent extends Event {

    public final Entity player;
    public final Relic relic;

    public RelicEvent(Entity player, Relic relic) {
        this.player = player;
        this.relic = relic;
    }

    public static class Gain extends RelicEvent {

        public Gain(Entity player, Relic relic) {
            super(player, relic);
        }
    }

    public static class Remove extends RelicEvent {

        public Remove(Entity player, Relic relic) {
            super(player, relic);
        }
    }
}
