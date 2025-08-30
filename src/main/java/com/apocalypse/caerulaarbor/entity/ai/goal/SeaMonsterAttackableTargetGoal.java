package com.apocalypse.caerulaarbor.entity.ai.goal;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class SeaMonsterAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    public SeaMonsterAttackableTargetGoal(Mob pMob, Class<T> pTargetType, boolean pMustSee, boolean pMustReach) {
        super(pMob, pTargetType, 10, pMustSee, pMustReach,
                livingEntity -> !(livingEntity instanceof Player player)
                        || ModCapabilities.getPlayerVariables(player).seabornization < 3);
    }
}
