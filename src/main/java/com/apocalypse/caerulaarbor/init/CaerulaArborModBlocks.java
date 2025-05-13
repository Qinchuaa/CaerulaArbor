
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import com.apocalypse.caerulaarbor.block.TrailTileBlock;
import com.apocalypse.caerulaarbor.block.TrailStairBlock;
import com.apocalypse.caerulaarbor.block.TrailSlabBlock;
import com.apocalypse.caerulaarbor.block.TrailPressurePlateBlock;
import com.apocalypse.caerulaarbor.block.TrailCakeBlock;
import com.apocalypse.caerulaarbor.block.TrailButtonBlock;
import com.apocalypse.caerulaarbor.block.TrailBrickBlock;
import com.apocalypse.caerulaarbor.block.TideObservationBlock;
import com.apocalypse.caerulaarbor.block.SeaTrailSolidBlock;
import com.apocalypse.caerulaarbor.block.SeaTrailInitBlock;
import com.apocalypse.caerulaarbor.block.SeaTrailGrownBlock;
import com.apocalypse.caerulaarbor.block.SeaTrailGrowingBlock;
import com.apocalypse.caerulaarbor.block.ResdtoneIrisBlock;
import com.apocalypse.caerulaarbor.block.RedstoneirisSeedingBlock;
import com.apocalypse.caerulaarbor.block.OceanGlasspaneBlock;
import com.apocalypse.caerulaarbor.block.OceanGlassBlock;
import com.apocalypse.caerulaarbor.block.OceanFarmlandBlock;
import com.apocalypse.caerulaarbor.block.OceanCrystalBlockBlock;
import com.apocalypse.caerulaarbor.block.KingsArmorBlock;
import com.apocalypse.caerulaarbor.block.EmergencyLightBlock;
import com.apocalypse.caerulaarbor.block.ComplexChitinBlockBlock;
import com.apocalypse.caerulaarbor.block.ChitinBlockBlock;
import com.apocalypse.caerulaarbor.block.CaramelCakeBlock;
import com.apocalypse.caerulaarbor.block.BombTrailerBlock;
import com.apocalypse.caerulaarbor.block.BlockSpearBlock;
import com.apocalypse.caerulaarbor.block.BlockRecorderBlock;
import com.apocalypse.caerulaarbor.block.BlockKettleBlock;
import com.apocalypse.caerulaarbor.block.BlockFateBlock;
import com.apocalypse.caerulaarbor.block.BlockExtensionBlock;
import com.apocalypse.caerulaarbor.block.BlockCrystalBlock;
import com.apocalypse.caerulaarbor.block.BlockCrownBlock;
import com.apocalypse.caerulaarbor.block.BlockBatbedBlock;
import com.apocalypse.caerulaarbor.block.BerryCanBlock;
import com.apocalypse.caerulaarbor.block.BatbedUpperBlock;
import com.apocalypse.caerulaarbor.block.AnchorUpperBlock;
import com.apocalypse.caerulaarbor.block.AnchorMediumBlock;
import com.apocalypse.caerulaarbor.block.AnchorLowerBlock;
import com.apocalypse.caerulaarbor.block.AllayBlockBlock;

