package se.Matryoshika.Underworld.Content.Blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Content.TileEntity.TileMetamorphicTable;
import se.Matryoshika.Underworld.Utils.ConfigHandler;

public class BlockMetamorphicTable extends Block implements ITileEntityProvider{
	
	public static Block ACTIVATION_BLOCK;
	public static int ACTIVATION_STATE;

	public BlockMetamorphicTable() {
		super(Material.ROCK);
		this.setRegistryName(Underworld.MODID, "blockMetamorphicTable");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Underworld.UnderworldTab);
		this.setHardness(10);
		ACTIVATION_BLOCK = Block.getBlockFromName(ConfigHandler.metamorphicTableActivationBlocks);
		ACTIVATION_STATE = ConfigHandler.metamorphicTableActivationMeta;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
		tooltip.add(I18n.format(Underworld.MODID+".lore.metamorphictable"));
    }

	
	
	@SideOnly(Side.CLIENT)
	public void trailParticles(World world, BlockPos pos, Random rand){
		final TileMetamorphicTable table = (TileMetamorphicTable) getTileEntity(world, pos);
		
		
		if(table.risingFrame % 2 == 0)
			return;
			
		BlockPos north = new BlockPos(pos.getX()+3, pos.getY()+1, pos.getZ());
		BlockPos east = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()+3);
		BlockPos south = new BlockPos(pos.getX()-3, pos.getY()+1, pos.getZ());
		BlockPos west = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()-3);
		
		world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, north.getX()+0.5, north.getY()+0.15, north.getZ()+0.5, -0.125, -0.0075, 0, new int[0]);
		world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, east.getX()+0.5, east.getY()+0.15, east.getZ()+0.5,         0, -0.0075, -0.125, new int[0]);
		world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, south.getX()+0.5, south.getY()+0.15, south.getZ()+0.5, 0.125, -0.0075, 0, new int[0]);
		world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, west.getX()+0.5, west.getY()+0.15, west.getZ()+0.5,         0, -0.0075, 0.125, new int[0]);
	}
	
	@SideOnly(Side.CLIENT)
	public void boomParticles(World world, BlockPos pos, Random rand){
		final TileMetamorphicTable table = (TileMetamorphicTable) getTileEntity(world, pos);
		
		BlockPos north = new BlockPos(pos.getX()+3, pos.getY()+1, pos.getZ());
		BlockPos east = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()+3);
		BlockPos south = new BlockPos(pos.getX()-3, pos.getY()+1, pos.getZ());
		BlockPos west = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()-3);

		world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, north.getX()+0.5, north.getY()+0.5, north.getZ()+0.5, 0, 0, 0, new int[0]);
		playSound(world, north);
		world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, east.getX()+0.5, east.getY()+0.5, east.getZ()+0.5,    0, 0, 0, new int[0]);
		playSound(world, east);
		world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, south.getX()+0.5, south.getY()+0.5, south.getZ()+0.5, 0, 0, 0, new int[0]);
		playSound(world, south);
		world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, west.getX()+0.5, west.getY()+0.5, west.getZ()+0.5,    0, 0, 0, new int[0]);
		playSound(world, west);

	}
	
	public static void playSound(World world, BlockPos origin){
		world.playSound(null, origin.getX() + 0.5, origin.getY() + 0.5 , origin.getZ() + 0.5, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}
	
	@Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.7D, 1.0D);
    }
	
	public static void setState(boolean active, World world, BlockPos pos){
		TileEntity tileentity = world.getTileEntity(pos);
		
		if (tileentity != null){
            tileentity.validate();
            world.setTileEntity(pos, tileentity);
        }
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state){
		return new TileMetamorphicTable();
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (stack.hasDisplayName()) {
			final TileMetamorphicTable tileEntity = (TileMetamorphicTable) getTileEntity(world, pos);
			if (tileEntity != null) {
				tileEntity.setDisplayName(stack.getDisplayName());
			}
		}
		createTileEntity(world, state);
	}
	
	public void tryToActivate(World world, BlockPos pos, EntityPlayer player, TileMetamorphicTable table){

		if(world.getBlockState(pos.north(3)) == ACTIVATION_BLOCK.getStateFromMeta(ACTIVATION_STATE) && 
				world.getBlockState(pos.east(3)) == ACTIVATION_BLOCK.getStateFromMeta(ACTIVATION_STATE) &&
				world.getBlockState(pos.south(3)) == ACTIVATION_BLOCK.getStateFromMeta(ACTIVATION_STATE) &&
				world.getBlockState(pos.west(3))== ACTIVATION_BLOCK.getStateFromMeta(ACTIVATION_STATE)
				){
			table.shouldGlitter = true;
			
		}
	}
	
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		
		if(!world.isRemote && !isBlocked(world, pos)) {
			final TileMetamorphicTable table = (TileMetamorphicTable) getTileEntity(world, pos);
			
			if(heldItem != null && heldItem.getItem() == Items.BOOK){
				table.renderMultiblock = true;
			}
			
			if(!table.isActivated && heldItem == null){
				tryToActivate(world, pos, player, table);
				if(!table.isActivated)
					return true;
			}
			
			ItemStack handStack = player.getHeldItem(hand);
			
			if(handStack != null && table.inventory.getStackInSlot(0) == null){

				table.inventory.insertItem(0, handStack, false);
				if(!table.hasValidRecipe()){
					table.inventory.extractItem(0, table.inventory.getStackInSlot(0).stackSize, false);
					return true;
				}
				
				ItemStack left = new ItemStack(handStack.getItem(), handStack.stackSize-1, handStack.getItemDamage());
				player.setHeldItem(hand, left);
				
			}
			if(handStack == null && table.inventory.getStackInSlot(0) != null){
				player.setHeldItem(hand, table.inventory.getStackInSlot(0));
				table.inventory.extractItem(0, table.inventory.getStackInSlot(0).stackSize, false);
				//System.out.println("Removing item");
			}
				
		}
		
		return true;
    }
	
	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player){
		return;
		
    }

	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean isBlocked(World worldIn, BlockPos pos) {
		return this.isBelowBlock(worldIn, pos) || this.isOcelotSittingOnChest(worldIn, pos);
	}

	/**
	 * Is the chest at the specified position below a solid block?
	 *
	 * @param worldIn The World
	 * @param pos     The position
	 * @return Is the chest below a solid block?
	 */
	private boolean isBelowBlock(World worldIn, BlockPos pos) {
		return !worldIn.isAirBlock(pos.up());
	}

	/**
	 * Is an Ocelot sitting on the chest at the specified position?
	 *
	 * @param worldIn The World
	 * @param pos     The position
	 * @return Is an Ocelot sitting on the chest?
	 */
	private boolean isOcelotSittingOnChest(World worldIn, BlockPos pos) {
		for (final EntityOcelot entityOcelot : worldIn.getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1))) {
			if (entityOcelot.isSitting()) {
				return true;
			}
		}

		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

	
	@SuppressWarnings("unchecked")
	@Nullable
	protected TileEntity getTileEntity(IBlockAccess world, BlockPos pos) {
		return world.getTileEntity(pos);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileMetamorphicTable();
	}
}
