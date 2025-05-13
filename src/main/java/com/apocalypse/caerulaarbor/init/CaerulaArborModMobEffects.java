
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import com.apocalypse.caerulaarbor.potion.WipeDustsMobEffect;
import com.apocalypse.caerulaarbor.potion.UntameConfirmMobEffect;
import com.apocalypse.caerulaarbor.potion.UmbrellaSettleMobEffect;
import com.apocalypse.caerulaarbor.potion.TrailBuffMobEffect;
import com.apocalypse.caerulaarbor.potion.TideOfChitinMobEffect;
import com.apocalypse.caerulaarbor.potion.SurvivorsGuideMobEffect;
import com.apocalypse.caerulaarbor.potion.StrengthOfCrowdMobEffect;
import com.apocalypse.caerulaarbor.potion.SplasherAttackMobEffect;
import com.apocalypse.caerulaarbor.potion.SpearFightMobEffect;
import com.apocalypse.caerulaarbor.potion.SelfKillMobEffect;
import com.apocalypse.caerulaarbor.potion.SanityProtectMobEffect;
import com.apocalypse.caerulaarbor.potion.SanityImmueMobEffect;
import com.apocalypse.caerulaarbor.potion.SanityHealMobEffect;
import com.apocalypse.caerulaarbor.potion.SanidyDefenderMobEffect;
import com.apocalypse.caerulaarbor.potion.SacreficeMobEffect;
import com.apocalypse.caerulaarbor.potion.RockBreakMobEffect;
import com.apocalypse.caerulaarbor.potion.ReefCrackerMobEffect;
import com.apocalypse.caerulaarbor.potion.PowerOfAnchorMobEffect;
import com.apocalypse.caerulaarbor.potion.LessArmorMobEffect;
import com.apocalypse.caerulaarbor.potion.KingsBreathMobEffect;
import com.apocalypse.caerulaarbor.potion.KingsBoostMobEffect;
import com.apocalypse.caerulaarbor.potion.KeepBeddingMobEffect;
import com.apocalypse.caerulaarbor.potion.InvulnerableMobEffect;
import com.apocalypse.caerulaarbor.potion.InstantSanityMobEffect;
import com.apocalypse.caerulaarbor.potion.InfestedMobEffect;
import com.apocalypse.caerulaarbor.potion.HandsSpeedMobEffect;
import com.apocalypse.caerulaarbor.potion.HaemophiliaMobEffect;
import com.apocalypse.caerulaarbor.potion.FrozenMobEffect;
import com.apocalypse.caerulaarbor.potion.FleshdeformityMobEffect;
import com.apocalypse.caerulaarbor.potion.FlagSwingsMobEffect;
import com.apocalypse.caerulaarbor.potion.FishReapMobEffect;
import com.apocalypse.caerulaarbor.potion.EngravedTriumphMobEffect;
import com.apocalypse.caerulaarbor.potion.DizzyMobEffect;
import com.apocalypse.caerulaarbor.potion.DeductOneSanityMobEffect;
import com.apocalypse.caerulaarbor.potion.CooldownSinalMobEffect;
import com.apocalypse.caerulaarbor.potion.ButchersPowerMobEffect;
import com.apocalypse.caerulaarbor.potion.BoostOfSilenceMobEffect;
import com.apocalypse.caerulaarbor.potion.AngerOfTideMobEffect;
import com.apocalypse.caerulaarbor.potion.AddReachMobEffect;
import com.apocalypse.caerulaarbor.potion.AddAttackPerclyMobEffect;

