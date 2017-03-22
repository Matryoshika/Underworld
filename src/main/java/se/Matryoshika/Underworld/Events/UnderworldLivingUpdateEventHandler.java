package se.Matryoshika.Underworld.Events;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.Matryoshika.Underworld.Utils.SugarPileList;

public class UnderworldLivingUpdateEventHandler {

	@SubscribeEvent
	public void onLivingTick(LivingUpdateEvent event) {
		if (event.getEntityLiving() instanceof EntityMob) {

			EntityMob mob = (EntityMob) event.getEntityLiving();
			if(mob.worldObj.isRemote)
				return;
			
			for (BlockPos pos : SugarPileList.getSugarPiles(mob.worldObj)) {

				if (mob.getDistance(pos.getX(), pos.getY(), pos.getZ()) <= 8) {
					if(mob.hurtResistantTime > 0)
						mob.hurtResistantTime--;
					((EntityMob) event.getEntityLiving()).setAttackTarget(null);
					if (((EntityMob) event.getEntityLiving()).getHealth() > 0 )
						event.setCanceled(true);
					
				}
			}
		}
	}
}
