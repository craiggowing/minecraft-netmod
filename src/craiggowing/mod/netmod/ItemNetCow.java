package craiggowing.mod.netmod;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityCow;

public class ItemNetCow extends ItemNetFull
{
    public ItemNetCow(int par1) {
        super(par1);
        this.maxStackSize = 1;
        this.setIconIndex(4);
    }
	
    public String getItemNetName()
    {
    	return "Cow";
    }

    protected EntityLiving getEntityToSpawn(World par2World)
    {
    	return new EntityCow(par2World);
    }
}
