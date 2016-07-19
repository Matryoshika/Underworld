package se.Matryoshika.Underworld;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import se.Matryoshika.Underworld.Content.BlockRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
		BlockRegistry.createBlocks();
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
