package craiggowing.mod.netmod;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.Icon;

@SideOnly(Side.CLIENT)
public class RenderNet extends Render
{
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.loadTexture("/craiggowing/mod/netmod/textures/items/net.png");  // FIXME: Is this needed any more?
        Tessellator tessellator = Tessellator.instance;

        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

        double size = 1.0D;

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.25D - size, 0.5D - size, 0.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(-0.25D + size, 0.5D - size, 0.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(-0.25D + size, 0.5D + size, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(-0.25D - size, 0.5D + size, 0.0D, 0.0D, 0.0D);
        tessellator.draw();

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }
}
