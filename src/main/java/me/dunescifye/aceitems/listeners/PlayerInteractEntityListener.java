package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.utils.CooldownManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.time.Duration;

public class PlayerInteractEntityListener implements Listener {

    public void PlayerInteractEntityHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;
        Player p = e.getPlayer();
        ItemStack heldItem = p.getInventory().getItemInMainHand();
        Entity entity = e.getRightClicked();

        if (!heldItem.hasItemMeta()) return;

        ItemMeta meta = heldItem.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);

        if (itemID == null) return;

        switch (itemID) {
            case "June24GrowthStunter" -> {
                if (!AceItems.disabledWorlds.get("June24GrowthStunter").contains(p.getWorld().getName())) {
                    if (entity instanceof Ageable) {
                        ((Ageable) entity).setBaby();
                        ((Ageable) entity).setAge(-2147483647);
                    }
                }
            }
            case "July24VillagerWand" -> {
                if (entity instanceof Villager villager) {
                    if (!AceItems.disabledWorlds.get("July24VillagerWand").contains(p.getWorld().getName())) {
                        if (CooldownManager.hasCooldown(CooldownManager.July24VillagerWandCooldowns, p.getUniqueId())) {
                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(CooldownManager.July24VillagerWandCooldowns, p.getUniqueId()));
                        } else {
                            villager.zombify();

                            int uses = container.get(AceItems.keyUses, PersistentDataType.INTEGER);
                            if (uses > 0) {
                                container.set(AceItems.keyUses, PersistentDataType.INTEGER, uses - 1);
                            } else {
                                container.set(AceItems.keyUses, PersistentDataType.INTEGER, JulyItemsConfig.July24VillagerWandUses);
                                CooldownManager.setCooldown(CooldownManager.July24VillagerWandCooldowns, p.getUniqueId(), Duration.ofMinutes(30));
                            }
                            heldItem.setItemMeta(meta);
                        }
                    }
                }
            }
            //July 24 Saddle right click any mob to ride them
            case "July24Saddle" -> {
                entity.addPassenger(p);
            }
        }
    }

}
