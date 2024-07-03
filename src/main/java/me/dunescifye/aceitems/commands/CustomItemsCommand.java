package me.dunescifye.aceitems.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.*;
import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.files.JuneItemsConfig;
import me.dunescifye.aceitems.files.Config;
import me.dunescifye.aceitems.utils.BlockUtils;
import me.dunescifye.aceitems.utils.Utils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.dunescifye.aceitems.utils.CooldownManager.*;

public class CustomItemsCommand {

    public static void register() {

        new CommandTree("customitems")
            .then(new LiteralArgument("give")
                .then(new PlayerArgument("player")
                    .then(new StringArgument("Item ID")
                        .replaceSuggestions(ArgumentSuggestions.strings(
                            AceItems.items.keySet()
                        ))
                        .executes((sender, args) -> {
                            Player p = (Player) args.get("player");
                            String key = (String) args.get("Item ID");
                            assert p != null;
                            if (AceItems.items.containsKey(key)) {
                                Utils.dropItems(p.getLocation(), p.getUniqueId(), AceItems.items.get(key));
                            }
                            else {
                                p.sendMessage("Item not found!");
                            }
                        })
                        .then(new IntegerArgument("Amount", 1)
                            .executes((sender, args) -> {
                                Player p = (Player) args.get("player");
                                String key = (String) args.get("Item ID");
                                int amount = (int) args.get("Amount");
                                if (AceItems.items.containsKey(key)) {
                                    ItemStack item = AceItems.items.get(key).clone();
                                    item.setAmount(amount);
                                    Utils.dropItems(p.getLocation(), p.getUniqueId(), item);
                                }
                                else {
                                    p.sendMessage("Item not found!");
                                }
                            })
                        )
                    )
                )
            )
            .then(new LiteralArgument("reload")
                .executes((sender, args) -> {
                    JuneItemsConfig.setup();
                    JulyItemsConfig.setup();
                    sender.sendMessage("Reloaded config!");
                })
                .then(new StringArgument("config file")
                    .replaceSuggestions(ArgumentSuggestions.strings(
                        "JuneItems", "Messages", "JulyItems"
                    ))
                    .executes((sender, args) -> {
                        String configFile = (String) args.get("config file");
                        switch (configFile){
                            case "JuneItems":
                                JuneItemsConfig.setup();
                                sender.sendMessage("Reloaded June Items Config!");
                                break;
                            case "JulyItems":
                                JulyItemsConfig.setup();
                                sender.sendMessage("Reloaded July Items Config!");
                                break;
                            case "Messages":
                                Config.setup();
                                sender.sendMessage("Reloaded Messages Config!");
                                break;
                        }
                    })
                )
            )
            .then(new LiteralArgument("remove")
            )
            .withAliases("ci")
            .withPermission("customitems.command")
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
            .withPermission("customitems.command")
            .register();



    }

}
