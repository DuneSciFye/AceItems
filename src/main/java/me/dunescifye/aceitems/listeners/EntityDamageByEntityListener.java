package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.items.JuneItemsManager.June24HelmetDisabledWorlds;
import static me.dunescifye.aceitems.utils.Utils.*;

public class EntityDamageByEntityListener implements Listener {

    private static HashMap<UUID, Integer> June24BowHits = new HashMap<>();

    public void EntityDamageByEntityHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
        if (e.isCancelled()) return;

        if (e.getDamager() instanceof Player p) {
            if (e.getEntity() instanceof LivingEntity livingEntity) {
                ItemStack boots = p.getInventory().getBoots();
                ItemStack leggings = p.getInventory().getLeggings();
                ItemStack chestplate = p.getInventory().getChestplate();
                ItemStack helmet = p.getInventory().getHelmet();
                PersistentDataContainer bootsContainer = null;
                String bootsItemID = "";
                ItemMeta bootsMeta = null;
                PersistentDataContainer leggingsContainer = null;
                String leggingsItemID = "";
                ItemMeta leggingsMeta = null;
                PersistentDataContainer chestplateContainer = null;
                String chestplateItemID = "";
                ItemMeta chestplateMeta = null;
                PersistentDataContainer helmetContainer = null;
                String helmetItemID = "";
                ItemMeta helmetMeta = null;
                if (boots != null){
                    if (boots.getItemMeta() != null){
                        bootsMeta = boots.getItemMeta();
                        bootsContainer = bootsMeta.getPersistentDataContainer();
                        if (bootsContainer.has(keyItemID)){
                            bootsItemID = bootsContainer.get(keyItemID, PersistentDataType.STRING);
                        }
                    }
                }
                if (leggings != null){
                    if (leggings.getItemMeta() != null){
                        leggingsMeta = leggings.getItemMeta();
                        leggingsContainer = leggingsMeta.getPersistentDataContainer();
                        if (leggingsContainer.has(keyItemID)){
                            leggingsItemID = leggingsContainer.get(keyItemID, PersistentDataType.STRING);
                        }
                    }
                }
                if (chestplate != null){
                    if (chestplate.getItemMeta() != null){
                        chestplateMeta = chestplate.getItemMeta();
                        chestplateContainer = chestplateMeta.getPersistentDataContainer();
                        if (chestplateContainer.has(keyItemID)){
                            chestplateItemID = chestplateContainer.get(keyItemID, PersistentDataType.STRING);
                        }
                    }
                }
                if (helmet != null){
                    if (helmet.getItemMeta() != null){
                        helmetMeta = helmet.getItemMeta();
                        helmetContainer = helmetMeta.getPersistentDataContainer();
                        if (helmetContainer.has(keyItemID)){
                            helmetItemID = helmetContainer.get(keyItemID, PersistentDataType.STRING);
                        }
                    }
                }

                if (helmetItemID.equals("June24Helmet") &&
                    (chestplateItemID.equals("June24Chestplate") || chestplateItemID.equals("June24Elytra"))&&
                    leggingsItemID.equals("June24Leggings") &&
                    bootsItemID.equals("June24Boots")){
                    if (!June24BootsDisabledWorlds.contains(p.getWorld().getName()) &&
                        !June24LeggingsDisabledWorlds.contains(p.getWorld().getName()) &&
                        !June24ChestplateDisabledWorlds.contains(p.getWorld().getName()) &&
                        !June24HelmetDisabledWorlds.contains(p.getWorld().getName())) {
                        if (p.getLocation().getBlock().getType() == Material.WATER ||
                            p.getLocation().getBlock().getType() == Material.BUBBLE_COLUMN ||
                            p.getLocation().getBlock().getType() == Material.KELP_PLANT) {
                            e.setDamage(e.getDamage() * 1.3);
                        }
                    }
                }


                ItemStack offHandItem = p.getInventory().getItemInOffHand();
                if (offHandItem.hasItemMeta()){
                    ItemMeta offHandItemMeta = offHandItem.getItemMeta();
                    PersistentDataContainer container =  offHandItemMeta.getPersistentDataContainer();
                    if (container.has(keyItemID, PersistentDataType.STRING)){
                        String itemID = container.get(keyItemID, PersistentDataType.STRING);
                        if (itemID.equals("June24Shield")){
                            if (!June24ShieldDisabledWorlds.contains(p.getWorld().getName())) {
                                e.setDamage(e.getDamage() * 1.3);
                            }

                        }

                    }
                }

                ItemStack item = p.getInventory().getItemInMainHand();
                if (item.hasItemMeta()){
                    PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
                    if (container.has(keyItemID, PersistentDataType.STRING)){
                        String itemID = container.get(keyItemID, PersistentDataType.STRING);
                        if (itemID.equals("June24Trident")){
                            if (!June24TridentDisabledWorlds.contains(p.getWorld().getName())) {
                                if (ThreadLocalRandom.current().nextInt(20) == 0) {
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 80, 0));
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 1));
                                }
                            }
                        }
                    }
                }

            }
        } else if (e.getDamager() instanceof Arrow arrow) {
            UUID arrowId = arrow.getUniqueId();
            if (getArrowDistances().containsKey(arrowId)) {
                double distance = getArrowDistances().get(arrowId);
                double baseDamage = e.getDamage();
                double additionalDamage = distance / 10;
                e.setDamage(baseDamage + additionalDamage);
                getArrowDistances().remove(arrowId);
            }
            if (arrow.getShooter() instanceof Player p) {
                ItemStack bow = p.getInventory().getItemInMainHand();
                if (bow.hasItemMeta()) {
                    PersistentDataContainer container = bow.getItemMeta().getPersistentDataContainer();
                    if (container.has(keyItemID, PersistentDataType.STRING)) {
                        String itemID = container.get(keyItemID, PersistentDataType.STRING);
                        if (itemID.equals("June24Bow")) {
                            if (!June24BowDisabledWorlds.contains(p.getWorld().getName())) {
                                UUID shooterUUID = p.getUniqueId();
                                int count = June24BowHits.getOrDefault(shooterUUID, 0) + 1;
                                June24BowHits.put(shooterUUID, count);
                                if (count > 5) {
                                    if (e.getEntity() instanceof LivingEntity livingEntity) {
                                        June24BowHits.remove(shooterUUID);
                                        if (ThreadLocalRandom.current().nextInt(2) == 0) {
                                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 600, 0));
                                        }
                                    }
                                }
                            }
                        }
                        else if (itemID.equals("June24Crossbow")) {
                            if (!June24CrossbowDisabledWorlds.contains(p.getWorld().getName())) {
                                UUID shooterUUID = p.getUniqueId();
                                int count = June24BowHits.getOrDefault(shooterUUID, 0) + 1;
                                June24BowHits.put(shooterUUID, count);
                                if (count > 5) {
                                    if (e.getEntity() instanceof LivingEntity livingEntity) {
                                        June24BowHits.remove(shooterUUID);
                                        if (ThreadLocalRandom.current().nextInt(2) == 0) {
                                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 600, 0));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
