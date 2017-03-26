package se.Matryoshika.Underworld.Utils;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class SugarPileDispenseBehaviour implements IBehaviorDispenseItem{
	
	/**
     * Dispenses the specified ItemStack from a dispenser.
     */
    public final ItemStack dispense(IBlockSource source, ItemStack stack){
        ItemStack itemstack = this.dispenseStack(source, stack);
        this.playDispenseSound(source);
        this.spawnDispenseParticles(source, (EnumFacing)source.func_189992_e().getValue(BlockDispenser.FACING));
        return itemstack;
    }

    /**
     * Dispense the specified stack, play the dispense sound and spawn particles.
     */
    protected ItemStack dispenseStack(IBlockSource source, ItemStack stack){
        EnumFacing enumfacing = (EnumFacing)source.func_189992_e().getValue(BlockDispenser.FACING);
        IPosition iposition = BlockDispenser.getDispensePosition(source);
        
        doDispense(source.getWorld(), stack, 6, enumfacing, iposition);
        return stack;
    }

    public static void doDispense(World world, ItemStack stack, int speed, EnumFacing facing, IPosition position){
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();

        if(!world.isAirBlock(new BlockPos(x,y,z)))
        	return;
        
        world.setBlockState(new BlockPos(x,y,z), ContentRegistry.BlockSugarPile.getDefaultState());
        SugarPileList.addSugarPile(world, new BlockPos(x,y,z));
        
        ItemStack itemstack = stack.splitStack(1);
    }

    /**
     * Play the dispense sound from the specified block.
     */
    protected void playDispenseSound(IBlockSource source){
        source.getWorld().playEvent(1000, source.getBlockPos(), 0);
    }

    /**
     * Order clients to display dispense particles from the specified block and facing.
     */
    protected void spawnDispenseParticles(IBlockSource source, EnumFacing facingIn){
        source.getWorld().playEvent(2000, source.getBlockPos(), this.getWorldEventDataFrom(facingIn));
    }

    private int getWorldEventDataFrom(EnumFacing facingIn){
        return facingIn.getFrontOffsetX() + 1 + (facingIn.getFrontOffsetZ() + 1) * 3;
    }
}
