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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
public class ArmorEquipListener implements Listener {


    public void ArmorEquipHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent e) {

        if (e.getNewArmorPiece() == null || e.getNewArmorPiece().getType() == Material.AIR) return;
        ItemStack item = e.getNewArmorPiece();
        if (!item.hasItemMeta()) return;
        Player p = e.getPlayer();
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
        if (itemID == null) return;

        ItemStack helmet = p.getInventory().getHelmet(),
                chestplate = p.getInventory().getChestplate(),
                leggings = p.getInventory().getLeggings(),
                boots = p.getInventory().getBoots();
        ItemMeta helmetMeta, chestplateMeta, leggingsMeta, bootsMeta;
        PersistentDataContainer helmetContainer, chestplateContainer, leggingsContainer, bootsContainer;
        String helmetID = "", chestplateID = "", leggingsID = "", bootsID = "";
        //Obtaining armor info
        if (helmet != null && helmet.hasItemMeta()) {
            helmetMeta = helmet.getItemMeta();
            helmetContainer = helmetMeta.getPersistentDataContainer();
            helmetID = helmetContainer.get(AceItems.keyItemID, PersistentDataType.STRING);
        }
        if (chestplate != null && chestplate.hasItemMeta()) {
            chestplateMeta = chestplate.getItemMeta();
            chestplateContainer = chestplateMeta.getPersistentDataContainer();
            chestplateID = chestplateContainer.get(AceItems.keyItemID, PersistentDataType.STRING);
        }
        if (leggings != null && leggings.hasItemMeta()) {
            leggingsMeta = leggings.getItemMeta();
            leggingsContainer = leggingsMeta.getPersistentDataContainer();
            leggingsID = leggingsContainer.get(AceItems.keyItemID, PersistentDataType.STRING);
        }
        if (boots != null && boots.hasItemMeta()) {
            bootsMeta = boots.getItemMeta();
            bootsContainer = bootsMeta.getPersistentDataContainer();
            bootsID = bootsContainer.get(AceItems.keyItemID, PersistentDataType.STRING);
        }
        //June armor
        if ((Objects.equals(helmetID, "June24Helmet") || Objects.equals(helmetID, "June24CrocHat")) && Objects.equals(chestplateID, "June24Chestplate") && Objects.equals(leggingsID, "June24Leggings") && Objects.equals(bootsID, "June24Boots")
                && !AceItems.disabledWorlds.get("June24Helmet").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Chestplate").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Leggings").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Boots").contains(p.getWorld().getName())) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack helmet = p.getInventory().getHelmet();
                    ItemStack chestplate = p.getInventory().getChestplate();
                    ItemStack leggings = p.getInventory().getLeggings();
                    ItemStack boots = p.getInventory().getBoots();

                    if (!p.isOnline() || helmet == null || chestplate == null || leggings == null || boots == null ||
                            !helmet.hasItemMeta() || !chestplate.hasItemMeta() || !leggings.hasItemMeta() || !boots.hasItemMeta() ||
                            !(p.getInventory().getHelmet().getItemMeta().getPersistentDataContainer().get(AceItems.keyItemID, PersistentDataType.STRING).equals("June24Helmet") &&
                                    p.getInventory().getChestplate().getItemMeta().getPersistentDataContainer().get(AceItems.keyItemID, PersistentDataType.STRING).equals("June24Chestplate") &&
                                    p.getInventory().getLeggings().getItemMeta().getPersistentDataContainer().get(AceItems.keyItemID, PersistentDataType.STRING).equals("June24Leggings") &&
                                    p.getInventory().getBoots().getItemMeta().getPersistentDataContainer().get(AceItems.keyItemID, PersistentDataType.STRING).equals("June24Boots"))) {
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
