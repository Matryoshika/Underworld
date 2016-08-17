package se.Matryoshika.Underworld;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Content.RecipeManager;
import se.Matryoshika.Underworld.Content.TileRegistry;
import se.Matryoshika.Underworld.Events.UnderworldMapEventHandler;
import se.Matryoshika.Underworld.Utils.BiomeType;
import se.Matryoshika.Underworld.Utils.CreativeTabUnderworld;
import se.Matryoshika.Underworld.WorldGen.WorldProviderCaves;
import se.Matryoshika.Underworld.WorldGen.Dirty.CustomWorldGenerators;
import se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument.UnderworldStructureOceanMonumentPieces;

@Mod(modid=Underworld.MODID, version=Underworld.VERSION, name="Underworld")
public class Underworld {
	
	public static final String MODID = "underworld";
	public static final String LOCALIZING = "UW";
	public static final String VERSION = "0.0.10";
	
	private final UnderworldMapEventHandler INIT_MAP_GEN_EVENT_HANDLER = new UnderworldMapEventHandler();
	
	public static final CreativeTabUnderworld UnderworldTab = new CreativeTabUnderworld("Underworld"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return new ItemStack(ContentRegistry.Lantern).getItem();
		}
	};
	
	@Instance("Underworld")
	public static Underworld instance;
	
	@SidedProxy(clientSide = "se.Matryoshika.Underworld.ClientProxy", serverSide = "se.Matryoshika.Underworld.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ContentRegistry.registerBlocks();
		ContentRegistry.registerItems();
		TileRegistry.registerTiles();
		BiomeType.init();
		proxy.preInit(event);
		
		RecipeManager.registerRecipes();
		
		UnderworldStructureOceanMonumentPieces.registerOceanMonumentPieces();
		MinecraftForge.TERRAIN_GEN_BUS.register(INIT_MAP_GEN_EVENT_HANDLER);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		proxy.init(event);
		
		//See WorldProviderCaves on how this doesn't mess up vanilla WorldTypes
		DimensionManager.unregisterDimension(0);
		DimensionManager.registerDimension(0, DimensionType.register("CAVES", "WhatIsThis", 0, WorldProviderCaves.class, true));
		
		CustomWorldGenerators.register();
		
		OreDictionary.registerOre("string", ContentRegistry.BlockHangVine);
		OreDictionary.registerOre("sugarcane", ContentRegistry.Sugarbeets);
		MinecraftForge.addGrassSeed(new ItemStack(ContentRegistry.Sugarbeets), 3);
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);

	}

}
