package se.Matryoshika.Underworld;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Content.BlockRegistry;
import se.Matryoshika.Underworld.Content.Rendering.BlockRenderRegister;
import se.Matryoshika.Underworld.Content.Rendering.ItemRenderRegister;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent event){
		BlockRenderRegister.registerBlockRenderer();
		ItemRenderRegister.registerItemRenderer();
		
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		
    }
	
	@Override
    public void postInit(FMLPostInitializationEvent event){

    }

}
