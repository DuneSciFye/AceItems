package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class EntityDeathListener implements Listener {


    public void EntityDeathHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        Player p = e.getEntity().getKiller();
        if (p == null) return;
        ItemStack mainHand = p.getInventory().getItemInMainHand();
        if (mainHand.hasItemMeta()){
            ItemMeta meta = mainHand.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            if (container.has(keyItemID)) {
                String itemID = container.get(keyItemID, PersistentDataType.STRING);
                if (itemID.equals("June24MoreOPSword")) {
                    if (!June24MoreOPSwordDisabledWorlds.contains(p.getWorld().getName())) {
                        int kills = container.get(keyKills, PersistentDataType.INTEGER);
                        int newKills;

                        if (kills > 9) {
                            meta.getPersistentDataContainer().set(keyItemID, PersistentDataType.INTEGER, 0);
                            Utils.strikeLightningAroundLocation(p.getLocation(), 3);
                            newKills = 0;
                        } else {
                            newKills = kills + 1;
                            meta.getPersistentDataContainer().set(keyKills, PersistentDataType.INTEGER, newKills);
                        }

                        meta.lore(updateLore(mainHand, String.valueOf(kills), String.valueOf(newKills)));
                        mainHand.setItemMeta(meta);
                    }
                } else if (itemID.equals("UltraJune24MoreOPSword")) {
                    if (!UltraJune24MoreOPSwordDisabledWorlds.contains(p.getWorld().getName())) {
                        int kills = container.get(keyKills, PersistentDataType.INTEGER);
                        int newKills;

                        if (kills > 9) {
                            meta.getPersistentDataContainer().set(keyItemID, PersistentDataType.INTEGER, 0);
                            Utils.strikeLightningAroundLocation(p.getLocation(), 3);
                            newKills = 0;
                        } else {
                            newKills = kills + 1;
                            meta.getPersistentDataContainer().set(keyKills, PersistentDataType.INTEGER, newKills);
                        }

                        meta.lore(updateLore(mainHand, String.valueOf(kills), String.valueOf(newKills)));
                        mainHand.setItemMeta(meta);
                    }
                }
            }
        }

    }

}
