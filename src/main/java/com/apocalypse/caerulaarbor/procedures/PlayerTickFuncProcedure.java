package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.configuration.CaerulaConfigsConfiguration;
import com.apocalypse.caerulaarbor.init.CaerulaArborModAttributes;
import com.apocalypse.caerulaarbor.init.CaerulaArborModEnchantments;
import com.apocalypse.caerulaarbor.init.ModItems;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;

@Mod.EventBusSubscriber
public class PlayerTickFuncProcedure {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
        }
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        execute(null, world, x, y, z, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        boolean valid;
        double modifi;
        double suitKing;
        double amplifi;
        double suitArchfi;
        double enchant;
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).disoclusion == 2) {
            if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModMobEffects.HAEMOPHILIA.get()))) {
                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                    _entity.addEffect(new MobEffectInstance(ModMobEffects.HAEMOPHILIA.get(), 299, 0, false, false));
            }
        } else {
            if (entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(ModMobEffects.HAEMOPHILIA.get())) {
                if (entity instanceof LivingEntity _entity)
                    _entity.removeEffect(ModMobEffects.HAEMOPHILIA.get());
            }
        }
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).disoclusion == 4) {
            if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffects.FLESHDEFORMITY.get()) ? _livEnt.getEffect(ModMobEffects.FLESHDEFORMITY.get()).getDuration() : 0) < 5) {
                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                    _entity.addEffect(new MobEffectInstance(ModMobEffects.FLESHDEFORMITY.get(), 200, 1, false, false));
            }
        } else {
            if (entity instanceof LivingEntity _livEnt6 && _livEnt6.hasEffect(ModMobEffects.FLESHDEFORMITY.get())) {
                if (entity instanceof LivingEntity _entity)
                    _entity.removeEffect(ModMobEffects.HAEMOPHILIA.get());
            }
        }
        modifi = 1;
        if (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY)
                .map(c -> c.light >= 1 && c.light < 50)
                .orElse(false)) {
            modifi = 1.2;
        } else if (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY)
                .map(c -> c.light < 1)
                .orElse(false)) {
            modifi = 1.5;
        }
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).player_oceanization >= 3) {
            modifi = 0.33;
        }
        if (entity instanceof LivingEntity _livingEntity8 && _livingEntity8.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY_MODIFIER.get()))
            _livingEntity8.getAttribute(CaerulaArborModAttributes.SANITY_MODIFIER.get()).setBaseValue(modifi);
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).lives <= 1) {
            suitKing = 0;
            if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_king_SPEAR) {
                suitKing = suitKing + 1;
                if (!(entity instanceof LivingEntity _livEnt9 && _livEnt9.hasEffect(ModMobEffects.KINGS_BOOST.get()))) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(ModMobEffects.KINGS_BOOST.get(), 100, 1, false, false));
                }
            }
            if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_king_ARMOR) {
                suitKing = suitKing + 1;
            }
            if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_king_EXTENSION) {
                suitKing = suitKing + 1;
                amplifi = Math.ceil((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 20);
                if (amplifi > 24) {
                    amplifi = 24;
                }
                if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.REGENERATION) ? _livEnt.getEffect(MobEffects.REGENERATION).getAmplifier() : 0) < amplifi) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, (int) amplifi, false, false));
                }
            }
            if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_king_CROWN) {
                suitKing = suitKing + 1;
                if (!(entity instanceof LivingEntity _livEnt14 && _livEnt14.hasEffect(ModMobEffects.KINGS_BREATH.get()))) {
                    if (suitKing < 3) {
                        if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModMobEffects.KINGS_BREATH.get(), 30, 0, false, false));
                        {
                            double _setval = 1;
                            entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                                capability.player_king_suit = _setval;
                                capability.syncPlayerVariables(entity);
                            });
                        }
                    } else {
                        if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModMobEffects.KINGS_BREATH.get(), 3, 2, false, false));
                        {
                            double _setval = 2;
                            entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                                capability.player_king_suit = _setval;
                                capability.syncPlayerVariables(entity);
                            });
                        }
                    }
                }
            } else {
                {
                    double _setval = 0;
                    entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                        capability.player_king_suit = _setval;
                        capability.syncPlayerVariables(entity);
                    });
                }
            }
        }
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_hand_SPEED
                && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("minecraft:pickaxes")))) {
            valid = true;
            {
                final Vec3 _center = new Vec3(x, y, z);
                List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
                for (Entity entityiterator : _entfound) {
                    if (entityiterator == entity) {
                        continue;
                    }
                    if (entityiterator instanceof ServerPlayer || entityiterator instanceof Player || entityiterator instanceof Animal) {
                        valid = false;
                    }
                }
            }
            if (valid) {
                if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.DIG_SPEED) ? _livEnt.getEffect(MobEffects.DIG_SPEED).getAmplifier() : 0) < 2) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 2));
                }
                if (!(entity instanceof LivingEntity _livEnt26 && _livEnt26.hasEffect(ModMobEffects.HANDS_SPEED.get()))) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(ModMobEffects.HANDS_SPEED.get(), 20, 2));
                }
            }
        }
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_hand_SWIPE) {
            if (!(entity instanceof LivingEntity _livEnt28 && _livEnt28.hasEffect(ModMobEffects.WIPE_DUSTS.get()))
                    && (entity instanceof Player _playerHasItem && _playerHasItem.getInventory().contains(new ItemStack(Items.BRUSH)))) {
                if (!_playerHasItem.level().isClientSide())
                    _playerHasItem.addEffect(new MobEffectInstance(ModMobEffects.WIPE_DUSTS.get(), 100, 0, false, false));
            }
        }
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).lives >= (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY)
                .orElse(new CaerulaArborModVariables.PlayerVariables())).maxLive) {
            suitArchfi = 0;
            if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_archfi_FLAG) {
                suitArchfi = suitArchfi + 1;
                if (!(entity instanceof LivingEntity _livEnt31 && _livEnt31.hasEffect(ModMobEffects.FLAG_SWINGS.get()))) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(ModMobEffects.FLAG_SWINGS.get(), 30, 2, false, false));
                }
            }
            if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_archfi_BED) {
                suitArchfi = suitArchfi + 1;
                if (!(entity instanceof LivingEntity _livEnt33 && _livEnt33.hasEffect(ModMobEffects.KEEP_BEDDING.get()))) {
                    if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(ModMobEffects.KEEP_BEDDING.get(), 30, 0, false, false));
                }
            }
            if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_archfi_ARTIFACT) {
                suitArchfi = suitArchfi + 1;
                if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffects.SACREFICE.get()) ? _livEnt.getEffect(ModMobEffects.SACREFICE.get()).getDuration() : 0) < 5) {
                    if (suitArchfi < 3) {
                        if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModMobEffects.SACREFICE.get(), 30, 0, false, false));

                        entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                            capability.player_demon_suit = 1;
                            capability.syncPlayerVariables(entity);
                        });
                    } else {
                        if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModMobEffects.SACREFICE.get(), 30, 2, false, false));

                        entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                            capability.player_demon_suit = 2;
                            capability.syncPlayerVariables(entity);
                        });
                    }
                }
            } else {
                {
                    double _setval = 0;
                    entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                        capability.player_demon_suit = _setval;
                        capability.syncPlayerVariables(entity);
                    });
                }
            }
        }
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_hand_ENGRAVE > 0) {
            valid = false;
            if (entity instanceof Player _playerHasItem && _playerHasItem.getInventory().contains(new ItemStack(Items.TRIDENT))) {
                valid = true;
            } else {
                for (String stringiterator : CaerulaConfigsConfiguration.HAND_ENGRAVE.get()) {
                    if ((entity instanceof Player _playerHasItem && _playerHasItem.getInventory().contains(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((stringiterator).toLowerCase(java.util.Locale.ENGLISH))))))
                            && !(ForgeRegistries.ITEMS.getValue(new ResourceLocation((stringiterator).toLowerCase(java.util.Locale.ENGLISH))) == ItemStack.EMPTY.getItem()
                            || ForgeRegistries.ITEMS.getValue(new ResourceLocation((stringiterator).toLowerCase(java.util.Locale.ENGLISH))) == Blocks.AIR.asItem())) {
                        valid = true;
                    }
                }
            }
            if (valid) {
                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                    _entity.addEffect(new MobEffectInstance(ModMobEffects.ENGRAVED_TRIUMPH.get(), 40,
                            (int) ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_hand_ENGRAVE - 1), false, false));
                }
            }
        }
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_SURVIVOR > 0) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                _entity.addEffect(new MobEffectInstance(ModMobEffects.SURVIVORS_GUIDE.get(), 40,
                        (int) ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_SURVIVOR - 1), false, false));
            }
        }
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).lives > (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY)
                .orElse(new CaerulaArborModVariables.PlayerVariables())).maxLive) {
            entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                capability.lives = (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).maxLive;
                capability.syncPlayerVariables(entity);
            });
        }
        if ((entity instanceof LivingEntity _livingEntity52 && _livingEntity52.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()) ? _livingEntity52.getAttribute(CaerulaArborModAttributes.SANITY.get()).getBaseValue() : 0) > 1000) {
            if (entity instanceof LivingEntity _livingEntity53 && _livingEntity53.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()))
                _livingEntity53.getAttribute(CaerulaArborModAttributes.SANITY.get()).setBaseValue(1000);
        }

        double suit;
        suit = 0;
        if ((entity instanceof LivingEntity _entGetArmor1 ? _entGetArmor1.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == ModItems.COMPLEXCHITIN_ARMOR_BOOTS.get()) {
            suit = suit + 1;
        }
        if ((entity instanceof LivingEntity _entGetArmor1 ? _entGetArmor1.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == ModItems.COMPLEXCHITIN_ARMOR_LEGGINGS.get()) {
            suit = suit + 1;
        }
        if ((entity instanceof LivingEntity _entGetArmor1 ? _entGetArmor1.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == ModItems.COMPLEXCHITIN_ARMOR_CHESTPLATE.get()) {
            suit = suit + 1;
        }
        if ((entity instanceof LivingEntity _entGetArmor1 ? _entGetArmor1.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == ModItems.COMPLEXCHITIN_ARMOR_HELMET.get()) {
            suit = suit + 1;
        }
        if (suit > 0 && !(entity instanceof LivingEntity _livEnt8 && _livEnt8.hasEffect(ModMobEffects.SANITY_PROTECT.get()))) {
            if (entity instanceof LivingEntity _entity1 && !_entity1.level().isClientSide())
                _entity1.addEffect(new MobEffectInstance(ModMobEffects.SANITY_PROTECT.get(), 40, (int) (suit - 1), false, false));
        }

        enchant = 0;
        if (EnchantmentHelper.getItemEnchantmentLevel(CaerulaArborModEnchantments.SANITY_DEFEND.get(), (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)) != 0) {
            enchant = enchant + (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getEnchantmentLevel(CaerulaArborModEnchantments.SANITY_DEFEND.get());
        }
        if (EnchantmentHelper.getItemEnchantmentLevel(CaerulaArborModEnchantments.SANITY_DEFEND.get(), (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)) != 0) {
            enchant = enchant + (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getEnchantmentLevel(CaerulaArborModEnchantments.SANITY_DEFEND.get());
        }
        if (EnchantmentHelper.getItemEnchantmentLevel(CaerulaArborModEnchantments.SANITY_DEFEND.get(), (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)) != 0) {
            enchant = enchant + (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getEnchantmentLevel(CaerulaArborModEnchantments.SANITY_DEFEND.get());
        }
        if (EnchantmentHelper.getItemEnchantmentLevel(CaerulaArborModEnchantments.SANITY_DEFEND.get(), (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)) != 0) {
            enchant = enchant + (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getEnchantmentLevel(CaerulaArborModEnchantments.SANITY_DEFEND.get());
        }
        if (enchant > 16) {
            enchant = 16;
        }
        if (enchant > 0 && !(entity instanceof LivingEntity _livEnt70 && _livEnt70.hasEffect(ModMobEffects.SANIDY_DEFENDER.get()))) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                _entity.addEffect(new MobEffectInstance(ModMobEffects.SANIDY_DEFENDER.get(), 20, (int) (enchant - 1), false, false));
        }
        if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == ModItems.WEARABLE_CROWN_HELMET.get()) {
            GainRelicCROWNProcedure.execute(world, x, y, z, entity, entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY);
        }
    }
}
