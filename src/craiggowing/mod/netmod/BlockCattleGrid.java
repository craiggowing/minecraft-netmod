package craiggowing.mod.netmod;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockCattleGrid extends BlockDirectional
{
    public BlockCattleGrid(int par1, int par2)
    {
        super(par1, par2, Material.lava); // This is the key to the block, it influences AI to avoid it
        float var3 = 0.5F;
        float var4 = 0.015625F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public String getTextureFile()
    {
        return CommonProxy.ITEMS_PNG;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 23;
    }
}
