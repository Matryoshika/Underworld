package se.Matryoshika.Underworld.Content;

import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.Blocks.*;
import se.Matryoshika.Underworld.Content.Items.*;
import se.Matryoshika.Underworld.Content.Rendering.BlockRenderRegister;
import se.Matryoshika.Underworld.Utils.ConfigHandler;

import java.io.File;
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
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
	public static Block BlockMossStone;
	public static Block BlockGlowMossStone;
	public static Block BlockSugarPile;
	
	
	public static List<Block>BlockList=new ArrayList<Block>();
	
	public static void prepareBlocks(){
		BlockList.add(BlockHangVine = new BlockHangVine());
		BlockList.add(BlockDirt = new BlockUnderworldDirt());
		BlockList.add(BlockBrazierOff = new BlockBrazierOff());
		BlockList.add(BlockBrazierOn = new BlockBrazierOn().setLightLevel(15F));
		BlockList.add(Light = new BlockCustomLight());
		BlockList.add(Spawner = new BlockInvisMobSpawner());
		BlockList.add(BlockSugarBeets = new BlockSugarbeet().setRegistryName("blocksugarbeet").setUnlocalizedName("underworld:blocksugarbeet"));
		BlockList.add(BlockMetamorphicTable = new BlockMetamorphicTable());
		BlockList.add(BlockCustomEndPortal = new BlockCustomEndPortal());
		BlockList.add(BlockMossStone = new BlockMossStone());
		BlockList.add(BlockGlowMossStone = new BlockGlowingMossStone().setLightLevel(5F));
		BlockList.add(BlockSugarPile = new BlockSugarPile().setLightLevel(5F));

	}
	
	
	
	@Mod.EventBusSubscriber
    public static class register{
        @SubscribeEvent
        public static void addBlocks(RegistryEvent.Register<Block> evt){
        	Underworld.mainConfig = new Configuration(new File("config/"+ Underworld.MODID+"/main.cfg"));
        	Underworld.itemConfig = new Configuration(new File("config/"+ Underworld.MODID+"/items.cfg"));
        	Underworld.blockConfig = new Configuration(new File("config/"+ Underworld.MODID+"/blocks.cfg"));
        	Underworld.genConfig = new Configuration(new File("config/"+ Underworld.MODID+"/worldGen.cfg"));
        	ConfigHandler.readMain();
        	prepareBlocks();
        	prepareItems();
        	ConfigHandler.setItemAndBlockConfigs();
        	for(Block block : BlockList){
        		if(!((Boolean) ConfigHandler.isBlockEnabledMap.get(block.getRegistryName().toString())))
    				continue;
    			
        		evt.getRegistry().register(
            		block
            	);
        	}
        }

        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> evt){
        	for(Item item : ItemList){
        		if(!((Boolean) ConfigHandler.isItemEnabledMap.get(item.getRegistryName().toString())))
        			continue;
        	
	            evt.getRegistry().registerAll(
	            	item
	            );
        	}
        	
        	for(Block block : BlockList){
        		if(!((Boolean) ConfigHandler.isBlockEnabledMap.get(block.getRegistryName().toString())))
        			continue;
        		ItemBlock iblock;
    			if(block == ContentRegistry.BlockCustomEndPortal)
    				iblock = new ItemBlockShiny(block);
    			else
    				iblock= new ItemBlock(block);
    			
	            evt.getRegistry().registerAll(
	            	iblock.setRegistryName(block.getRegistryName())
	            );
        	}
        }
	}
	
	//Items--------------------------------------------------------------------------------------------
	
	public static Item Debugger;
	public static Item Lantern;
	public static Item Sugarbeets;
	public static Item FireflyShield;
	
	
	public static List<Item>ItemList=new ArrayList<Item>();
	
	public static void prepareItems() {
		
		ItemList.add(Debugger = new ItemDebugger());
		ItemList.add(Lantern = new ItemLantern());
		ItemList.add(Sugarbeets = new ItemSugarbeet());
		//ItemList.add(FireflyShield = new ItemFireflyShield());
		
	}
}
