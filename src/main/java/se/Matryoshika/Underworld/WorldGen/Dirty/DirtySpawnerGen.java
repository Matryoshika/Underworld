package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class DirtySpawnerGen implements IWorldGenerator{

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
		if(rand.nextInt(8) == 0){
			world.setBlockState(new BlockPos(chunkX*16, 129, chunkZ*16),  ContentRegistry.Spawner.getDefaultState());
		}
	}

}
