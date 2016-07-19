package se.Matryoshika.Underworld;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Content.Rendering.BlockRenderRegister;

public class ClientProxy implements IProxy{
	
	@Override
	public void preInit(){
		BlockRenderRegister.registerBlockRenderer();
	}
	
	@Override
	public void init(){

    }
	
	@Override
    public void postInit(){

    }

}
