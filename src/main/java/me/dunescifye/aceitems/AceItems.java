package me.dunescifye.aceitems;

import dev.jorel.commandapi.CommandAPI;
import me.dunescifye.aceitems.commands.CustomItemsCommand;
import me.dunescifye.aceitems.files.Config;
import me.dunescifye.aceitems.libs.armorequip.ArmorEquipEvent;
import me.dunescifye.aceitems.listeners.*;
import me.dunescifye.aceitems.files.JuneItemsConfig;
import me.dunescifye.aceitems.items.JuneItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AceItems extends JavaPlugin {

    private static AceItems plugin;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("AceItems plugin starting.");

        plugin = this;

        Config.setup();
        JuneItemsConfig.setup();

        JuneItemsManager.init();
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
        ArmorEquipEvent.registerListener(this);
        CustomItemsCommand.register();

        Bukkit.getLogger().info("AceItems plugin finished starting.");
    }

    @Override
    public void onDisable() {
        CommandAPI.unregister("customitems");
    }

    public static AceItems getInstance() {
        return plugin;
    }
}
