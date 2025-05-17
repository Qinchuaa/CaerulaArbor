
package com.apocalypse.caerulaarbor.command;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LightSetCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("caerula_arbor:setLights").requires(s -> s.hasPermission(2)).then(Commands.argument("name", EntityArgument.players()).then(Commands.literal("ablaze").executes(arguments -> {
            setLight(arguments, 100);
            return 0;
        })).then(Commands.literal("flicker").executes(arguments -> {
            setLight(arguments, 80);
            return 0;
        })).then(Commands.literal("dim").executes(arguments -> {
            setLight(arguments, 40);
            return 0;
        })).then(Commands.literal("tranquil").executes(arguments -> {
            setLight(arguments, 0);
            return 0;
        })).then(Commands.argument("light", DoubleArgumentType.doubleArg(0, 100)).executes(arguments -> {
            setLight(arguments, DoubleArgumentType.getDouble(arguments, "light"));
            return 0;
        }))));
    }

    private static void setLight(CommandContext<CommandSourceStack> arguments, double value) {
        try {
            for (Entity entity1 : EntityArgument.getEntities(arguments, "name")) {
                entity1.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(cap -> {
                    cap.light = value;
                    cap.syncPlayerVariables(entity1);
                });
            }
        } catch (CommandSyntaxException e) {
            CaerulaArborMod.LOGGER.error(e);
        }
    }
}
