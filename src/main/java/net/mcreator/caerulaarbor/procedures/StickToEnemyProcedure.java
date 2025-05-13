package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.caerulaarbor.entity.FakeOffspringEntity;

public class StickToEnemyProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		Entity obj = null;
		if (entity instanceof Mob _mobEnt0 && _mobEnt0.isAggressive()) {
			obj = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
			if (!(obj == null)) {
				if ((obj != null ? entity.distanceTo(obj) : -1) <= 4) {
					{
						Entity _ent = entity;
						_ent.teleportTo((obj.getX() + obj.getBbWidth() * (entity instanceof FakeOffspringEntity _datEntI ? _datEntI.getEntityData().get(FakeOffspringEntity.DATA_dx) : 0) * 0.01), (obj.getY()),
								(obj.getZ() + obj.getBbWidth() * (entity instanceof FakeOffspringEntity _datEntI ? _datEntI.getEntityData().get(FakeOffspringEntity.DATA_dz) : 0) * 0.01));
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport((obj.getX() + obj.getBbWidth() * (entity instanceof FakeOffspringEntity _datEntI ? _datEntI.getEntityData().get(FakeOffspringEntity.DATA_dx) : 0) * 0.01), (obj.getY()),
									(obj.getZ() + obj.getBbWidth() * (entity instanceof FakeOffspringEntity _datEntI ? _datEntI.getEntityData().get(FakeOffspringEntity.DATA_dz) : 0) * 0.01), _ent.getYRot(), _ent.getXRot());
					}
				}
			}
		}
	}
}
