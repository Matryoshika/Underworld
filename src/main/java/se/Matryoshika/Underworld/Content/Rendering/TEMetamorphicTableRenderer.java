package se.Matryoshika.Underworld.Content.Rendering;
 
import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.Blocks.BlockMetamorphicTable;
import se.Matryoshika.Underworld.Content.TileEntity.TileMetamorphicTable;
import se.Matryoshika.Underworld.Utils.ConfigHandler;
 
@SideOnly(Side.CLIENT)
public class TEMetamorphicTableRenderer extends TileEntitySpecialRenderer<TileMetamorphicTable> {
	
	float i = 0.01f;
 
    private IModel model;
    private IBakedModel bakedModel;
    private Block ACTIVATION_BLOCK;
    private int ACTIVATION_STATE;
 
    private IBakedModel getBakedModel() {
 
        if (bakedModel == null) {
            try {
                model = ModelLoaderRegistry.getModel(new ResourceLocation(Underworld.MODID, "block/blockMetamorphicTable"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            bakedModel = model.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM,
                    location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
        }
        return bakedModel;
    }
 
    @Override
    public void render(TileMetamorphicTable te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
    	GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        
        BlockMetamorphicTable table = (BlockMetamorphicTable)te.getWorld().getBlockState(te.getPos()).getBlock();
        if(ACTIVATION_BLOCK == null){
        	ACTIVATION_STATE = ConfigHandler.metamorphicTableActivationMeta;
        	ACTIVATION_BLOCK = table.ACTIVATION_BLOCK;
        }

        GlStateManager.translate(x, y, z);
        GlStateManager.disableRescaleNormal();

        
        if(te.isActivated)
        	renderFrame(te);
        renderBody(te);
        if(te.getWorld() != null && te.isActivated)
        	renderItem(te);
        if(te.shouldGlitter && te.risingFrame <= 15f)
        	renderFancy(te);
        if(!te.isActivated && !te.shouldGlitter && getWorld().getBlockState(te.getPos().down()).getBlock() == Blocks.REDSTONE_BLOCK)
        	renderMultiblock(te);

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
       
    }
    
    private void renderMultiblock(TileMetamorphicTable te){
    	GlStateManager.pushMatrix();
    	GlStateManager.enableBlend();
    	long angle = (System.currentTimeMillis() / 20) % 360;
    	GlStateManager.translate(0.5, 0.5, 0.5);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GlStateManager.translate(3, 0, 0);
        if(te.getWorld().isAirBlock(te.getPos().east(3))){
        	GlStateManager.scale(0.5, 0.5, 0.5);
        	GlStateManager.rotate(angle, 0, 1, 0);
        	Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(ACTIVATION_BLOCK, 1, ACTIVATION_STATE), ItemCameraTransforms.TransformType.NONE);
        	GlStateManager.rotate(-angle, 0, 1, 0);
        	GlStateManager.scale(2, 2, 2);
        }
        GlStateManager.translate(-6, 0, 0);
        if(te.getWorld().isAirBlock(te.getPos().west(3))){
        	GlStateManager.scale(0.5, 0.5, 0.5);
        	GlStateManager.rotate(angle, 0, 1, 0);
        	Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(ACTIVATION_BLOCK, 1, ACTIVATION_STATE), ItemCameraTransforms.TransformType.NONE);
        	GlStateManager.rotate(-angle, 0, 1, 0);
        	GlStateManager.scale(2, 2, 2);
        }
        GlStateManager.translate(3, 0, 3);
        if(te.getWorld().isAirBlock(te.getPos().south(3))){
        	GlStateManager.scale(0.5, 0.5, 0.5);
        	GlStateManager.rotate(angle, 0, 1, 0);
        	Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(ACTIVATION_BLOCK, 1, ACTIVATION_STATE), ItemCameraTransforms.TransformType.NONE);
        	GlStateManager.rotate(-angle, 0, 1, 0);
        	GlStateManager.scale(2, 2, 2);
        }
        GlStateManager.translate(0, 0, -6);
        if(te.getWorld().isAirBlock(te.getPos().north(3))){
        	GlStateManager.scale(0.5, 0.5, 0.5);
        	GlStateManager.rotate(angle, 0, 1, 0);
        	Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(ACTIVATION_BLOCK, 1, ACTIVATION_STATE), ItemCameraTransforms.TransformType.NONE);
        	GlStateManager.rotate(-angle, 0, 1, 0);
        	GlStateManager.scale(2, 2, 2);
        }
        GlStateManager.translate(0, 0, 3);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);
    	GlStateManager.popMatrix();
    }
    
    private void renderFancy(TileMetamorphicTable te){
    	GlStateManager.pushMatrix();
        GlStateManager.translate(0.5, 0.50 + te.risingFrame/100, 0.5);
        GlStateManager.scale(0.25, 0.25, 0.25);
 
        Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(Blocks.END_PORTAL_FRAME), ItemCameraTransforms.TransformType.NONE);
        GlStateManager.scale(4, 4, 4);
        GlStateManager.translate(-0.5, -0.65, -0.5);
        GlStateManager.popMatrix();
    }
   
   
    private void renderBody(TileMetamorphicTable te) {
    	GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
 
        RenderHelper.disableStandardItemLighting();
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
 
        //GlStateManager.enableBlend();
        
        World world = getWorld();
        GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());
 
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(
                world,
                getBakedModel(),
                world.getBlockState(te.getPos()),
                te.getPos(),
                Tessellator.getInstance().getBuffer(), false);
        tessellator.draw();
 
        //GlStateManager.disableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.translate(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ());
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
   
   
    private void renderItem(TileMetamorphicTable te){
        BlockMetamorphicTable block = (BlockMetamorphicTable) te.getWorld().getBlockState(te.getPos()).getBlock();
        if(block.isBlocked(getWorld(), te.getPos()))
            return;
           
        EntityPlayer player = te.getWorld().getClosestPlayer(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), 32, false);
        if(player != null){
            ItemStack stack = player.getHeldItemMainhand();
            if (stack != null && te.hasValidInput(stack)) {
            	
            	
            	
                GlStateManager.pushMatrix();
                
                GlStateManager.translate(0.5, 1, 0.5);
                GlStateManager.scale(0.25, 0.25, 0.25);
                
                long angle = (System.currentTimeMillis() / 20) % 360;
                GlStateManager.rotate(angle, 0, 1, 0);
               
                GlStateManager.enableBlend();
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
 
                Minecraft.getMinecraft().getRenderItem().renderItem(te.getOutputFromInput(stack), ItemCameraTransforms.TransformType.NONE);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);
                
                GlStateManager.scale(4, 4, 4);
                GlStateManager.translate(-0.5, -1, -0.5);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
                
                
                
            }
        }
    }
   
   
    private void renderFrame(TileMetamorphicTable te){
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5, 0.65, 0.5);
        GlStateManager.scale(0.25, 0.25, 0.25);
 
        Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(Blocks.END_PORTAL_FRAME), ItemCameraTransforms.TransformType.NONE);
        GlStateManager.scale(4, 4, 4);
        GlStateManager.translate(-0.5, -0.65, -0.5);
        GlStateManager.popMatrix();
    }
}