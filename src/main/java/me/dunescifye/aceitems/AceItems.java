package me.dunescifye.aceitems;

import com.jeff_media.customblockdata.CustomBlockData;
import dev.jorel.commandapi.CommandAPI;
import me.dunescifye.aceitems.commands.CustomItemsCommand;
import me.dunescifye.aceitems.files.Config;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.libs.armorequip.ArmorEquipEvent;
import me.dunescifye.aceitems.listeners.*;
import me.dunescifye.aceitems.files.JuneItemsConfig;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AceItems extends JavaPlugin {

    private static AceItems plugin;

    public static final NamespacedKey keyItemID = new NamespacedKey("customitems", "item-id");
    public static final NamespacedKey keyRadius = new NamespacedKey("customitems", "radius");
    public static final NamespacedKey keyRadiusLore = new NamespacedKey("customitems", "radiuslore");
    public static final NamespacedKey keyKills = new NamespacedKey("customitems", "kills");
    public static final NamespacedKey keyState = new NamespacedKey("customitems", "state");
    public static final NamespacedKey keyStateLore = new NamespacedKey("customitems", "statelore");
    public static final NamespacedKey keyUses = new NamespacedKey("customitems", "uses");
    public static final NamespacedKey keyInt = new NamespacedKey("customitems", "int");
    public static final NamespacedKey keyString = new NamespacedKey("customitems", "string");
    //Map of all items
    public static Map<String, ItemStack> items = new HashMap<>();
    //Map of all disabled worlds for each item
    public static Map<String, List<String>> disabledWorlds = new HashMap<>();
    public static Map<NamespacedKey, PersistentDataType> dataType = new HashMap<>();
    public static Map<NamespacedKey, Object> defaultValue = new HashMap<>();

    public static LuckPerms luckPerms;

    static {
        dataType.put(keyRadius, PersistentDataType.INTEGER);
        defaultValue.put(keyRadius, 0);
        dataType.put(keyRadiusLore, PersistentDataType.STRING);
        defaultValue.put(keyRadiusLore, "1x1");
        dataType.put(keyKills, PersistentDataType.INTEGER);
        defaultValue.put(keyKills, 0);
        dataType.put(keyState, PersistentDataType.STRING);
        dataType.put(keyStateLore, PersistentDataType.STRING);
        dataType.put(keyUses, PersistentDataType.INTEGER);
        dataType.put(keyInt, PersistentDataType.INTEGER);
        defaultValue.put(keyInt, 0);
        dataType.put(keyString, PersistentDataType.STRING);
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("AceItems plugin loading.");

        plugin = this;

        Config.setup();
        JuneItemsConfig.setup();
        JulyItemsConfig.setup();

        ArmorEquipEvent.registerListener(this);
        registerEvents();
        CustomItemsCommand.register();
        //Obtaining luckperms instance
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();
        }
        CustomBlockData.registerListener(plugin);

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
        new PlayerItemHeldListener().playerItemHeldHandler(this);
        new BlockPlaceListener().blockPlaceHandler(this);
        new FurnaceSmeltListener().furnaceSmeltHandler(this);
    }

    @Override
    public void onDisable() {
        CommandAPI.unregister("customitems");
    }

    public static AceItems getInstance() {
        return plugin;
    }
}
