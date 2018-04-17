package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.fml.common.IWorldGenerator;
import se.Matryoshika.Underworld.Utils.ConfigHandler;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class DirtyTreeGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isSurfaceWorld()) {
			// System.out.println("NOT 0! IT IS "+world.provider.getDimension());
			return;
		}
		if (!(world.getWorldType() instanceof WorldTypeCaves) && !ConfigHandler.forceUnderworld) {
			// System.out.println(world.getWorldType());
			return;
		}
		int x = 8;
		int z = 8;
		int blockX = chunkX * 16 + x;
		int blockZ = chunkZ * 16 + z;

		for (int y = 32; y < 80; y++) {
			int blockY = y + 1;
			// System.out.println("Scanned a block, Boss");
			if (canGenerate(world, blockX, blockY, blockZ)) {
				generateStructure(world, blockX, blockY, blockZ, random);

			}
		}
	}

	public int getOffset(int bound) {
		Random rand = new Random();
		int offset = rand.nextInt(bound) + 3;
		return offset;
	}

	public int getNegOffset(int bound) {
		int offset = getOffset(bound);
		return offset * -1;
	}

	private boolean canGenerate(World world, int x, int y, int z) {

		if (world.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.STONE.getDefaultState()) {
			if (world.isAirBlock(new BlockPos(x, y, z)) && world.isAirBlock(new BlockPos(x, y + 1, z)) && world.isAirBlock(new BlockPos(x, y + 2, z))) {
				return true;
			}
		}

		// System.out.println("Cannot Spawn");
		return false;
	}

	private void generateStructure(World world, int x, int y, int z, Random rand) {
		int radius = 5;

		BlockPos pos = new BlockPos(x, y, z);
		BlockPos tree1 = new BlockPos(x + getOffset(5), y, z + getOffset(5));
		BlockPos tree2 = new BlockPos(x + getOffset(5), y, z + getNegOffset(5));
		BlockPos tree3 = new BlockPos(x + getNegOffset(5), y, z + getOffset(5));
		BlockPos tree4 = new BlockPos(x + getNegOffset(5), y, z + getNegOffset(5));

		BlockPos[] states = { pos, tree1, tree2, tree3, tree4 };

		if (rand.nextInt(3) == 0) {

			for (int i = 0; i < states.length; i++) {
				if (canGenerate(world, states[i].getX(), states[i].getY(), states[i].getZ())) {
					// System.out.println("Generating a forest at: " + x + ", " + y + ", " + z);
					genGrass(states[i].getX(), states[i].getY(), states[i].getZ(), radius, world);
				}
			}

			int state = rand.nextInt(4);

			switch (state) {
			case 0: {
				for (int i = 0; i < states.length; i++) {
					new WorldGenTrees(true).generate(world, rand, states[i]);
				}
				break;
			}
			case 1: {
				for (int i = 0; i < states.length; i++) {
					new WorldGenBirchTree(true, true).generate(world, rand, states[i]);
				}
				break;
			}
			case 2: {
				for (int i = 0; i < states.length; i++) {
					new WorldGenCanopyTree(true).generate(world, rand, states[i]);
				}
				break;
			}
			case 3: {
				for (int i = 0; i < states.length; i++) {
					new WorldGenSavannaTree(true).generate(world, rand, states[1]);
				}
				break;
			}
			default: {
				for (int i = 0; i < states.length; i++) {
					new WorldGenTrees(true).generate(world, rand, states[i]);
				}
				break;
			}
			}

		}
	}

	public void genGrass(int x, int y, int z, int radius, World world) {
		for (int dx = x - radius; dx < x + radius; dx++) {
			for (int dy = y - radius; dy < y + radius; dy++) {
				for (int dz = z - radius; dz < z + radius; dz++) {
					int sqrt = ((dx - x) * (dx - x)) + ((dy - y) * (dy - y)) + ((dz - z) * (dz - z));
					if (sqrt <= (radius * radius)) {
						if (world.getBlockState(new BlockPos(dx, dy, dz)) == Blocks.STONE.getDefaultState()) {
							if (world.getBlockState(new BlockPos(dx, dy + 1, dz)) == Blocks.AIR.getDefaultState()) {
								world.setBlockState(new BlockPos(dx, dy, dz), Blocks.GRASS.getDefaultState());
							} else {
								world.setBlockState(new BlockPos(dx, dy, dz), Blocks.DIRT.getDefaultState());
							}
						}
					}
				}
			}
		}
	}

	public double getDistance(int x, int y, int z, int a, int b, int c) {

		double dx = x - a;
		double dy = y - b;
		double dz = z - c;

		return (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);

	}
}
