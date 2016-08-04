package se.Matryoshika.Underworld;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import se.Matryoshika.Underworld.Content.BlockRegistry;
import se.Matryoshika.Underworld.Content.ItemRegistry;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class CommonProxy {
	
	public static WorldTypeCaves worldTypeCaves;
	
	public void preInit(FMLPreInitializationEvent event){
		
    }

    public void init(FMLInitializationEvent event) {

    }
	

    public void postInit(FMLPostInitializationEvent event) {
    	worldTypeCaves = new WorldTypeCaves();
    }

}
