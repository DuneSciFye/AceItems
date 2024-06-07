package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class PlayerDeathListener implements Listener {

    public void PlayerDeathHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        Player killer;
        if (e.getEntity().getKiller() != null) {
            killer = e.getEntity().getKiller();

            ItemStack heldItem = killer.getInventory().getItemInMainHand();

            if (heldItem.hasItemMeta()) {
                ItemMeta meta = heldItem.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(keyItemID)) {
                    String itemID = container.get(keyItemID, PersistentDataType.STRING);
                    if (itemID.equals("June24MoreOPSword")){
                        if (!June24MoreOPSwordDisabledWorlds.contains(killer.getWorld().getName())) {
                            int kills = container.get(keyKills, PersistentDataType.INTEGER);
                            int newKills;

                            if (kills > 9) {
                                meta.getPersistentDataContainer().set(keyItemID, PersistentDataType.INTEGER, 0);
                                Utils.strikeLightningAroundLocation(killer.getLocation(), 3);
                                newKills = 0;
                            } else {
                                newKills = kills + 1;
                                meta.getPersistentDataContainer().set(keyKills, PersistentDataType.INTEGER, newKills);
                            }

                            meta.lore(updateLore(heldItem, String.valueOf(kills), String.valueOf(newKills)));
                            heldItem.setItemMeta(meta);
                        }
                    } if (itemID.equals("UltraJune24MoreOPSword")){
                        if (!UltraJune24MoreOPSwordDisabledWorlds.contains(killer.getWorld().getName())) {
                            int kills = container.get(keyKills, PersistentDataType.INTEGER);
                            int newKills;

                            if (kills > 9) {
                                meta.getPersistentDataContainer().set(keyItemID, PersistentDataType.INTEGER, 0);
                                Utils.strikeLightningAroundLocation(killer.getLocation(), 3);
                                newKills = 0;
                            } else {
                                newKills = kills + 1;
                                meta.getPersistentDataContainer().set(keyKills, PersistentDataType.INTEGER, newKills);
                            }

                            meta.lore(updateLore(heldItem, String.valueOf(kills), String.valueOf(newKills)));
                            heldItem.setItemMeta(meta);
                        }
                    } else if (itemID.equals("June24LessOPSword")) {
                        if (!June24LessOPSwordDisabledWorlds.contains(killer.getWorld().getName())){
                            int random = ThreadLocalRandom.current().nextInt(300);
                            if (random < 15) {
                                killer.getWorld().dropItemNaturally(killed.getLocation(), new ItemStack(Material.SEA_LANTERN));
                            } else if (random < 30) {
                                killer.getWorld().dropItemNaturally(killed.getLocation(), new ItemStack(Material.PRISMARINE_SHARD));
                            } else if (random == 30) {
                                Utils.runConsoleCommand("silkspawners:ss give " + killer.getName() + Utils.randomObject("tropical_fish", "pufferfish", "salmon", "cod"));
                            }
                        }
                    }
                }
            }
        }

    }
}
