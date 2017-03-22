package se.Matryoshika.Underworld.Integration.CraftTweaker;

import mezz.jei.JustEnoughItems;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.mods.jei.JEIRecipeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import se.Matryoshika.Underworld.API.MetamorphicTableRecipes;
import se.Matryoshika.Underworld.API.TableRecipes;
import se.Matryoshika.Underworld.Integration.JEI.JEIMetamorphicTablePlugin;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.underworld.metamorphictable")
public class MTMetamorphicTableRecipes {

	public static class MetamorphicTableAction extends AddRemoveAction {

		ItemStack input;
		ItemStack output;
		int meta;

		public MetamorphicTableAction(ItemStack input, ItemStack output, int meta) {
			this.input = input;
			this.output = output;
			this.meta = meta;
		}

		@Override
		protected void add() {
			MetamorphicTableRecipes.registerTableRecipes(input, output, meta);

		}

		@Override
		protected void remove() {
			MetamorphicTableRecipes.removeTableRecipes(input, output, meta);

		}

		@Override
		public String getDescription() {
			return String.format(" %s -> %s ", getItemDescription(this.input), getItemDescription(this.output));
		}

		@Override
		public String getRecipeType() {
			return "metamorphic table";
		}

	}
	
	@ZenMethod
	static public void addRecipe(IItemStack _input, IItemStack _output, int _meta){
		ItemStack input = null;
		ItemStack output = null;
		int meta = 0;
		
		try{
			output = MineTweakerMC.getItemStack(_output);
			input = MineTweakerMC.getItemStack(_input);
			meta = _meta;
		}
		catch(IllegalArgumentException e){
			MineTweakerAPI.logError("Invalid MetamorphicTable recipe: " + e.getMessage());
		}
		
		MineTweakerAPI.apply(new MetamorphicTableAction(input, output, meta).action_add);
		MineTweakerAPI.ijeiRecipeRegistry.addRecipe(MetamorphicTableRecipes.getRecipe(MineTweakerMC.getItemStack(_input), MineTweakerMC.getItemStack(_output), _meta));
		
	}
	
	@ZenMethod
	static public void removeRecipe(IItemStack input, IItemStack output, int meta){
		if(!MetamorphicTableRecipes.recipeExists(MineTweakerMC.getItemStack(input), MineTweakerMC.getItemStack(output), meta)){
			MineTweakerAPI.logWarning("Metamorphic Table recipe not found: "+ MineTweakerMC.getItemStack(input).getItem().getRegistryName() + " -> " + MineTweakerMC.getItemStack(output).getItem().getRegistryName() + " with metadata of " + meta);
			return;
		}
		MineTweakerAPI.ijeiRecipeRegistry.removeRecipe(MetamorphicTableRecipes.getRecipe(MineTweakerMC.getItemStack(input), MineTweakerMC.getItemStack(output), meta));
		MineTweakerAPI.apply(new MetamorphicTableAction(MineTweakerMC.getItemStack(input), MineTweakerMC.getItemStack(output), meta).action_remove);
	}

	static public String getItemDescription(ItemStack stack) {
		ResourceLocation item = stack.getItem().getRegistryName();
		return String.format("I(%s:%s:%d,%d)", item.getResourceDomain(), item.getResourcePath(), stack.getItemDamage(),
				stack.stackSize);
	}
}
