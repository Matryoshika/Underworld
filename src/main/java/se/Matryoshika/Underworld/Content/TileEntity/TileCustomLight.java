package se.Matryoshika.Underworld.Content.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
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
		EntityPlayer player = world.getClosestPlayer(this.pos.getX(), this.pos.getY(), this.pos.getZ(), 2D, false);
		if(player == null){
			world.setBlockToAir(pos);
		}
		else{
			if(player.inventory.getCurrentItem() != null){
				if(player.inventory.getCurrentItem().getItem() != ContentRegistry.Lantern){
					world.setBlockToAir(pos);
				}
			}
			else{
				world.setBlockToAir(pos);
			}
		}
	}
}
