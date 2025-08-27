package com.apocalypse.caerulaarbor.entity.base;

import com.apocalypse.caerulaarbor.entity.ai.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class SkilledSeaMonster extends SeaMonster {

    public final List<Skill> skills = new ArrayList<>();
    private int durationTime = 0;

    public SkilledSeaMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void addSkill(Skill skill) {
        skill.init();
        this.skills.add(skill);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("durationTime",this.durationTime);
        ListTag list = new ListTag();
        for (var skill : this.skills) {
            CompoundTag tag = new CompoundTag();
            skill.serialize(tag);
            list.add(tag);
        }
        pCompound.put("Skills", list);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        ListTag list = pCompound.getList("Skills", CompoundTag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag tag = list.getCompound(i);
            Skill skill = Skill.deserialize(tag);
            this.addSkill(skill);
        }
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();
        this.regenerateSkill();
        this.durationTime --;
    }

    /**
     * 判断技能是否准本完成
     *
     * @param index 技能序号
     * @return 技力恢复是否完成
     */
    public boolean skillReady(int index) {
        if (index >= this.skills.size()) return false;
        return this.skills.get(index).isReady() && !this.skillOccupied();
    }

    public boolean skillOccupied(){
        return this.durationTime > 0;
    }

    public void setDuration(int index){
        if (index >= this.skills.size()) return;
        this.durationTime = this.skills.get(index).duration;
    }

    public void resetSkill(int index) {
        if (index >= this.skills.size()) return;
        this.skills.get(index).reset();
    }

    public void resetSkill(int index, int maxPoint) {
        if (index >= this.skills.size()) return;
        this.skills.get(index).resetAndSetMaxPoint(maxPoint);
    }

    public void regenerateSkill(){
        for(var skill:this.skills){
            if(skill.canRegenerate())skill.regenerate();
        }
    }
}
