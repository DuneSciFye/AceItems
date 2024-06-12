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

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.keyItemID;

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
            if (container.has(keyItemID)) {
                String itemID = container.get(keyItemID, PersistentDataType.STRING);
                assert itemID != null;
                if (itemID.equals("June24XPBeacon")) {
                    if (!June24XPBeaconDisabledWorlds.contains(p.getWorld().getName())) {
                        e.setAmount((int) (e.getAmount() * 1.5));
                    }
                } else if (itemID.equals("UltraJune24XPBeacon")) {
                    if (!UltraJune24XPBeaconDisabledWorlds.contains(p.getWorld().getName())) {
                        e.setAmount((int) (e.getAmount() * 2.5));
                    }
                }
            }
        }
    }
}
