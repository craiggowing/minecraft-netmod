package craiggowing.mod.netmod;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockNet extends Block
{
    private Random random = new Random();

    public BlockNet(int par1)
    {
        super(par1, Material.web);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.setInWeb();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    public int getRenderType()
    {
        return 6;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }

    protected boolean canSilkHarvest()
    {
        return true;
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        float x_off = this.random.nextFloat() * 0.8F + 0.1F;
        float y_off = this.random.nextFloat() * 0.8F + 0.1F;
        float z_off = this.random.nextFloat() * 0.8F + 0.1F;
        EntityItem ent;
        ent = new EntityItem(par1World, (double)((float)par2 + x_off), (double)((float)par3 + y_off), (double)((float)par4 + z_off), new ItemStack(Block.cobblestone.blockID, 1, 0));
        ent.motionX = (double)((float)this.random.nextGaussian() * 0.05f);
        ent.motionY = (double)((float)this.random.nextGaussian() * 0.05f + 0.2f);
        ent.motionZ = (double)((float)this.random.nextGaussian() * 0.05f);
        par1World.spawnEntityInWorld(ent);

        x_off = this.random.nextFloat() * 0.8F + 0.1F;
        y_off = this.random.nextFloat() * 0.8F + 0.1F;
        z_off = this.random.nextFloat() * 0.8F + 0.1F;
        ent = new EntityItem(par1World, (double)((float)par2 + x_off), (double)((float)par3 + y_off), (double)((float)par4 + z_off), new ItemStack(Block.fenceIron.blockID, 1, 0));
        ent.motionX = (double)((float)this.random.nextGaussian() * 0.05f);
        ent.motionY = (double)((float)this.random.nextGaussian() * 0.05f + 0.2f);
        ent.motionZ = (double)((float)this.random.nextGaussian() * 0.05f);
        par1World.spawnEntityInWorld(ent);

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
         this.blockIcon = par1IconRegister.registerIcon("craiggowing/mod/netmod:netblock");
    }

    public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5)
    {
        if (par1EntityPlayer.canHarvestBlock(Block.web))
        {
            return 0.04f; // This appears to be inverse... 1.0f == not hard, 0.001f == very hard
        }
        else
        {
            return super.getPlayerRelativeBlockHardness(par1EntityPlayer, par2World, par3, par4, par5);
        }
    }
}
