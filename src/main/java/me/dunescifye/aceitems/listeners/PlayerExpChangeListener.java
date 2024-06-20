package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerExpChangeListener implements Listener {

    public void PlayerExpChangeHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerExpChangeEvent(PlayerExpChangeEvent e){
        Player p = e.getPlayer();
        ItemStack offhandItem = p.getInventory().getItemInOffHand();
        if (offhandItem.hasItemMeta()) {
            PersistentDataContainer container = offhandItem.getItemMeta().getPersistentDataContainer();
            String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
            if (itemID != null) {
                switch (itemID) {
                    case "June24XPBeacon" -> {
                        if (!AceItems.disabledWorlds.get("June24XPBeacon").contains(p.getWorld().getName())) {
                            e.setAmount((int) (e.getAmount() * 1.5));
                        }
                    }
                    case "UltraJune24XPBeacon" -> {
                        if (!AceItems.disabledWorlds.get("UltraJune24XPBeacon").contains(p.getWorld().getName())) {
                            e.setAmount((int) (e.getAmount() * 2.5));
                        }
                    }
                    case "July24JobsLantern" -> {
                        if (!AceItems.disabledWorlds.get("July24JobsLantern").contains(p.getWorld().getName())) {
                            e.setAmount((int) (e.getAmount() * 1.25));
                        }
                    }
                    case "UltraJuly24JobsLantern" -> {
                        if (!AceItems.disabledWorlds.get("UltraJuly24JobsLantern").contains(p.getWorld().getName())) {
                            e.setAmount((int) (e.getAmount() * 1.50));
                        }
                    }
                }
            }
        }
    }
}
