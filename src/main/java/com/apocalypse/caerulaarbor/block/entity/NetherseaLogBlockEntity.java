package com.apocalypse.caerulaarbor.block.entity;

import com.apocalypse.caerulaarbor.block.NetherseaLogBlock;
import com.apocalypse.caerulaarbor.init.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.ParametersAreNonnullByDefault;

public class NetherseaLogBlockEntity extends BlockEntity {

    private int age;
    private int nutrition;

    public NetherseaLogBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.NETHERSEA_LOG.get(), pPos, pBlockState);
        this.nutrition = 16;
        this.age = 0;
    }

    private int randomDelay(){
        int base = 30 * 20;
        int addition = 5 * 20;
        return (int) (base + addition * Math.random());
    }

    private boolean canSpread(){
        return this.nutrition > 0 && this.age > 30 && this.age < 64;
    }

    private void nutritionShrink(){
        if(Math.random() < 0.5) this.nutrition --;
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, NetherseaLogBlockEntity pBlockEntity){
        if(pBlockEntity.age >= 64) return;
        pBlockEntity.age ++;
        if(pBlockEntity.canSpread()){

        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("age", this.age);
        pTag.putInt("nutr", this.nutrition);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.age = pTag.getInt("age");
        this.nutrition = pTag.getInt("nutr");
    }
}
