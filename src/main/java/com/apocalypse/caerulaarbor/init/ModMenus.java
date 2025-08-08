package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.menu.CaerulaRecorderMenu;
import com.apocalypse.caerulaarbor.menu.EvoTreeMenu;
import com.apocalypse.caerulaarbor.menu.RelicShowcaseMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CaerulaArborMod.MODID);

    public static final RegistryObject<MenuType<CaerulaRecorderMenu>> CAERULA_RECORDER = REGISTRY.register("caerula_recorder",
            () -> IForgeMenuType.create((id, inv, data) -> new CaerulaRecorderMenu(id, inv)));
    public static final RegistryObject<MenuType<RelicShowcaseMenu>> RELIC_SHOWCASE = REGISTRY.register("relic_showcase",
            () -> IForgeMenuType.create((id, inv, data) -> new RelicShowcaseMenu(id, inv)));
    public static final RegistryObject<MenuType<EvoTreeMenu>> EVO_TREE = REGISTRY.register("evo_tree", () -> IForgeMenuType.create(EvoTreeMenu::new));
}
