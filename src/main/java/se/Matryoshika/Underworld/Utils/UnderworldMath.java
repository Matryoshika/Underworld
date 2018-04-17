package se.Matryoshika.Underworld.Utils;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class UnderworldMath {

	public static Vec3d moveAwayFrom(double x1, double y1, double z1, double x2, double y2, double z2, double distance) {

		Vec3d vector = new Vec3d(x2 - x1, y2 - y1, z2 - z1);
		double length = Math.sqrt(vector.x * vector.x + vector.y * vector.y + vector.z * vector.z);

		Vec3d unit = new Vec3d(vector.x / length, vector.y / length, vector.z / length);
		Vec3d point = new Vec3d(x1 + unit.x * distance, y1 + unit.y * distance, z1 + unit.z * distance);

		return point;
	}

	public static Vec3d moveAwayFrom(BlockPos pos1, BlockPos pos2, double distance) {

		return moveAwayFrom(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ(), distance);
	}

}
