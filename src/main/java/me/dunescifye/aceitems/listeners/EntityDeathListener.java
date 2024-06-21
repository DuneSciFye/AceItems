package me.dunescifye.aceitems.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.Config;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.utils.Utils;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionType;

import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.utils.Utils.*;

public class EntityDeathListener implements Listener {


    public void EntityDeathHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        Player p = e.getEntity().getKiller();
        if (p == null) return;

        LivingEntity livingEntity = e.getEntity();
        ItemStack mainHand = p.getInventory().getItemInMainHand();

        if (!mainHand.hasItemMeta()) return;
        ItemMeta meta = mainHand.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
        if (itemID != null) {
            switch (itemID) {
                case "July24MoreOPSword" -> {
                    if (livingEntity instanceof Player target) {
                        if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24MoreOPSwordKillAnnouncementChance) == 0) {
                            Bukkit.broadcast(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(p, JulyItemsConfig.July24MoreOPSwordKillAnnouncement.replace("%player%", p.getName()).replace("%target%", target.getName()))));
                        }
                    } else {
                        if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24MoreOPSwordSlayerPointsCommandChance) == 0) {
                            Utils.runConsoleCommand(JulyItemsConfig.July24MoreOPSwordSlayerPointsCommand.replace("%player%", p.getName()));
                        }
                    }

                    if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24MoreOPSwordBlazeSpawnerChance) == 0) {
                        Utils.runConsoleCommand(Config.spawnerCommand.replace("%player%", p.getName()).replace("%type%", "BLAZE"));
                    }
                    if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24MoreOPSwordMagmaCubeSpawnerChance) == 0) {
                        Utils.runConsoleCommand(Config.spawnerCommand.replace("%player%", p.getName()).replace("%type%", "MAGMA_CUBE"));
                    }
                }
                case "June24MoreOPSword" -> {
                    if (!AceItems.disabledWorlds.get("June24MoreOPSword").contains(p.getWorld().getName())) {
                        int kills = container.get(AceItems.keyKills, PersistentDataType.INTEGER);
                        int newKills;

                        if (kills > 9) {
                            meta.getPersistentDataContainer().set(AceItems.keyItemID, PersistentDataType.INTEGER, 0);
                            Utils.strikeLightningAroundLocation(p.getLocation(), 3);
                            newKills = 0;
                        } else {
                            newKills = kills + 1;
                            meta.getPersistentDataContainer().set(AceItems.keyKills, PersistentDataType.INTEGER, newKills);
                        }

                        meta.lore(updateLore(mainHand, String.valueOf(kills), String.valueOf(newKills)));
                        mainHand.setItemMeta(meta);
                    }
                }
                case "UltraJune24MoreOPSword" -> {
                    if (!AceItems.disabledWorlds.get("UltraJune24MoreOPSword").contains(p.getWorld().getName())) {
                        int kills = container.get(AceItems.keyKills, PersistentDataType.INTEGER);
                        int newKills;

                        if (kills > 9) {
                            meta.getPersistentDataContainer().set(AceItems.keyItemID, PersistentDataType.INTEGER, 0);
                            Utils.strikeLightningAroundLocation(p.getLocation(), 3);
                            newKills = 0;
                        } else {
                            newKills = kills + 1;
                            meta.getPersistentDataContainer().set(AceItems.keyKills, PersistentDataType.INTEGER, newKills);
                        }

                        meta.lore(updateLore(mainHand, String.valueOf(kills), String.valueOf(newKills)));
                        mainHand.setItemMeta(meta);
                    }
                }
                case "July24LessOPSWord" -> {
                    if (livingEntity instanceof Player) {
                        if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24LessOPSWordPlayerRandomPotionChance) == 0) {
                            Utils.dropItems(livingEntity.getLocation(), Utils.randomPotion());
                        }
                    } else  {
                        if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24LessOPSWordEntityRandomPotionChance) == 0) {
                            Utils.dropItems(livingEntity.getLocation(), Utils.randomPotion());
                        }
                    }
                }
            }
        }
    }
}
