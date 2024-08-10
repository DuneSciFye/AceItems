package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerInteractAtEntityListener implements Listener {

    public void playerInteractAtEntityHandler(AceItems plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        String itemID = container.get(AceItems.keyEIID, PersistentDataType.STRING);
        if (itemID == null) return;

        if (itemID.equals("August24ArmorTrimmer")) {
            if (e.getRightClicked() instanceof ArmorStand armorStand) {
                ItemStack[] armorContents = armorStand.getEquipment().getArmorContents();
                String trim = container.get(AceItems.keyScoreType, PersistentDataType.STRING);
                String material = container.get(AceItems.keyScoreMaterial, PersistentDataType.STRING);
                if (trim != null && material != null) {
                    TrimMaterial trimMaterial = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft(material.toLowerCase()));
                    TrimPattern trimPattern = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft(trim.toLowerCase()));
                    if (trimMaterial != null && trimPattern != null) {
                        ArmorTrim armorTrim = new ArmorTrim(trimMaterial, trimPattern);

                        ItemStack armor;
                        e.setCancelled(true);
                        double y = e.getClickedPosition().getY();
                        if (y > 1.6) {
                            armor = armorContents[3];
                        } else if (y > 0.9) {
                            armor = armorContents[2];
                        } else if (y > 0.4) {
                            armor = armorContents[1];
                        } else {
                            armor = armorContents[0];
                        }

                        if (armor.getItemMeta() instanceof ArmorMeta armorMeta) {
                            armorMeta.setTrim(armorTrim);
                            armor.setItemMeta(armorMeta);
                            armorStand.getEquipment().setArmorContents(armorContents);
                        }

                    }
                }
            }
        }
    }
}