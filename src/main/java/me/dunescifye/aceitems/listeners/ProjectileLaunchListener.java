package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.utils.Utils.*;

public class ProjectileLaunchListener implements Listener {

    public void ProjectileLaunchHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Arrow arrow) {
            if (arrow.getShooter() instanceof Player p) {
                ItemStack item = p.getInventory().getItemInMainHand();
                if (item.hasItemMeta()){
                    ItemMeta meta = item.getItemMeta();
                    PersistentDataContainer container = meta.getPersistentDataContainer();
                    if (container.has(AceItems.keyItemID, PersistentDataType.STRING)) {
                        String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
                        if (itemID != null) {
                            switch (itemID) {
                                case "June24Bow" -> {
                                    if (!AceItems.disabledWorlds.get("June24Bow").contains(p.getWorld().getName()))
                                        getArrowDistances().put(arrow.getUniqueId(), arrow.getLocation().distance(p.getLocation()));
                                }
                                case "June24Crossbow" -> {
                                    if (!AceItems.disabledWorlds.get("June24Crossbow").contains(p.getWorld().getName()))
                                        getArrowDistances().put(arrow.getUniqueId(), arrow.getLocation().distance(p.getLocation()));
                                }
                                case "July24Bow", "July24XBow" -> {
                                    if (!AceItems.disabledWorlds.get("July24Bow").contains(p.getWorld().getName())) {
                                        if (ThreadLocalRandom.current().nextInt(5) == 0) {
                                            Bukkit.getScheduler().runTaskTimer(AceItems.getInstance(), task -> {
                                                if (arrow.isDead()) task.cancel();
                                                for (Entity entity : arrow.getNearbyEntities(10.0, 10.0, 10.0)) {
                                                    if (entity == p) continue;
                                                    arrow.setVelocity(entity.getLocation().toVector().subtract(arrow.getLocation().toVector()).normalize());
                                                    break;
                                                }
                                            }, 0, 5);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
