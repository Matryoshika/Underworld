package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CustomWorldGenerators {
	
	public static void register(){
		
		GameRegistry.registerWorldGenerator(new DirtyClayGen(), 49);
		GameRegistry.registerWorldGenerator(new DirtyTreeGen(), 50);
		GameRegistry.registerWorldGenerator(new DirtyVineGen(), 51);
		GameRegistry.registerWorldGenerator(new DirtyHutGen(), 52);
		GameRegistry.registerWorldGenerator(new DirtyDungeonGen(), 53);
		GameRegistry.registerWorldGenerator(new DirtySpawnPointGen(), Integer.MAX_VALUE-1);
		GameRegistry.registerWorldGenerator(new DirtySpawnerGen(), Integer.MAX_VALUE);
		GameRegistry.registerWorldGenerator(new DirtyLilypadGen(), 54);
		GameRegistry.registerWorldGenerator(new DirtySugarcaneGen(), 55);
		
		
	}

}
