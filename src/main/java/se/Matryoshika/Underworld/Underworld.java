package se.Matryoshika.Underworld;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import se.Matryoshika.Underworld.Content.BlockRegistry;
import se.Matryoshika.Underworld.Content.ItemRegistry;
import se.Matryoshika.Underworld.Content.RecipeManager;
import se.Matryoshika.Underworld.Content.TileRegistry;
import se.Matryoshika.Underworld.Events.UnderworldMapEventHandler;
import se.Matryoshika.Underworld.Utils.BiomeType;
import se.Matryoshika.Underworld.Utils.CreativeTabUnderworld;
import se.Matryoshika.Underworld.WorldGen.WorldProviderCaves;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyClayGen;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyDungeonGen;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyHutGen;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyOceanMonument;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtySpawnPointGen;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtySpawnerGen;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyTreeGen;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyVineGen;
import se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument.UnderworldStructureOceanMonumentPieces;

@Mod(modid=Underworld.MODID, version=Underworld.VERSION, name="Underworld")
public class Underworld {
	
	public static final String MODID = "underworld";
	public static final String LOCALIZING = "UW";
	public static final String VERSION = "0.0.7";
	
	private final UnderworldMapEventHandler INIT_MAP_GEN_EVENT_HANDLER = new UnderworldMapEventHandler();
	
	public static final CreativeTabUnderworld UnderworldTab = new CreativeTabUnderworld("Underworld"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return new ItemStack(ItemRegistry.Lantern).getItem();
		}
	};
	
	@Instance("Underworld")
	public static Underworld instance;
	
	@SidedProxy(clientSide = "se.Matryoshika.Underworld.ClientProxy", serverSide = "se.Matryoshika.Underworld.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		BlockRegistry.registerBlocks();
		ItemRegistry.registerItems();
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
		
		GameRegistry.registerWorldGenerator(new DirtyClayGen(), 49);
		GameRegistry.registerWorldGenerator(new DirtyTreeGen(), 50);
		GameRegistry.registerWorldGenerator(new DirtyVineGen(), 51);
		GameRegistry.registerWorldGenerator(new DirtyHutGen(), 52);
		GameRegistry.registerWorldGenerator(new DirtyDungeonGen(), 53);
		GameRegistry.registerWorldGenerator(new DirtySpawnPointGen(), Integer.MAX_VALUE-1);
		GameRegistry.registerWorldGenerator(new DirtySpawnerGen(), Integer.MAX_VALUE);
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);

	}

}
