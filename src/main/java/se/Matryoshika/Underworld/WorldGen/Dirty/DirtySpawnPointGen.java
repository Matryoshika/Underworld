package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.fml.common.IWorldGenerator;
import se.Matryoshika.Underworld.Content.BlockRegistry;

public class DirtySpawnPointGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(chunkX == 0 && chunkZ == 0){
			
			int radius = 16;
			
			for(int dx = 0 - radius; dx < 0 + radius; dx++){
				for(int dy = 50 - radius; dy < 50 + radius; dy++){
						for(int dz = 0 - radius; dz < 0 + radius; dz++){
						int sqrt = ((dx)*(dx)) + ((dy-50)*(dy-50)) + ((dz)*(dz));
						if(sqrt <= (radius*radius)){
							if(dy <= 50){
								world.setBlockState(new BlockPos(dx, dy, dz), Blocks.STONE.getDefaultState());
							}
							else{
								world.setBlockToAir(new BlockPos(dx, dy, dz));
							}
						}
					}
				}
			}
			BlockPos poss1 = new BlockPos(5, 51, 5);
			BlockPos poss2 = new BlockPos(5, 51, -5);
			BlockPos poss3 = new BlockPos(-5, 51, 5);
			BlockPos poss4 = new BlockPos(-5, 51, -5);
			List<BlockPos> positions = new ArrayList();
			positions.add(poss1);
			positions.add(poss2);
			positions.add(poss3);
			positions.add(poss4);
			
			BlockPos finished = positions.get(random.nextInt(3));
			world.setBlockState(finished.down(), Blocks.GRASS.getDefaultState());
			new WorldGenTrees(true).generate(world, random, finished);
			for(BlockPos pos:positions){
				if(pos != finished){
					world.setBlockState(pos, BlockRegistry.BlockBrazierOn.getDefaultState());
				}
			}
			world.setBlockState(new BlockPos(0, 51, 10), BlockRegistry.BlockBrazierOn.getDefaultState());
			world.setBlockState(new BlockPos(0, 51, -10), BlockRegistry.BlockBrazierOn.getDefaultState());
			world.setBlockState(new BlockPos(10, 51, 0), BlockRegistry.BlockBrazierOn.getDefaultState());
			world.setBlockState(new BlockPos(-10, 51, 0), BlockRegistry.BlockBrazierOn.getDefaultState());
		}
	}

}
