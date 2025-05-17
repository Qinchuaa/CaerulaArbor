package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;

public class SwitchStatsShowProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		{
			boolean _setval = guistate.containsKey("checkbox:show_hud") && ((Checkbox) guistate.get("checkbox:show_hud")).selected();
			entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
				capability.show_stats = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			boolean _setval = guistate.containsKey("checkbox:show_ptc") && ((Checkbox) guistate.get("checkbox:show_ptc")).selected();
			entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
				capability.kingShowPtc = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
