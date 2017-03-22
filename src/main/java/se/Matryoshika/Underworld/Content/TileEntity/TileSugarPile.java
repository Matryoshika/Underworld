package se.Matryoshika.Underworld.Content.TileEntity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Utils.SugarPileList;

public class TileSugarPile extends TileEntity implements ITickable {

	private int age = 0;
	
	public TileSugarPile(){
	}

	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		age++;
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		int radius = 8;
		List<EntityLiving> entities = worldObj.getEntitiesWithinAABB(EntityLiving.class,
				new AxisAlignedBB((double) (x - radius), (double) (y - radius), (double) (z - radius),
						(double) (x + radius), (double) (y + radius), (double) (z + radius)));
		for (EntityLiving entity : entities) {

			double distance;
			String entityName;
			Class entityClass;

			if (entity instanceof EntityAnimal
					|| (distance = entity.getDistance((double) x, (double) y, (double) z)) >= (double) 8
					|| distance == 0.0)
				continue;

			if (distance < 1)
				distance = 1;

			for (int i = 0; i < 3; i++)
				Underworld.proxy.spawnCustomParticle("firefly", worldObj,
						entity.posX - 0.4 + (Math.random() - Math.random()),
						(entity.posY + 1) + (Math.random() * 2 - 1),
						entity.posZ - 0.4 + (Math.random() - Math.random()), 0.3, 0.3, 0.3, 3, 1, 0.1f, 0.2f);

		}
		if (age >= 6000) {
			worldObj.setBlockToAir(getPos());
			if (worldObj.getBlockState(getPos().down()).getBlock() == ContentRegistry.BlockMossStone) {
				if (new Random().nextInt(100) < 25)
					worldObj.setBlockState(getPos().down(), ContentRegistry.BlockGlowMossStone.getDefaultState());
			}
		}
	}

}
