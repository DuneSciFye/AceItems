package me.dunescifye.aceitems;

import dev.jorel.commandapi.CommandAPI;
import me.dunescifye.aceitems.commands.CustomItemsCommand;
import me.dunescifye.aceitems.files.Config;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.libs.armorequip.ArmorEquipEvent;
import me.dunescifye.aceitems.listeners.*;
import me.dunescifye.aceitems.files.JuneItemsConfig;
import me.dunescifye.aceitems.items.JuneItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class AceItems extends JavaPlugin {

    private static AceItems plugin;

    public static final NamespacedKey keyID = new NamespacedKey("aceitems", "id");
    //Map of all items
    public static Map<String, ItemStack> items = new HashMap<>();
    public static Map<NamespacedKey, PersistentDataType> dataType = new HashMap<>();
    public static Map<NamespacedKey, Object> defaultValue = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("AceItems plugin loading.");

        plugin = this;

        Config.setup();
        JuneItemsConfig.setup();

        JuneItemsManager.init();
        ArmorEquipEvent.registerListener(this);
        registerEvents();
        CustomItemsCommand.register();

        Bukkit.getLogger().info("AceItems plugin loaded.");
    }

    public void registerEvents() {
        new PlayerBlockBreakListener().PlayerBlockBreakHandler(this);
        new PlayerInteractListener().PlayerInteractHandler(this);
        new PlayerDeathListener().PlayerDeathHandler(this);
        new PlayerInteractEntityListener().PlayerInteractEntityHandler(this);
        new ArmorEquipListener().ArmorEquipHandler(this);
        new PlayerToggleSneakListener().PlayerToggleSneakHandler(this);
        new EntityPotionEffectListener().EntityPotionEffectHandler(this);
        new PlayerFishListener().PlayerFishHandler(this);
        new EntityDamageByEntityListener().EntityDamageByEntityHandler(this);
        new PlayerExpChangeListener().PlayerExpChangeHandler(this);
        new EntityShootBowListener().EntityShootBowHandler(this);
        new EntityToggleGlideListener().EntityToggleGlideHandler(this);
        new InventoryClickListener().InventoryClickHandler(this);
        new PlayerSwapHandItemsListener().PlayerSwapHandItemsHandler(this);
        new EntityDeathListener().EntityDeathHandler(this);
        new ProjectileLaunchListener().ProjectileLaunchHandler(this);
        new ProjectileHitListener().ProjectileHitHandler(this);
    }

    @Override
    public void onDisable() {
        CommandAPI.unregister("customitems");
    }

    public static AceItems getInstance() {
        return plugin;
    }
}
