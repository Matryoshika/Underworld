package se.Matryoshika.Underworld.Content;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import se.Matryoshika.Underworld.Utils.ConfigHandler;

public class RecipeManager {
	
	static ItemStack STONE_SLAB = new ItemStack(Blocks.STONE_SLAB, 1, 0);
	
	public static void registerRecipes(){
		
		//Items----------------------------------------------
		if(!((Boolean) ConfigHandler.isItemEnabledMap.get(ContentRegistry.Lantern.getRegistryName().toString()))){
			ItemStack Lantern = new ItemStack(ContentRegistry.Lantern);
			GameRegistry.addRecipe(Lantern, new Object[]{
					"SSS",
					"GDG",
					"SSS",
					'S', STONE_SLAB,
					'D', Items.GLOWSTONE_DUST,
					'G', Blocks.GLASS_PANE
			});
		}
		
		//Blocks---------------------------------------------
		
		if(!((Boolean) ConfigHandler.isBlockEnabledMap.get(ContentRegistry.BlockBrazierOff.getRegistryName().toString()))){
			ItemStack Brazier = new ItemStack(ContentRegistry.BlockBrazierOff, 4);
			GameRegistry.addRecipe(Brazier, new Object[]{
					" S ",
					"SCS",
					"SSS",
					'S', STONE_SLAB,
					'C', new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE)
					
			});
		}
		
		if(!((Boolean) ConfigHandler.isBlockEnabledMap.get(ContentRegistry.BlockMetamorphicTable.getRegistryName().toString()))){
			ItemStack Table = new ItemStack(ContentRegistry.BlockMetamorphicTable, 1);
			GameRegistry.addRecipe(Table, new Object[]{
					"SES",
					" S ",
					"SSS",
					'S', STONE_SLAB,
					'E', Items.ENDER_EYE
					
			});
		}
		
		
		
		//Not Underworld content-------------------------------------------------
		
		ItemStack LargeGrass = new ItemStack(Blocks.DOUBLE_PLANT, 1, 2);
		GameRegistry.addRecipe(LargeGrass, new Object[]{
				"G ",
				"GB",
				'G', new ItemStack(Blocks.TALLGRASS, 1, 1),
				'B', new ItemStack(Items.DYE, 1, 15)
				
		});
	}

}
