package se.Matryoshika.Underworld.Content.Blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class BlockGlowingMossStone extends Block{
	
	public BlockGlowingMossStone() {
		super(Material.ROCK);
		this.setRegistryName("glowing_moss_stone");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Underworld.UnderworldTab);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
	}
	
	@Override
	public Block setLightLevel(float value){
        this.lightValue = (int)(value);
        return this;
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand){
    	
		
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.5D;
        double d2 = (double)pos.getZ() + 0.5D;
        Underworld.proxy.spawnCustomParticle("firefly", world, d0-0.4, d1, d2-0.4, 3, 1, 1, 1);
    }
	
	@Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(ContentRegistry.BlockMossStone);
    }

}
