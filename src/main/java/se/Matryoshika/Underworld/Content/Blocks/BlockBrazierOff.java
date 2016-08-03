package se.Matryoshika.Underworld.Content.Blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.BlockRegistry;

public class BlockBrazierOff extends Block{
	
	public BlockBrazierOff(){
		super(Material.GROUND);
		this.setTickRandomly(true);
		this.setUnlocalizedName("BlockBrazier");
		this.setRegistryName(Underworld.MODID, "blockbrazieroff");
		this.setCreativeTab(Underworld.UnderworldTab);
	}
	
	public Block setLightLevel(float value)
    {
        this.lightValue = (int) 0F;
        return this;
    }
	
	public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    public boolean isFullCube(IBlockState state){
        return false;
    }
    
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack 
    		heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
    	if(player.inventory.getCurrentItem() != null){
    		if(player.inventory.getCurrentItem().getItem() == Items.FLINT_AND_STEEL){
    			world.setBlockState(pos, BlockRegistry.BlockBrazierOn.getDefaultState(), 3);
    			player.inventory.getCurrentItem().setItemDamage(player.inventory.getCurrentItem().getItemDamage()+1);
    		}
    	}
    	
    	return true;
    }
}
