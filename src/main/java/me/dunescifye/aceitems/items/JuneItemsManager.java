package me.dunescifye.aceitems.items;

import me.dunescifye.aceitems.files.JuneItemsConfig;
import me.dunescifye.aceitems.utils.ConfigUtils;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.dunescifye.aceitems.utils.Utils.*;

public class JuneItemsManager {

    public static ItemStack June24LavaWaterBucket, UltraJune24LavaWaterBucket, June24MoreOPPickaxe, UltraJune24MoreOPPickaxe, June24LessOPPickaxe, June24MoreOPSword,
        June24QuartzWand, UltraJune24QuartzWand, June24PropTool, UltraJune24PropTool, June24GrowthStunter, June24Axe,
        UltraJune24Axe, June24TechnologicalAdvancement, UltraJune24TechnologicalAdvancement, June24LessOPSword,
        UltraJune24MoreOPSword, June24GuardianBeam, June24Boots, June24Leggings, June24Chestplate, June24Helmet,
        June24Shovel, June24XPBeacon, UltraJune24XPBeacon, June24BlockWand, UltraJune24BlockWand, June24LeafBlower,
        June24Hoe, June24Amulet, UltraJune24Amulet, June24Shield, June24Elytra, June24Wings, June24Bow, June24Crossbow,
        June24Trident, June24FishingRod, UltraJune24FishingRod;

    public static List<String> June24LavaWaterBucketDisabledWorlds, UltraJune24LavaWaterBucketDisabledWorlds, June24MoreOPPickaxeDisabledWorlds, UltraJune24MoreOPPickaxeDisabledWorlds, June24LessOPPickaxeDisabledWorlds, June24MoreOPSwordDisabledWorlds,
        June24QuartzWandDisabledWorlds, UltraJune24QuartzWandDisabledWorlds, June24PropToolDisabledWorlds, UltraJune24PropToolDisabledWorlds, June24GrowthStunterDisabledWorlds, June24AxeDisabledWorlds,
        UltraJune24AxeDisabledWorlds, June24TechnologicalAdvancementDisabledWorlds, UltraJune24TechnologicalAdvancementDisabledWorlds, June24LessOPSwordDisabledWorlds,
        UltraJune24MoreOPSwordDisabledWorlds, June24GuardianBeamDisabledWorlds, June24BootsDisabledWorlds, June24LeggingsDisabledWorlds, June24ChestplateDisabledWorlds, June24HelmetDisabledWorlds,
        June24ShovelDisabledWorlds, June24XPBeaconDisabledWorlds, UltraJune24XPBeaconDisabledWorlds, June24BlockWandDisabledWorlds, UltraJune24BlockWandDisabledWorlds, June24LeafBlowerDisabledWorlds,
        June24HoeDisabledWorlds, June24AmuletDisabledWorlds, UltraJune24AmuletDisabledWorlds, June24ShieldDisabledWorlds, June24ElytraDisabledWorlds, June24WingsDisabledWorlds, June24BowDisabledWorlds, June24CrossbowDisabledWorlds, June24TridentDisabledWorlds,
        June24FishingRodDisabledWorlds, UltraJune24FishingRodDisabledWorlds;
    
    private static ConfigUtils configUtils;
    private static FileConfiguration config;
    public static void init(){
        configUtils = JuneItemsConfig.getConfig();
        config = configUtils.getConfig();
        createJune24MoreOPPickaxe();
        createUltraJune24MoreOPPickaxe();
        createJune24LessOPPickaxe();
        createJune24MoreOPSword();
        createJune24QuartzWand();
        createUltraJune24QuartzWand();
        createJune24PropTool();
        createUltraJune24PropTool();
        createJune24GrowthStunter();
        createJune24Axe();
        createUltraJune24Axe();
        createJune24TechnologicalAdvancement();
        createUltraJune24TechnologicalAdvancement();
        createJune24LessOPSword();
        createUltraJune24MoreOPSword();
        createJune24GuardianBeam();
        createJune24Boots();
        createJune24Leggings();
        createJune24Chestplate();
        createJune24Helmet();
        createJune24Shovel();
        createJune24XPBeacon();
        createUltraJune24XPBeacon();
        createJune24BlockWand();
        createUltraJune24BlockWand();
        createJune24LeafBlower();
        createJune24Hoe();
        createJune24Amulet();
        createUltraJune24Amulet();
        createJune24Shield();
        createJune24Elytra();
        createJune24Wings();
        createJune24Bow();
        createJune24Crossbow();
        createJune24Trident();
        createJune24FishingRod();
        createUltraJune24FishingRod();
        createJune24LavaWaterBucket();
        createUltraJune24LavaWaterBucket();
    }

