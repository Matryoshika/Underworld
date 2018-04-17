package se.Matryoshika.Underworld.Content.Rendering;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.TileEntity.TileUnderworldEnderPortal;

public class TERenderEnderPortal extends TileEntitySpecialRenderer<TileUnderworldEnderPortal>{
	
    private IModel modelBall;
    private IBakedModel bakedModelBall;
    private static final ResourceLocation END_SKY_TEXTURE = new ResourceLocation("textures/environment/end_sky.png");
    private static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");
    
    private IBakedModel getBakedModelBall() {
    	 
        if (bakedModelBall == null) {
            try {
                modelBall = ModelLoaderRegistry.getModel(new ResourceLocation(Underworld.MODID, "block/blockunderworldenderportal"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            bakedModelBall = modelBall.bake(TRSRTransformation.identity(), DefaultVertexFormats.BLOCK,
                    location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
        }
        return bakedModelBall;
    }

    @Override
    public void render(TileUnderworldEnderPortal te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
    	GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
    	GlStateManager.translate(x, y, z);
    	
    	
    	
    	
    	GlStateManager.translate(0, te.timer/500, 0);
    	
    	
    	long angle = (System.currentTimeMillis() / 40) % 360;
    	GlStateManager.rotate(angle, 1, 1, 1);
    	renderBody(te, x, y, z, partialTicks, destroyStage);
    	GlStateManager.rotate(-angle, 1, 1, 1);
    	
    	GlStateManager.translate(0, -te.timer/500, 0);
    	
    	GlStateManager.translate(-x, -y, -z);
    	GlStateManager.popMatrix();
        GlStateManager.popAttrib();
        
    }
    
    public void renderBody(TileUnderworldEnderPortal te, double x, double y, double z, float ticks, int stage){
    	GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
 
        RenderHelper.disableStandardItemLighting();
        this.bindTexture(END_SKY_TEXTURE);
 
        GlStateManager.enableBlend();
        
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_DST_COLOR);
        
        World world = getWorld();
        GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());
 
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(
                world,
                getBakedModelBall(),
                world.getBlockState(te.getPos()),
                te.getPos(),
                Tessellator.getInstance().getBuffer(), false);
        tessellator.draw();
 
        GlStateManager.disableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.translate(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ());
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

}
