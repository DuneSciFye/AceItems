package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerItemHeldListener implements Listener {

    public void playerItemHeldHandler(AceItems plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        ItemStack oldItem = p.getInventory().getItemInMainHand();
        ItemStack newItem = p.getInventory().getItem(e.getNewSlot());

        if (newItem != null && newItem.hasItemMeta()) {
            ItemMeta meta = newItem.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
            if (itemID != null) {
                switch (itemID) {
                    case
                        "July24MoreOPPickaxe" -> {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, -1, 0));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 0));

                    }
                    case
                        "UltraJuly24MoreOPPickaxe" -> {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, -1, 1));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 0));
                    }
                    case "July24SpeedVoucher" ->
                        Utils.addPermission(p.getUniqueId(), JulyItemsConfig.July24SpeedVoucherPerm);
                }
            }
        }
        if (oldItem.hasItemMeta()) {
            ItemMeta meta = oldItem.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
            if (itemID != null) {
                switch (itemID) {
                    case "July24MoreOPPickaxe", "UltraJuly24MoreOPPickaxe" -> {
                        p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                        p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                    }
                    case "July24SpeedVoucher" ->
                        Utils.removePermission(p.getUniqueId(), JulyItemsConfig.July24SpeedVoucherPerm);
                }
            }
        }
    }
}
