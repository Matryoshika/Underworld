package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.fml.common.IWorldGenerator;

public class DirtyTreeGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isSurfaceWorld()){
			//System.out.println("NOT 0! IT IS "+world.provider.getDimension());
			return;
		}
		
		
		for(int x = 0; x < 16; x++){
			for (int y = 32; y < 80; y++){
				for (int z = 0; z < 16; z++) {
					int blockX = chunkX * 16 + x;
					int blockY = y + 1;
					int blockZ = chunkZ * 16 + z;
					
					//System.out.println("Scanned a block, Boss");
					if (canGenerate(world, blockX, blockY, blockZ))
						generateStructure(world, blockX, blockY, blockZ, random);
				}
			}
		}
	}
	private boolean canGenerate(World world, int x, int y, int z) {
		
			if(world.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.STONE.getDefaultState()){
				if(world.isAirBlock(new BlockPos(x, y , z)) && world.isAirBlock(new BlockPos(x, y + 1, z)) && world.isAirBlock(new BlockPos(x, y + 2, z))){
					return true;
				}
			}
		
		
		//System.out.println("Cannot Spawn");
		return false;
	}
	
	private void generateStructure(World world, int x, int y, int z, Random rand) {
		Random genRand = new Random();
		int radius = 5;
		
		if(genRand.nextInt(500) == 0){
			
			for (int dx = x - radius; dx < x + radius; dx++){
				for(int dy = y - radius; dy < y + radius; dy++){
					for(int dz = z - radius; dz < z + radius; dz++){
						int sqrt = ((dx-x)*(dx-x)) + ((dy-y)*(dy-y)) + ((dz-z)*(dz-z));
						if(sqrt <= (radius*radius)){
							if(world.getBlockState(new BlockPos(dx, dy, dz)) == Blocks.STONE.getDefaultState()){
								if(world.getBlockState(new BlockPos(dx, dy+1, dz)) == Blocks.AIR.getDefaultState()){
									world.setBlockState(new BlockPos(dx, dy, dz), Blocks.GRASS.getDefaultState());
								}
								else{
									world.setBlockState(new BlockPos(dx, dy, dz), Blocks.DIRT.getDefaultState());
								}
							}
						}
					}
				}
			}
			System.out.println("spawned a tree in biome: " + world.getBiomeForCoordsBody(new BlockPos(x,y,z)));
			new WorldGenTrees(true).generate(world, rand, new BlockPos(x, y, z));
		}
	}
	
	
	public double getDistance(int x, int y, int z, int a, int b, int c){
		
		double dx = x - a;
		double dy = y - b;
		double dz = z - c;
		
		
		return (double)MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
		
	}
}
