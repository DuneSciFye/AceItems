package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
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
                    if (container.has(keyItemID, PersistentDataType.STRING)) {
                        String itemID = container.get(keyItemID, PersistentDataType.STRING);
                        assert itemID != null;
                        if (itemID.equals("June24Bow")) {
                            if (!June24BowDisabledWorlds.contains(p.getWorld().getName())) {
                                getArrowDistances().put(arrow.getUniqueId(), arrow.getLocation().distance(p.getLocation()));
                            }
                        } else if (itemID.equals("June24Crossbow")) {
                            if (!June24CrossbowDisabledWorlds.contains(p.getWorld().getName())) {
                                getArrowDistances().put(arrow.getUniqueId(), arrow.getLocation().distance(p.getLocation()));
                            }
                        }
                    }
                }
            }
        }
    }

}