public class CaerulaArborModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, CaerulaArborMod.MODID);
	public static final RegistryObject<Block> EMERGENCY_LIGHT = REGISTRY.register("emergency_light", () -> new EmergencyLightBlock());
	public static final RegistryObject<Block> KINGS_ARMOR = REGISTRY.register("kings_armor", () -> new KingsArmorBlock());
	public static final RegistryObject<Block> BLOCK_CROWN = REGISTRY.register("block_crown", () -> new BlockCrownBlock());
	public static final RegistryObject<Block> BLOCK_SPEAR = REGISTRY.register("block_spear", () -> new BlockSpearBlock());
	public static final RegistryObject<Block> BLOCK_EXTENSION = REGISTRY.register("block_extension", () -> new BlockExtensionBlock());
	public static final RegistryObject<Block> BLOCK_CRYSTAL = REGISTRY.register("block_crystal", () -> new BlockCrystalBlock());
	public static final RegistryObject<Block> BLOCK_FATE = REGISTRY.register("block_fate", () -> new BlockFateBlock());
	public static final RegistryObject<Block> SCREAMING_CHERRY = REGISTRY.register("screaming_cherry", BerryCanBlock::new);
	public static final RegistryObject<Block> REDSTONE_IRIS = REGISTRY.register("redstone_iris", () -> new ResdtoneIrisBlock());
	public static final RegistryObject<Block> REDSTONEIRIS_SEEDING = REGISTRY.register("redstoneiris_seeding", () -> new RedstoneirisSeedingBlock());
	public static final RegistryObject<Block> SEA_TRAIL_INIT = REGISTRY.register("sea_trail_init", () -> new SeaTrailInitBlock());
	public static final RegistryObject<Block> SEA_TRAIL_GROWING = REGISTRY.register("sea_trail_growing", () -> new SeaTrailGrowingBlock());
	public static final RegistryObject<Block> SEA_TRAIL_GROWN = REGISTRY.register("sea_trail_grown", () -> new SeaTrailGrownBlock());
	public static final RegistryObject<Block> BOMB_TRAILER = REGISTRY.register("bomb_trailer", () -> new BombTrailerBlock());
	public static final RegistryObject<Block> OCEAN_GLASS = REGISTRY.register("ocean_glass", () -> new OceanGlassBlock());
	public static final RegistryObject<Block> OCEAN_GLASSPANE = REGISTRY.register("ocean_glasspane", () -> new OceanGlasspaneBlock());
	public static final RegistryObject<Block> CHITIN_BLOCK = REGISTRY.register("chitin_block", () -> new ChitinBlockBlock());
	public static final RegistryObject<Block> BLOCK_KETTLE = REGISTRY.register("block_kettle", () -> new BlockKettleBlock());
	public static final RegistryObject<Block> OCEAN_FARMLAND = REGISTRY.register("ocean_farmland", () -> new OceanFarmlandBlock());
	public static final RegistryObject<Block> ALLAY_BLOCK = REGISTRY.register("allay_block", () -> new AllayBlockBlock());
	public static final RegistryObject<Block> BLOCK_BATBED = REGISTRY.register("block_batbed", () -> new BlockBatbedBlock());
	public static final RegistryObject<Block> BATBED_UPPER = REGISTRY.register("batbed_upper", () -> new BatbedUpperBlock());
	public static final RegistryObject<Block> TRAIL_CAKE = REGISTRY.register("trail_cake", () -> new TrailCakeBlock());
	public static final RegistryObject<Block> CARAMEL_CAKE = REGISTRY.register("caramel_cake", () -> new CaramelCakeBlock());
	public static final RegistryObject<Block> OCEAN_CRYSTAL_BLOCK = REGISTRY.register("ocean_crystal_block", () -> new OceanCrystalBlockBlock());
	public static final RegistryObject<Block> COMPLEX_CHITIN_BLOCK = REGISTRY.register("complex_chitin_block", () -> new ComplexChitinBlockBlock());
	public static final RegistryObject<Block> TIDE_OBSERVATION = REGISTRY.register("tide_observation", () -> new TideObservationBlock());
	public static final RegistryObject<Block> ANCHOR_LOWER = REGISTRY.register("anchor_lower", () -> new AnchorLowerBlock());
	public static final RegistryObject<Block> ANCHOR_MEDIUM = REGISTRY.register("anchor_medium", () -> new AnchorMediumBlock());
	public static final RegistryObject<Block> ANCHOR_UPPER = REGISTRY.register("anchor_upper", () -> new AnchorUpperBlock());
	public static final RegistryObject<Block> BLOCK_RECORDER = REGISTRY.register("block_recorder", () -> new BlockRecorderBlock());
	public static final RegistryObject<Block> SEA_TRAIL_SOLID = REGISTRY.register("sea_trail_solid", () -> new SeaTrailSolidBlock());
	public static final RegistryObject<Block> TRAIL_BRICK = REGISTRY.register("trail_brick", () -> new TrailBrickBlock());
	public static final RegistryObject<Block> TRAIL_SLAB = REGISTRY.register("trail_slab", () -> new TrailSlabBlock());
	public static final RegistryObject<Block> TRAIL_STAIR = REGISTRY.register("trail_stair", () -> new TrailStairBlock());
	public static final RegistryObject<Block> TRAIL_BUTTON = REGISTRY.register("trail_button", () -> new TrailButtonBlock());
	public static final RegistryObject<Block> TRAIL_PRESSURE_PLATE = REGISTRY.register("trail_pressure_plate", () -> new TrailPressurePlateBlock());
	public static final RegistryObject<Block> TRAIL_TILE = REGISTRY.register("trail_tile", () -> new TrailTileBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
