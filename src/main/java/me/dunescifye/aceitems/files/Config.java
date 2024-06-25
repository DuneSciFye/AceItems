package me.dunescifye.aceitems.files;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.ConfigUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public static String changeVariableMessage, cooldownMessageHours, cooldownMessageMinutes, cooldownMessageSeconds, confirmMessage,
        inClaimMessage, spawnerCommand, cannotUseHereMessage, keyCommand;

    public static List<Material> obtainableBlocks = new ArrayList<>();

    public static void setup(){
        Plugin plugin = AceItems.getInstance();
        FileConfiguration config = plugin.getConfig();

        if (!config.isSet("Messages.ChangeVariableMessage")){
            config.set("Messages.ChangeVariableMessage", "&aSet %variable% to %content%!");
        }
        if (!config.isSet("Messages.CooldownMessage.Hours")){
            config.set("Messages.CooldownMessage.Hours", "&aThis item is on cooldown for %hours% Hours, %minutes% Minutes, and %seconds% Seconds!");
        }
        if (!config.isSet("Messages.CooldownMessage.Minutes")){
            config.set("Messages.CooldownMessage.Minutes", "&aThis item is on cooldown for %minutes% Minutes and %seconds% Seconds!");
        }
        if (!config.isSet("Messages.CooldownMessage.Seconds")){
            config.set("Messages.CooldownMessage.Seconds", "&aThis item is on cooldown for %seconds% Seconds!");
        }
        if (!config.isSet("Messages.Confirm")){
            config.set("Messages.Confirm", "&aRun this action again to confirm.");
        }
        if (!config.isSet("Messages.InClaimMessage")){
            config.set("Messages.InClaimMessage", "&cThis can only be used in your claim.");
        }
        if (!config.isSet("obtainableBlocks")){
            List<String> obtainableBlocks = List.of(
                "STONE"
            );
            config.set("unobtainableBlocks", obtainableBlocks);
        }

        changeVariableMessage = config.getString("Messages.ChangeVariableMessage");
        cooldownMessageHours = config.getString("Messages.CooldownMessage.Hours");
        cooldownMessageMinutes = config.getString("Messages.CooldownMessage.Minutes");
        cooldownMessageSeconds = config.getString("Messages.CooldownMessage.Seconds");
        confirmMessage = config.getString("Messages.Confirm");
        inClaimMessage = config.getString("Messages.InClaimMessage");
        cannotUseHereMessage = ConfigUtils.setupConfig("Message.CannotUseHere", config, "&cYou cannot use this here!", List.of("Message for when an item cannot be used. Example disabled worlds,"));

        for (String material : config.getStringList("obtainableBlocks")){
            obtainableBlocks.add(Material.getMaterial(material));
        }

        spawnerCommand = ConfigUtils.setupConfig("Commands.SpawnerCommand", config, "spawner admin give %player% %type%", List.of("Command used to give spawner. %player% for player and %type% for mob type."));
        keyCommand = ConfigUtils.setupConfig("Commands.KeyCommand", config, "crazy crates key give %player% %type%", List.of("Command used to give key. %player% for player name and %type% for name of key."));

        plugin.saveConfig();
    }

    public static String getInClaimMessage(){
        return inClaimMessage;
    }

}
