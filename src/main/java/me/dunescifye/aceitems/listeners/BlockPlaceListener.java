package me.dunescifye.aceitems.listeners;

import com.jeff_media.customblockdata.CustomBlockData;
import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BlockPlaceListener implements Listener {

    public void blockPlaceHandler(AceItems plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        ItemStack item = e.getItemInHand();
        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer itemContainer = meta.getPersistentDataContainer();
        String itemID = itemContainer.get(AceItems.keyItemID, PersistentDataType.STRING);
        if (itemID == null) return;

        Player p = e.getPlayer();
        Block b = e.getBlockPlaced();

        switch (itemID) {
            case "July24Grill" -> {
                PersistentDataContainer blockContainer = new CustomBlockData(b, AceItems.getInstance());
                blockContainer.set(AceItems.keyItemID, PersistentDataType.STRING, "July24Grill");
            }
            case "UltraJuly24Grill" -> {
                PersistentDataContainer blockContainer = new CustomBlockData(b, AceItems.getInstance());
                blockContainer.set(AceItems.keyItemID, PersistentDataType.STRING, "UltraJuly24Grill");
            }
            case "July24JobsLantern", "UltraJuly24JobsLantern" -> e.setCancelled(true);
        }

    }

}