public class CaerulaArborModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CaerulaArborMod.MODID);
	public static final RegistryObject<MobEffect> HAEMOPHILIA = REGISTRY.register("haemophilia", () -> new HaemophiliaMobEffect());
	public static final RegistryObject<MobEffect> SANITY_IMMUE = REGISTRY.register("sanity_immue", () -> new SanityImmueMobEffect());
	public static final RegistryObject<MobEffect> DIZZY = REGISTRY.register("dizzy", () -> new DizzyMobEffect());
	public static final RegistryObject<MobEffect> KINGS_BREATH = REGISTRY.register("kings_breath", () -> new KingsBreathMobEffect());
	public static final RegistryObject<MobEffect> KINGS_BOOST = REGISTRY.register("kings_boost", () -> new KingsBoostMobEffect());
	public static final RegistryObject<MobEffect> SPEAR_FIGHT = REGISTRY.register("spear_fight", () -> new SpearFightMobEffect());
	public static final RegistryObject<MobEffect> HANDS_SPEED = REGISTRY.register("hands_speed", () -> new HandsSpeedMobEffect());
	public static final RegistryObject<MobEffect> BUTCHERS_POWER = REGISTRY.register("butchers_power", () -> new ButchersPowerMobEffect());
	public static final RegistryObject<MobEffect> WIPE_DUSTS = REGISTRY.register("wipe_dusts", () -> new WipeDustsMobEffect());
	public static final RegistryObject<MobEffect> SACREFICE = REGISTRY.register("sacrefice", () -> new SacreficeMobEffect());
	public static final RegistryObject<MobEffect> ENGRAVED_TRIUMPH = REGISTRY.register("engraved_triumph", () -> new EngravedTriumphMobEffect());
	public static final RegistryObject<MobEffect> FLAG_SWINGS = REGISTRY.register("flag_swings", () -> new FlagSwingsMobEffect());
	public static final RegistryObject<MobEffect> KEEP_BEDDING = REGISTRY.register("keep_bedding", () -> new KeepBeddingMobEffect());
	public static final RegistryObject<MobEffect> SURVIVORS_GUIDE = REGISTRY.register("survivors_guide", () -> new SurvivorsGuideMobEffect());
	public static final RegistryObject<MobEffect> ADD_REACH = REGISTRY.register("add_reach", () -> new AddReachMobEffect());
	public static final RegistryObject<MobEffect> FROZEN = REGISTRY.register("frozen", () -> new FrozenMobEffect());
	public static final RegistryObject<MobEffect> FISH_REAP = REGISTRY.register("fish_reap", () -> new FishReapMobEffect());
	public static final RegistryObject<MobEffect> TRAIL_BUFF = REGISTRY.register("trail_buff", () -> new TrailBuffMobEffect());
	public static final RegistryObject<MobEffect> TIDE_OF_CHITIN = REGISTRY.register("tide_of_chitin", () -> new TideOfChitinMobEffect());
	public static final RegistryObject<MobEffect> SANITY_PROTECT = REGISTRY.register("sanity_protect", () -> new SanityProtectMobEffect());
	public static final RegistryObject<MobEffect> SANIDY_DEFENDER = REGISTRY.register("sanidy_defender", () -> new SanidyDefenderMobEffect());
	public static final RegistryObject<MobEffect> UNTAME_CONFIRM = REGISTRY.register("untame_confirm", () -> new UntameConfirmMobEffect());
	public static final RegistryObject<MobEffect> SPLASHER_ATTACK = REGISTRY.register("splasher_attack", () -> new SplasherAttackMobEffect());
	public static final RegistryObject<MobEffect> INSTANT_SANITY = REGISTRY.register("instant_sanity", () -> new InstantSanityMobEffect());
	public static final RegistryObject<MobEffect> SANITY_HEAL = REGISTRY.register("sanity_heal", () -> new SanityHealMobEffect());
	public static final RegistryObject<MobEffect> ROCK_BREAK = REGISTRY.register("rock_break", () -> new RockBreakMobEffect());
	public static final RegistryObject<MobEffect> POWER_OF_ANCHOR = REGISTRY.register("power_of_anchor", () -> new PowerOfAnchorMobEffect());
	public static final RegistryObject<MobEffect> UMBRELLA_SETTLE = REGISTRY.register("umbrella_settle", () -> new UmbrellaSettleMobEffect());
	public static final RegistryObject<MobEffect> COOLDOWN_SINAL = REGISTRY.register("cooldown_sinal", () -> new CooldownSinalMobEffect());
	public static final RegistryObject<MobEffect> REEF_CRACKER = REGISTRY.register("reef_cracker", () -> new ReefCrackerMobEffect());
	public static final RegistryObject<MobEffect> FLESHDEFORMITY = REGISTRY.register("fleshdeformity", () -> new FleshdeformityMobEffect());
	public static final RegistryObject<MobEffect> BOOST_OF_SILENCE = REGISTRY.register("boost_of_silence", () -> new BoostOfSilenceMobEffect());
	public static final RegistryObject<MobEffect> STRENGTH_OF_CROWD = REGISTRY.register("strength_of_crowd", () -> new StrengthOfCrowdMobEffect());
	public static final RegistryObject<MobEffect> INFESTED = REGISTRY.register("infested", () -> new InfestedMobEffect());
	public static final RegistryObject<MobEffect> LESS_ARMOR = REGISTRY.register("less_armor", () -> new LessArmorMobEffect());
	public static final RegistryObject<MobEffect> SELF_KILL = REGISTRY.register("self_kill", () -> new SelfKillMobEffect());
	public static final RegistryObject<MobEffect> INVULNERABLE = REGISTRY.register("invulnerable", () -> new InvulnerableMobEffect());
	public static final RegistryObject<MobEffect> ANGER_OF_TIDE = REGISTRY.register("anger_of_tide", () -> new AngerOfTideMobEffect());
	public static final RegistryObject<MobEffect> DEDUCT_ONE_SANITY = REGISTRY.register("deduct_one_sanity", () -> new DeductOneSanityMobEffect());
	public static final RegistryObject<MobEffect> ADD_ATTACK_PERCLY = REGISTRY.register("add_attack_percly", () -> new AddAttackPerclyMobEffect());
}
