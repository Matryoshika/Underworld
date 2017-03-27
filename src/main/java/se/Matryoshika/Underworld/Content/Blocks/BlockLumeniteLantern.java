package se.Matryoshika.Underworld.Content.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.Mod.EventHandler;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.TileEntity.TileLumeniteLantern;

public class BlockLumeniteLantern extends Block{

	public BlockLumeniteLantern() {
		super(Material.IRON);
		this.setRegistryName(Underworld.MODID, "block_lumenite_lantern");
		this.setUnlocalizedName(getRegistryName().toString());
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state){
        return true;
    }
	
	@Override
	public Block setLightLevel(float value){
        this.lightValue = (int)(value);
        return this;
    }
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state){
        return new TileLumeniteLantern();
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state){
        return false;
    }

	@Override
    public boolean isFullCube(IBlockState state){
        return false;
    }
	
	@EventHandler
	public void stopSpawns(CheckSpawn event){
		ChunkPos pos = new ChunkPos(((int)event.getX()) >> 4, ((int)event.getZ()) >> 4);
		if(TileLumeniteLantern.claimedChunks.get(event.getWorld()).contains(pos)){
			event.setCanceled(true);
		}
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
        
		if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileLumeniteLantern)
			((TileLumeniteLantern)world.getTileEntity(pos)).removeChunks();

		world.removeTileEntity(pos);
    }

}
