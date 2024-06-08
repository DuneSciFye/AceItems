package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.libs.armorequip.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class ArmorEquipListener implements Listener {


    public void ArmorEquipHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent e) {

        if (e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR) {
            ItemStack item = e.getNewArmorPiece();
            if (item.hasItemMeta()){
                Player p = e.getPlayer();
                PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
                if (container.has(keyItemID, PersistentDataType.STRING)){
                    String itemID = container.get(keyItemID, PersistentDataType.STRING);
                    if (itemID.equals("June24Boots") ||
                        itemID.equals("June24Leggings") ||
                        itemID.equals("June24Chestplate") ||
                        itemID.equals("June24Helmet")){
                        if (!June24BootsDisabledWorlds.contains(p.getWorld().getName()) &&
                            !June24LeggingsDisabledWorlds.contains(p.getWorld().getName()) &&
                            !June24ChestplateDisabledWorlds.contains(p.getWorld().getName()) &&
                            !June24HelmetDisabledWorlds.contains(p.getWorld().getName())) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ItemStack helmet = p.getInventory().getHelmet();
                                    ItemStack chestplate = p.getInventory().getChestplate();
                                    ItemStack leggings = p.getInventory().getLeggings();
                                    ItemStack boots = p.getInventory().getBoots();

                                    if (!p.isOnline() || helmet == null || chestplate == null || leggings == null || boots == null ||
                                        !helmet.hasItemMeta() || !chestplate.hasItemMeta() || !leggings.hasItemMeta() || !boots.hasItemMeta() ||
                                        !(p.getInventory().getHelmet().getItemMeta().getPersistentDataContainer().get(keyItemID, PersistentDataType.STRING).equals("June24Helmet") &&
                                            p.getInventory().getChestplate().getItemMeta().getPersistentDataContainer().get(keyItemID, PersistentDataType.STRING).equals("June24Chestplate") &&
                                            p.getInventory().getLeggings().getItemMeta().getPersistentDataContainer().get(keyItemID, PersistentDataType.STRING).equals("June24Leggings") &&
                                            p.getInventory().getBoots().getItemMeta().getPersistentDataContainer().get(keyItemID, PersistentDataType.STRING).equals("June24Boots"))) {
                                        this.cancel();
                                        return;
                                    }

                                    for (int x = -1; x <= 1; x++) {
                                        for (int y = 0; y <= 1; y++) {
                                            for (int z = -1; z <= 1; z++) {
                                                if (ThreadLocalRandom.current().nextInt(10) == 0) {
                                                    Block block = p.getLocation().getBlock().getRelative(x, y, z);
                                                    if (block.getBlockData() instanceof Ageable ageable) {
                                                        if (ageable.getAge() < ageable.getMaximumAge()) {
                                                            ageable.setAge(ageable.getAge() + 1);
                                                            block.setBlockData(ageable);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }.runTaskTimer(AceItems.getInstance(), 1, 400);
                        }
                    }
                }
            }
        }

    }
}
