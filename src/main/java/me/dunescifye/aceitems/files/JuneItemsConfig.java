package me.dunescifye.aceitems.files;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.ConfigUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class JuneItemsConfig {

    public static ItemStack June24LavaWaterBucket, UltraJune24LavaWaterBucket, June24MoreOPPickaxe, UltraJune24MoreOPPickaxe, June24LessOPPickaxe, June24MoreOPSword,
            June24QuartzWand, UltraJune24QuartzWand, June24PropTool, UltraJune24PropTool, June24GrowthStunter, June24Axe,
            UltraJune24Axe, June24TechnologicalAdvancement, UltraJune24TechnologicalAdvancement, June24LessOPSword,
            UltraJune24MoreOPSword, June24GuardianBeam, June24Boots, June24Leggings, June24Chestplate, June24Helmet,
            June24Shovel, June24XPBeacon, UltraJune24XPBeacon, June24BlockWand, UltraJune24BlockWand, June24LeafBlower,
            June24Hoe, June24Amulet, UltraJune24Amulet, June24Shield, June24Elytra, June24Wings, June24Bow, June24Crossbow,
            June24Trident, June24FishingRod, UltraJune24FishingRod;
    public static int June24FishingRodSeaLanternChance;
    public static int June24FishingRodFishSpawnEggChance;
    public static int June24FishingRodFroglightChance;
    public static int UltraJune24FishingRodSeaLanternChance;
    public static int UltraJune24FishingRodFishSpawnEggChance;
    public static int UltraJune24FishingRodFroglightChance;
    public static int UltraJune24FishingRodGuardianSpawnEggChance;
    public static int June24ElytraTurtleEggChance;
    public static int June24ElytraAxolotlSpawnEggChance;
    public static int June24MoreOPPickaxeWaterBreathingPotionChance;
    public static int June24MoreOPPickaxeSeaLanternChance;
    public static int June24MoreOPPickaxeFishSpawnerChance;
    public static int June24LessOPPickaxeWaterBreathingPotionChance;
    public static int June24LessOPPickaxeSeaLanternChance;
    public static int June24LessOPPickaxeFishSpawnerChance;
    public static int June24LessOPPickaxeGuardianSpawnerChance;
    public static int June24ShovelGlowstoneChance;
    public static int June24ShovelFroglightChance;
    public static int June24ShovelSeaLanternChance;

    public static void setup(){
        ConfigUtils juneitems = new ConfigUtils(AceItems.getInstance(), "items/juneitems.yml");
        FileConfiguration config = juneitems.getConfig();

        if (!config.isSet("June24MoreOPPickaxe")){
            config.addDefault("June24MoreOPPickaxe.name", "&eJune 24 More OP Pickaxe");
            List<String> lore = Arrays.asList(
                "&7Chance to mine up water breathing potions,",
                "&7sea lanterns, and fish spawners.",
                "&eCrouch right click &7to toggle 3x3 mining."
            );
            config.addDefault("June24MoreOPPickaxe.lore", lore);
            List<String> enchantments = Arrays.asList(
                "fortune:3",
                "efficiency:5"
            );
            config.addDefault("June24MoreOPPickaxe.enchantments", enchantments);
            config.addDefault("June24MoreOPPickaxe.customModelData", 10000);
            config.addDefault("June24MoreOPPickaxe.material", "NETHERITE_PICKAXE");
            config.addDefault("June24MoreOPPickaxe.unbreakable", true);
            List<String> itemFlags = Arrays.asList(
                "HIDE_ATTRIBUTES",
                "HIDE_UNBREAKABLE"
            );
            config.addDefault("June24MoreOPPickaxe.itemFlags", itemFlags);
            List<String> disabledWorlds = Arrays.asList(
                "world",
                "spawn"
            );
            config.addDefault("June24MoreOPPickaxe.disabledWorlds", disabledWorlds);
        }
        //Items setup
        June24LavaWaterBucket = ConfigUtils.initializeItem("June24LavaWaterBucket", config);
        UltraJune24LavaWaterBucket = ConfigUtils.initializeItem("UltraJune24LavaWaterBucket", config);
        June24MoreOPPickaxe = ConfigUtils.initializeItem("June24MoreOPPickaxe", config, AceItems.keyRadius, AceItems.keyRadiusLore);
        UltraJune24MoreOPPickaxe = ConfigUtils.initializeItem("UltraJune24MoreOPPickaxe", config, AceItems.keyRadius, AceItems.keyRadiusLore);
        June24LessOPPickaxe = ConfigUtils.initializeItem("June24LessOPPickaxe", config, AceItems.keyRadius, AceItems.keyRadiusLore);
        June24MoreOPSword = ConfigUtils.initializeItem("June24MoreOPSword", config, AceItems.keyKills);
        AceItems.defaultValue.put(AceItems.keyState, "QUARTZ_BLOCK");
        AceItems.defaultValue.put(AceItems.keyStateLore, "Quartz Block");
        AceItems.defaultValue.put(AceItems.keyUses, 300);
        June24QuartzWand = ConfigUtils.initializeItem("June24QuartzWand", config, AceItems.keyState, AceItems.keyStateLore, AceItems.keyUses);
        UltraJune24QuartzWand = ConfigUtils.initializeItem("UltraJune24QuartzWand", config, AceItems.keyState, AceItems.keyStateLore, AceItems.keyUses);
        June24PropTool = ConfigUtils.initializeItem("June24PropTool", config);
        UltraJune24PropTool = ConfigUtils.initializeItem("UltraJune24PropTool", config);
        June24GrowthStunter = ConfigUtils.initializeItem("June24GrowthStunter", config);
        AceItems.defaultValue.put(AceItems.keyState, "");
        AceItems.defaultValue.put(AceItems.keyStateLore, "Original");
        June24Axe = ConfigUtils.initializeItem("June24Axe", config);
        UltraJune24Axe = ConfigUtils.initializeItem("UltraJune24Axe", config);
        June24TechnologicalAdvancement = ConfigUtils.initializeItem("June24TechnologicalAdvancement", config);
        UltraJune24TechnologicalAdvancement = ConfigUtils.initializeItem("UltraJune24TechnologicalAdvancement", config);
        June24LessOPSword = ConfigUtils.initializeItem("June24LessOPSword", config);
        UltraJune24MoreOPSword = ConfigUtils.initializeItem("UltraJune24MoreOPSword", config, AceItems.keyKills);
        June24GuardianBeam = ConfigUtils.initializeItem("June24GuardianBeam", config);
        June24Boots = ConfigUtils.initializeItem("June24Boots", config, AceItems.keyInt);
        June24Leggings = ConfigUtils.initializeItem("June24Leggings", config);
        June24Chestplate = ConfigUtils.initializeItem("June24Chestplate", config);
        June24Helmet = ConfigUtils.initializeItem("June24Helmet", config);
        June24Shovel = ConfigUtils.initializeItem("June24Shovel", config, AceItems.keyRadius, AceItems.keyRadiusLore);
        June24XPBeacon = ConfigUtils.initializeItem("June24XPBeacon", config);
        UltraJune24XPBeacon = ConfigUtils.initializeItem("UltraJune24XPBeacon", config);
        June24BlockWand = ConfigUtils.initializeItem("June24BlockWand", config);
        UltraJune24BlockWand = ConfigUtils.initializeItem("UltraJune24BlockWand", config);
        AceItems.defaultValue.put(AceItems.keyState, "OAK");
        AceItems.defaultValue.put(AceItems.keyStateLore, "Oak");
        June24LeafBlower = ConfigUtils.initializeItem("June24LeafBlower", config, AceItems.keyState, AceItems.keyStateLore, AceItems.keyUses);
        June24Hoe = ConfigUtils.initializeItem("June24Hoe", config);
        June24Amulet = ConfigUtils.initializeItem("June24Amulet", config);
        UltraJune24Amulet = ConfigUtils.initializeItem("UltraJune24Amulet", config);
        June24Shield = ConfigUtils.initializeItem("June24Shield", config);
        June24Elytra = ConfigUtils.initializeItem("June24Elytra", config);
        June24Wings = ConfigUtils.initializeItem("June24Wings", config);
        June24Bow = ConfigUtils.initializeItem("June24Bow", config);
        June24Crossbow = ConfigUtils.initializeItem("June24Crossbow", config);
        June24Trident = ConfigUtils.initializeItem("June24Trident", config);
        June24FishingRod = ConfigUtils.initializeItem("June24FishingRod", config);
        UltraJune24FishingRod = ConfigUtils.initializeItem("UltraJune24FishingRod", config);

        config.options().copyDefaults(true);

        juneitems.save();

        June24FishingRodSeaLanternChance = config.getInt("June24FishingRod.SeaLanternChance");
        June24FishingRodFishSpawnEggChance = config.getInt("June24FishingRod.FishSpawnEggChance");
        June24FishingRodFroglightChance = config.getInt("June24FishingRod.FroglightChance");
        UltraJune24FishingRodSeaLanternChance = config.getInt("UltraJune24FishingRod.SeaLanternChance");
        UltraJune24FishingRodFishSpawnEggChance = config.getInt("UltraJune24FishingRod.FishSpawnEggChance");
        UltraJune24FishingRodFroglightChance = config.getInt("UltraJune24FishingRod.FroglightChance");
        UltraJune24FishingRodGuardianSpawnEggChance = config.getInt("UltraJune24FishingRod.GuardianSpawnEggChance");
        June24ElytraTurtleEggChance = config.getInt("June24Elytra.TurtleEggChance");
        June24ElytraAxolotlSpawnEggChance = config.getInt("June24Elytra.AxolotlSpawnEggChance");
        June24MoreOPPickaxeSeaLanternChance = config.getInt("June24MoreOPPickaxe.SeaLanternChance");
        June24MoreOPPickaxeWaterBreathingPotionChance = config.getInt("June24MoreOPPickaxe.WaterBreathingPotionChance");
        June24MoreOPPickaxeFishSpawnerChance = config.getInt("June24MoreOPPickaxe.FishSpawnerChance");
        June24LessOPPickaxeWaterBreathingPotionChance = config.getInt("June24MoreOPPickaxe.WaterBreathingPotionChance");
        June24LessOPPickaxeGuardianSpawnerChance = config.getInt("June24MoreOPPickaxe.GuardianSpawnerChance");
        June24LessOPPickaxeSeaLanternChance = config.getInt("June24MoreOPPickaxe.SeaLanternChance");
        June24LessOPPickaxeFishSpawnerChance = config.getInt("June24MoreOPPickaxe.FishSpawnerChance");
        June24ShovelSeaLanternChance = config.getInt("June24Shovel.SeaLanternChance");
        June24ShovelGlowstoneChance = config.getInt("June24Shovel.GlowstoneChance");
        June24ShovelFroglightChance = config.getInt("June24Shovel.FroglightChance");
        
        juneitems.save();
    }

}
