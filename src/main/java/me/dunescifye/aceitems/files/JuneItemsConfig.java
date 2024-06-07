package me.dunescifye.aceitems.files;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.ConfigUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;

public class JuneItemsConfig {

    private static ConfigUtils juneitems;
    private static FileConfiguration config;
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
        juneitems = new ConfigUtils(AceItems.getInstance(), "items/juneitems.yml");
        config = juneitems.getConfig();

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

        if (!config.isSet("UltraJune24MoreOPPickaxe")) {
            config.addDefault("UltraJune24MoreOPPickaxe.name", "&eUltra June 24 More OP Pickaxe");
            List<String> lore = Arrays.asList(
                "&7Chance to mine up water breathing potions,",
                "&7sea lanterns, fish, and guardian spawners.",
                "&eCrouch right click &7to toggle 1x1-5x5 mining."
            );
            config.addDefault("UltraJune24MoreOPPickaxe.lore", lore);
        }

        if (!config.isSet("June24LessOPPickaxe")) {
            config.addDefault("June24LessOPPickaxe.name", "&eJune 24 Less OP Pickaxe");
        }
        if (!config.isSet("June24MoreOPSword")) {
            config.addDefault("June24MoreOPSword.name", "&eJune 24 More OP Sword");
        }
        if (!config.isSet("June24QuartzWand")) {
            config.addDefault("June24QuartzWand.name", "&eJune24QuartzWand");
        }
        if (!config.isSet("UltraJune24QuartzWand")) {
            config.addDefault("UltraJune24QuartzWand.name", "&eUltraJune24QuartzWand");
        }
        if (!config.isSet("June24PropTool")) {
            config.addDefault("June24PropTool.name", "&eJune24PropTool");
        }
        if (!config.isSet("UltraJune24PropTool")) {
            config.addDefault("UltraJune24PropTool.name", "&eUltraJune24PropTool");
        }
        if (!config.isSet("June24GrowthStunter")) {
            config.addDefault("June24GrowthStunter.name", "&eJune24GrowthStunter");
        }
        if (!config.isSet("June24Axe")) {
            config.addDefault("June24Axe.name", "&eJune24Axe");
            List<String> lore = Arrays.asList(
                "&7Drops 8x of selected block drop",
                "&eCrouch right click &7to change block drop.",
                "&fCurrent block drop: Original"
            );
            config.addDefault("June24Axe.lore", lore);
        }
        if (!config.isSet("UltraJune24Axe")) {
            config.addDefault("UltraJune24Axe.name", "&eUltraJune24Axe");
            List<String> lore = Arrays.asList(
                "&7Drops 16x of selected block drop",
                "&eCrouch right click &7to change block drop.",
                "&fCurrent block drop: Original"
            );
            config.addDefault("UltraJune24Axe.lore", lore);
        }
        if (!config.isSet("June24TechnologicalAdvancement")) {
            config.addDefault("June24TechnologicalAdvancement.name", "&eJune24TechnologicalAdvancement");
        }
        if (!config.isSet("UltraJune24TechnologicalAdvancement")) {
            config.addDefault("UltraJune24TechnologicalAdvancement.name", "&eUltraJune24TechnologicalAdvancement");
        }
        if (!config.isSet("June24LessOPSword")) {
            config.addDefault("June24LessOPSword.name", "&eJune24LessOPSword");
        }
        if (!config.isSet("UltraJune24MoreOPSword")) {
            config.addDefault("UltraJune24MoreOPSword.name", "&eUltraJune24MoreOPSword");
            List<String> lore = Arrays.asList(
                "&7Right click to fire an armor penetrating",
                "&7guardian beam and inflict slowness.",
                "&7Shift left click to pull in players",
                "&7tagged by the beam."
            );
            config.addDefault("UltraJune24MoreOPSword.lore", lore);
        }
        if (!config.isSet("June24GuardianBeam")) {
            config.addDefault("June24GuardianBeam.name", "&eJune24GuardianBeam");
            List<String> lore = Arrays.asList(
                "&7Shift left click to shoot an armor piercing guardian",
                "&7beam and tags target. Right click to teleport to",
                "&7tagged player and give them blindness or slowness.",
                "&7Shift right click to get ocean monument items blocks."
            );
            config.addDefault("June24GuardianBeam.lore", lore);
        }
        if (!config.isSet("June24Boots")) {
            config.addDefault("June24Boots.name", "&eJune24Boots");
            config.addDefault("June24Boots.material", "LEATHER_BOOTS");
            config.addDefault("June24Boots.isNetherite", true);
            config.addDefault("June24Boots.armorColor", 10000);
        }
        if (!config.isSet("June24Leggings")) {
            config.addDefault("June24Leggings.name", "&eJune24Leggings");
        }
        if (!config.isSet("June24Chestplate")) {
            config.addDefault("June24Chestplate.name", "&eJune24Chestplate");
        }
        if (!config.isSet("June24Helmet")) {
            config.addDefault("June24Helmet.name", "&eJune24Helmet");
        }
        if (!config.isSet("June24Shovel")) {
            config.addDefault("June24Shovel.name", "&eJune24Shovel");
        }
        if (!config.isSet("June24XPBeacon")) {
            config.addDefault("June24XPBeacon.name", "&eJune24XPBeacon");
        }
        if (!config.isSet("UltraJune24XPBeacon")){
            config.addDefault("UltraJune24XPBeacon.name", "&eUltraJune24XPBeacon");
        }
        if (!config.isSet("June24BlockWand")){
            config.addDefault("June24BlockWand.name", "&eJune24BlockWand");
        }
        if (!config.isSet("UltraJune24BlockWand")){
            config.addDefault("UltraJune24BlockWand.name", "&eUltraJune24BlockWand");
        }
        if (!config.isSet("June24LeafBlower")){
            config.addDefault("June24LeafBlower.name", "&eJune24LeafBlower");
        }
        if (!config.isSet("June24Hoe")){
            config.addDefault("June24Hoe.name", "&eJune24Hoe");
        }
        if (!config.isSet("June24Amulet")){
            config.addDefault("June24Amulet.name", "&eJune24Amulet");
        }
        if (!config.isSet("UltraJune24Amulet")){
            config.addDefault("UltraJune24Amulet.name", "&eUltraJune24Amulet");
        }
        if (!config.isSet("June24Shield")){
            config.addDefault("June24Shield.name", "&eJune24Shield");
        }
        if (!config.isSet("June24Elytra")){
            config.addDefault("June24Elytra.name", "&eJune24Elytra");
        }
        if (!config.isSet("June24Wings")){
            config.addDefault("June24Wings.name", "&eJune24Wings");
        }
        if (!config.isSet("June24Bow")){
            config.addDefault("June24Bow.name", "&eJune24Bow");
        }
        if (!config.isSet("June24Crossbow")){
            config.addDefault("June24Crossbow.name", "&eJune24Crossbow");
        }
        if (!config.isSet("June24Trident")){
            config.addDefault("June24Trident.name", "&eJune24Trident");
        }
        if (!config.isSet("June24FishingRod")){
            config.addDefault("June24FishingRod.name", "&eJune24FishingRod");
        }
        if (!config.isSet("June24FishingRod.SeaLanternChance")){
            config.addDefault("June24FishingRod.SeaLanternChance", 100);
        }
        if (!config.isSet("June24FishingRod.FishSpawnEggChance")){
            config.addDefault("June24FishingRod.FishSpawnEggChance", 100);
        }
        if (!config.isSet("June24FishingRod.FroglightChance")){
            config.addDefault("June24FishingRod.FroglightChance", 100);
        }
        if (!config.isSet("UltraJune24FishingRod")){
            config.addDefault("UltraJune24FishingRod.name", "&eUltraJune24FishingRod");
        }
        if (!config.isSet("UltraJune24FishingRod.SeaLanternChance")){
            config.addDefault("UltraJune24FishingRod.SeaLanternChance", 100);
        }
        if (!config.isSet("UltraJune24FishingRod.FishSpawnEggChance")){
            config.addDefault("UltraJune24FishingRod.FishSpawnEggChance", 100);
        }
        if (!config.isSet("UltraJune24FishingRod.FroglightChance")){
            config.addDefault("UltraJune24FishingRod.FroglightChance", 100);
        }
        if (!config.isSet("UltraJune24FishingRod.GuardianSpawnEggChance")){
            config.addDefault("UltraJune24FishingRod.GuardianSpawnEggChance", 100);
        }
        if (!config.isSet("June24Elytra.TurtleEggChance")){
            config.addDefault("June24Elytra.TurtleEggChance", 500);
        }
        if (!config.isSet("June24Elytra.AxolotlSpawnEggChance")){
            config.addDefault("June24Elytra.AxolotlSpawnEggChance", 500);
        }
        if (!config.isSet("June24MoreOPPickaxe.WaterBreathingPotionChance")){
            config.addDefault("June24MoreOPPickaxe.WaterBreathingPotionChance", 2000);
        }
        if (!config.isSet("June24MoreOPPickaxe.SeaLanternChance")){
            config.addDefault("June24MoreOPPickaxe.SeaLanternChance", 2000);
        }
        if (!config.isSet("June24MoreOPPickaxe.FishSpawnerChance")){
            config.addDefault("June24MoreOPPickaxe.FishSpawnerChance", 6000);
        }
        if (!config.isSet("June24LessOPPickaxe.FishSpawnerChance")){
            config.addDefault("June24LessOPPickaxe.FishSpawnerChance", 6000);
        }
        if (!config.isSet("June24LessOPPickaxe.SeaLanternChance")){
            config.addDefault("June24LessOPPickaxe.SeaLanternChance", 3000);
        }
        if (!config.isSet("June24LessOPPickaxe.WaterBreathingPotionChance")){
            config.addDefault("June24LessOPPickaxe.WaterBreathingPotionChance", 3000);
        }
        if (!config.isSet("June24LessOPPickaxe.GuardianSpawnerChance")){
            config.addDefault("June24LessOPPickaxe.GuardianSpawnerChance", 12000);
        }
        if (!config.isSet("June24Shovel.GlowstoneChance")){
            config.addDefault("June24Shovel.GlowstoneChance", 3000);
        }
        if (!config.isSet("June24Shovel.FroglightChance")){
            config.addDefault("June24Shovel.FroglightChance", 3000);
        }
        if (!config.isSet("June24Shovel.SeaLanternChance")){
            config.addDefault("June24Shovel.SeaLanternChance", 3000);
        }
        if (!config.isSet("June24LavaWaterBucket")){
            config.addDefault("June24LavaWaterBucket.name", "June24LavaWaterBucket");
        }
        if (!config.isSet("UltraJune24LavaWaterBucket")){
            config.addDefault("UltraJune24LavaWaterBucket.name", "UltraJune24LavaWaterBucket");
        }

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
