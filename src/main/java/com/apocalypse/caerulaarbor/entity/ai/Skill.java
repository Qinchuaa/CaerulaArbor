package com.apocalypse.caerulaarbor.entity.ai;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class Skill {

    public int initialPoint;
    public int skillPoint;
    public int maxPoint;
    public int regenerateSpeed;
    public boolean canRegenerate;
    public String name;
    public int duration;

    public Skill(Builder builder) {
        this.initialPoint = builder.initialPoint;
        this.skillPoint = builder.skillPoint;
        this.maxPoint = builder.maxPoint;
        this.regenerateSpeed = builder.regenerateSpeed;
        this.canRegenerate = builder.canRegenerate;
        this.name = builder.name;
        this.duration = builder.duration;
    }

    public Skill(int initialPoint, int skillPoint, int maxPoint, int regenerateSpeed, boolean canRegenerate, String name, int duration) {
        this.initialPoint = initialPoint;
        this.skillPoint = skillPoint;
        this.maxPoint = maxPoint;
        this.regenerateSpeed = regenerateSpeed;
        this.canRegenerate = canRegenerate;
        this.name = name;
        this.duration = duration;
    }

    public void init() {
        this.skillPoint = this.initialPoint;
    }

    public void reset() {
        this.skillPoint = 0;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public void resetAndSetMaxPoint(int maxPoint) {
        this.reset();
        this.setMaxPoint(maxPoint);
    }

    public void regenerate() {
        this.skillPoint = Mth.clamp(this.skillPoint + this.regenerateSpeed, 0, this.maxPoint);
    }

    public boolean canRegenerate() {
        return this.canRegenerate && this.skillPoint < this.maxPoint;
    }

    public boolean isUsing() {
        return this.duration > 0;
    }

    public boolean isReady() {
        return this.skillPoint >= this.maxPoint;
    }

    public void serialize(CompoundTag tag) {
        tag.putInt("InitialPoint", this.initialPoint);
        tag.putInt("SkillPoint", this.skillPoint);
        tag.putInt("MaxPoint", this.maxPoint);
        tag.putInt("RegenerateSpeed", this.regenerateSpeed);
        tag.putBoolean("CanRegenerate", this.canRegenerate);
        tag.putString("SkillName", this.name);
        tag.putInt("SkillDuration", this.duration);
    }

    public static Skill deserialize(CompoundTag tag) {
        return new Skill(
                tag.getInt("InitialPoint"),
                tag.getInt("SkillPoint"),
                tag.getInt("MaxPoint"),
                tag.getInt("RegenerateSpeed"),
                tag.getBoolean("CanRegenerate"),
                tag.getString("SkillName"),
                tag.getInt("SkillDuration"));
    }

    public Component getDescriptionName(String name) {
        return Component.translatable("skill.caerula_arbor." + name);
    }

    public static class Builder {

        private int initialPoint = 0;
        private int skillPoint = 0;
        private int maxPoint;
        private int regenerateSpeed = 1;
        private boolean canRegenerate = true;
        private String name = "empty";
        private int duration = 0;

        public static Builder of() {
            return new Builder();
        }

        public Skill build() {
            return new Skill(this);
        }

        /**
         * 设置技能的初始技力
         *
         * @param initialPoint 初始技力
         * @return 技能构造器
         */
        public Builder init(int initialPoint) {
            this.initialPoint = initialPoint;
            this.skillPoint = initialPoint;
            return this;
        }

        /**
         * 设置技能的初始冷却，以转化冷却制和技力制的技能
         * 需要在设置好最大技力后使用
         * @param initialCooldown 初始冷却
         * @return 技能构造器
         */
        public Builder initCooldown(int initialCooldown){
            return init(maxPoint - initialCooldown);
        }

        /**
         * 设置技能的持续时间（刻）
         *
         * @param duration 持续时间
         * @return 技能构造器
         */
        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        /**
         * 设置技能的最大技力
         *
         * @param maxPoint 最大技力
         * @return 技能构造器
         */
        public Builder max(int maxPoint) {
            this.maxPoint = maxPoint;
            return this;
        }

        /**
         * 设置技能的恢复速度
         *
         * @param regenerateSpeed 恢复速度
         * @return 技能构造器
         */
        public Builder speed(int regenerateSpeed) {
            this.regenerateSpeed = regenerateSpeed;
            return this;
        }

        /**
         * 禁止技能自动恢复技力
         *
         * @return 技能构造器
         */
        public Builder noRegenerate() {
            this.canRegenerate = false;
            return this;
        }

        /**
         * 发挥中二病的时候到了
         *
         * @param name 技能在lang里的条目，不填的话默认值为爆裂黎明（什
         * @return 技能构造器
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }
    }
}
