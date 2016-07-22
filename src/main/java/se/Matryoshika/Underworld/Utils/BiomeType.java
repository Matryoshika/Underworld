package se.Matryoshika.Underworld.Utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Biomes;

public class BiomeType {
	
	public static List COLD = new ArrayList();
	public static List WARM = new ArrayList();
	public static List WATER = new ArrayList();
	public static List LIVELY = new ArrayList();
	public static List LIFELESS = new ArrayList();
	
	public static void init(){
		//COLD BIOMES
		COLD.add(Biomes.OCEAN);
		COLD.add(Biomes.COLD_BEACH);
		COLD.add(Biomes.COLD_TAIGA);
		COLD.add(Biomes.COLD_TAIGA_HILLS);
		COLD.add(Biomes.DEEP_OCEAN);
		COLD.add(Biomes.DEFAULT);
		COLD.add(Biomes.FROZEN_OCEAN);
		COLD.add(Biomes.FROZEN_RIVER);
		COLD.add(Biomes.MUTATED_ICE_FLATS);
		COLD.add(Biomes.MUTATED_TAIGA);
		COLD.add(Biomes.MUTATED_TAIGA_COLD);
		COLD.add(Biomes.REDWOOD_TAIGA);
		COLD.add(Biomes.REDWOOD_TAIGA_HILLS);
		COLD.add(Biomes.MUTATED_REDWOOD_TAIGA);
		COLD.add(Biomes.MUTATED_REDWOOD_TAIGA_HILLS);
		COLD.add(Biomes.TAIGA);
		COLD.add(Biomes.TAIGA_HILLS);
		COLD.add(Biomes.ICE_MOUNTAINS);
		COLD.add(Biomes.ICE_PLAINS);
		
		//WARM BIOMES
		WARM.add(Biomes.BEACH);
		WARM.add(Biomes.DESERT);
		WARM.add(Biomes.HELL);
		WARM.add(Biomes.JUNGLE);
	}

}
