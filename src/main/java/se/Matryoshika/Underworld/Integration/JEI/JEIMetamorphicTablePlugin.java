package se.Matryoshika.Underworld.Integration.JEI;

import javax.annotation.Nonnull;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import se.Matryoshika.Underworld.API.MetamorphicTableRecipes;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Utils.ConfigHandler;

@JEIPlugin
public class JEIMetamorphicTablePlugin implements IModPlugin{

	@Override
	public void register(@Nonnull IModRegistry registry) {
		
		if(((Boolean) ConfigHandler.isBlockEnabledMap.get(ContentRegistry.BlockMetamorphicTable.getRegistryName().toString()))){
			
			IJeiHelpers jeihelpers = registry.getJeiHelpers();
			
			
			
			registry.addRecipeCategories(
					new MetamorphicTableCategory(jeihelpers.getGuiHelper())
					);
			
			registry.addRecipeHandlers(
					new MetamorphicTableHandler()
					);
			
			registry.addRecipes(MetamorphicTableRecipes.metamorphicTableRecipes);
			registry.addRecipeCategoryCraftingItem(new ItemStack(ContentRegistry.BlockMetamorphicTable), MetamorphicTableCategory.UID);
			
		}
		
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		// TODO Auto-generated method stub
		
	}
	

}
