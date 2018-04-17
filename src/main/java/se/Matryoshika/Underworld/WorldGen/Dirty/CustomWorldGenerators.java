package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Underworld.Utils.ConfigHandler;

public class CustomWorldGenerators {
	
	
	
	public static void register(){
		
		if(ConfigHandler.clayGen)
			GameRegistry.registerWorldGenerator(new DirtyClayGen(), 49);
		if(ConfigHandler.treeGen)
			GameRegistry.registerWorldGenerator(new DirtyTreeGen(), 50);
		if(ConfigHandler.vineGen)
			GameRegistry.registerWorldGenerator(new DirtyVineGen(), 51);
		if(ConfigHandler.hutGen)
			GameRegistry.registerWorldGenerator(new DirtyHutGen(), 52);
		if(ConfigHandler.dungeonGen)
			GameRegistry.registerWorldGenerator(new DirtyDungeonGen(), 53);
		if(ConfigHandler.spawnPointGen)
			GameRegistry.registerWorldGenerator(new DirtySpawnPointGen(), Integer.MAX_VALUE-1);
		if(ConfigHandler.spawnerGen)
			GameRegistry.registerWorldGenerator(new DirtySpawnerGen(), Integer.MAX_VALUE);
		if(ConfigHandler.lilyPadGen)
			GameRegistry.registerWorldGenerator(new DirtyLilypadGen(), 54);
		
		GameRegistry.registerWorldGenerator(new DirtyCaves(), 65536);
		
		
	}

}
