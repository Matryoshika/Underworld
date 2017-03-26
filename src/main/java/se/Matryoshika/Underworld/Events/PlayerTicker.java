package se.Matryoshika.Underworld.Events;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class PlayerTicker {
	
	@SubscribeEvent
	public void LanternLogic(TickEvent.PlayerTickEvent event){
		if(event.side == Side.CLIENT){
			spawnFireflies(event.player);
			return;
		}
		
		EntityPlayer player = event.player;
		if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == ContentRegistry.Lantern)
			doLanternLogic(player);
		
		if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ContentRegistry.Lantern)
			doLanternLogic(player);
	}
	
	public void spawnFireflies(EntityPlayer player){
		Random rand = new Random();
		if(rand.nextInt(10) == 0)
			if((player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ContentRegistry.Lantern) || (player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == ContentRegistry.Lantern))
				Underworld.proxy.spawnCustomParticle("firefly", player.worldObj, player.posX, player.posY, player.posZ, 0, 0, 0, 1, 1, 1, 1, true);
		
	}
	
	public void doLanternLogic(EntityPlayer player){
		BlockPos pos = new BlockPos(player.posX, player.posY+1, player.posZ);
		
		for(int x = -2; x < 3; x++){
			for(int z = -2; z < 3; z++){
				for(int y = -2; y < 3; y++){
					BlockPos scannedBlock = new BlockPos(pos.getX()+x, pos.getY()+y, pos.getZ()+z); 
					
					//If the scan finds a single Light-block, stop this update.
					if(player.worldObj.getBlockState(scannedBlock) == ContentRegistry.Light.getDefaultState()){
						return;
					}
					//Else place a new light.
					else{
						if(player.worldObj.isAirBlock(pos))
							player.worldObj.setBlockState(pos, ContentRegistry.Light.getDefaultState());
					}
				}
			}
		}
	}
}
