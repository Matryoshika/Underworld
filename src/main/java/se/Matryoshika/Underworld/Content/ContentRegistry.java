package se.Matryoshika.Underworld.Content;

import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.Blocks.*;
import se.Matryoshika.Underworld.Content.Items.*;
import se.Matryoshika.Underworld.Content.Rendering.BlockRenderRegister;
import se.Matryoshika.Underworld.Utils.ConfigHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ContentRegistry {
	
	//Blocks-------------------------------------------------------------------------------------------

	public static Block BlockHangVine;
	public static Block BlockDirt;
	public static Block BlockBrazierOff;
	public static Block BlockBrazierOn;
	public static Block Light;
	public static Block Spawner;
	public static Block BlockSugarBeets;
	public static Block BlockMetamorphicTable;
	public static Block BlockCustomEndPortal;
	
	
	public static List<Block>BlockList=new ArrayList<Block>();
	
	public static void prepareBlocks(){
		BlockList.add(BlockHangVine = new BlockHangVine());
		BlockList.add(BlockDirt = new BlockUnderworldDirt());
		BlockList.add(BlockBrazierOff = new BlockBrazierOff());
		BlockList.add(BlockBrazierOn = new BlockBrazierOn().setLightLevel(1.0F));
		BlockList.add(Light = new BlockCustomLight());
		BlockList.add(Spawner = new BlockInvisMobSpawner());
		BlockList.add(BlockSugarBeets = new BlockSugarbeet().setRegistryName("blocksugarbeet").setUnlocalizedName("underworld:blocksugarbeet"));
		BlockList.add(BlockMetamorphicTable = new BlockMetamorphicTable());
		BlockList.add(BlockCustomEndPortal = new BlockCustomEndPortal());

	}
	
	public static void registerBlocks(){
		for(Block block : BlockList){
			if(!((Boolean) ConfigHandler.isBlockEnabledMap.get(block.getRegistryName().toString()))){
				continue;
			}
			GameRegistry.register(block);
			ItemBlock iblock;
			if(block == ContentRegistry.BlockCustomEndPortal)
				iblock = new ItemBlockShiny(block);
			else
				iblock= new ItemBlock(block);
			iblock.setRegistryName(block.getRegistryName());
			GameRegistry.register(iblock);
		}
	}
	
	//Items--------------------------------------------------------------------------------------------
	
	public static Item Debugger;
	public static Item Lantern;
	public static Item Sugarbeets;
	
	
	public static List<Item>ItemList=new ArrayList<Item>();
	
	public static void prepareItems() {
		
		ItemList.add(Debugger = new ItemDebugger());
		ItemList.add(Lantern = new ItemLantern());
		ItemList.add(Sugarbeets = new ItemSugarbeet());
		
	}
	
	public static void registerItems(){
		for(Item item:ItemList){
			if(!((Boolean) ConfigHandler.isItemEnabledMap.get(item.getRegistryName().toString()))){
				continue;
			}
			GameRegistry.register(item);
		}
	}
}
