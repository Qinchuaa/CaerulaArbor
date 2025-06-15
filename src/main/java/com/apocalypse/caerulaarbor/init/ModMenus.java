package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.world.inventory.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CaerulaArborMod.MODID);

    public static final RegistryObject<MenuType<CaerulaRecordGUIMenu>> CAERULA_RECORD_GUI = REGISTRY.register("caerula_record_gui", () -> IForgeMenuType.create(CaerulaRecordGUIMenu::new));
    public static final RegistryObject<MenuType<RelicShowcaseMenu>> RELIC_SHOWCASE = REGISTRY.register("relic_showcase",
            () -> IForgeMenuType.create((id, inv, data) -> new RelicShowcaseMenu(id, inv)));
    public static final RegistryObject<MenuType<InfoStrategySubsisMenu>> INFO_STRATEGY_SUBSIS = REGISTRY.register("info_strategy_subsis", () -> IForgeMenuType.create(InfoStrategySubsisMenu::new));
    public static final RegistryObject<MenuType<InfoStrategyBreedMenu>> INFO_STRATEGY_BREED = REGISTRY.register("info_strategy_breed", () -> IForgeMenuType.create(InfoStrategyBreedMenu::new));
    public static final RegistryObject<MenuType<InfoStrategyMigrationMenu>> INFO_STRATEGY_MIGRATION = REGISTRY.register("info_strategy_migration", () -> IForgeMenuType.create(InfoStrategyMigrationMenu::new));
    public static final RegistryObject<MenuType<InfoStrategyGrowMenu>> INFO_STRATEGY_GROW = REGISTRY.register("info_strategy_grow", () -> IForgeMenuType.create(InfoStrategyGrowMenu::new));
    public static final RegistryObject<MenuType<InfoStrategyAllMenu>> INFO_STRATEGY_ALL = REGISTRY.register("info_strategy_all", () -> IForgeMenuType.create(InfoStrategyAllMenu::new));
    public static final RegistryObject<MenuType<EvoTreeMenu>> EVO_TREE = REGISTRY.register("evo_tree", () -> IForgeMenuType.create(EvoTreeMenu::new));
}
