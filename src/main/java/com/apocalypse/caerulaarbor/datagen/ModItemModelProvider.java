package com.apocalypse.caerulaarbor.datagen;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"ConstantConditions", "UnusedReturnValue", "SameParameterValue", "unused"})
public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CaerulaArborMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        // relics
        simpleItem(ModItems.HOT_WATER_KETTLE);
        simpleItem(ModItems.VAMPIRES_BED);
        simpleItem(ModItems.FEATURED_CANNED_MEAT);
        simpleItem(ModItems.PURE_GOLD_EXPEDITION);
        simpleItem(ModItems.PITTS_ASSORTED_FRUITS);
        simpleItem(ModItems.SCREAMING_CHERRY);
        simpleItem(ModItems.PROOF_OF_LONGEVITY);
        simpleItem(ModItems.WEIRD_FLUTE);
        simpleItem(ModItems.DURIN_OVERGROUND_ODYSSEY);
        simpleItem(ModItems.COFFEE_PLAINS_COFFEE_CANDY);

        simpleItem(ModItems.SURVIVOR_CONTRACT);

        // equipments
        handheldItem(ModItems.CHITIN_AXE);
        handheldItem(ModItems.CHITIN_HOE);
        handheldItem(ModItems.CHITIN_PICKAXE);
        handheldItem(ModItems.CHITIN_SHOVEL);
        handheldItem(ModItems.CHITIN_SWORD);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return simpleItem(item, "");
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item, String location) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", CaerulaArborMod.loc("item/" + location + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item, String location, String renderType) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", CaerulaArborMod.loc("item/" + location + item.getId().getPath())).renderType(renderType);
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(CaerulaArborMod.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/handheld"))
                .texture("layer0", CaerulaArborMod.loc("item/" + item.getId().getPath()));
    }
}
