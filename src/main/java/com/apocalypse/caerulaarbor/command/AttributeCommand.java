package com.apocalypse.caerulaarbor.command;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;

public class AttributeCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> get() {
        return Commands.literal("attribute").requires(s -> s.hasPermission(2))
                .then(Commands.argument("player", EntityArgument.players())
                        .then(Commands.literal("life").then(Commands.argument("value", IntegerArgumentType.integer(0, 325))
                                .executes(arguments -> {
                                    var players = EntityArgument.getPlayers(arguments, "player");
                                    int value = IntegerArgumentType.getInteger(arguments, "value");

                                    for (var player : players) {
                                        var variables = ModCapabilities.getPlayerVariables(player);
                                        variables.life = Math.min(value, variables.maxLive);
                                        variables.syncPlayerVariables(player);
                                    }

                                    if (players.size() == 1) {
                                        arguments.getSource().sendSuccess(() -> Component.translatable("command.caerula_arbor.attribute.life.single", players.iterator().next().getDisplayName(), value), true);
                                    } else {
                                        arguments.getSource().sendSuccess(() -> Component.translatable("command.caerula_arbor.attribute.life.multiple", players.size(), value), true);
                                    }
                                    return 0;
                                })))
                        .then(Commands.literal("maxLife").then(Commands.argument("value", IntegerArgumentType.integer(0, 325))
                                .executes(arguments -> {
                                    var players = EntityArgument.getPlayers(arguments, "player");
                                    int value = IntegerArgumentType.getInteger(arguments, "value");

                                    for (var player : players) {
                                        var variables = ModCapabilities.getPlayerVariables(player);
                                        variables.maxLive = value;
                                        variables.syncPlayerVariables(player);
                                    }

                                    if (players.size() == 1) {
                                        arguments.getSource().sendSuccess(() -> Component.translatable("command.caerula_arbor.attribute.max_life.single", players.iterator().next().getDisplayName(), value), true);
                                    } else {
                                        arguments.getSource().sendSuccess(() -> Component.translatable("command.caerula_arbor.attribute.max_life.multiple", players.size(), value), true);
                                    }
                                    return 0;
                                })))
                        .then(Commands.literal("shield").then(Commands.argument("value", IntegerArgumentType.integer(0, Integer.MAX_VALUE))
                                .executes(arguments -> {
                                    var players = EntityArgument.getPlayers(arguments, "player");
                                    int value = IntegerArgumentType.getInteger(arguments, "value");

                                    for (var player : players) {
                                        var variables = ModCapabilities.getPlayerVariables(player);
                                        variables.shield = value;
                                        variables.syncPlayerVariables(player);
                                    }

                                    if (players.size() == 1) {
                                        arguments.getSource().sendSuccess(() -> Component.translatable("command.caerula_arbor.attribute.shield.single", players.iterator().next().getDisplayName(), value), true);
                                    } else {
                                        arguments.getSource().sendSuccess(() -> Component.translatable("command.caerula_arbor.attribute.shield.multiple", players.size(), value), true);
                                    }
                                    return 0;
                                })))
                );
    }
}
