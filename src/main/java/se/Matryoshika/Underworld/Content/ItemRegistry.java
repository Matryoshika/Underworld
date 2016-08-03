package se.Matryoshika.Underworld.Content;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Underworld.Content.Items.ItemDebugger;
import se.Matryoshika.Underworld.Content.Items.ItemLantern;

public class ItemRegistry {
	
	public static final Set<Item> ITEMS = new HashSet<>();
	
	public static Item Debugger;
	public static Item Lantern;
	
	
	public static List<Item>ItemList=new ArrayList<Item>();
	
	public static void registerItems() {
		ItemList.add(Debugger = new ItemDebugger());
		ItemList.add(Lantern = new ItemLantern());
		
		
		for(Item Item:ItemList){
			GameRegistry.register(Item);
		}
	}
}
