package se.Matryoshika.Underworld.Events;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.Matryoshika.Underworld.Utils.SugarPileList;

public class UnderworldEventHandler {

	@SubscribeEvent
	public void onLivingTick(LivingUpdateEvent event) {
		if (event.getEntityLiving() instanceof EntityMob) {

			EntityMob mob = (EntityMob) event.getEntityLiving();
			if(mob.world.isRemote || !mob.isNonBoss())
				return;
			
			for (BlockPos pos : SugarPileList.getSugarPiles(mob.world)) {

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
	
	
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event){
		event.getWorld().addEventListener(new UWEventListener(event.getWorld()));
	}
}
