
package com.apocalypse.caerulaarbor.command;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DisoclutionCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("caerula_arbor:disoclution").requires(s -> s.hasPermission(2)).then(Commands.argument("name", EntityArgument.player()).then(Commands.literal("clear").executes(arguments -> {
            setDisoclusion(arguments, 0);
            return 0;
        })).then(Commands.literal("disconcentration").executes(arguments -> {
            setDisoclusion(arguments, 1);
            return 0;
        })).then(Commands.literal("haemophilia").executes(arguments -> {
            setDisoclusion(arguments, 2);
            return 0;
        })).then(Commands.literal("neurodegression").executes(arguments -> {
            setDisoclusion(arguments, 3);
            return 0;
        })).then(Commands.literal("deformity").executes(arguments -> {
            setDisoclusion(arguments, 4);
            return 0;
        }))));
    }

    public static void setDisoclusion(com.mojang.brigadier.context.CommandContext<net.minecraft.commands.CommandSourceStack> arguments, int value) throws CommandSyntaxException {
        var target = EntityArgument.getEntity(arguments, "name");
        target.getCapability(ModCapabilities.PLAYER_VARIABLE).ifPresent(capability -> {
            capability.disoclusion = value;
            capability.syncPlayerVariables(target);
        });
    }
}
