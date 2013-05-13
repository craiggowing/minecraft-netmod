package craiggowing.mod.netmod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemNet extends Item
{
    public ItemNet(int par1) {
        super(par1);
        this.maxStackSize = 64;
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setIconIndex(0);
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }
        
        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        
        if (!par2World.isRemote)
        {
        	par2World.spawnEntityInWorld(new EntityItemNet(par2World, par3EntityPlayer));
        }
        
        return par1ItemStack;
    }
    
    public String getTextureFile ()
    {
        return CommonProxy.ITEMS_PNG;
    }
   
}
