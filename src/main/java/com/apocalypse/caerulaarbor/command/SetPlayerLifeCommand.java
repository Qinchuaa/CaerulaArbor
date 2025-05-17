
package com.apocalypse.caerulaarbor.command;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SetPlayerLifeCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("caerula_arbor:setLife").requires(s -> s.hasPermission(2)).then(Commands.argument("name", EntityArgument.player()).then(Commands.argument("life", DoubleArgumentType.doubleArg(0, 255)).executes(arguments -> {
            Entity entity = EntityArgument.getEntity(arguments, "name");

            if (DoubleArgumentType.getDouble(arguments, "life") <
                    entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY)
                            .map(c -> c.maxLive)
                            .orElse(0.0)
            ) {
                entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(cap -> {
                    cap.lives = DoubleArgumentType.getDouble(arguments, "life");
                    cap.syncPlayerVariables(entity);
                });
            } else {
                entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(cap -> {
                    cap.lives = cap.maxLive;
                    cap.syncPlayerVariables(entity);
                });
            }
            return 0;
        }))));
    }
}
