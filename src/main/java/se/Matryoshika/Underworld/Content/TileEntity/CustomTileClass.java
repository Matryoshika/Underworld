package se.Matryoshika.Underworld.Content.TileEntity;

import net.minecraft.tileentity.TileEntity;
import se.Matryoshika.Underworld.Underworld;

public class CustomTileClass extends TileEntity{
	
	public static String name;
	
	public static String getName(){
		return Underworld.MODID+":"+name;
	}
	
	public String setName(String name){
		this.name = name;
		return name;
	}
	

}
