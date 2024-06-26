package me.dunescifye.aceitems.files;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.ConfigUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class JulyItemsConfig {

    public static ItemStack July24VillagerWand, UltraJuly24VillagerWand, July24Boots, July24Leggings, July24Chestplate, July24Helmet, July24JobsLantern,
        UltraJuly24JobsLantern, July24Saddle, July24SpawnerBundle, July24DirtWand, July24LessOPPickaxe, July24MelonWand,
        UltraJuly24MelonWand, July24PaintBrush, UltraJuly24PaintBrush, July24GrapplingHook, July24PocketBeacon, UltraJuly24PocketBeacon,
        July24AIDisabler, UltraJuly24AIDisabler, July24MoreOPSword, July24LessOPSWord, July24MoreOPPickaxe, UltraJuly24MoreOPPickaxe,
        July24Shield, July24Hoe, UltraJuly24Hoe, July24Grill, UltraJuly24Grill, July24Fireball, July24FishingRod, July24Bow, July24XBow,
        July24Elytra, July24BeachMaker, UltraJuly24BeachMaker, July24SpeedVoucher, July24SlimeWand, July24Trident, July24Axe, July24Shovel;

    public static int July24ArmorLaunchChance, July24ArmorLaunchStrength, July24ArmorJumpStrength,
        July24ArmorExtraDamageChance, July24SaddleLaunchCooldown, July24SaddleJumpBoostCooldown, July24LessOPPickaxeSquidSpawnerChance,
        July24LessOPPickaxeGlowSquidSpawnerChance, July24LessOPPickaxeExplosionRadius, July24LessOPPickaxeExplosionCooldown,
        July24MelonWandRadius, July24MelonWandCooldown, UltraJuly24MelonWandRadius, UltraJuly24MelonWandCooldown, July24SpawnerBundleUses,
        July24PaintBrushCooldown, July24PaintBrushUses, July24GrapplingHookSelfLaunchStrength, July24GrapplingHookSelfLaunchCooldown,
        July24GrapplingHookTargetLaunchStrength, July24GrapplingHookTargetLaunchCooldown, July24AIDisablerUses, July24MoreOPSwordSlayerPointsCommandChance,
        July24MoreOPSwordKillAnnouncementChance, July24MoreOPSwordBlazeSpawnerChance, July24MoreOPSwordMagmaCubeSpawnerChance,
        July24LessOPSWordPlayerRandomPotionChance, July24LessOPSWordEntityRandomPotionChance, July24HoeNoSeedReplantChance, July24HoeBlazeSpawnEggChance,
        UltraJuly24HoeNoSeedReplantChance, UltraJuly24HoeBlazeSpawnEggChance, UltraJuly24HoeGuardianSpawnEggChance, July24FishingRodFishKeyChance,
        July24FishingRodSalmonSpawnEggChance, July24FishingRodAxolotlSpawnEggChance, July24FishingRodTurtleSpawnEggChance, July24FishingRodSquidSpawnEggChance,
        July24BeachMakerCooldown, July24SlimeWandDefaultUses, July24TridentPVPKeyChance, July24TridentFishKeyChance, July24AxeFireworkDamage,
        July24AxeIgnoreArmorChance, July24ShovelBedrockChance, July24ShovelReinforcedDeepslateChance, July24VillagerWandCureCooldown, July24VillagerWandZombifyCooldown,
        UltraJuly24VillagerWandEmeraldCooldown;
    public static double July24ArmorExtraDamagePercent, July24ArmorFireDamageReductionPercent, July24SaddleLaunchStrength, July24TridentExtraDamagePercent;
    public static String July24JobsLanternIncomeBoostPerm, UltraJuly24JobsLanternIncomeBoostPerm, July24MoreOPSwordSlayerPointsCommand,
        July24MoreOPSwordKillAnnouncement, July24FishingRodFishKeyCommand, July24SpeedVoucherPerm, July24SlimeWandMissingSlimeBallsMessage,
        July24SlimeWandAddedSlimeBallsMessage, July24SlimeWandOutOfUsesMessage, July24TridentPVPKeyID, July24TridentFishKeyID;
    public static List<String> July24SpawnerBundleMobTypes, July24LessOPPickaxeExplosionAllowedWorlds, July24PocketBeaconWhitelistedWorlds,
        UltraJuly24PocketBeaconWhitelistedWorlds;

    public static void setup() {
        ConfigUtils julyitems = new ConfigUtils(AceItems.getInstance(), "items/julyitems.yml");
        FileConfiguration config = julyitems.getConfig();

        July24VillagerWandCureCooldown = ConfigUtils.setupConfig("July24VillagerWand.CureCooldown", config, 300, 0);
        July24VillagerWandZombifyCooldown = ConfigUtils.setupConfig("July24VillagerWand.ZombifyCooldown", config, 300, 0);
        July24VillagerWand = ConfigUtils.initializeItem("July24VillagerWand", config);
        UltraJuly24VillagerWand = ConfigUtils.initializeItem("UltraJuly24VillagerWand", config);
        UltraJuly24VillagerWandEmeraldCooldown = ConfigUtils.setupConfig("UltraJuly24VillagerWand.EmeraldCooldown", config, 600, 0);

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

        AceItems.defaultValue.put(AceItems.keyState, "PODZOL");
        AceItems.defaultValue.put(AceItems.keyStateLore, "Podzol");
        July24DirtWand = ConfigUtils.initializeItem("July24DirtWand", config, AceItems.keyState, AceItems.keyStateLore);

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
        July24PaintBrush = ConfigUtils.initializeItem("July24PaintBrush", config, AceItems.keyUses, AceItems.keyState, AceItems.keyStateLore);
        July24PaintBrushCooldown = ConfigUtils.setupConfig("July24PaintBrush.Cooldown", config, 600, 0, List.of("Cooldown time in seconds after uses."));
        UltraJuly24PaintBrush = ConfigUtils.initializeItem("UltraJuly24PaintBrush", config, AceItems.keyState, AceItems.keyStateLore);

        July24GrapplingHook = ConfigUtils.initializeItem("July24GrapplingHook", config);
        July24GrapplingHookSelfLaunchStrength = ConfigUtils.setupConfig("July24GrapplingHook.SelfLaunchStrength", config, 2, 0, List.of("How strong the player gets pulled towards bob"));
        July24GrapplingHookSelfLaunchCooldown = ConfigUtils.setupConfig("July24GrapplingHook.SelfLaunchCooldown", config, 5, 0, List.of("Cooldown on self launch ability in seconds"));
        July24GrapplingHookTargetLaunchStrength = ConfigUtils.setupConfig("July24GrapplingHook.TargetLaunchStrength", config, 2, 0, List.of("How strong the hit player gets launched into the air"));
        July24GrapplingHookTargetLaunchCooldown = ConfigUtils.setupConfig("July24GrapplingHook.TargetLaunchCooldown", config, 300, 0, List.of("Cooldown on launching hit player into the air"));

        July24PocketBeacon = ConfigUtils.initializeItem("July24PocketBeacon", config);
        UltraJuly24PocketBeacon = ConfigUtils.initializeItem("UltraJuly24PocketBeacon", config);
        July24PocketBeaconWhitelistedWorlds = ConfigUtils.setupConfig("July24PocketBeacon.WhitelistedWorlds", config, List.of("resource_world", "resourceworld", "world", "spawn"), List.of("Worlds the explosion can be used in"));
        UltraJuly24PocketBeaconWhitelistedWorlds = ConfigUtils.setupConfig("UltraJuly24PocketBeacon.WhitelistedWorlds", config, List.of("resource_world", "resourceworld", "world", "spawn"), List.of("Worlds the explosion can be used in"));

        AceItems.defaultValue.put(AceItems.keyState, "disabled");
        AceItems.defaultValue.put(AceItems.keyStateLore, "Disabled");
        July24AIDisablerUses = ConfigUtils.setupConfig("July24AIDisabler.Uses", config, 1000, 1, List.of("How many default uses this item has"));
        AceItems.defaultValue.put(AceItems.keyUses, July24AIDisablerUses);
        July24AIDisabler = ConfigUtils.initializeItem("July24AIDisabler", config, AceItems.keyState, AceItems.keyStateLore, AceItems.keyUses);
        UltraJuly24AIDisabler = ConfigUtils.initializeItem("UltraJuly24AIDisabler", config, AceItems.keyState, AceItems.keyStateLore);

        July24MoreOPSword = ConfigUtils.initializeItem("July24MoreOPSword", config);
        July24MoreOPSwordSlayerPointsCommand = ConfigUtils.setupConfig("July24MoreOPSword.SlayerPointsCommand", config, "slayerpoints give %player% 1", List.of("Command used to give player slayer points"));
        July24MoreOPSwordSlayerPointsCommandChance = ConfigUtils.setupConfig("July24MoreOPSword.SlayerPointsCommandChance", config, 10, 1, List.of("Chance for slayer points command to be ran when killing mob"));
        July24MoreOPSwordKillAnnouncement = ConfigUtils.setupConfig("July24MoreOPSword.KillAnnouncement", config, "&a%player% has killed %target%!", List.of("Message sent to everyone. %player% for killer and %target% for victim."));
        July24MoreOPSwordKillAnnouncementChance = ConfigUtils.setupConfig("July24MoreOPSword.KillAnnouncementChance", config, 10, 1, List.of("Chance for kill announcement message"));
        July24MoreOPSwordBlazeSpawnerChance = ConfigUtils.setupConfig("July24MoreOPSword.BlazeSpawnerChance", config, 3000, 1, List.of("Chance for blaze spawner to be given"));
        July24MoreOPSwordMagmaCubeSpawnerChance = ConfigUtils.setupConfig("July24MoreOPSword.MagmaCubeSpawnerChance", config, 6000, 1, List.of("Chance for magma cube spawner to be given"));

        July24LessOPSWord = ConfigUtils.initializeItem("July24LessOPSWord", config);
        July24LessOPSWordPlayerRandomPotionChance = ConfigUtils.setupConfig("July24LessOPSWord.PlayerRandomPotionChance", config, 100, 1, List.of("Chance for random potion to drop when killing players"));
        July24LessOPSWordEntityRandomPotionChance = ConfigUtils.setupConfig("July24LessOPSWord.EntityRandomPotionChance", config, 1000, 1, List.of("Chance for random potion to drop when killing entities"));

        July24MoreOPPickaxe = ConfigUtils.initializeItem("July24MoreOPPickaxe", config, AceItems.keyRadius, AceItems.keyRadiusLore);
        UltraJuly24MoreOPPickaxe = ConfigUtils.initializeItem("UltraJuly24MoreOPPickaxe", config, AceItems.keyRadius, AceItems.keyRadiusLore);

        July24Shield = ConfigUtils.initializeItem("July24Shield", config);

        July24Hoe = ConfigUtils.initializeItem("July24Hoe", config, AceItems.keyRadius, AceItems.keyRadiusLore);
        UltraJuly24Hoe = ConfigUtils.initializeItem("UltraJuly24Hoe", config, AceItems.keyRadius, AceItems.keyRadiusLore);
        July24HoeNoSeedReplantChance = ConfigUtils.setupConfig("July24Hoe.NoSeedReplantChance", config, 6, 1, List.of("Chance for replant without seeds."));
        July24HoeBlazeSpawnEggChance = ConfigUtils.setupConfig("July24Hoe.BlazeSpawnEggChance", config, 1000, 1, List.of("Chance for blaze spawn egg to be given."));
        UltraJuly24HoeNoSeedReplantChance = ConfigUtils.setupConfig("UltraJuly24Hoe.NoSeedReplantChance", config, 3, 1, List.of("Chance for replant without seeds."));
        UltraJuly24HoeBlazeSpawnEggChance = ConfigUtils.setupConfig("UltraJuly24Hoe.BlazeSpawnEggChance", config, 800, 1, List.of("Chance for blaze spawn egg to be given."));
        UltraJuly24HoeGuardianSpawnEggChance = ConfigUtils.setupConfig("UltraJuly24Hoe.GuardianSpawnEggChance", config, 1000, 1, List.of("Chance for guardian spawn egg to be given."));

        July24Grill = ConfigUtils.initializeItem("July24Grill", config);
        UltraJuly24Grill = ConfigUtils.initializeItem("UltraJuly24Grill", config);

        July24Fireball = ConfigUtils.initializeItem("July24Fireball", config);

        July24FishingRod = ConfigUtils.initializeItem("July24FishingRod", config);
        July24FishingRodFishKeyChance = ConfigUtils.setupConfig("July24FishingRod.FishKeyChance", config, 100, 1, List.of("Chance for fish key to be fished up"));
        July24FishingRodFishKeyCommand = ConfigUtils.setupConfig("July24FishingRod.FishKeyCommand", config, "crates key give %player% Fish 1", List.of("Command to be ran for Fish key. %player% for player name"));
        July24FishingRodSalmonSpawnEggChance = ConfigUtils.setupConfig("July24FishingRod.SalmonSpawnEggChance", config, 50, 1, List.of("Chance for salmon spawn egg to be fished up"));
        July24FishingRodAxolotlSpawnEggChance = ConfigUtils.setupConfig("July24FishingRod.AxolotlSpawnEggChance", config, 50, 1, List.of("Chance for axolotl spawn egg to be fished up"));
        July24FishingRodTurtleSpawnEggChance = ConfigUtils.setupConfig("July24FishingRod.TurtleSpawnEggChance", config, 50, 1, List.of("Chance for turtle spawn egg to be fished up"));
        July24FishingRodSquidSpawnEggChance = ConfigUtils.setupConfig("July24FishingRod.SquidSpawnEggChance", config, 50, 1, List.of("Chance for squid spawn egg to be fished up"));

        July24Bow = ConfigUtils.initializeItem("July24Bow", config);
        July24XBow = ConfigUtils.initializeItem("July24XBow", config);

        AceItems.defaultValue.put(AceItems.keyState, "16733525");
        AceItems.defaultValue.put(AceItems.keyStateLore, "Red");
        July24Elytra = ConfigUtils.initializeItem("July24Elytra", config, AceItems.keyState, AceItems.keyStateLore);

        July24BeachMaker = ConfigUtils.initializeItem("July24BeachMaker", config);
        UltraJuly24BeachMaker = ConfigUtils.initializeItem("UltraJuly24BeachMaker", config);
        July24BeachMakerCooldown = ConfigUtils.setupConfig("July24BeachMaker.Cooldown", config, 30, 0, List.of("Cooldown for ability in seconds."));

        July24SpeedVoucher = ConfigUtils.initializeItem("July24SpeedVoucher", config);
        July24SpeedVoucherPerm = ConfigUtils.setupConfig("July24SpeedVoucher.Perm", config, "essentials.flyspeed.4", List.of("Permission node to be given when held"));

        July24SlimeWandDefaultUses = ConfigUtils.setupConfig("July24SlimeWand.DefaultUses", config, 64, 0, List.of("Default number of uses this item has when obtaining it."));
        AceItems.defaultValue.put(AceItems.keyUses, July24SlimeWandDefaultUses);
        July24SlimeWand = ConfigUtils.initializeItem("July24SlimeWand", config, AceItems.keyUses);
        config.setComments("July24SlimeWand", List.of("Use the default uses number in your lore (default 64) and it will auto update."));
        July24SlimeWandMissingSlimeBallsMessage = ConfigUtils.setupConfig("July24SlimeWand.MissingSlimeBallsMessage", config, "&cYou don't have any slime balls to reload!", List.of("Error message for when player tries reloading item but doesn't have any slime balls."));
        July24SlimeWandAddedSlimeBallsMessage = ConfigUtils.setupConfig("July24SlimeWand.AddedSlimeBallsMessage", config, "&aSuccessfully added %amount% slime balls!", List.of("Success message for when player reloads with slime balls. %amount% for amount."));
        July24SlimeWandOutOfUsesMessage = ConfigUtils.setupConfig("July24SlimeWand.OutOfUsesMessage", config, "&cNot enough uses! Reload this item with slime balls!", List.of("Error message for when player tries to activate ability but out of uses."));

        July24Trident = ConfigUtils.initializeItem("July24Trident", config);
        July24TridentExtraDamagePercent = ConfigUtils.setupConfig("July24Trident.ExtraDamagePercent", config, 1.3, 0, List.of("How much extra damage to deal. This number is multiplied by original damage. 1.3 = 30% more damage."));
        July24TridentFishKeyChance = ConfigUtils.setupConfig("July24Trident.FishKeyChance", config, 10000, 1);
        July24TridentPVPKeyChance = ConfigUtils.setupConfig("July24Trident.PVPKeyChance", config, 10000, 1);
        July24TridentFishKeyID = ConfigUtils.setupConfig("July24Trident.FishKeyID", config, "Fish", List.of("ID of Fish Key"));
        July24TridentPVPKeyID = ConfigUtils.setupConfig("July24Trident.PVPKeyID", config, "PVP", List.of("ID of PVP Key"));

        July24Axe = ConfigUtils.initializeItem("July24Axe", config, AceItems.keyInt);
        July24AxeFireworkDamage = ConfigUtils.setupConfig("July24Axe.FireworkDamage", config, 5, 0, List.of("How much damage the firework should do"));
        July24AxeIgnoreArmorChance = ConfigUtils.setupConfig("July24Axe.IgnoreArmorChance", config, 7, 1, List.of("Chance for axe to ignore armor"));

        July24Shovel = ConfigUtils.initializeItem("July24Shovel", config, AceItems.keyRadius, AceItems.keyRadiusLore);
        July24ShovelBedrockChance = ConfigUtils.setupConfig("July24Shovel.BedrockChance", config, 5000, 1, List.of("Chance to dig up bedrock."));
        July24ShovelReinforcedDeepslateChance = ConfigUtils.setupConfig("July24Shovel.ReinforcedDeepslateChance", config, 3000, 1, List.of("Chance to dig up reinforced deepslate."));

        julyitems.save();


    }

}
