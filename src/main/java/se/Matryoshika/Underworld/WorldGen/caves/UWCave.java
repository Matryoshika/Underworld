package se.Matryoshika.Underworld.WorldGen.caves;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorSimplex;

public class UWCave {

	public final Chunk chunk;

	public final int caveMin;
	public final int caveMax;

	public final double freqXZ1;
	public final double freqY1;
	public final int amp1 = 100;
	public final double cutOff;

	public final double freqXZ2 = 0.25;
	public final double freqY2 = 0.05;
	public final int amp2 = 2;

	public final double freqXZ3 = 0.025;
	public final double freqY3 = 0.005;
	public final int amp3 = 20;

	public final int caveBuffer;

	public final NoiseGeneratorSimplex noiseGen1;
	public final NoiseGeneratorSimplex noiseGen2;
	public final NoiseGeneratorSimplex noiseGen3;

	public UWCave(Chunk c, double fXZ, double fY, int cutOffVal, int caveMi, int caveMa) {
		chunk = c;
		caveMin = caveMi;
		caveMax = caveMa;
		freqXZ1 = fXZ;
		freqY1 = fY;
		cutOff = cutOffVal;

		if (caveMax - caveMin > 128)
			caveBuffer = 32;
		else
			caveBuffer = 16;

		noiseGen1 = new NoiseGeneratorSimplex(chunk.getWorld().rand);
		Random rand = new Random((long) noiseGen1.getValue(chunk.x, chunk.z));
		noiseGen2 = new NoiseGeneratorSimplex(rand);
		noiseGen3 = new NoiseGeneratorSimplex(rand);
	}

	public boolean insideGiantCave(int x, int y, int z) {

		double n1 = (noiseGen1.getValue(x * freqXZ1, z * freqXZ1) * amp1);
		double n2 = (noiseGen2.getValue(x * freqXZ1, z * freqXZ1) * amp2);
		double n3 = (noiseGen3.getValue(x * freqXZ1, z * freqXZ1) * amp3);
		double lc = linearCutOff(y);

		return n1 + n2 - n3 - lc > cutOff;
	}

	private double linearCutOff(double y) {
		if (y < caveMin || y > caveMax)
			return cutOff;
		if (y >= caveMin && y <= caveMin + caveBuffer)
			return (-cutOff / (double) caveBuffer) * (y - caveMin) * cutOff;
		if (y <= caveMax && y >= caveMax - caveBuffer)
			return (cutOff / (double) caveBuffer) * (y - caveMax + caveBuffer);

		return 0;

	}

}
