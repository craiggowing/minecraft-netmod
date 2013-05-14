package craiggowing.mod.netmod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;

@Mod(modid = "NetMod", name = "Net Mod", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class mod_NetMod
{
	@Instance("NetMod")
	public static mod_NetMod instance;
	
	@SidedProxy(clientSide="craiggowing.mod.netmod.ClientProxy", serverSide="craiggowing.mod.netmod.CommonProxy")
	public static CommonProxy proxy;

	public static Item itemNet;
	public static Item itemNetChicken;
	public static Item itemNetSheep;
	public static Item itemNetPig;
	public static Item itemNetCow;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
	}
	
	@Init
	public void init(FMLInitializationEvent event) {
    	itemNet = new ItemNet(4001).setItemName("itemnet");
    	itemNetChicken = new ItemNetChicken(4010).setItemName("itemnetchicken");
    	itemNetSheep = new ItemNetSheep(4011).setItemName("itemnetsheep");
    	itemNetPig = new ItemNetPig(4012).setItemName("itemnetpig");
    	itemNetCow = new ItemNetCow(4013).setItemName("itemnetcow");
        LanguageRegistry.addName(itemNet, "Net");
        LanguageRegistry.addName(itemNetChicken, "Net");
        LanguageRegistry.addName(itemNetSheep, "Net");
        LanguageRegistry.addName(itemNetPig, "Net");
        LanguageRegistry.addName(itemNetCow, "Net");
        EntityRegistry.registerModEntity(EntityItemNet.class, "Net", 1, instance, 64, 10, true);        
        RenderingRegistry.registerEntityRenderingHandler(EntityItemNet.class, new RenderNet(0));
        GameRegistry.addRecipe(new ItemStack(this.itemNet, 4), new Object[] { "DdD", "ddd", "DdD", 'D', Block.cobblestone, 'd', Block.fenceIron});
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {
	}
}
