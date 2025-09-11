package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributes {

    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, CaerulaArborMod.MODID);

    // 受到神经损伤时的减免效果
    public static final RegistryObject<Attribute> SANITY_INJURY_RESISTANCE = REGISTRY.register("sanity_injury_resistance", () -> new RangedAttribute("attribute.caerula_arbor.sanity_injury_resistance", 0, 0, 100).setSyncable(true));
    // 自然恢复神经损伤的速率
    public static final RegistryObject<Attribute> SANITY_REGENERATE = REGISTRY.register("sanity_regenerate", () -> new RangedAttribute("attribute.caerula_arbor.sanity_regenerate", 0, 0, 1000).setSyncable(true));
    // 攻击时造成的神经损伤（固定值）
    public static final RegistryObject<Attribute> SANITY_INJURY_DAMAGE = REGISTRY.register("sanity_injury_damage", () -> new RangedAttribute("attribute.caerula_arbor.sanity_injury_damage", 0, 0, Integer.MAX_VALUE).setSyncable(true));
    // 攻击时根据伤害附带的神经损伤比例
    public static final RegistryObject<Attribute> SANITY_INJURY_DAMAGE_RATE = REGISTRY.register("sanity_injury_damage_rate", () -> new RangedAttribute("attribute.caerula_arbor.sanity_injury_damage_rate", 0, 0, 10000).setSyncable(true));
    // 全局神经损伤伤害系数
    public static final RegistryObject<Attribute> GLOBAL_SANITY_INJURY_RATE = REGISTRY.register("global_sanity_injury_rate", () -> new RangedAttribute("attribute.caerula_arbor.global_sanity_injury_rate", 1, 0, 10000).setSyncable(true));

    @SubscribeEvent
    public static void addAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(entity -> {
            event.add(entity, SANITY_INJURY_RESISTANCE.get());
            event.add(entity, SANITY_REGENERATE.get());
            event.add(entity, SANITY_INJURY_DAMAGE.get());
            event.add(entity, SANITY_INJURY_DAMAGE_RATE.get());
            event.add(entity, GLOBAL_SANITY_INJURY_RATE.get());
        });
    }

    @Mod.EventBusSubscriber
    public static class PlayerAttributesSync {
        @SubscribeEvent
        public static void playerClone(PlayerEvent.Clone event) {
            Player oldPlayer = event.getOriginal();
            Player newPlayer = event.getEntity();
            newPlayer.getAttribute(SANITY_INJURY_RESISTANCE.get()).setBaseValue(oldPlayer.getAttribute(SANITY_INJURY_RESISTANCE.get()).getBaseValue());
            newPlayer.getAttribute(SANITY_REGENERATE.get()).setBaseValue(oldPlayer.getAttribute(SANITY_REGENERATE.get()).getBaseValue());
            newPlayer.getAttribute(SANITY_INJURY_DAMAGE.get()).setBaseValue(oldPlayer.getAttribute(SANITY_INJURY_DAMAGE.get()).getBaseValue());
            newPlayer.getAttribute(SANITY_INJURY_DAMAGE_RATE.get()).setBaseValue(oldPlayer.getAttribute(SANITY_INJURY_DAMAGE_RATE.get()).getBaseValue());
            newPlayer.getAttribute(GLOBAL_SANITY_INJURY_RATE.get()).setBaseValue(oldPlayer.getAttribute(GLOBAL_SANITY_INJURY_RATE.get()).getBaseValue());
        }
    }
}
