package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.potion.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobEffects {

	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CaerulaArborMod.MODID);

	public static final RegistryObject<MobEffect> SANITY_IMMUNE = REGISTRY.register("sanity_immune", SanityImmuneMobEffect::new);
	public static final RegistryObject<MobEffect> DIZZY = REGISTRY.register("dizzy", DizzyMobEffect::new);
	public static final RegistryObject<MobEffect> ARMOR_BREAKING = REGISTRY.register("armor_breaking", ArmorBreakingEffect::new);

	public static final RegistryObject<MobEffect> PERMANENCE = REGISTRY.register("permanence",PermanenceMobEffect::new);

	public static final RegistryObject<MobEffect> PALSY = REGISTRY.register("palsy", PalsyMobEffect::new);
	public static final RegistryObject<MobEffect> PALSYING = REGISTRY.register("palsying", PalsyingMobEffect::new);

	public static final RegistryObject<MobEffect> KINGS_BREATH = REGISTRY.register("kings_breath", KingsBreathMobEffect::new);
	public static final RegistryObject<MobEffect> KINGS_BOOST = REGISTRY.register("kings_boost", KingsBoostMobEffect::new);
	public static final RegistryObject<MobEffect> SPEAR_FIGHT = REGISTRY.register("spear_fight", SpearFightMobEffect::new);
	public static final RegistryObject<MobEffect> HANDS_SPEED = REGISTRY.register("hands_speed", HandsSpeedMobEffect::new);
	public static final RegistryObject<MobEffect> WIPE_DUSTS = REGISTRY.register("wipe_dusts", WipeDustsMobEffect::new);
	public static final RegistryObject<MobEffect> SACREFICE = REGISTRY.register("sacrefice", SacreficeMobEffect::new);
	public static final RegistryObject<MobEffect> ENGRAVED_TRIUMPH = REGISTRY.register("engraved_triumph", EngravedTriumphMobEffect::new);
	public static final RegistryObject<MobEffect> FLAG_SWINGS = REGISTRY.register("flag_swings", FlagSwingsMobEffect::new);
	public static final RegistryObject<MobEffect> KEEP_BEDDING = REGISTRY.register("keep_bedding", KeepBeddingMobEffect::new);
	public static final RegistryObject<MobEffect> SURVIVORS_GUIDE = REGISTRY.register("survivors_guide", SurvivorsGuideMobEffect::new);
	public static final RegistryObject<MobEffect> ADD_REACH = REGISTRY.register("add_reach", AddReachMobEffect::new);
	public static final RegistryObject<MobEffect> FROZEN = REGISTRY.register("frozen", FrozenMobEffect::new);
	public static final RegistryObject<MobEffect> FISH_REAP = REGISTRY.register("fish_reap", FishReapMobEffect::new);
	public static final RegistryObject<MobEffect> TRAIL_BUFF = REGISTRY.register("trail_buff", TrailBuffMobEffect::new);
	public static final RegistryObject<MobEffect> TIDE_OF_CHITIN = REGISTRY.register("tide_of_chitin", TideOfChitinMobEffect::new);
	public static final RegistryObject<MobEffect> SANIDY_DEFENDER = REGISTRY.register("sanidy_defender", SanidyDefenderMobEffect::new);
	public static final RegistryObject<MobEffect> UNTAME_CONFIRM = REGISTRY.register("untame_confirm", UntameConfirmMobEffect::new);
	public static final RegistryObject<MobEffect> SPLASHER_ATTACK = REGISTRY.register("splasher_attack", SplasherAttackMobEffect::new);
	public static final RegistryObject<MobEffect> INSTANT_SANITY = REGISTRY.register("instant_sanity", InstantSanityMobEffect::new);
	public static final RegistryObject<MobEffect> SANITY_HEAL = REGISTRY.register("sanity_heal", SanityHealMobEffect::new);
	public static final RegistryObject<MobEffect> ROCK_BREAK = REGISTRY.register("rock_break", RockBreakMobEffect::new);
	public static final RegistryObject<MobEffect> POWER_OF_ANCHOR = REGISTRY.register("power_of_anchor", PowerOfAnchorMobEffect::new);
	public static final RegistryObject<MobEffect> UMBRELLA_SETTLE = REGISTRY.register("umbrella_settle", UmbrellaSettleMobEffect::new);
	public static final RegistryObject<MobEffect> COOLDOWN_SINAL = REGISTRY.register("cooldown_sinal", CooldownSinalMobEffect::new);
	public static final RegistryObject<MobEffect> FLESHDEFORMITY = REGISTRY.register("fleshdeformity", FleshdeformityMobEffect::new);
	public static final RegistryObject<MobEffect> BOOST_OF_SILENCE = REGISTRY.register("boost_of_silence", BoostOfSilenceMobEffect::new);
	public static final RegistryObject<MobEffect> STRENGTH_OF_CROWD = REGISTRY.register("strength_of_crowd", StrengthOfCrowdMobEffect::new);
	public static final RegistryObject<MobEffect> INFESTED = REGISTRY.register("infested", InfestedMobEffect::new);
	public static final RegistryObject<MobEffect> ANGER_OF_TIDE = REGISTRY.register("anger_of_tide", AngerOfTideMobEffect::new);
	public static final RegistryObject<MobEffect> DEDUCT_ONE_SANITY = REGISTRY.register("deduct_one_sanity", DeductOneSanityMobEffect::new);
	public static final RegistryObject<MobEffect> ADD_ATTACK_PERCLY = REGISTRY.register("add_attack_percly", AddAttackPerclyMobEffect::new);
}
