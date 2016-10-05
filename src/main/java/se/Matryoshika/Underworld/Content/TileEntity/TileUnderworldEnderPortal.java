package se.Matryoshika.Underworld.Content.TileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileUnderworldEnderPortal extends TileEntity implements ITickable{

	public boolean goUp = false;
	public float timer = 0;
	
	@Override
	public void update() {
		if(goUp)
    		timer+=2;
    	else
    		timer-=2;
    	if(timer >= 100)
    		goUp = false;
    	if(timer <= -100)
    		goUp = true;
		
	}

	//Dummy to get TESR
}
