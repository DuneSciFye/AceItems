package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class EntityDamageListener implements Listener {

    public void entityDamageHandler(AceItems plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player p){
            ItemStack helmet = p.getInventory().getHelmet(),
                chestplate = p.getInventory().getChestplate(),
                leggings = p.getInventory().getLeggings(),
                boots = p.getInventory().getBoots();
            ItemMeta helmetMeta, chestplateMeta, leggingsMeta, bootsMeta;
            PersistentDataContainer helmetContainer, chestplateContainer, leggingsContainer, bootsContainer;
            String helmetID = "", chestplateID = "", leggingsID = "", bootsID = "";
            //Obtaining armor info
            if (helmet != null && helmet.hasItemMeta()) {
                helmetMeta = helmet.getItemMeta();
                helmetContainer = helmetMeta.getPersistentDataContainer();
                helmetID = helmetContainer.get(AceItems.keyItemID, PersistentDataType.STRING);
            }
            if (chestplate != null && chestplate.hasItemMeta()) {
                chestplateMeta = chestplate.getItemMeta();
                chestplateContainer = chestplateMeta.getPersistentDataContainer();
                chestplateID = chestplateContainer.get(AceItems.keyItemID, PersistentDataType.STRING);
            }
            if (leggings != null && leggings.hasItemMeta()) {
                leggingsMeta = leggings.getItemMeta();
                leggingsContainer = leggingsMeta.getPersistentDataContainer();
                leggingsID = leggingsContainer.get(AceItems.keyItemID, PersistentDataType.STRING);
            }
            if (boots != null && boots.hasItemMeta()) {
                bootsMeta = boots.getItemMeta();
                bootsContainer = bootsMeta.getPersistentDataContainer();
                bootsID = bootsContainer.get(AceItems.keyItemID, PersistentDataType.STRING);
            }
            //July 24 Armor
            if (Objects.equals(helmetID, "July24Helmet") && Objects.equals(chestplateID, "July24Chestplate") && Objects.equals(leggingsID, "July24Leggings") && Objects.equals(bootsID, "July24Boots")
                && !AceItems.disabledWorlds.get("July24Helmet").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Chestplate").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Leggings").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Boots").contains(p.getWorld().getName())) {
                if ((e.getCause() == EntityDamageEvent.DamageCause.FALL && PlayerToggleSneakListener.sneaks.get(p.getUniqueId()) == 0) || ((e.getCause() == EntityDamageEvent.DamageCause.LAVA || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || e.getCause() == EntityDamageEvent.DamageCause.FIRE) && p.getWorld().getEnvironment() == World.Environment.NETHER)) e.setCancelled(true);
            }
        }
    }
}
