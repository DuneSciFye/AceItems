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
            if (container.has(AceItems.keyItemID)) {
                String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
                assert itemID != null;
                if (itemID.equals("June24MoreOPSword")) {
                    if (!AceItems.disabledWorlds.get("June24MoreOPSword").contains(p.getWorld().getName())) {
                        int kills = container.get(AceItems.keyKills, PersistentDataType.INTEGER);
                        int newKills;

                        if (kills > 9) {
                            meta.getPersistentDataContainer().set(AceItems.keyItemID, PersistentDataType.INTEGER, 0);
                            Utils.strikeLightningAroundLocation(p.getLocation(), 3);
                            newKills = 0;
                        } else {
                            newKills = kills + 1;
                            meta.getPersistentDataContainer().set(AceItems.keyKills, PersistentDataType.INTEGER, newKills);
                        }

                        meta.lore(updateLore(mainHand, String.valueOf(kills), String.valueOf(newKills)));
                        mainHand.setItemMeta(meta);
                    }
                } else if (itemID.equals("UltraJune24MoreOPSword")) {
                    if (!AceItems.disabledWorlds.get("UltraJune24MoreOPSword").contains(p.getWorld().getName())) {
                        int kills = container.get(AceItems.keyKills, PersistentDataType.INTEGER);
                        int newKills;

                        if (kills > 9) {
                            meta.getPersistentDataContainer().set(AceItems.keyItemID, PersistentDataType.INTEGER, 0);
                            Utils.strikeLightningAroundLocation(p.getLocation(), 3);
                            newKills = 0;
                        } else {
                            newKills = kills + 1;
                            meta.getPersistentDataContainer().set(AceItems.keyKills, PersistentDataType.INTEGER, newKills);
                        }

                        meta.lore(updateLore(mainHand, String.valueOf(kills), String.valueOf(newKills)));
                        mainHand.setItemMeta(meta);
                    }
                }
            }
        }

    }

}
