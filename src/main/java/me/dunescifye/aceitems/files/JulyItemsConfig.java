package me.dunescifye.aceitems.files;

import me.dunescifye.aceitems.utils.ConfigUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class JulyItemsConfig {

    public static ItemStack July24VillagerWand;

    public static void setup() {
        ConfigUtils julyItems = new ConfigUtils("items/July.yml");
    }

}
