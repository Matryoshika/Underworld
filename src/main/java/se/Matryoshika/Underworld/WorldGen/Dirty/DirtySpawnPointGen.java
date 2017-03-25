package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.awt.geom.Ellipse2D;
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
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Utils.ConfigHandler;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class DirtySpawnPointGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (!world.provider.isSurfaceWorld()) {
			// System.out.println("NOT 0! IT IS
			// "+world.provider.getDimension());
			return;
		}
		if (!(world.getWorldType() instanceof WorldTypeCaves) && !ConfigHandler.forceUnderworld) {
			// System.out.println(world.getWorldType());
			return;
		}

		if (chunkX == 0 && chunkZ == 0) {


			int xRange = 24;
			int zRange = 24;
			int yRange = 0;

			int yBottom = 50 - yRange;
			
			int repeats = 0;

			for(int level = 64; level > 0; level--){
				
				repeats++;
				
				BlockPos center = new BlockPos(0, level, 0);
				BlockPos start = new BlockPos(0 - (xRange+repeats) / 2, level, 0 - (zRange+repeats) / 2);

				// the elliptic shape
				Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, xRange+repeats, zRange+repeats);

				// Basic shape
				for (int x = 0; x <= xRange+repeats; x++) {
					for (int z = 0; z <= zRange+repeats; z++) {
						for (int y = 0; y <= yRange; y++) {
							if (ellipse.contains(x, z)) {
								
								if(level > 50)
									world.setBlockToAir(start.add(x, y, z));
								else
									world.setBlockState(start.add(x, y, z), Blocks.STONE.getDefaultState(), 2);
								// System.out.println("Placing a block Boss: " +
								// Print.print(start.add(x, y, z)));
							}
						}
					}
				}
			}

			/*
			 * 
			 * for(int dx = 0 - radius; dx < 0 + radius; dx++){ for(int dy = 50
			 * - radius; dy < 50 + radius; dy++){ for(int dz = 0 - radius; dz <
			 * 0 + radius; dz++){ int sqrt = ((dx)*(dx)) + ((dy-50)*(dy-50)) +
			 * ((dz)*(dz)); if(sqrt <= (radius*radius)){ if(dy <= 50){
			 * world.setBlockState(new BlockPos(dx, dy, dz),
			 * Blocks.STONE.getDefaultState()); } else{ world.setBlockToAir(new
			 * BlockPos(dx, dy, dz)); } } } } }
			 */
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
			for (BlockPos pos : positions) {
				if (pos != finished) {
					world.setBlockState(pos, ContentRegistry.BlockBrazierOn.getDefaultState());
				}
			}
			world.setBlockState(new BlockPos(0, 51, 10), ContentRegistry.BlockBrazierOn.getDefaultState());
			world.setBlockState(new BlockPos(0, 51, -10), ContentRegistry.BlockBrazierOn.getDefaultState());
			world.setBlockState(new BlockPos(10, 51, 0), ContentRegistry.BlockBrazierOn.getDefaultState());
			world.setBlockState(new BlockPos(-10, 51, 0), ContentRegistry.BlockBrazierOn.getDefaultState());
		}
	}

}
