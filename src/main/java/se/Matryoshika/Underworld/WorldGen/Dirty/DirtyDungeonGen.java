package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.IWorldGenerator;
import se.Matryoshika.Underworld.Utils.ConfigHandler;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class DirtyDungeonGen implements IWorldGenerator {

	public int SIZE;
	// Sizes of the outside. Inside would be size-2
	public int SMALL = 7;
	public int MEDIUM = 9;
	public int LARGE = 11;

	public int TOP = 112;
	public int BOTTOM = 8;

	public int[] SIZES = { SMALL, MEDIUM, LARGE };

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isSurfaceWorld()) {
			// System.out.println("NOT 0! IT IS "+world.provider.getDimension());
			return;
		}
		if (!(world.getWorldType() instanceof WorldTypeCaves) && !ConfigHandler.forceUnderworld) {
			// System.out.println(world.getWorldType());
			return;
		}

		if (rand.nextInt(50) != 0) {
			return;
		}
		int X = (chunkX * 16) + 8;
		int Z = (chunkZ * 16) + 8;
		int Y = rand.nextInt(2) == 0 ? BOTTOM : TOP;
		Y = Y + rand.nextInt(6) - 3;
		spawnRoom(world, new BlockPos(X, Y, Z), rand);

	}

	public void spawnRoom(World world, BlockPos pos, Random rand) {
		SIZE = SIZES[rand.nextInt(SIZES.length)];

		for (int x = 1; x <= SIZE; x++) {
			for (int z = 1; z <= SIZE; z++) {
				for (int y = 1; y <= SIZE; y++) {
					if (x > 1 && x < SIZE && z > 1 && z < SIZE && y > 1 && y < SIZE) {
						world.setBlockToAir(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z));
					} else {
						IBlockState state = rand.nextInt(2) == 0 ? Blocks.COBBLESTONE.getDefaultState() : Blocks.MOSSY_COBBLESTONE.getDefaultState();
						world.setBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z), state);
					}
				}
			}
		}
		int middle = (int) Math.ceil(SIZE / 2) + 1;
		BlockPos center = new BlockPos(pos.getX() + middle, pos.getY() + 1, pos.getZ() + middle);
		world.setBlockState(center.up(), Blocks.MOSSY_COBBLESTONE.getDefaultState());
		world.setBlockState(center.up(2), Blocks.MOB_SPAWNER.getDefaultState());
		TileEntity tileentity = world.getTileEntity(center.up(2));

		if (tileentity instanceof TileEntityMobSpawner) {
			((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setEntityId(this.pickMobSpawner(rand));
		}

		BlockPos chest = new BlockPos(center.getX() + ((int) Math.ceil(SIZE / 2) - 1), center.getY() + 1, center.getZ() + ((int) Math.ceil(SIZE / 2) - 1));
		world.setBlockState(chest, Blocks.CHEST.getDefaultState());

		TileEntity chestPos = world.getTileEntity(chest);

		if (chestPos instanceof TileEntityChest) {
			((TileEntityChest) chestPos).setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON, rand.nextLong());
		}

		// System.out.println("Placed a dungeon at: " + Print.print(center.up(2)) + "
		// with size: "+SIZE);
	}

	private ResourceLocation pickMobSpawner(Random rand) {
		return net.minecraftforge.common.DungeonHooks.getRandomDungeonMob(rand);
	}
}
