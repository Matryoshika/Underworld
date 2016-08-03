package se.Matryoshika.Underworld.Content;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Underworld.Content.TileEntity.CustomTileClass;
import se.Matryoshika.Underworld.Content.TileEntity.TileCustomLight;

public class TileRegistry {
	
	public static CustomTileClass Light;
	
	public static List<CustomTileClass>TileList=new ArrayList<CustomTileClass>();
	
	
	public static void registerTiles(){
		TileList.add(Light = new TileCustomLight());
		
		for(CustomTileClass tile:TileList){
			GameRegistry.registerTileEntity(tile.getClass(), tile.getName());
		}
	}

}
