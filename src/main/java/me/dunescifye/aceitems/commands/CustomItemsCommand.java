package me.dunescifye.aceitems.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.*;
import me.dunescifye.aceitems.files.JuneItemsConfig;
import me.dunescifye.aceitems.files.Config;
import me.dunescifye.aceitems.items.JuneItemsManager;
import org.bukkit.entity.Player;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.CooldownManager.*;

public class CustomItemsCommand {

    public static void register() {

        new CommandTree("customitems")
            .then(new LiteralArgument("give")
                .then(new PlayerArgument("player")
                    .then(new MultiLiteralArgument("item id",
                        "June24LavaWaterBucket",
                        "UltraJune24LavaWaterBucket",
                        "June24MoreOPPickaxe",
                        "UltraJune24MoreOPPickaxe",
                        "June24LessOPPickaxe",
                        "June24MoreOPSword",
                        "June24QuartzWand",
                        "UltraJune24QuartzWand",
                        "June24PropTool",
                        "UltraJune24PropTool",
                        "June24GrowthStunter",
                        "June24Axe",
                        "UltraJune24Axe",
                        "June24TechnologicalAdvancement",
                        "UltraJune24TechnologicalAdvancement",
                        "June24LessOPSword",
                        "UltraJune24MoreOPSword",
                        "June24GuardianBeam",
                        "June24Boots",
                        "June24Leggings",
                        "June24Chestplate",
                        "June24Helmet",
                        "June24Shovel",
                        "June24XPBeacon",
                        "UltraJune24XPBeacon",
                        "June24BlockWand",
                        "UltraJune24BlockWand",
                        "June24LeafBlower",
                        "June24Hoe",
                        "June24Amulet",
                        "UltraJune24Amulet",
                        "June24Shield",
                        "June24Elytra",
                        "June24Wings",
                        "June24Bow",
                        "June24Crossbow",
                        "June24Trident",
                        "June24FishingRod",
                        "UltraJune24FishingRod")
                        .executes((sender, args) -> {
                            Player p = (Player) args.get("player");
                            String itemID = (String) args.get("item id");
                            assert p != null;
                            switch (itemID) {
                                case "June24LavaWaterBucket":
                                    p.getInventory().addItem(June24LavaWaterBucket);
                                    break;
                                case "UltraJune24LavaWaterBucket":
                                    p.getInventory().addItem(UltraJune24LavaWaterBucket);
                                    break;
                                case "June24MoreOPPickaxe":
                                    p.getInventory().addItem(June24MoreOPPickaxe);
                                    break;
                                case "UltraJune24MoreOPPickaxe":
                                    p.getInventory().addItem(UltraJune24MoreOPPickaxe);
                                    break;
                                case "June24LessOPPickaxe":
                                    p.getInventory().addItem(June24LessOPPickaxe);
                                    break;
                                case "June24MoreOPSword":
                                    p.getInventory().addItem(June24MoreOPSword);
                                    break;
                                case "June24QuartzWand":
                                    p.getInventory().addItem(June24QuartzWand);
                                    break;
                                case "UltraJune24QuartzWand":
                                    p.getInventory().addItem(UltraJune24QuartzWand);
                                    break;
                                case "June24PropTool":
                                    p.getInventory().addItem(June24PropTool);
                                    break;
                                case "UltraJune24PropTool":
                                    p.getInventory().addItem(UltraJune24PropTool);
                                    break;
                                case "June24GrowthStunter":
                                    p.getInventory().addItem(June24GrowthStunter);
                                    break;
                                case "June24Axe":
                                    p.getInventory().addItem(June24Axe);
                                    break;
                                case "UltraJune24Axe":
                                    p.getInventory().addItem(UltraJune24Axe);
                                    break;
                                case "June24TechnologicalAdvancement":
                                    p.getInventory().addItem(June24TechnologicalAdvancement);
                                    break;
                                case "UltraJune24TechnologicalAdvancement":
                                    p.getInventory().addItem(UltraJune24TechnologicalAdvancement);
                                    break;
                                case "June24LessOPSword":
                                    p.getInventory().addItem(June24LessOPSword);
                                    break;
                                case "UltraJune24MoreOPSword":
                                    p.getInventory().addItem(UltraJune24MoreOPSword);
                                    break;
                                case "June24GuardianBeam":
                                    p.getInventory().addItem(June24GuardianBeam);
                                    break;
                                case "June24Boots":
                                    p.getInventory().addItem(June24Boots);
                                    break;
                                case "June24Leggings":
                                    p.getInventory().addItem(June24Leggings);
                                    break;
                                case "June24Chestplate":
                                    p.getInventory().addItem(June24Chestplate);
                                    break;
                                case "June24Helmet":
                                    p.getInventory().addItem(June24Helmet);
                                    break;
                                case "June24Shovel":
                                    p.getInventory().addItem(June24Shovel);
                                    break;
                                case "June24XPBeacon":
                                    p.getInventory().addItem(June24XPBeacon);
                                    break;
                                case "UltraJune24XPBeacon":
                                    p.getInventory().addItem(UltraJune24XPBeacon);
                                    break;
                                case "June24BlockWand":
                                    p.getInventory().addItem(June24BlockWand);
                                    break;
                                case "UltraJune24BlockWand":
                                    p.getInventory().addItem(UltraJune24BlockWand);
                                    break;
                                case "June24LeafBlower":
                                    p.getInventory().addItem(June24LeafBlower);
                                    break;
                                case "June24Hoe":
                                    p.getInventory().addItem(June24Hoe);
                                    break;
                                case "June24Amulet":
                                    p.getInventory().addItem(June24Amulet);
                                    break;
                                case "UltraJune24Amulet":
                                    p.getInventory().addItem(UltraJune24Amulet);
                                    break;
                                case "June24Shield":
                                    p.getInventory().addItem(June24Shield);
                                    break;
                                case "June24Elytra":
                                    p.getInventory().addItem(June24Elytra);
                                    break;
                                case "June24Wings":
                                    p.getInventory().addItem(June24Wings);
                                    break;
                                case "June24Bow":
                                    p.getInventory().addItem(June24Bow);
                                    break;
                                case "June24Crossbow":
                                    p.getInventory().addItem(June24Crossbow);
                                    break;
                                case "June24Trident":
                                    p.getInventory().addItem(June24Trident);
                                    break;
                                case "June24FishingRod":
                                    p.getInventory().addItem(June24FishingRod);
                                    break;
                                case "UltraJune24FishingRod":
                                    p.getInventory().addItem(UltraJune24FishingRod);
                                    break;

                            }

                        })
                    )
                )
            )
            .then(new LiteralArgument("reload")
                .executes((sender, args) -> {
                    JuneItemsConfig.setup();
                    JuneItemsManager.init();
                    sender.sendMessage("Reloaded config!");
                })
                .then(new StringArgument("config file")
                    .replaceSuggestions(ArgumentSuggestions.strings(
                        "JuneItems", "Messages"
                    ))
                    .executes((sender, args) -> {
                        String configFile = (String) args.get("config file");
                        switch (configFile){
                            case "JuneItems":
                                JuneItemsConfig.setup();
                                sender.sendMessage("Reloaded June Items Config!");
                                break;
                            case "Messages":
                                Config.setup();
                                sender.sendMessage("Reloaded Messages Config!");
                                break;
                        }
                        JuneItemsManager.init();
                    })
                )
            )
            .then(new LiteralArgument("remove")
            )
            .register();

        new CommandAPICommand("customitems")
            .withArguments(new LiteralArgument("cooldown"))
            .withArguments(new LiteralArgument("clear"))
            .withArguments(new PlayerArgument("Player"))
            .executes((sender, args) -> {
                Player p = (Player) args.get("Player");

                removeCooldown(June24QuartzWandCooldowns, p.getUniqueId());
                removeCooldown(June24PropToolCooldowns, p.getUniqueId());
                removeCooldown(UltraJune24MoreOPSwordCooldowns, p.getUniqueId());
                removeCooldown(June24GuardianBeamCooldowns, p.getUniqueId());
                removeCooldown(June24GuardianBeamGetItemsCooldowns, p.getUniqueId());
                removeCooldown(June24ArmorBeesCooldown, p.getUniqueId());
                removeCooldown(June24BlockWandCooldowns, p.getUniqueId());
                removeCooldown(UltraJune24BlockWandCooldowns, p.getUniqueId());
                removeCooldown(June24LeafBlowerCooldowns, p.getUniqueId());
                removeCooldown(June24HoeCooldowns, p.getUniqueId());
                removeCooldown(June24LavaWaterBucketCooldowns, p.getUniqueId());
                removeCooldown(UltraJune24LavaWaterBucketCooldowns, p.getUniqueId());

                p.sendMessage("Cleared cooldowns.");
            })
            .register();
    }

}
