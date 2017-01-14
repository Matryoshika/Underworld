package se.Matryoshika.Underworld.Content.TileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileUnderworldEnderPortal extends TileEntity implements ITickable{

	public boolean goUp = false;
	public float timer = 0;
	
	@Override
	public void update() {
		if(goUp)
    		timer++;
    	else
    		timer--;
    	if(timer >= 66)
    		goUp = false;
    	if(timer <= -66)
    		goUp = true;
		
	}

	//Dummy to get TESR
}
