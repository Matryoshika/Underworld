package se.Matryoshika.Underworld.Utils;

import java.io.File;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class ConfigHandler {
	
	public static File configDir;
    public static Configuration configMain;
    public static Configuration itemConf;
    public static Configuration blockConf;
    public static Configuration configGen;
    
    public static HashMap isBlockEnabledMap = new HashMap();
    public static HashMap isItemEnabledMap = new HashMap();
    public static HashMap isGenEnabledMap = new HashMap();
    public static String metamorphicTableActivationBlocks;
    public static int metamorphicTableActivationMeta;
    public static boolean clayGen;
    public static boolean treeGen;
    public static boolean vineGen;
    public static boolean hutGen;
    public static boolean dungeonGen;
    public static boolean spawnPointGen;
    public static boolean spawnerGen;
    public static boolean lilyPadGen;
	
	public static void readMain(){
		
		configMain = Underworld.mainConfig;
		configMain.load();
		
		configGen = Underworld.genConfig;
		configGen.load();
		
		configMain.addCustomCategoryComment("Balance Configs", "Configs to make things easier or harder");
		metamorphicTableActivationBlocks = configMain.getString("MetamorphicTableActivationBlocks","Balance Configs", "minecraft:enchanting_table", "Defines what block is required to activate the Metamorphic table. ModID : BlockName");
		metamorphicTableActivationMeta = configMain.getInt("MetamorphicTableActivationMeta","Balance Configs", 0, 0, 15, "The meta for the activation blocks");
		
		configGen.addCustomCategoryComment("WorldGen configs", "Configs to enable or disable Underworld's WorldGen. Will not undo already generated terrain, just hinder more from generating.");
		clayGen = configGen.getBoolean("ClayGen", "WorldGen configs", true, "Wether or not Underworld should spawn clay");
		treeGen = configGen.getBoolean("TreeGen", "WorldGen configs", true, "Wether or not Underworld should spawn trees");
		vineGen = configGen.getBoolean("VineGen", "WorldGen configs", true, "Wether or not Underworld should spawn vines");
		hutGen = configGen.getBoolean("HutGen", "WorldGen configs", true, "Wether or not Underworld should spawn huts");
		dungeonGen = configGen.getBoolean("DungeonGen", "WorldGen configs", true, "Wether or not Underworld should spawn dungeons");
		spawnPointGen = configGen.getBoolean("SpawnPointGen", "WorldGen configs", true, "Wether or not Underworld will create a spawn-area. WARNING: May spawn in stone or above bedrock if disabled");
		spawnerGen = configGen.getBoolean("SpawnerGen", "WorldGen configs", true, "Wether or not Underworld will stand for mob-spawns. WARNING may get quite empty on passive mobs if disabled");
		lilyPadGen = configGen.getBoolean("LilypadGen", "WorldGen configs", true, "Wether or not Underworld will spawn Lilypads on water.");
		
		
		if(configMain.hasChanged())
			configMain.save();
		
		if(configGen.hasChanged())
			configGen.save();
        
	}
	
	public static void setItemAndBlockConfigs(){
		itemConf = Underworld.itemConfig;
		itemConf.load();
		blockConf = Underworld.blockConfig;
		blockConf.load();
		
		blockConf.addCustomCategoryComment("Enabled Blocks", "Config to fully remove blocks from the game. Already existing ones will be destroyed.");
		for(Block block : ContentRegistry.BlockList){
			boolean isBlockEnabled = blockConf.getBoolean(block.getRegistryName().toString(), "Enabled Blocks", true, "Wether or not this block is enabled");
			isBlockEnabledMap.put(block.getRegistryName().toString(), isBlockEnabled);
		}
		
		itemConf.addCustomCategoryComment("Enabled Items", "All enabled items");
		for(Item item : ContentRegistry.ItemList){
			boolean isItemEnabled = itemConf.getBoolean(item.getRegistryName().toString(), "Enabled Items", true, "Wether or not this item is enabled");
			isItemEnabledMap.put(item.getRegistryName().toString(), isItemEnabled);
		}
		
		if(itemConf.hasChanged())
        	itemConf.save();
        
        if(blockConf.hasChanged())
        	blockConf.save();
	}

}
