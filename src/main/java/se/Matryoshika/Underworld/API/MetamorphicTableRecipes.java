package se.Matryoshika.Underworld.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetamorphicTableRecipes {
	
	public static List<TableRecipes> metamorphicTableRecipes = new ArrayList<TableRecipes>();
	public static HashMap<String, ItemStack> registry = new HashMap<String, ItemStack>();
	
	
	public static TableRecipes registerTableRecipes(ItemStack input, ItemStack output, int outputMeta){
		registry.put(String.valueOf(input.getItem())+String.valueOf(input.getMetadata()), output);
		TableRecipes recipe = new TableRecipes(input, output, outputMeta);
		metamorphicTableRecipes.add(recipe);
		return recipe;
	}

}
