package com.apocalypse.caerulaarbor.menu;

import com.apocalypse.caerulaarbor.init.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CaerulaRecorderMenu extends AbstractContainerMenu {

    public final Level level;
    public final Player player;

    public CaerulaRecorderMenu(int id, Inventory inv) {
        super(ModMenus.CAERULA_RECORDER.get(), id);
        this.player = inv.player;
        this.level = inv.player.level();
    }

    @Override
    public boolean stillValid(Player player) {
        return player.isAlive();
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player entity = event.player;
        if (event.phase == TickEvent.Phase.END && entity.containerMenu instanceof CaerulaRecorderMenu) {
//            {
//                boolean _setval = guistate.containsKey("checkbox:show_hud") && ((Checkbox) guistate.get("checkbox:show_hud")).selected();
//                entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
//                    capability.show_stats = _setval;
//                    capability.syncPlayerVariables(entity);
//                });
//            }
//            {
//                boolean _setval = guistate.containsKey("checkbox:show_ptc") && ((Checkbox) guistate.get("checkbox:show_ptc")).selected();
//                entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
//                    capability.kingShowPtc = _setval;
//                    capability.syncPlayerVariables(entity);
//                });
//            }
        }
    }
}
