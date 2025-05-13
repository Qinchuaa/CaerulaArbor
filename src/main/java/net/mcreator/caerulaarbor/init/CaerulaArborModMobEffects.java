
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.caerulaarbor.potion.WipeDustsMobEffect;
import net.mcreator.caerulaarbor.potion.UntameConfirmMobEffect;
import net.mcreator.caerulaarbor.potion.UmbrellaSettleMobEffect;
import net.mcreator.caerulaarbor.potion.TrailBuffMobEffect;
import net.mcreator.caerulaarbor.potion.TideOfChitinMobEffect;
import net.mcreator.caerulaarbor.potion.SurvivorsGuideMobEffect;
import net.mcreator.caerulaarbor.potion.StrengthOfCrowdMobEffect;
import net.mcreator.caerulaarbor.potion.SplasherAttackMobEffect;
import net.mcreator.caerulaarbor.potion.SpearFightMobEffect;
import net.mcreator.caerulaarbor.potion.SelfKillMobEffect;
import net.mcreator.caerulaarbor.potion.SanityProtectMobEffect;
import net.mcreator.caerulaarbor.potion.SanityImmueMobEffect;
import net.mcreator.caerulaarbor.potion.SanityHealMobEffect;
import net.mcreator.caerulaarbor.potion.SanidyDefenderMobEffect;
import net.mcreator.caerulaarbor.potion.SacreficeMobEffect;
import net.mcreator.caerulaarbor.potion.RockBreakMobEffect;
import net.mcreator.caerulaarbor.potion.ReefCrackerMobEffect;
import net.mcreator.caerulaarbor.potion.PowerOfAnchorMobEffect;
import net.mcreator.caerulaarbor.potion.LessArmorMobEffect;
import net.mcreator.caerulaarbor.potion.KingsBreathMobEffect;
import net.mcreator.caerulaarbor.potion.KingsBoostMobEffect;
import net.mcreator.caerulaarbor.potion.KeepBeddingMobEffect;
import net.mcreator.caerulaarbor.potion.InvulnerableMobEffect;
import net.mcreator.caerulaarbor.potion.InstantSanityMobEffect;
import net.mcreator.caerulaarbor.potion.InfestedMobEffect;
import net.mcreator.caerulaarbor.potion.HandsSpeedMobEffect;
import net.mcreator.caerulaarbor.potion.HaemophiliaMobEffect;
import net.mcreator.caerulaarbor.potion.FrozenMobEffect;
import net.mcreator.caerulaarbor.potion.FleshdeformityMobEffect;
import net.mcreator.caerulaarbor.potion.FlagSwingsMobEffect;
import net.mcreator.caerulaarbor.potion.FishReapMobEffect;
import net.mcreator.caerulaarbor.potion.EngravedTriumphMobEffect;
import net.mcreator.caerulaarbor.potion.DizzyMobEffect;
import net.mcreator.caerulaarbor.potion.DeductOneSanityMobEffect;
import net.mcreator.caerulaarbor.potion.CooldownSinalMobEffect;
import net.mcreator.caerulaarbor.potion.ButchersPowerMobEffect;
import net.mcreator.caerulaarbor.potion.BoostOfSilenceMobEffect;
import net.mcreator.caerulaarbor.potion.AngerOfTideMobEffect;
import net.mcreator.caerulaarbor.potion.AddReachMobEffect;
import net.mcreator.caerulaarbor.potion.AddAttackPerclyMobEffect;
import net.mcreator.caerulaarbor.CaerulaArborMod;

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
