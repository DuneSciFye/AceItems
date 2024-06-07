package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class PlayerSwapHandItemsListener implements Listener {

    public void PlayerSwapHandItemsHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    //Items are after event
    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent e) {
        ItemStack mainHandItem = e.getMainHandItem();
        ItemStack offHandItem = e.getOffHandItem();
        Player p = e.getPlayer();
        if (offHandItem.hasItemMeta()){
            PersistentDataContainer container = offHandItem.getItemMeta().getPersistentDataContainer();
            if (container.has(keyItemID, PersistentDataType.STRING)){
                String itemID = container.get(keyItemID, PersistentDataType.STRING);
                if (itemID.equals("June24Wings")){
                    if (!June24WingsDisabledWorlds.contains(p.getWorld().getName())) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
                    }
                } else if (itemID.equals("June24Amulet")) {
                    if (!June24AmuletDisabledWorlds.contains(p.getWorld().getName())) {
                        BukkitScheduler scheduler = Bukkit.getScheduler();
                        scheduler.runTaskTimer(AceItems.getInstance(), task -> {
                            if (!p.isOnline())
                                task.cancel();
                            ItemStack offHand = p.getInventory().getItemInOffHand();
                            if (!offHand.hasItemMeta()) {
                                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                task.cancel();
                            }
                            if (!offHand.getItemMeta().getPersistentDataContainer().has(keyItemID, PersistentDataType.STRING)) {
                                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                task.cancel();
                            }
                            if (!Objects.equals(offHand.getItemMeta().getPersistentDataContainer().get(keyItemID, PersistentDataType.STRING), "June24Amulet")) {
                                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                task.cancel();
                            }

                            if (p.getWorld().isDayTime()) {
                                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0));
                            } else {
                                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 0));
                            }
                        }, 0L, 100L);
                    }
                } else if (itemID.equals("UltraJune24Amulet")) {
                    if (!UltraJune24AmuletDisabledWorlds.contains(p.getWorld().getName())) {
                        BukkitScheduler scheduler = Bukkit.getScheduler();
                        scheduler.runTaskTimer(AceItems.getInstance(), task -> {
                            if (!p.isOnline())
                                task.cancel();
                            ItemStack offHand = p.getInventory().getItemInOffHand();
                            if (!offHand.hasItemMeta()) {
                                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                task.cancel();
                            }
                            if (!offHand.getItemMeta().getPersistentDataContainer().has(keyItemID, PersistentDataType.STRING)) {
                                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                task.cancel();
                            }
                            if (!Objects.equals(offHand.getItemMeta().getPersistentDataContainer().get(keyItemID, PersistentDataType.STRING), "UltraJune24Amulet")) {
                                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                task.cancel();
                            }

                            if (p.getWorld().isDayTime()) {
                                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 1));
                            } else {
                                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1));
                            }
                        }, 0L, 100L);
                    }
                    }
            }
        }
        if (mainHandItem.hasItemMeta()){
            PersistentDataContainer container = mainHandItem.getItemMeta().getPersistentDataContainer();
            if (container.has(keyItemID, PersistentDataType.STRING)) {
                String itemID = container.get(keyItemID, PersistentDataType.STRING);
                if (itemID.equals("June24Wings")) {
                    p.removePotionEffect(PotionEffectType.WATER_BREATHING);
                } else if (itemID.equals("June24Amulet")) {
                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                } else if (itemID.equals("UltraJune24Amulet")) {
                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                }
            }
        }

    }

}