    private static void createJune24LavaWaterBucket(){
        June24LavaWaterBucket = initializeItem("June24LavaWaterBucket", configUtils);
        June24LavaWaterBucketDisabledWorlds = config.getStringList("June24LavaWaterBucket.disabledWorlds");
    }
    private static void createUltraJune24LavaWaterBucket(){
        UltraJune24LavaWaterBucket = initializeItem("UltraJune24LavaWaterBucket", configUtils);
        UltraJune24LavaWaterBucketDisabledWorlds = config.getStringList("UltraJune24LavaWaterBucket.disabledWorlds");
    }

    private static void createJune24MoreOPPickaxe(){
        ItemStack item = initializeItem("June24MoreOPPickaxe", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyRadius, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(keyRadiusLore, PersistentDataType.STRING, "1x1");

        item.setItemMeta(meta);
        June24MoreOPPickaxe = item;
        June24MoreOPPickaxeDisabledWorlds = config.getStringList("June24MoreOPPickaxe.disabledWorlds");
    }

    private static void createUltraJune24MoreOPPickaxe(){
        ItemStack item = initializeItem("UltraJune24MoreOPPickaxe", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyRadius, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(keyRadiusLore, PersistentDataType.STRING, "1x1");

        item.setItemMeta(meta);
        UltraJune24MoreOPPickaxe = item;
        UltraJune24MoreOPPickaxeDisabledWorlds = config.getStringList("UltraJune24MoreOPPickaxe.disabledWorlds");
    }

    private static void createJune24LessOPPickaxe(){
        ItemStack item = initializeItem("June24LessOPPickaxe", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyRadius, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(keyRadiusLore, PersistentDataType.STRING, "1x1");

        item.setItemMeta(meta);
        June24LessOPPickaxe = item;
        June24LessOPPickaxeDisabledWorlds = config.getStringList("June24LessOPPickaxe.disabledWorlds");
    }
    private static void createJune24MoreOPSword(){
        ItemStack item = initializeItem("June24MoreOPSword", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyKills, PersistentDataType.INTEGER, 0);

        item.setItemMeta(meta);
        June24MoreOPSword = item;
        June24MoreOPSwordDisabledWorlds = config.getStringList("June24MoreOPSword.disabledWorlds");
    }
    private static void createJune24QuartzWand(){
        ItemStack item = initializeItem("June24QuartzWand", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyBlockType, PersistentDataType.STRING, "QUARTZ_BLOCK");
        meta.getPersistentDataContainer().set(keyBlockTypeLore, PersistentDataType.STRING, "Quartz Block");
        meta.getPersistentDataContainer().set(keyUses, PersistentDataType.INTEGER, 300);

        item.setItemMeta(meta);
        June24QuartzWand = item;
        June24QuartzWandDisabledWorlds = config.getStringList("June24QuartzWand.disabledWorlds");
    }


    private static void createUltraJune24QuartzWand(){
        ItemStack item = initializeItem("UltraJune24QuartzWand", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyBlockType, PersistentDataType.STRING, "QUARTZ_BLOCK");
        meta.getPersistentDataContainer().set(keyBlockTypeLore, PersistentDataType.STRING, "Quartz Block");

        item.setItemMeta(meta);
        UltraJune24QuartzWand = item;
        UltraJune24QuartzWandDisabledWorlds = config.getStringList("UltraJune24QuartzWand.disabledWorlds");
    }

    private static void createJune24PropTool(){
        June24PropTool = initializeItem("June24PropTool", configUtils);
        June24PropToolDisabledWorlds = config.getStringList("June24PropTool.disabledWorlds");
    }

    private static void createUltraJune24PropTool(){
        UltraJune24PropTool = initializeItem("UltraJune24PropTool", configUtils);
        UltraJune24PropToolDisabledWorlds = config.getStringList("UltraJune24PropTool.disabledWorlds");
    }
    private static void createJune24GrowthStunter(){
        June24GrowthStunter = initializeItem("June24GrowthStunter", configUtils);
        June24GrowthStunterDisabledWorlds = config.getStringList("June24GrowthStunter.disabledWorlds");
    }

    
    private static void createJune24Axe(){
        ItemStack item = initializeItem("June24Axe", configUtils);
        ItemMeta meta = item.getItemMeta();
        
        meta.getPersistentDataContainer().set(keyBlockType, PersistentDataType.STRING, "");
        meta.getPersistentDataContainer().set(keyBlockTypeLore, PersistentDataType.STRING, "Original");

        item.setItemMeta(meta);
        June24Axe = item;
        
        June24AxeDisabledWorlds = config.getStringList("June24Axe.disabledWorlds");
    }
    private static void createUltraJune24Axe(){
        ItemStack item = initializeItem("UltraJune24Axe", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyBlockType, PersistentDataType.STRING, "");
        meta.getPersistentDataContainer().set(keyBlockTypeLore, PersistentDataType.STRING, "Original");

        item.setItemMeta(meta);
        UltraJune24Axe = item;
        UltraJune24AxeDisabledWorlds = config.getStringList("UltraJune24Axe.disabledWorlds");
    }
    private static void createJune24TechnologicalAdvancement(){
        June24TechnologicalAdvancement = initializeItem("June24TechnologicalAdvancement", configUtils);
        June24TechnologicalAdvancementDisabledWorlds = config.getStringList("June24TechnologicalAdvancement.disabledWorlds");
    }
    private static void createUltraJune24TechnologicalAdvancement(){
        UltraJune24TechnologicalAdvancement = initializeItem("UltraJune24TechnologicalAdvancement", configUtils);
        UltraJune24TechnologicalAdvancementDisabledWorlds = config.getStringList("UltraJune24TechnologicalAdvancement.disabledWorlds");
    }
    private static void createJune24LessOPSword(){
        June24LessOPSword = initializeItem("June24LessOPSword", configUtils);
        June24LessOPSwordDisabledWorlds = config.getStringList("June24LessOPSword.disabledWorlds");
    }
    private static void createUltraJune24MoreOPSword(){
        ItemStack item = initializeItem("UltraJune24MoreOPSword", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyKills, PersistentDataType.INTEGER, 0);

        item.setItemMeta(meta);
        UltraJune24MoreOPSword = item;
        UltraJune24MoreOPSwordDisabledWorlds = config.getStringList("UltraJune24MoreOPSword.disabledWorlds");
    }
    private static void createJune24GuardianBeam(){
        June24GuardianBeam = initializeItem("June24GuardianBeam", configUtils);
        June24GuardianBeamDisabledWorlds = config.getStringList("June24GuardianBeam.disabledWorlds");
    }
    private static void createJune24Boots(){
        ItemStack item = initializeItem("June24Boots", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyInt, PersistentDataType.INTEGER, 0);

        item.setItemMeta(meta);
        June24Boots = item;
        June24BootsDisabledWorlds = config.getStringList("June24Boots.disabledWorlds");
    }
    private static void createJune24Leggings(){
        June24Leggings = initializeItem("June24Leggings", configUtils);
        June24LeggingsDisabledWorlds = config.getStringList("June24Leggings.disabledWorlds");
    }
    private static void createJune24Chestplate(){
        June24Chestplate = initializeItem("June24Chestplate", configUtils);
        June24ChestplateDisabledWorlds = config.getStringList("June24Chestplate.disabledWorlds");
    }
    private static void createJune24Helmet(){
        June24Helmet = initializeItem("June24Helmet", configUtils);
        June24HelmetDisabledWorlds = config.getStringList("June24Helmet.disabledWorlds");
    }
    private static void createJune24Shovel(){
        ItemStack item = initializeItem("June24Shovel", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyRadius, PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(keyRadiusLore, PersistentDataType.STRING, "1x1");

        item.setItemMeta(meta);
        June24Shovel = item;
        June24ShovelDisabledWorlds = config.getStringList("June24Shovel.disabledWorlds");
    }
    private static void createJune24XPBeacon(){
        June24XPBeacon = initializeItem("June24XPBeacon", configUtils);
        June24XPBeaconDisabledWorlds = config.getStringList("June24XPBeacon.disabledWorlds");
    }
    private static void createUltraJune24XPBeacon(){
        UltraJune24XPBeacon = initializeItem("UltraJune24XPBeacon", configUtils);
        UltraJune24XPBeaconDisabledWorlds = config.getStringList("UltraJune24XPBeacon.disabledWorlds");
    }
    private static void createJune24BlockWand(){
        June24BlockWand = initializeItem("June24BlockWand", configUtils);
        June24BlockWandDisabledWorlds = config.getStringList("June24BlockWand.disabledWorlds");
    }
    private static void createUltraJune24BlockWand(){
        UltraJune24BlockWand = initializeItem("UltraJune24BlockWand", configUtils);
        UltraJune24BlockWandDisabledWorlds = config.getStringList("UltraJune24BlockWand.disabledWorlds");
    }
    private static void createJune24LeafBlower(){
        ItemStack item = initializeItem("June24LeafBlower", configUtils);
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(keyBlockType, PersistentDataType.STRING, "OAK");
        meta.getPersistentDataContainer().set(keyBlockTypeLore, PersistentDataType.STRING, "Oak");

        item.setItemMeta(meta);
        June24LeafBlower = item;
        June24LeafBlowerDisabledWorlds = config.getStringList("June24LeafBlower.disabledWorlds");
    }
    private static void createJune24Hoe(){
        June24Hoe = initializeItem("June24Hoe", configUtils);
        June24HoeDisabledWorlds = config.getStringList("June24Hoe.disabledWorlds");
    }
    private static void createJune24Amulet(){
        June24Amulet = initializeItem("June24Amulet", configUtils);
        June24AmuletDisabledWorlds = config.getStringList("June24Amulet.disabledWorlds");
    }
    private static void createUltraJune24Amulet(){
        UltraJune24Amulet = initializeItem("UltraJune24Amulet", configUtils);
        UltraJune24AmuletDisabledWorlds = config.getStringList("UltraJune24Amulet.disabledWorlds");
    }
    private static void createJune24Shield(){
        June24Shield = initializeItem("June24Shield", configUtils);
        June24ShieldDisabledWorlds = config.getStringList("June24Shield.disabledWorlds");
    }
    private static void createJune24Elytra(){
        June24Elytra = initializeItem("June24Elytra", configUtils);
        June24ElytraDisabledWorlds = config.getStringList("June24Elytra.disabledWorlds");
    }
    private static void createJune24Wings(){
        June24Wings = initializeItem("June24Wings", configUtils);
        June24WingsDisabledWorlds = config.getStringList("June24Wings.disabledWorlds");
    }
    private static void createJune24Bow(){
        June24Bow = initializeItem("June24Bow", configUtils);
        June24BowDisabledWorlds = config.getStringList("June24Bow.disabledWorlds");
    }
    private static void createJune24Crossbow(){
        June24Crossbow = initializeItem("June24Crossbow", configUtils);
        June24CrossbowDisabledWorlds = config.getStringList("June24Crossbow.disabledWorlds");
    }
    private static void createJune24Trident(){
        June24Trident = initializeItem("June24Trident", configUtils);
        June24TridentDisabledWorlds = config.getStringList("June24Trident.disabledWorlds");
    }
    private static void createJune24FishingRod(){
        June24FishingRod = initializeItem("June24FishingRod", configUtils);
        June24FishingRodDisabledWorlds = config.getStringList("June24FishingRod.disabledWorlds");
    }
    private static void createUltraJune24FishingRod(){
        UltraJune24FishingRod = initializeItem("UltraJune24FishingRod", configUtils);
        UltraJune24FishingRodDisabledWorlds = config.getStringList("UltraJune24FishingRod.disabledWorlds");
    }

    private static final Map<String, ItemStack> items = new HashMap<>();

    static {
        items.put("June24MoreOPPickaxe", June24MoreOPPickaxe);
        items.put("UltraJune24MoreOPPickaxe", UltraJune24MoreOPPickaxe);
        items.put("June24LessOPPickaxe", June24LessOPPickaxe);
        items.put("June24MoreOPSword", June24MoreOPSword);
        items.put("June24QuartzWand", June24QuartzWand);
        items.put("UltraJune24QuartzWand", UltraJune24QuartzWand);
        items.put("June24PropTool", June24PropTool);
        items.put("UltraJune24PropTool", UltraJune24PropTool);
        items.put("June24GrowthStunter", June24GrowthStunter);
        items.put("June24Axe", June24Axe);
        items.put("UltraJune24Axe", UltraJune24Axe);
        items.put("June24TechnologicalAdvancement", June24TechnologicalAdvancement);
        items.put("UltraJune24TechnologicalAdvancement", UltraJune24TechnologicalAdvancement);
        items.put("June24LessOPSword", June24LessOPSword);
        items.put("UltraJune24MoreOPSword", UltraJune24MoreOPSword);
        items.put("June24GuardianBeam", June24GuardianBeam);
        items.put("June24Boots", June24Boots);
        items.put("June24Leggings", June24Leggings);
        items.put("June24Chestplate", June24Chestplate);
        items.put("June24Helmet", June24Helmet);
    }

    public static ItemStack getItem(String itemID){
        return items.get(itemID);
    }


}
