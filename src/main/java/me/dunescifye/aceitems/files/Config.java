package me.dunescifye.aceitems.files;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Config {

    public static String changeVariableMessage;
    public static String cooldownMessageHours;
    public static String cooldownMessageMinutes;
    public static String cooldownMessageSeconds;
    public static String confirmMessage;
    private static String inClaimMessage;

    private static Set<Material> unobtainableBlocks = new HashSet<>(Arrays.asList(
        Material.BEDROCK,
        Material.END_PORTAL_FRAME,
        Material.END_PORTAL,
        Material.LIGHT,
        Material.BARRIER,
        Material.COMMAND_BLOCK,
        Material.CHAIN_COMMAND_BLOCK,
        Material.REPEATING_COMMAND_BLOCK,
        Material.STRUCTURE_VOID,
        Material.STRUCTURE_BLOCK,
        Material.JIGSAW,
        Material.BLACK_CANDLE_CAKE,
        Material.CANDLE_CAKE,
        Material.BLUE_CANDLE_CAKE,
        Material.CYAN_CANDLE_CAKE,
        Material.BROWN_CANDLE_CAKE,
        Material.GRAY_CANDLE_CAKE,
        Material.GREEN_CANDLE_CAKE,
        Material.LIGHT_BLUE_CANDLE_CAKE,
        Material.LIGHT_GRAY_CANDLE_CAKE,
        Material.LIME_CANDLE_CAKE,
        Material.MAGENTA_CANDLE_CAKE,
        Material.ORANGE_CANDLE_CAKE,
        Material.PINK_CANDLE_CAKE,
        Material.PURPLE_CANDLE_CAKE,
        Material.RED_CANDLE_CAKE,
        Material.WHITE_CANDLE_CAKE,
        Material.YELLOW_CANDLE_CAKE
    ));

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
        if (!config.isSet("unobtainableBlocks")){
            List<String> unobtainableBlocks = Arrays.asList(
                "BEDROCK",
                "END_PORTAL_FRAME",
                "END_PORTAL",
                "LIGHT",
                "BARRIER",
                "COMMAND_BLOCK",
                "CHAIN_COMMAND_BLOCK",
                "REPEATING_COMMAND_BLOCK",
                "STRUCTURE_VOID",
                "STRUCTURE_BLOCK",
                "JIGSAW",
                "BLACK_CANDLE_CAKE",
                "CANDLE_CAKE",
                "BLUE_CANDLE_CAKE",
                "CYAN_CANDLE_CAKE",
                "BROWN_CANDLE_CAKE",
                "GRAY_CANDLE_CAKE",
                "GREEN_CANDLE_CAKE",
                "LIGHT_BLUE_CANDLE_CAKE",
                "LIGHT_GRAY_CANDLE_CAKE",
                "LIME_CANDLE_CAKE",
                "MAGENTA_CANDLE_CAKE",
                "ORANGE_CANDLE_CAKE",
                "PINK_CANDLE_CAKE",
                "PURPLE_CANDLE_CAKE",
                "RED_CANDLE_CAKE",
                "WHITE_CANDLE_CAKE",
                "YELLOW_CANDLE_CAKE"
            );
            config.set("unobtainableBlocks", unobtainableBlocks);
        }

        changeVariableMessage = config.getString("Messages.ChangeVariableMessage");
        cooldownMessageHours = config.getString("Messages.CooldownMessage.Hours");
        cooldownMessageMinutes = config.getString("Messages.CooldownMessage.Minutes");
        cooldownMessageSeconds = config.getString("Messages.CooldownMessage.Seconds");
        confirmMessage = config.getString("Messages.Confirm");
        inClaimMessage = config.getString("Messages.InClaimMessage");
        for (String material : config.getStringList("unobtainableBlocks")){
            unobtainableBlocks.add(Material.getMaterial(material));
        }

        plugin.saveConfig();
    }

    public static String getInClaimMessage(){
        return inClaimMessage;
    }

    public static Set<Material> getUnobtainableBlocks(){
        return unobtainableBlocks;
    }

}
