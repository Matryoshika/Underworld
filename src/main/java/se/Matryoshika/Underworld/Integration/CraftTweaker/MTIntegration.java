package se.Matryoshika.Underworld.Integration.CraftTweaker;

import minetweaker.MineTweakerAPI;
import net.minecraftforge.fml.common.Optional;

public class MTIntegration {

	@Optional.Method(modid = "MineTweaker3")
	public static void onInit() {
		MineTweakerAPI.registerClass(MTMetamorphicTableRecipes.class);
	}

}
