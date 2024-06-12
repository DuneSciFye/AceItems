package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class EntityShootBowListener implements Listener {

    public void EntityShootBowHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent e){
        if(e.getEntity() instanceof Player p){
            ItemStack offHandItem = p.getInventory().getItemInOffHand();
            if (offHandItem.hasItemMeta()){
                ItemMeta offHandItemMeta = offHandItem.getItemMeta();
                PersistentDataContainer container =  offHandItemMeta.getPersistentDataContainer();
                if (container.has(keyItemID, PersistentDataType.STRING)){
                    String itemID = container.get(keyItemID, PersistentDataType.STRING);
                    Arrow projectile = (Arrow) e.getProjectile();
                    assert itemID != null;
                    if (itemID.equals("June24Shield")){
                        if (!June24ShieldDisabledWorlds.contains(p.getWorld().getName())) {
                            int arrows = itemCount(p, Material.ARROW);
                            projectile.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            Bukkit.getScheduler().runTask(AceItems.getInstance(), () -> {
                                int arrowsNew = itemCount(p, Material.ARROW);
                                if (arrowsNew + 1 == arrows)
                                    p.getInventory().addItem(new ItemStack(Material.ARROW));
                            });
                        }
                    }
                }
            }
        }
    }
}
