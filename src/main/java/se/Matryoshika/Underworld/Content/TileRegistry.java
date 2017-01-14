package se.Matryoshika.Underworld.Content;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Underworld.Content.TileEntity.CustomTileClass;
import se.Matryoshika.Underworld.Content.TileEntity.TileCustomLight;
import se.Matryoshika.Underworld.Content.TileEntity.TileInvisMobSpawner;
import se.Matryoshika.Underworld.Content.TileEntity.TileMetamorphicTable;
import se.Matryoshika.Underworld.Content.TileEntity.TileSugarPile;
import se.Matryoshika.Underworld.Content.TileEntity.TileUnderworldEnderPortal;

public class TileRegistry {
	
	public static CustomTileClass Light;
	public static CustomTileClass Spawner;
	
	public static List<CustomTileClass>TileList=new ArrayList<CustomTileClass>();
	
	
	public static void registerTiles(){
		GameRegistry.registerTileEntity(TileCustomLight.class, "underworld:TileCustomLight");
		GameRegistry.registerTileEntity(TileInvisMobSpawner.class, "underworld:InvisMobSpawner");
		GameRegistry.registerTileEntity(TileMetamorphicTable.class, "underworld:Metamorphic_Table");
		GameRegistry.registerTileEntity(TileUnderworldEnderPortal.class, "underworld:TileUnderworldEnderPortal");
		GameRegistry.registerTileEntity(TileSugarPile.class, "underworld:TileSugarPile");
	}

}
