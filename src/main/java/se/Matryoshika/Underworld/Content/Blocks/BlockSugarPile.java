package se.Matryoshika.Underworld.Content.Blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Content.TileEntity.TileSugarPile;
import se.Matryoshika.Underworld.Content.TileEntity.TileUnderworldEnderPortal;
import se.Matryoshika.Underworld.Utils.SugarPileList;

public class BlockSugarPile extends Block{
	
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.175D, 0.7D);
	
	public BlockSugarPile() {
		super(Material.CIRCUITS);
		this.setRegistryName("sugar_pile");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Underworld.UnderworldTab);
	}

	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand){
    	
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        Underworld.proxy.spawnCustomParticle("firefly", world, d0-0.4, d1, d2-0.4, 0, 0, 0, 5, 1, 1, 1);
    }
	
	public Block setLightLevel(float value){
        this.lightValue = (int)(value);
        return this;
    }
	
	public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    public boolean isFullCube(IBlockState state){
        return false;
    }
    
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Items.SUGAR;
    }
    
    @Override
	public boolean hasTileEntity(IBlockState state){
        return true;
    }
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state){
        return new TileSugarPile();
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return AABB;
    }
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos){
		boolean sugarNearby = false;
		for(BlockPos opos : BlockPos.getAllInBox(pos.add(-5, -3, -5), pos.add(5, 3, 5))){
			if(world.getBlockState(opos).getBlock() == ContentRegistry.BlockSugarPile)
				sugarNearby = true;
		}
        return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && !sugarNearby;
    }
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
        
		SugarPileList.removeSugarPile(world, pos);;
		world.removeTileEntity(pos);
    }
}
