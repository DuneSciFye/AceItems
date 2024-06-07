package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class PlayerInteractEntityListener implements Listener {

    public void PlayerInteractEntityHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        ItemStack heldItem = p.getInventory().getItemInMainHand();

        if (heldItem != null){
            if (heldItem.hasItemMeta()){
                ItemMeta meta = heldItem.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(keyItemID)){
                    String itemID = container.get(keyItemID, PersistentDataType.STRING);
                    if (itemID.equals("June24GrowthStunter")){
                        if (!June24GrowthStunterDisabledWorlds.contains(p.getWorld().getName())) {
                            Entity entity = event.getRightClicked();
                            if (entity instanceof Ageable) {
                                ((Ageable) entity).setBaby();
                                ((Ageable) entity).setAge(-2147483647);
                            }
                        }
                    }
                }
            }
        }
    }

}
