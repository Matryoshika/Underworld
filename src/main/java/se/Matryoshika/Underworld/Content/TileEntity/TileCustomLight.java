package se.Matryoshika.Underworld.Content.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class TileCustomLight extends CustomTileClass implements ITickable{
	
	public TileCustomLight(){
		this.setName("TileCustomLight");
	}

	@Override
	public void update() {
		World world = this.worldObj;
		EntityPlayer player = world.getClosestPlayer(this.pos.getX(), this.pos.getY(), this.pos.getZ(), 3D, false);
		if(player == null){
			world.setBlockToAir(pos);
		}
		else{
			if(player.getHeldItemMainhand() != null)
				if(player.getHeldItemMainhand().getItem() == ContentRegistry.Lantern){
					return;
				}
					
			
			if(player.getHeldItemOffhand() != null)
				if(player.getHeldItemOffhand().getItem() == ContentRegistry.Lantern){
					return;
				}
					
			
			world.setBlockToAir(pos);
			
		}
	}
}
