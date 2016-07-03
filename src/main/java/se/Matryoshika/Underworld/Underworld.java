package se.Matryoshika.Underworld;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Utils.CreativeTabUnderworld;

@Mod(modid=Underworld.MODID, version=Underworld.VERSION, name="Underworld")
public class Underworld {
	
	public static final String MODID = "underworld";
	public static final String LOCALIZING = "UW";
	public static final String VERSION = "0.01";
	
	public static final CreativeTabUnderworld UnderworldTab = new CreativeTabUnderworld("Underworld"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return new ItemStack(Blocks.MOSSY_COBBLESTONE).getItem();
		}
	};
	
	@Instance("Underworld")
	public static Underworld instance;
	
	@SidedProxy(clientSide="se.Matryoshika.Underworld.ClientProxy",serverSide="se.Matryoshika.Underworld.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){}

}
