package me.dunescifye.aceitems.files;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.ConfigUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class JulyItemsConfig {

    public static ItemStack July24VillagerWand, July24Boots, July24Leggings, July24Chestplate, July24Helmet, July24JobsLantern,
        UltraJuly24JobsLantern, July24Saddle, July24SpawnerBundle, July24DirtWand, July24LessOPPickaxe, July24MelonWand,
        UltraJuly24MelonWand, July24PaintBrush;
    public static int July24VillagerWandUses, July24ArmorLaunchChance, July24ArmorLaunchStrength, July24ArmorJumpStrength,
        July24ArmorExtraDamageChance, July24SaddleLaunchCooldown, July24SaddleJumpBoostCooldown, July24LessOPPickaxeSquidSpawnerChance,
        July24LessOPPickaxeGlowSquidSpawnerChance, July24LessOPPickaxeExplosionRadius, July24LessOPPickaxeExplosionCooldown,
        July24MelonWandRadius, July24MelonWandCooldown, UltraJuly24MelonWandRadius, UltraJuly24MelonWandCooldown, July24SpawnerBundleUses,
        July24PaintBrushCooldown, July24PaintBrushUses;
    public static double July24ArmorExtraDamagePercent, July24ArmorFireDamageReductionPercent, July24SaddleLaunchStrength;
    public static String July24JobsLanternIncomeBoostPerm, UltraJuly24JobsLanternIncomeBoostPerm;
    public static List<String> July24SpawnerBundleMobTypes, July24LessOPPickaxeExplosionAllowedWorlds;

    public static void setup() {
        ConfigUtils julyitems = new ConfigUtils(AceItems.getInstance(), "items/julyitems.yml");
        FileConfiguration config = julyitems.getConfig();

        July24VillagerWandUses = ConfigUtils.setupConfig("July24VillagerWand.DefaultUses", config, 16, 1);

        AceItems.defaultValue.put(AceItems.keyUses, July24VillagerWandUses);
        July24VillagerWand = ConfigUtils.initializeItem("July24VillagerWand", config, AceItems.keyUses);

        July24ArmorLaunchChance = ConfigUtils.setupConfig("July24Armor.LaunchChance", config, 10, 1, List.of("Chance for attacker to be launched away."));
        July24ArmorLaunchStrength = ConfigUtils.setupConfig("July24Armor.LaunchStrength", config, 1, 0, List.of("How much attacker will be launched away."));
        July24ArmorJumpStrength = ConfigUtils.setupConfig("July24Armor.JumpStrength", config, 2, 0,  List.of("How high the player will jump up."));
        July24ArmorExtraDamageChance = ConfigUtils.setupConfig("July24Armor.ExtraDamageChance", config, 20, 1, List.of("Chance to deal extra damage."));
        July24ArmorExtraDamagePercent = ConfigUtils.setupConfig("July24Armor.ExtraDamagePercent", config, 1.3, 0, List.of("How much extra damage to deal. This number is multiplied by original damage. 1.3 = 30% more damage."));
        July24ArmorFireDamageReductionPercent = ConfigUtils.setupConfig("July24Armor.FireDamageReductionPercent", config, 0.5, 0, List.of("How much damage is reduced. This number is multiplied by original fire tick damage. 0.5 = 50% less damage"));
        July24Boots = ConfigUtils.initializeItem("July24Boots", config);
        July24Leggings = ConfigUtils.initializeItem("July24Leggings", config);
        July24Chestplate = ConfigUtils.initializeItem("July24Chestplate", config);
        July24Helmet = ConfigUtils.initializeItem("July24Helmet", config);

        AceItems.defaultValue.put(AceItems.keyString, "xp");
        July24JobsLantern = ConfigUtils.initializeItem("July24JobsLantern", config);
        July24JobsLanternIncomeBoostPerm = ConfigUtils.setupConfig("July24JobsLantern.IncomeBoostPerm", config, "jobs.boost.25", List.of("Permission node to give for income boost"));
        UltraJuly24JobsLantern = ConfigUtils.initializeItem("UltraJuly24JobsLantern", config);
        UltraJuly24JobsLanternIncomeBoostPerm = ConfigUtils.setupConfig("UltraJuly24JobsLantern.IncomeBoostPerm", config, "jobs.boost.50");

        July24Saddle = ConfigUtils.initializeItem("July24Saddle", config);
        July24SaddleLaunchStrength = ConfigUtils.setupConfig("July24Saddle.LaunchStrength", config, 1.5, 0, List.of("How high player will be launched up"));
        July24SaddleLaunchCooldown = ConfigUtils.setupConfig("July24Saddle.LaunchCooldown", config, 60, 0, List.of("Cooldown time for Launch ability in Seconds."));
        July24SaddleJumpBoostCooldown = ConfigUtils.setupConfig("July24Saddle.JumpBoostCooldown", config, 60, 0, List.of("Cooldown time for Jump Boost III Potion Effect in Seconds."));

        July24SpawnerBundleUses = ConfigUtils.setupConfig("July24SpawnerBundle.Uses", config, 3, 1, List.of("How many items can the spawner bundle be used before it breaks."));
        AceItems.defaultValue.put(AceItems.keyUses, July24SpawnerBundleUses);
        July24SpawnerBundle = ConfigUtils.initializeItem("July24SpawnerBundle", config, AceItems.keyUses);
        July24SpawnerBundleMobTypes = ConfigUtils.setupConfig("July24SpawnerBundle.MobTypes", config, List.of("COW", "PIG", "SHEEP"), List.of("List of available mob types to choose from"));

        AceItems.defaultValue.put(AceItems.keyBlockType, "PODZOL");
        AceItems.defaultValue.put(AceItems.keyBlockTypeLore, "Podzol");
        July24DirtWand = ConfigUtils.initializeItem("July24DirtWand", config, AceItems.keyBlockType, AceItems.keyBlockTypeLore);

        July24LessOPPickaxe = ConfigUtils.initializeItem("July24LessOPPickaxe", config);
        July24LessOPPickaxeSquidSpawnerChance = ConfigUtils.setupConfig("July24LessOPPickaxe.SquidSpawnerChance", config, 5000, 1, List.of("Chance for a squid spawner to be given"));
        July24LessOPPickaxeGlowSquidSpawnerChance = ConfigUtils.setupConfig("July24LessOPPickaxe.GlowSquidSpawnerChance", config, 5000, 1, List.of("Chance for a glow squid spawner to be given"));
        July24LessOPPickaxeExplosionRadius = ConfigUtils.setupConfig("July24LessOPPickaxe.ExplosionRadius", config, 20, 0, List.of("How big explosion should be"));
        July24LessOPPickaxeExplosionCooldown = ConfigUtils.setupConfig("July24LessOPPickaxe.ExplosionCooldown", config, 1200, 0, List.of("Cooldown for explosion in seconds"));
        July24LessOPPickaxeExplosionAllowedWorlds = ConfigUtils.setupConfig("July24LessOPPickaxe.ExplosionAllowedWorlds", config, List.of("resource_world", "resourceworld"), List.of("Worlds the explosion can be used in"));

        July24MelonWand = ConfigUtils.initializeItem("July24MelonWand", config);
        July24MelonWandRadius = ConfigUtils.setupConfig("July24MelonWand.Radius", config, 2, 0, List.of("Radius of melon growth"));
        July24MelonWandCooldown = ConfigUtils.setupConfig("July24MelonWand.Cooldown", config, 30, 0, List.of("Cooldown on melon growth ability in seconds."));
        UltraJuly24MelonWand = ConfigUtils.initializeItem("UltraJuly24MelonWand", config);
        UltraJuly24MelonWandRadius = ConfigUtils.setupConfig("UltraJuly24MelonWand.Radius", config, 5, 0, List.of("Radius of melon growth"));
        UltraJuly24MelonWandCooldown = ConfigUtils.setupConfig("UltraJuly24MelonWand.Cooldown", config, 15, 0, List.of("Cooldown on melon growth ability in seconds."));

        July24PaintBrushUses = ConfigUtils.setupConfig("July24PaintBrush.Uses", config, 300, 1, List.of("Uses until cooldown."));
        AceItems.defaultValue.put(AceItems.keyUses, July24PaintBrushUses);
        July24PaintBrush = ConfigUtils.initializeItem("July24PaintBrush", config, AceItems.keyUses, AceItems.keyBlockType, AceItems.keyBlockTypeLore);
        July24PaintBrushCooldown = ConfigUtils.setupConfig("July24PaintBrush.Cooldown", config, 600, 0, List.of("Cooldown time in seconds after uses."));

        julyitems.save();


    }

}
