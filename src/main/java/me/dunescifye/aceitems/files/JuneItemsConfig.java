package me.dunescifye.aceitems.files;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.ConfigUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

import static me.dunescifye.aceitems.utils.Utils.*;

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

        June24LavaWaterBucket = ConfigUtils.initializeItem("June24LavaWaterBucket", juneitems);
        UltraJune24LavaWaterBucket = ConfigUtils.initializeItem("UltraJune24LavaWaterBucket", juneitems);
        June24MoreOPPickaxe = ConfigUtils.initializeItem("June24MoreOPPickaxe", juneitems, AceItems.keyRadius, AceItems.keyRadiusLore);
        UltraJune24MoreOPPickaxe = ConfigUtils.initializeItem("UltraJune24MoreOPPickaxe", juneitems, AceItems.keyRadius, AceItems.keyRadiusLore);
        June24LessOPPickaxe = ConfigUtils.initializeItem("June24LessOPPickaxe", juneitems, AceItems.keyRadius, AceItems.keyRadiusLore);
        June24MoreOPSword = ConfigUtils.initializeItem("June24MoreOPSword", juneitems, AceItems.keyKills);
        AceItems.defaultValue.put(AceItems.keyBlockType, "QUARTZ_BLOCK");
        AceItems.defaultValue.put(AceItems.keyBlockTypeLore, "Quartz Block");
        AceItems.defaultValue.put(AceItems.keyUses, 300);
        June24QuartzWand = ConfigUtils.initializeItem("June24QuartzWand", juneitems, AceItems.keyBlockType, AceItems.keyBlockTypeLore, AceItems.keyUses);
        UltraJune24QuartzWand = ConfigUtils.initializeItem("UltraJune24QuartzWand", juneitems, AceItems.keyBlockType, AceItems.keyBlockTypeLore, AceItems.keyUses);
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
    }

    public static ConfigUtils getConfig(){
        return juneitems;
    }

}
