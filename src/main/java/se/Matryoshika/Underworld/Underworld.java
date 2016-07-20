package se.Matryoshika.Underworld;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.DimensionManager;
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
import se.Matryoshika.Underworld.Content.BlockRegistry;
import se.Matryoshika.Underworld.Utils.CreativeTabUnderworld;
import se.Matryoshika.Underworld.WorldGen.WorldProviderCaves;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyTreeGen;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyVineGen;

@Mod(modid=Underworld.MODID, version=Underworld.VERSION, name="Underworld")
public class Underworld {
	
	public static final String MODID = "underworld";
	public static final String LOCALIZING = "UW";
	public static final String VERSION = "0.01";
	
	
	public static WorldTypeCaves worldTypeCaves;
	
	
	public static final CreativeTabUnderworld UnderworldTab = new CreativeTabUnderworld("Underworld"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return new ItemStack(Blocks.MOSSY_COBBLESTONE).getItem();
		}
	};
	
	@Instance("Underworld")
	public static Underworld instance;
	
	@SidedProxy(clientSide = "se.Matryoshika.Underworld.ClientProxy", serverSide = "se.Matryoshika.Underworld.ServerProxy")
	public static IProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		proxy.preInit();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		proxy.init();
		
		DimensionManager.unregisterDimension(0);
		DimensionManager.registerDimension(0, DimensionType.register("CAVES", "WhatIsTHis", 0, WorldProviderCaves.class, true));
		GameRegistry.registerWorldGenerator(new DirtyTreeGen(), 50);
		GameRegistry.registerWorldGenerator(new DirtyVineGen(), 51);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit();
		
		worldTypeCaves = new WorldTypeCaves();
		
	}

}