package com.apocalypse.caerulaarbor.event;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.api.event.RelicEvent;
import com.apocalypse.caerulaarbor.block.SeaTrailBaseBlock;
import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.sanity.SanityInjuryCapability;
import com.apocalypse.caerulaarbor.init.ModAttributes;
import com.apocalypse.caerulaarbor.init.ModTags;
import com.apocalypse.caerulaarbor.item.relic.IRelic;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingEventHandler {

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        var living = event.getEntity();

        living.getCapability(ModCapabilities.SANITY_INJURY).ifPresent(cap -> {
            if (cap instanceof SanityInjuryCapability capImpl) capImpl.tick();
        });
    }

    @SubscribeEvent
    public static void onGainRelic(RelicEvent.Gain event) {
        if (!(event.player instanceof Player player)) return;
        if (!(event.relic.item instanceof IRelic iRelic)) return;
        if (player.level().isClientSide) return;

        for (Map.Entry<Attribute, AttributeModifier> entry : iRelic.getRelicAttributeModifiers(player).entrySet()) {
            AttributeInstance attributeinstance = player.getAttributes().getInstance(entry.getKey());
            if (attributeinstance != null) {
                AttributeModifier attributemodifier = entry.getValue();
                attributeinstance.removeModifier(attributemodifier);
                attributeinstance.addPermanentModifier(new AttributeModifier(attributemodifier.getId(), attributemodifier.getName(),
                        attributemodifier.getAmount(), attributemodifier.getOperation()));
            }
        }
    }

    @SubscribeEvent
    public static void onUpdateRelic(RelicEvent.Update event) {
        if (!(event.player instanceof Player player)) return;
        if (!(event.relic.item instanceof IRelic iRelic)) return;
        if (player.level().isClientSide) return;

        for (Map.Entry<Attribute, AttributeModifier> entry : iRelic.getRelicAttributeModifiers(player).entrySet()) {
            AttributeInstance attributeinstance = player.getAttributes().getInstance(entry.getKey());
            if (attributeinstance != null) {
                attributeinstance.removeModifier(entry.getValue());
            }
        }

        for (Map.Entry<Attribute, AttributeModifier> entry : iRelic.getRelicAttributeModifiers(player).entrySet()) {
            AttributeInstance attributeinstance = player.getAttributes().getInstance(entry.getKey());
            if (attributeinstance != null) {
                AttributeModifier attributemodifier = entry.getValue();
                attributeinstance.removeModifier(attributemodifier);
                attributeinstance.addPermanentModifier(new AttributeModifier(attributemodifier.getId(), attributemodifier.getName(),
                        attributemodifier.getAmount(), attributemodifier.getOperation()));
            }
        }
    }

    @SubscribeEvent
    public static void onRemoveRelic(RelicEvent.Remove event) {
        if (!(event.player instanceof Player player)) return;
        if (!(event.relic.item instanceof IRelic iRelic)) return;
        if (player.level().isClientSide) return;

        for (Map.Entry<Attribute, AttributeModifier> entry : iRelic.getRelicAttributeModifiers(player).entrySet()) {
            AttributeInstance attributeinstance = player.getAttributes().getInstance(entry.getKey());
            if (attributeinstance != null) {
                attributeinstance.removeModifier(entry.getValue());
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (!(level instanceof ServerLevel serverLevel)) return;
        BlockPos entityPos = entity.blockPosition();
        Iterable<BlockPos> iter = BlockPos.betweenClosed(
                entityPos.offset(-1, -1, -1),
                entityPos.offset(1, 1, 1));
        for (BlockPos pos : iter) {
            if (!level.isInWorldBounds(pos)) continue;
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof SeaTrailBaseBlock seaTrailBaseBlock) {
                seaTrailBaseBlock.onEntityDeathNearby(serverLevel, pos, state);
            }
        }
    }

    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        var entity = event.getEntity();
        handleSanityInjuryResistance(entity);
        handleSeaBornAttributes(entity);
    }

    private static void handleSanityInjuryResistance(LivingEntity entity) {
        var attribute = entity.getAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get());
        if (attribute == null) return;
        if (entity.getType().is(ModTags.EntityTypes.SEA_BORN_BOSS)) {
            attribute.addPermanentModifier(new AttributeModifier(CaerulaArborMod.ATTRIBUTE_MODIFIER, 85, AttributeModifier.Operation.ADDITION));
        } else if (entity.getType().is(ModTags.EntityTypes.SEA_BORN)) {
            attribute.addPermanentModifier(new AttributeModifier(CaerulaArborMod.ATTRIBUTE_MODIFIER, 60, AttributeModifier.Operation.ADDITION));
        }
        if (entity instanceof IronGolem) {
            attribute.addPermanentModifier(new AttributeModifier(CaerulaArborMod.ATTRIBUTE_MODIFIER, 90, AttributeModifier.Operation.ADDITION));
        }
        if (entity instanceof Warden) {
            attribute.addPermanentModifier(new AttributeModifier(CaerulaArborMod.ATTRIBUTE_MODIFIER, 75, AttributeModifier.Operation.ADDITION));
        }
        if (entity.getMobType() == MobType.UNDEAD) {
            attribute.addPermanentModifier(new AttributeModifier(CaerulaArborMod.ATTRIBUTE_MODIFIER, 50, AttributeModifier.Operation.ADDITION));
        }
    }

    private static void handleSeaBornAttributes(LivingEntity entity) {
        if (!entity.getType().is(ModTags.EntityTypes.SEA_BORN)) return;
        var level = entity.level();
        double value = CaerulaArborModVariables.MapVariables.get(level).strategySubsisting;

        if (entity.getAttributes().hasAttribute(ForgeMod.SWIM_SPEED.get())) {

        }


//            _livingEntity2.getAttribute(ForgeMod.SWIM_SPEED.get())
//                    .setBaseValue(((entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED) ? _livingEntity1.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue() : 0) * 10));
//        if ((entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(ModAttributes.EVOLVED.get()) ? _livingEntity3.getAttribute(ModAttributes.EVOLVED.get()).getBaseValue() : 0) == 0) {
//            if (entity instanceof LivingEntity _livingEntity5 && _livingEntity5.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
//                _livingEntity5.getAttribute(Attributes.MAX_HEALTH)
//                        .setBaseValue(((entity instanceof LivingEntity _livingEntity4 && _livingEntity4.getAttributes().hasAttribute(Attributes.MAX_HEALTH) ? _livingEntity4.getAttribute(Attributes.MAX_HEALTH).getBaseValue() : 0)
//                                * (1 + 0.3 * CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting)));
//            if (entity instanceof LivingEntity _entity)
//                _entity.setHealth((float) (entity instanceof LivingEntity _livingEntity6 && _livingEntity6.getAttributes().hasAttribute(Attributes.MAX_HEALTH) ? _livingEntity6.getAttribute(Attributes.MAX_HEALTH).getValue() : 0));
//            if (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(Attributes.ARMOR))
//                _livingEntity9.getAttribute(Attributes.ARMOR)
//                        .setBaseValue(((entity instanceof LivingEntity _livingEntity8 && _livingEntity8.getAttributes().hasAttribute(Attributes.ARMOR) ? _livingEntity8.getAttribute(Attributes.ARMOR).getBaseValue() : 0)
//                                + 2 * CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting));
//            if (entity instanceof LivingEntity _livingEntity11 && _livingEntity11.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS))
//                _livingEntity11.getAttribute(Attributes.ARMOR_TOUGHNESS)
//                        .setBaseValue(((entity instanceof LivingEntity _livingEntity10 && _livingEntity10.getAttributes().hasAttribute(Attributes.ARMOR_TOUGHNESS) ? _livingEntity10.getAttribute(Attributes.ARMOR_TOUGHNESS).getBaseValue() : 0)
//                                + 2 * CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting));
//            if (entity instanceof LivingEntity _livingEntity13 && _livingEntity13.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
//                _livingEntity13.getAttribute(Attributes.ATTACK_DAMAGE)
//                        .setBaseValue(((entity instanceof LivingEntity _livingEntity12 && _livingEntity12.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity12.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0)
//                                * (1 + 0.25 * CaerulaArborModVariables.MapVariables.get(world).strategy_grow)));
//            if (CaerulaArborModVariables.MapVariables.get(world).strategy_breed > 0) {
//                if (!entity.getType().is(ModTags.EntityTypes.SEA_BORN) && !entity.getType().is(ModTags.EntityTypes.SEA_BORN_CREATURE)) {
//                    if (Math.random() < 0.05 + 0.05 * CaerulaArborModVariables.MapVariables.get(world).strategy_breed) {
//                        {
//                            Entity _ent = entity;
//                            if (!_ent.level().isClientSide() && _ent.getServer() != null) {
//                                _ent.getServer().getCommands().performPrefixedCommand(
//                                        new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(),
//                                                _ent.getDisplayName(), _ent.level().getServer(), _ent),
//                                        ("summon " + ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString() + " ~" + Mth.nextDouble(RandomSource.create(), -1, 1) + " ~ ~" + Mth.nextDouble(RandomSource.create(), -1, 1)));
//                            }
//                        }
//                    }
//                    if (CaerulaArborModVariables.MapVariables.get(world).strategy_breed >= 3) {
//                        if (Math.random() < 0.05 * (CaerulaArborModVariables.MapVariables.get(world).strategy_breed - 2)) {
//                            for (int index0 = 0; index0 < 2; index0++) {
//                                {
//                                    Entity _ent = entity;
//                                    if (!_ent.level().isClientSide() && _ent.getServer() != null) {
//                                        _ent.getServer().getCommands().performPrefixedCommand(
//                                                new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(),
//                                                        _ent.getDisplayName(), _ent.level().getServer(), _ent),
//                                                ("summon " + ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString() + " ~" + Mth.nextDouble(RandomSource.create(), -1, 1) + " ~ ~" + Mth.nextDouble(RandomSource.create(), -1, 1)));
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            if (entity instanceof LivingEntity _livingEntity24 && _livingEntity24.getAttributes().hasAttribute(ModAttributes.EVOLVED.get()))
//                _livingEntity24.getAttribute(ModAttributes.EVOLVED.get()).setBaseValue(1);
//        }
//    }
    }
}
