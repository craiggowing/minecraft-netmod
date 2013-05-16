package craiggowing.mod.netmod;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;

public class ItemNetChicken extends ItemNetFull
{
    public ItemNetChicken(int par1) {
        super(par1);
        this.maxStackSize = 1;
        this.setIconIndex(1);
    }
	
    public String getItemNetName()
    {
    	return "Chicken";
    }

    protected EntityLiving getEntityToSpawn(World par2World)
    {
    	return new EntityChicken(par2World);
    }
}
