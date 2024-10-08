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

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.utils.Utils.*;

public class PlayerDeathListener implements Listener {

    public void PlayerDeathHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player killed = e.getEntity();
        Utils.runConsoleCommand("score variables clear player August24ExtraHearts " + killed.getName(), "attribute " + killed.getName() + " minecraft:generic.max_health modifier remove 597cc153-c857-4b57-9321-75a945a0b29f");
        Player killer;
        if (e.getEntity().getKiller() != null) {
            killer = e.getEntity().getKiller();

            ItemStack heldItem = killer.getInventory().getItemInMainHand();

            if (heldItem.hasItemMeta()) {
                ItemMeta meta = heldItem.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(AceItems.keyItemID, PersistentDataType.STRING)) {
                    String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
                    if (itemID != null) {
                        if (itemID.equals("June24MoreOPSword")) {
                            if (!AceItems.disabledWorlds.get("June24MoreOPSword").contains(killer.getWorld().getName())) {
                                int kills = container.get(AceItems.keyKills, PersistentDataType.INTEGER);
                                int newKills = kills + 1;
                                meta.getPersistentDataContainer().set(AceItems.keyKills, PersistentDataType.INTEGER, newKills);

                                meta.lore(updateLore(heldItem, String.valueOf(kills), String.valueOf(newKills)));
                                heldItem.setItemMeta(meta);
                            }
                        }
                        if (itemID.equals("UltraJune24MoreOPSword")) {
                            if (!AceItems.disabledWorlds.get("UltraJune24MoreOPSword").contains(killer.getWorld().getName())) {
                                int kills = container.get(AceItems.keyKills, PersistentDataType.INTEGER);
                                int newKills;

                                if (kills > 9) {
                                    meta.getPersistentDataContainer().set(AceItems.keyItemID, PersistentDataType.INTEGER, 0);
                                    Utils.strikeLightningAroundLocation(killer.getLocation(), 3);
                                    newKills = 0;
                                } else {
                                    newKills = kills + 1;
                                    meta.getPersistentDataContainer().set(AceItems.keyKills, PersistentDataType.INTEGER, newKills);
                                }

                                meta.lore(updateLore(heldItem, String.valueOf(kills), String.valueOf(newKills)));
                                heldItem.setItemMeta(meta);
                            }
                        } else if (itemID.equals("June24LessOPSword")) {
                            if (!AceItems.disabledWorlds.get("June24LessOPSword").contains(killer.getWorld().getName())) {
                                int random = ThreadLocalRandom.current().nextInt(300);
                                if (random < 15) {
                                    killer.getWorld().dropItemNaturally(killed.getLocation(), new ItemStack(Material.SEA_LANTERN));
                                } else if (random < 30) {
                                    killer.getWorld().dropItemNaturally(killed.getLocation(), new ItemStack(Material.PRISMARINE_SHARD));
                                } else if (random == 30) {
                                    Utils.runConsoleCommand("silkspawners:ss give " + killer.getName() + Utils.randomObject(List.of("tropical_fish", "pufferfish", "salmon", "cod")));
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
