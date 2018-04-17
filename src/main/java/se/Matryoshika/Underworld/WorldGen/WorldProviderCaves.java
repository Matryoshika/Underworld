package se.Matryoshika.Underworld.WorldGen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Utils.ConfigHandler;

public class WorldProviderCaves extends WorldProvider {
	
	public static final int STRATUM_HIGH = 189;
	public static final int STRATUM_MID = 126;
	public static final int STRATUM_LOW = 63;

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.OVERWORLD;
	}

	@Override
	public BlockPos getSpawnPoint() {
		WorldInfo info = world.getWorldInfo();
		BlockPos spawn = world.getWorldType() instanceof WorldTypeCaves ? new BlockPos(0, 51, 0) : ConfigHandler.forceUnderworld ? new BlockPos(0, 51, 0) : new BlockPos(info.getSpawnX(), info.getSpawnY(), info.getSpawnZ());

		return spawn;
	}

	@Override
	protected void init() {
		this.biomeProvider = world.getWorldType().getBiomeProvider(world);

		if (ConfigHandler.forceUnderworld)
			this.biomeProvider = Underworld.getCaves().getBiomeProvider(world);
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		if (ConfigHandler.forceUnderworld)
			return new ChunkGeneratorCaves(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), world.getWorldInfo().getGeneratorOptions());

		return world.getWorldInfo().getTerrainType().getChunkGenerator(world, world.getWorldInfo().getGeneratorOptions());
	}

	@Override
	public boolean canDropChunk(int x, int z) {
		return !this.world.isSpawnChunk(x, z) || !this.world.provider.getDimensionType().shouldLoadSpawn();
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		int i = (int) (worldTime % 24000L);
		float f = ((float) i + partialTicks) / 24000.0F - 0.25F;

		if (f < 0.0F) {
			++f;
		}

		if (f > 1.0F) {
			--f;
		}

		float f1 = 1.0F - (float) ((Math.cos((double) f * Math.PI) + 1.0D) / 2.0D);
		f += (f1 - f) / 3.0F;

		if (ConfigHandler.allowTime == 0)
			return 0.50F;

		if (ConfigHandler.allowTime == 2)
			return 1;

		return f;
	}

	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.0F;
		if (ConfigHandler.gloomyLight)
			f += 0.1F;

		for (int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - (float) i / 15.0F;
			this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		}

	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

}
