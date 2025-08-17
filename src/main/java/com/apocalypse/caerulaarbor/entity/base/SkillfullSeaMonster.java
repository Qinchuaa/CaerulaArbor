package com.apocalypse.caerulaarbor.entity.base;

import com.apocalypse.caerulaarbor.init.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class SkillfullSeaMonster extends SeaMonster {
    protected SkillfullSeaMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public float[] skillP = new float[]{114,514};

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("MigrationCooldown", this.migrationCooldown);
        pCompound.putFloat("skillPoint0",skillP[0]);
        pCompound.putFloat("skillPoint1",skillP[1]);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.migrationCooldown = pCompound.getInt("MigrationCooldown");
        this.skillP[0] = pCompound.getInt("skillPoint0");
        this.skillP[1] = pCompound.getInt("skillPoint1");
    }

    /**
    * @param index 技能序号
     * @param isNatural 是否自然恢复
     * @param scale 技力恢复系数
     * @return 技力恢复是否完成
     */
    protected boolean skillReady(int index,boolean isNatural,float scale){
        boolean flag = skillP[index] <= 0;
        if (isNatural) skillP[index] -= scale;
        return flag;
    }

    protected void skillReset(int index, int point){
        skillP[index] = point;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }
}
