package com.apocalypse.caerulaarbor.entity.base;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.map.MapVariables;
import com.apocalypse.caerulaarbor.capability.map.MapVariablesHandler;
import com.apocalypse.caerulaarbor.client.font.ModFontHelper;
import com.apocalypse.caerulaarbor.config.server.MiscConfig;
import com.apocalypse.caerulaarbor.init.ModGameRules;
import com.apocalypse.caerulaarbor.init.ModTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public abstract class SeaMonster extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected boolean swinging;
    protected long lastSwing;
    // 策略-迁徙的摇人冷却
    public int migrationCooldown;

    protected SeaMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("MigrationCooldown", this.migrationCooldown);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.migrationCooldown = pCompound.getInt("MigrationCooldown");
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean flag = super.hurt(source, amount);
        if (source.is(DamageTypes.DROWN)) {
            return false;
        }
        if (this.level().getLevelData().getGameRules().getBoolean(ModGameRules.NATURAL_EVOLUTION) && source.getEntity() != null) {
            MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.SUBSISTING, this.level(), Math.min(amount * 0.025, this.getMaxHealth()));
            MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.SILENCE, this.level(), Math.min(amount * 0.025, this.getMaxHealth()));
        }

        // 策略-迁徙等级大于0时，被有来源的伤害攻击后，会召唤附近海嗣锁定目标
        int migrationLevel = MapVariables.get(this.level()).strategyMigration;
        if (migrationLevel > 0 && this.migrationCooldown <= 0) {
            if (!(source.getEntity() instanceof LivingEntity living)) return flag;
            if (living == this) return flag;

            int radius = migrationLevel * 32;
            this.level().getEntities(this,
                    new AABB(
                            this.getX() - radius, this.getY() - 16, this.getZ() - radius,
                            this.getX() + radius, this.getY() + 16, this.getZ() + radius
                    ),
                    entity -> entity.getType().is(ModTags.EntityTypes.SEA_BORN) && entity != this
            ).forEach(entity -> {
                if (entity instanceof SeaMonster seaMonster) {
                    seaMonster.getNavigation().moveTo(this, 1 + 0.1 * migrationLevel);
                    seaMonster.setTarget(living);
                }
            });
            this.migrationCooldown = 200;
        }
        return flag;
    }

    @Override
    public void tick() {
        super.tick();

        this.naturalRegeneration();
        if (this.tickCount % 10 == 0) {
            this.addMigrationEvoPoint();
        }
        if (this.migrationCooldown > 0) {
            this.migrationCooldown--;
        }
    }

    /**
     * 策略-迁徙，在有目标的情况下，有概率增长迁徙进化点数
     */
    private void addMigrationEvoPoint() {
        if (this.getTarget() != null && this.level().getLevelData().getGameRules().getBoolean(ModGameRules.NATURAL_EVOLUTION)) {
            if (this.getTarget() instanceof Player player && ModCapabilities.getPlayerVariables(player).seabornization >= 3) {
                return;
            }
            if (this.random.nextDouble() < 0.08) {
                double amount = new Random().nextDouble(0, 0.05);
                MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.SUBSISTING, this.level(), amount);
                MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.SILENCE, this.level(), amount);
            }
        }
    }

    /**
     * 静谧-存续，在没有受击的情况下，快速恢复生命值
     */
    private void naturalRegeneration() {
        if (this.getLastAttacker() != null) return;

        var mapVariables = MapVariables.get(this.level());
        if (mapVariables.enabledStrategySilence && mapVariables.strategySilence > 0) {
            this.heal(0.0025f * mapVariables.strategySilence * this.getMaxHealth());
        }
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    /**
     * 策略-生长到达3级及以上时，攻击时额外造成魔法伤害
     * 成功攻击后，获得一定的策略-生长进化点数
     */
    @Override
    public boolean doHurtTarget(Entity pEntity) {
        float damage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        var level = this.level();
        double grow = MapVariables.get(level).strategyGrow;
        if (grow >= 3) {
            boolean flag = pEntity.hurt(level.damageSources().indirectMagic(this, this), (float) (damage * 0.2 * (grow - 2)));
            if (flag) {
                pEntity.invulnerableTime = 0;

                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.ENCHANTED_HIT, pEntity.getX(), pEntity.getY() + 1, pEntity.getZ(), 48, 1.5, 1.5, 1.5, 0.2);
                }
            }
            if (flag && super.doHurtTarget(pEntity)) {
                if (this.level().getLevelData().getGameRules().getBoolean(ModGameRules.NATURAL_EVOLUTION)) {
                    MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.GROW, level, damage * 0.025);
                    MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.SILENCE, level, damage * 0.025);
                }
                return true;
            }
            return false;
        }
        if (super.doHurtTarget(pEntity)) {
            if (this.level().getLevelData().getGameRules().getBoolean(ModGameRules.NATURAL_EVOLUTION)) {
                MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.GROW, level, damage * 0.025);
                MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.SILENCE, level, damage * 0.025);
            }
            return true;
        }
        return false;
    }

    // TODO 目前还没有在其他component渲染的地方实现海嗣文转写，需要完成这个
    @Override
    public Component getDisplayName() {
        if (this.hasCustomName()) {
            return super.getDisplayName();
        }
        return ModFontHelper.translatableSeaborn(this.getType().getDescriptionId(), MiscConfig.USE_SEABORN_LANGUAGE.get(), this.uuid.getLeastSignificantBits() % 2 == 0)
                .withStyle(style -> style.withHoverEvent(this.createHoverEvent()).withInsertion(this.getStringUUID()).withColor(this.getTeamColor()));
    }

    @Override
    public void die(DamageSource pDamageSource) {
        // 如果开启自然进化，被玩家击杀后，获得一定的策略-繁育进化点数
        if (this.level().getLevelData().getGameRules().getBoolean(ModGameRules.NATURAL_EVOLUTION)) {
            if (pDamageSource.getEntity() instanceof Player) {
                MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.BREED, this.level(), 0.1 * this.getMaxHealth());
                MapVariablesHandler.addEvoPoint(MapVariables.StrategyType.SILENCE, this.level(), 0.1 * this.getMaxHealth());
            }
        }
        super.die(pDamageSource);
    }
}
