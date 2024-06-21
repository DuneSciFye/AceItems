package me.dunescifye.aceitems.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.Config;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.utils.CooldownManager;
import me.dunescifye.aceitems.utils.Utils;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.utils.Utils.*;

public class EntityDamageByEntityListener implements Listener {

    private static HashMap<UUID, Integer> June24BowHits = new HashMap<>();

    public void EntityDamageByEntityHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
        if (e.isCancelled()) return;

        //Attacker is player
        if (e.getDamager() instanceof Player p) {
            if (e.getEntity() instanceof LivingEntity livingEntity) {
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
                //June 24 armor damage boost in water
                if (Objects.equals(helmetID, "June24Helmet") && Objects.equals(chestplateID, "June24Chestplate") && Objects.equals(leggingsID, "June24Leggings") && Objects.equals(bootsID, "June24Boots")
                    && !AceItems.disabledWorlds.get("June24Helmet").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Chestplate").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Leggings").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Boots").contains(p.getWorld().getName())) {
                    if (p.getLocation().getBlock().getType() == Material.WATER ||
                        p.getLocation().getBlock().getType() == Material.BUBBLE_COLUMN ||
                        p.getLocation().getBlock().getType() == Material.KELP_PLANT) {
                        e.setDamage(e.getDamage() * 1.3);
                    }
                }
                //July 24 armor damage boost chance
                else if (Objects.equals(helmetID, "July24Helmet") && Objects.equals(chestplateID, "July24Chestplate") && Objects.equals(leggingsID, "July24Leggings") && Objects.equals(bootsID, "July24Boots")
                    && !AceItems.disabledWorlds.get("July24Helmet").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Chestplate").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Leggings").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Boots").contains(p.getWorld().getName())) {
                    if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24ArmorExtraDamageChance) == 0) {
                        e.setDamage(e.getDamage() * JulyItemsConfig.July24ArmorExtraDamagePercent);
                    }
                }
                //Mainhand items
                ItemStack item = p.getInventory().getItemInMainHand();
                if (item.hasItemMeta()){
                    PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
                    String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
                    if (itemID != null) {
                        switch (itemID) {
                            case "June24Trident" -> {
                                if (!AceItems.disabledWorlds.get("June24Trident").contains(p.getWorld().getName())) {
                                    if (ThreadLocalRandom.current().nextInt(20) == 0) {
                                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 80, 0));
                                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 1));
                                    }
                                }
                            }
                            case "July24VillagerWand" -> {
                                if (!AceItems.disabledWorlds.get("July24VillagerWand").contains(p.getWorld().getName())) {
                                    if (livingEntity instanceof ZombieVillager zombieVillager) {
                                        e.setCancelled(true);

                                        if (CooldownManager.hasCooldown(CooldownManager.July24VillagerWandCooldowns, p.getUniqueId())) {
                                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(CooldownManager.July24VillagerWandCooldowns, p.getUniqueId()));
                                        } else {
                                            zombieVillager.setConversionTime(0);

                                            int uses = container.get(AceItems.keyUses, PersistentDataType.INTEGER);
                                            if (uses > 0) {
                                                container.set(AceItems.keyUses, PersistentDataType.INTEGER, uses - 1);
                                            } else {
                                                container.set(AceItems.keyUses, PersistentDataType.INTEGER, JulyItemsConfig.July24VillagerWandUses);
                                                CooldownManager.setCooldown(CooldownManager.July24VillagerWandCooldowns, p.getUniqueId(), Duration.ofMinutes(30));
                                            }
                                        }
                                    }
                                }
                            }
                            case "July24Saddle" -> {
                                if (livingEntity instanceof Player target) {
                                }
                            }
                            case "July24MoreOPSword" -> {
                                if (e.isCritical()) {
                                    Location loc = p.getLocation();
                                    Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
                                    FireworkMeta fwm = fw.getFireworkMeta();

                                    fwm.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(ThreadLocalRandom.current().nextInt(0, 256), ThreadLocalRandom.current().nextInt(0, 256), ThreadLocalRandom.current().nextInt(0, 256))).build());

                                    fw.setFireworkMeta(fwm);
                                    fw.setMetadata("nodamage", new FixedMetadataValue(AceItems.getInstance(), true));
                                    fw.detonate();
                                }
                            }
                        }
                    }
                }
                //Offhand items
                ItemStack offHandItem = p.getInventory().getItemInOffHand();
                if (offHandItem.hasItemMeta()){
                    ItemMeta offHandItemMeta = offHandItem.getItemMeta();
                    PersistentDataContainer container =  offHandItemMeta.getPersistentDataContainer();
                    String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
                    if (itemID != null) {
                        //June 24 Shield Damage boost
                        if (itemID.equals("June24Shield")){
                            if (!AceItems.disabledWorlds.get("June24Shield").contains(p.getWorld().getName()))
                                e.setDamage(e.getDamage() * 1.3);
                        }

                    }
                }

            }
        }
        //Damager is arrow
        else if (e.getDamager() instanceof Arrow arrow) {
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
                    if (container.has(AceItems.keyItemID, PersistentDataType.STRING)) {
                        String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
                        if (itemID.equals("June24Bow")) {
                            if (!AceItems.disabledWorlds.get("June24Bow").contains(p.getWorld().getName())) {
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
                            if (!AceItems.disabledWorlds.get("June24Crossbow").contains(p.getWorld().getName())) {
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
        //Prevent custom firework damage
        else if (e.getDamager() instanceof Firework fw) {
            if (fw.hasMetadata("nodamage")) e.setCancelled(true);
        }
        //Player was damaged
        if (e.getEntity() instanceof Player p && e.getDamager() instanceof Player attacker) {
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
            //July 24 Armor
            if (Objects.equals(helmetID, "July24Helmet") && Objects.equals(chestplateID, "July24Chestplate") && Objects.equals(leggingsID, "July24Leggings") && Objects.equals(bootsID, "July24Boots")
                && !AceItems.disabledWorlds.get("July24Helmet").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Chestplate").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Leggings").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Boots").contains(p.getWorld().getName())) {
                //July 24 Armor Chance to push attacker back
                if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24ArmorLaunchChance) == 0)
                    attacker.setVelocity(attacker.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(1));
                //July 24 Armor Thorn Immunity
                if (e.getCause() == EntityDamageEvent.DamageCause.THORNS) e.setCancelled(true);
                else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                    //Fire immunity in nether
                    if (p.getWorld().getEnvironment() == World.Environment.NETHER) {
                        e.setCancelled(true);
                    } else {
                        //Extra resistant to fire damage
                        e.setDamage(e.getDamage() * JulyItemsConfig.July24ArmorFireDamageReductionPercent);
                    }
                }
            }
        }
    }
}