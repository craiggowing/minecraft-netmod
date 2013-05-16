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
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.boss.*;

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

	public static int mobTotal = 28;
	public static Class[] itemClasses = new Class[] {
			EntityChicken.class,
			EntitySheep.class,
			EntityPig.class,
			EntityCow.class,
			EntityBat.class,
			EntityMooshroom.class,
			EntityOcelot.class,
			EntitySquid.class,
			EntityVillager.class,
			EntityWolf.class,
			EntityBlaze.class,
			EntityCaveSpider.class,
			EntityCreeper.class,
			EntityEnderman.class,
			EntityGhast.class,
			EntityGiantZombie.class,
			EntityIronGolem.class,
			EntityMagmaCube.class,
			EntityPigZombie.class,
			EntitySilverfish.class,
			EntitySkeleton.class,
			EntitySlime.class,
			EntitySnowman.class,
			EntitySpider.class,
			EntityWitch.class,
			EntityZombie.class,
			EntityDragon.class,
			EntityWither.class			
		};
	public static String[] itemNames = new String[] {
			"Chicken",
			"Sheep",
			"Pig",
			"Cow",
			"Bat",
			"Mushroom Cow",
			"Ocelot",
			"Squid",
			"Villager",
			"Wolf",
			"Blaze",
			"Cave Spider",
			"Creeper",
			"Enderman",
			"Ghast",
			"Giant",
			"Iron Golem",
			"Magma Cube",
			"Zombie Pigman",
			"Silverfish",
			"Skeleton",
			"Slime",
			"Snowman",
			"Spider",
			"Witch",
			"Zombie",
			"Ender Dragon",
			"Wither"
		};
	public static String[] mobNames = new String[] {
			"Chicken",
			"Sheep",
			"Pig",
			"Cow",
			"Bat",
			"MushroomCow",
			"Ozelot",
			"Squid",
			"Villager",
			"Wolf",
			"Blaze",
			"CaveSpider",
			"Creeper",
			"Enderman",
			"Ghast",
			"Giant",
			"VillagerGolem",
			"LavaSlime",
			"PigZombie",
			"Silverfish",
			"Skeleton",
			"Slime",
			"SnowMan",
			"Spider",
			"Witch",
			"Zombie",
			"Pig", // Ender Dragon does not work well with Spawn Blocks
			"Pig" // Wither does not work well with Spawn Blocks
		};

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
	}
	
	@Init
	public void init(FMLInitializationEvent event) {
		blockNet = (new BlockNet(1000, 1)).setLightOpacity(1).setHardness(4.0F).setBlockName("blocknet");
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
