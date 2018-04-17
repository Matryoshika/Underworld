package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.awt.geom.Ellipse2D;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import se.Matryoshika.Underworld.Utils.ConfigHandler;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class DirtyClayGen implements IWorldGenerator {

	public boolean usedChunk;
	protected int randomness = 1;

	public IBlockState dirt = Blocks.CLAY.getDefaultState();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isSurfaceWorld()) {
			// System.out.println("NOT 0! IT IS
			// "+world.provider.getDimension());
			return;
		}
		if (!(world.getWorldType() instanceof WorldTypeCaves) && !ConfigHandler.forceUnderworld) {
			// System.out.println(world.getWorldType());
			return;
		}

		for (int y = 20; y < 32; y++) {
			if (canPlace(world, new BlockPos((chunkX * 16) + 8, y, (chunkZ * 16) + 8))) {
				actualGen(world, random, (chunkX * 16) + 8, (chunkZ * 16) + 8, y);
				// System.out.println("Placed a clay-chunk at: " +
				// Print.print((chunkX*16)+8, y, (chunkZ*16)+8));
				break;
			}
		}

	}

	public boolean canPlace(World world, BlockPos pos) {
		if (world.getBlockState(pos) == Blocks.DIRT.getDefaultState()) {
			if (world.getBlockState(pos.up()) == Blocks.WATER.getDefaultState() && world.getBlockState(pos.up(2)) == Blocks.WATER.getDefaultState()) {
				return true;
			}
		}
		return false;
	}

	public void actualGen(World world, Random random, int xPos, int zPos, int ySurfacePos) {

		int xRange = 5 + random.nextInt(13);
		int zRange = 5 + random.nextInt(13);
		int yRange = random.nextInt(7) + 2;
		int height = yRange;
		// int top = height;

		int yBottom = ySurfacePos - yRange;

		BlockPos center = new BlockPos(xPos, yBottom + height, zPos);
		BlockPos start = new BlockPos(xPos - xRange / 2, yBottom, zPos - zRange / 2);

		// the elliptic shape
		Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, xRange, zRange);

		// Basic shape
		for (int x = 0; x <= xRange; x++) {
			for (int z = 0; z <= zRange; z++) {
				for (int y = 0; y <= yRange; y++) {
					if (ellipse.contains(x, z)) {
						world.setBlockState(start.add(x, y, z), dirt, 2);
						// System.out.println("Placing a block Boss: " +
						// Print.print(start.add(x, y, z)));
					}
				}
			}
		}
	}
}
