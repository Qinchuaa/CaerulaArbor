package com.apocalypse.caerulaarbor.command;

import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class LowerEnumArgument<T extends Enum<T>> implements ArgumentType<T> {

    private static final Dynamic2CommandExceptionType INVALID_ENUM =
            new Dynamic2CommandExceptionType((found, constants) -> Component.translatable("commands.forge.arguments.enum.invalid", constants, found));

    public final Function<T, String> valueMapper = e -> {
        var input = e.name();
        if (input == null || input.trim().isEmpty()) {
            return input;
        }

        String trimmed = input.replaceAll("^_+|_+$", "");
        if (trimmed.isEmpty()) {
            return input;
        }
        String[] parts = trimmed.toLowerCase(Locale.ENGLISH).split("_+");

        StringBuilder result = new StringBuilder();
        result.append(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            if (!parts[i].isEmpty()) {
                result.append(Character.toUpperCase(parts[i].charAt(0)))
                        .append(parts[i].substring(1));
            }
        }
        return result.toString();
    };

    private final Class<T> enumClass;

    private LowerEnumArgument(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    public static <T extends Enum<T>> LowerEnumArgument<T> enumArgument(Class<T> enumClass) {
        return new LowerEnumArgument<>(enumClass);
    }

    @Override
    public T parse(final StringReader reader) throws CommandSyntaxException {
        String input = reader.readUnquotedString();

        for (T enumValue : enumClass.getEnumConstants()) {
            if (valueMapper.apply(enumValue).equals(input)) {
                return enumValue;
            }
        }
        throw INVALID_ENUM.createWithContext(reader, input, Arrays.toString(Arrays.stream(enumClass.getEnumConstants()).map(valueMapper).toArray()));
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(Arrays.stream(enumClass.getEnumConstants()).map(valueMapper).toList(), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.stream(enumClass.getEnumConstants()).map(valueMapper).toList();
    }

    public static class Info<T extends Enum<T>> implements ArgumentTypeInfo<LowerEnumArgument<T>, Info<T>.Template> {
        @Override
        public void serializeToNetwork(Template template, FriendlyByteBuf buffer) {
            buffer.writeUtf(template.enumClass.getName());
        }

        @SuppressWarnings("unchecked")
        @Override
        public Template deserializeFromNetwork(FriendlyByteBuf buffer) {
            try {
                String name = buffer.readUtf();
                return new Template((Class<T>) Class.forName(name));
            } catch (ClassNotFoundException e) {
                return null;
            }
        }

        @Override
        public void serializeToJson(Template template, JsonObject json) {
            json.addProperty("enum", template.enumClass.getName());
        }

        @Override
        public Template unpack(LowerEnumArgument<T> argument) {
            return new Template(argument.enumClass);
        }

        public class Template implements ArgumentTypeInfo.Template<LowerEnumArgument<T>> {
            final Class<T> enumClass;

            Template(Class<T> enumClass) {
                this.enumClass = enumClass;
            }

            @Override
            public LowerEnumArgument<T> instantiate(CommandBuildContext pStructure) {
                return new LowerEnumArgument<>(this.enumClass);
            }

            @Override
            public ArgumentTypeInfo<LowerEnumArgument<T>, ?> type() {
                return Info.this;
            }
        }
    }
}
