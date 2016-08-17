package se.Matryoshika.Underworld.Content;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeManager {
	
	public static void registerRecipes(){
		
		//Items----------------------------------------------
		ItemStack Lantern = new ItemStack(ContentRegistry.Lantern);
		GameRegistry.addRecipe(Lantern, new Object[]{
				"SSS",
				" D ",
				"SSS",
				'S', Blocks.STONE_SLAB,
				'D', Items.GLOWSTONE_DUST
		});
		
		
		//Blocks---------------------------------------------
		ItemStack Brazier = new ItemStack(ContentRegistry.BlockBrazierOff, 4);
		GameRegistry.addRecipe(Brazier, new Object[]{
				" S ",
				"SCS",
				"SSS",
				'S', Blocks.STONE_SLAB,
				'C', new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE)
				
		});
		
	}

}
