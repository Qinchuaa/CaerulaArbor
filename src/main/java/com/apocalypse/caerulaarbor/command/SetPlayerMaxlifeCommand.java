
package com.apocalypse.caerulaarbor.command;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SetPlayerMaxlifeCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("caerula_arbor:setMaxlife").requires(s -> s.hasPermission(2)).then(Commands.argument("name", EntityArgument.player()).then(Commands.argument("life", DoubleArgumentType.doubleArg(1, 255)).executes(arguments -> {
            var entity = EntityArgument.getEntity(arguments, "name");

            entity.getCapability(ModCapabilities.PLAYER_VARIABLE).ifPresent(cap -> {
                cap.maxLive = DoubleArgumentType.getDouble(arguments, "life");
                cap.syncPlayerVariables(entity);
            });
            return 0;
        }))));
    }
}
