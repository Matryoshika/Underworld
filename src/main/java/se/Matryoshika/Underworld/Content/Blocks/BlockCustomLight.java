package se.Matryoshika.Underworld.Content.Blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.TileEntity.TileCustomLight;

public class BlockCustomLight extends BlockContainer{

	public BlockCustomLight() {
		super(Material.AIR);
		this.setRegistryName(Underworld.MODID, "blockcustomlight");
		this.setLightLevel(1F);
		this.setUnlocalizedName("blockcustomlight");
	}
	
	public static void setState(boolean active, World world, BlockPos pos){
		TileEntity tileentity = world.getTileEntity(pos);
		
		if (tileentity != null){
            tileentity.validate();
            world.setTileEntity(pos, tileentity);
        }
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta){
		return new TileCustomLight();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.INVISIBLE;
    }

	@Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
        return NULL_AABB;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
	@Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

	@Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid){
        return false;
    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
	@Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune){
    }

    /**
     * Whether this Block can be replaced directly by other blocks (true for e.g. tall grass)
     */
	@Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos){
        return true;
    }

	@Override
    public boolean isFullCube(IBlockState state){
        return false;
    }

}
