
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.caerulaarbor.block.TrailTileBlock;
import net.mcreator.caerulaarbor.block.TrailStairBlock;
import net.mcreator.caerulaarbor.block.TrailSlabBlock;
import net.mcreator.caerulaarbor.block.TrailPressurePlateBlock;
import net.mcreator.caerulaarbor.block.TrailCakeBlock;
import net.mcreator.caerulaarbor.block.TrailButtonBlock;
import net.mcreator.caerulaarbor.block.TrailBrickBlock;
import net.mcreator.caerulaarbor.block.TideObservationBlock;
import net.mcreator.caerulaarbor.block.SeaTrailSolidBlock;
import net.mcreator.caerulaarbor.block.SeaTrailInitBlock;
import net.mcreator.caerulaarbor.block.SeaTrailGrownBlock;
import net.mcreator.caerulaarbor.block.SeaTrailGrowingBlock;
import net.mcreator.caerulaarbor.block.ResdtoneIrisBlock;
import net.mcreator.caerulaarbor.block.RedstoneirisSeedingBlock;
import net.mcreator.caerulaarbor.block.OceanGlasspaneBlock;
import net.mcreator.caerulaarbor.block.OceanGlassBlock;
import net.mcreator.caerulaarbor.block.OceanFarmlandBlock;
import net.mcreator.caerulaarbor.block.OceanCrystalBlockBlock;
import net.mcreator.caerulaarbor.block.KingsArmorBlock;
import net.mcreator.caerulaarbor.block.EmergencyLightBlock;
import net.mcreator.caerulaarbor.block.ComplexChitinBlockBlock;
import net.mcreator.caerulaarbor.block.ChitinBlockBlock;
import net.mcreator.caerulaarbor.block.CaramelCakeBlock;
import net.mcreator.caerulaarbor.block.BombTrailerBlock;
import net.mcreator.caerulaarbor.block.BlockSpearBlock;
import net.mcreator.caerulaarbor.block.BlockRecorderBlock;
import net.mcreator.caerulaarbor.block.BlockKettleBlock;
import net.mcreator.caerulaarbor.block.BlockFateBlock;
import net.mcreator.caerulaarbor.block.BlockExtensionBlock;
import net.mcreator.caerulaarbor.block.BlockCrystalBlock;
import net.mcreator.caerulaarbor.block.BlockCrownBlock;
import net.mcreator.caerulaarbor.block.BlockBatbedBlock;
import net.mcreator.caerulaarbor.block.BerryCanBlock;
import net.mcreator.caerulaarbor.block.BatbedUpperBlock;
import net.mcreator.caerulaarbor.block.AnchorUpperBlock;
import net.mcreator.caerulaarbor.block.AnchorMediumBlock;
import net.mcreator.caerulaarbor.block.AnchorLowerBlock;
import net.mcreator.caerulaarbor.block.AllayBlockBlock;
import net.mcreator.caerulaarbor.CaerulaArborMod;

public class CaerulaArborModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, CaerulaArborMod.MODID);
	public static final RegistryObject<Block> EMERGENCY_LIGHT = REGISTRY.register("emergency_light", () -> new EmergencyLightBlock());
	public static final RegistryObject<Block> KINGS_ARMOR = REGISTRY.register("kings_armor", () -> new KingsArmorBlock());
	public static final RegistryObject<Block> BLOCK_CROWN = REGISTRY.register("block_crown", () -> new BlockCrownBlock());
	public static final RegistryObject<Block> BLOCK_SPEAR = REGISTRY.register("block_spear", () -> new BlockSpearBlock());
	public static final RegistryObject<Block> BLOCK_EXTENSION = REGISTRY.register("block_extension", () -> new BlockExtensionBlock());
	public static final RegistryObject<Block> BLOCK_CRYSTAL = REGISTRY.register("block_crystal", () -> new BlockCrystalBlock());
	public static final RegistryObject<Block> BLOCK_FATE = REGISTRY.register("block_fate", () -> new BlockFateBlock());
	public static final RegistryObject<Block> BERRY_CAN = REGISTRY.register("berry_can", () -> new BerryCanBlock());
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
