package craiggowing.mod.netmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.BlockDispenser;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import net.minecraft.src.ModLoader;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;

@Mod(modid = "NetMod", name = "Net Mod", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class mod_NetMod
{
	@Instance("NetMod")
	public static mod_NetMod instance;
	
	@SidedProxy(clientSide="craiggowing.mod.netmod.ClientProxy", serverSide="craiggowing.mod.netmod.CommonProxy")
	public static CommonProxy proxy;

	public static Item itemNet;
	public static Item itemNetFull;
	public static Block blockNet;

	public static int mobTotal = 4;
	public static Class[] itemClasses = new Class[] {EntityChicken.class, EntitySheep.class, EntityPig.class, EntityCow.class};
	public static String[] itemNames = new String[] {"Chicken", "Sheep", "Pig", "Cow"};
	public static String[] mobNames = new String[] {"Chicken", "Sheep", "Pig", "Cow"};

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
	}
	
	@Init
	public void init(FMLInitializationEvent event) {
		blockNet = (new BlockNet(1000, 16)).setLightOpacity(1).setHardness(4.0F).setBlockName("blocknet");
    	itemNet = new ItemNet(4001).setItemName("itemnet");
    	itemNetFull = new ItemNetFull(4002).setItemName("itemnetfull");
		ModLoader.registerBlock(blockNet);
		ModLoader.addName(blockNet, "Net Block");
		ModLoader.addName(itemNet, "Net");
		ModLoader.addName(itemNetFull, "Net");
        ModLoader.addRecipe(new ItemStack(this.itemNet, 4), new Object[] { "DdD", "ddd", "DdD", 'D', Block.cobblestone, 'd', Block.fenceIron});
        ModLoader.addShapelessRecipe(new ItemStack(this.blockNet, 1), new Object[] {this.itemNet});
        ModLoader.addShapelessRecipe(new ItemStack(this.itemNet, 1), new Object[] {this.blockNet});
        EntityRegistry.registerModEntity(EntityItemNet.class, "Net", 1, instance, 64, 10, true);        
        RenderingRegistry.registerEntityRenderingHandler(EntityItemNet.class, new RenderNet(0));
        BlockDispenser.dispenseBehaviorRegistry.putObject(this.itemNet, new BehaviorNetDispense(ModLoader.getMinecraftServerInstance()));
        BlockDispenser.dispenseBehaviorRegistry.putObject(this.itemNetFull, new BehaviorNetFullDispense(ModLoader.getMinecraftServerInstance()));
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {
	}
}
