package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.command.LowerEnumArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCommandArguments {

    public static final DeferredRegister<ArgumentTypeInfo<?, ?>> COMMAND_ARGUMENT_TYPES = DeferredRegister.create(Registries.COMMAND_ARGUMENT_TYPE, CaerulaArborMod.MODID);

    public static final RegistryObject<LowerEnumArgument.Info> LOWER_ENUM =
            COMMAND_ARGUMENT_TYPES.register("lower_enum", () -> ArgumentTypeInfos.registerByClass(LowerEnumArgument.class, new LowerEnumArgument.Info()));
}
