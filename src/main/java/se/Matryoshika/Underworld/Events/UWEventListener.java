package se.Matryoshika.Underworld.Events;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class UWEventListener implements IWorldEventListener{
	
	public World world;
	
	public UWEventListener(World world){
		this.world = world;
	}

	@Override
	public void notifyBlockUpdate(World worldIn, BlockPos pos, IBlockState oldState, IBlockState newState, int flags) {
		
	}

	@Override
	public void notifyLightSet(BlockPos pos) {
		
	}

	@Override
	public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
		
	}

	@Override
	public void playSoundToAllNearExcept(EntityPlayer player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {
		
	}

	@Override
	public void playRecord(SoundEvent soundIn, BlockPos pos) {
		
	}

	@Override
	public void onEntityAdded(Entity entityIn) {
		
	}

	@Override
	public void onEntityRemoved(Entity entityIn) {
		
	}

	@Override
	public void broadcastSound(int soundID, BlockPos pos, int data) {
		
	}

	@Override
	public void playEvent(EntityPlayer player, int type, BlockPos pos, int data) {
		if(world.isRemote)
			return;
		
		if(type == 1031 && world.getBlockState(pos.down()).getBlock() == ContentRegistry.BlockGlowMossStone){
			world.setBlockState(pos.down(), Blocks.MOSSY_COBBLESTONE.getDefaultState());
			world.spawnEntity(new EntityItem(world, pos.getX()+0.5, pos.getY(), pos.getZ()+0.5, new ItemStack(ContentRegistry.Lumenite)));
		}
	}

	@Override
	public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
		
	}

	@Override
	public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
		
	}

	@Override
	public void spawnParticle(int id, boolean ignoreRange, boolean p_190570_3_, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... parameters) {
		
	}

}
