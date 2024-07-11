package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.time.Duration;
import java.util.*;

import static me.dunescifye.aceitems.utils.CooldownManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class PlayerToggleSneakListener implements Listener {

    public static Map<UUID, Integer> sneaks = new HashMap<>();

    public void PlayerToggleSneakHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
        if (!e.isSneaking()) return;
        Player p = e.getPlayer();
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
            if (helmetID == null) helmetID = helmetContainer.get(AceItems.keyEIID, PersistentDataType.STRING);
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
        //July armor
        if (Objects.equals(helmetID, "July24Helmet") && Objects.equals(chestplateID, "July24Chestplate") && Objects.equals(leggingsID, "July24Leggings") && Objects.equals(bootsID, "July24Boots")
            && !AceItems.disabledWorlds.get("July24Helmet").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Chestplate").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Leggings").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("July24Boots").contains(p.getWorld().getName())) {
            if (p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                BukkitTask cancelTask = activeTasks.remove(p.getUniqueId());
                if (cancelTask != null) {
                    cancelTask.cancel();
                }
                if (!sneaks.containsKey(p.getUniqueId())) {
                    sneaks.put(p.getUniqueId(), 1);
                }
                else if (sneaks.get(p.getUniqueId()) == 5) {
                    sneaks.put(p.getUniqueId(), 0);
                    p.setVelocity(new Vector(0, JulyItemsConfig.July24ArmorJumpStrength, 0));
                }
                else {
                    int sneakAmount = sneaks.get(p.getUniqueId());
                    sneaks.replace(p.getUniqueId(), sneakAmount + 1);
                }

                BukkitTask task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        sneaks.remove(p.getUniqueId());
                    }
                }.runTaskLater(AceItems.getInstance(), 50);

                activeTasks.put(p.getUniqueId(), task);
            }
        }
        //June Armor
        else if ((Objects.equals(helmetID, "June24Helmet") || Objects.equals(helmetID, "June24CrocHat"))&& Objects.equals(chestplateID, "June24Chestplate") && Objects.equals(leggingsID, "June24Leggings") && Objects.equals(bootsID, "June24Boots")
            && !AceItems.disabledWorlds.get("June24Helmet").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Chestplate").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Leggings").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Boots").contains(p.getWorld().getName())) {
            BukkitTask cancelTask = activeTasks.remove(p.getUniqueId());
            if (cancelTask != null) {
                cancelTask.cancel();
            }

            if (June24ArmorSneaks.containsKey(p.getUniqueId())) {
                if (hasCooldown(June24ArmorBeesCooldown, p.getUniqueId())) {
                    sendCooldownMessage(p, getRemainingCooldown(June24ArmorBeesCooldown, p.getUniqueId()));
                } else {
                    if (June24ArmorSneaks.get(p.getUniqueId()) == 2) {
                        setCooldown(June24ArmorBeesCooldown, p.getUniqueId(), Duration.ofMinutes(20));
                        June24ArmorSneaks.remove(p.getUniqueId());
                        p.spawnParticle(Particle.CLOUD, p.getLocation().add(0, 1, 0), 100, 1, 1, 1, 0);
                        List<Bee> bees = new ArrayList<>();
                        Bee bee1 = (Bee) p.getWorld().spawnEntity(p.getLocation().add(1, 2, -1), EntityType.BEE);
                        Bee bee2 = (Bee) p.getWorld().spawnEntity(p.getLocation().add(-1, 2, 1), EntityType.BEE);
                        Bee bee3 = (Bee) p.getWorld().spawnEntity(p.getLocation().add(1, 2, 0), EntityType.BEE);
                        bees.add(bee1);
                        bees.add(bee2);
                        bees.add(bee3);
                        BukkitTask angrybee = new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (Bee bee : bees) {
                                    bee.setHasStung(false);
                                    List<Entity> nearbyEntities = bee.getNearbyEntities(5, 5, 5);
                                    for (Entity entity : nearbyEntities) {
                                        if (entity instanceof Player && entity != p) {
                                            bee.setTarget((LivingEntity) entity);
                                            break;
                                        }
                                    }
                                }
                            }
                        }.runTaskTimer(AceItems.getInstance(), 1, 60);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (Bee bee : bees)
                                    bee.remove();
                                angrybee.cancel();
                            }
                        }.runTaskLater(AceItems.getInstance(), 200);

                    } else {
                        June24ArmorSneaks.put(p.getUniqueId(), 2);
                    }
                }
            } else {
                June24ArmorSneaks.put(p.getUniqueId(), 1);
            }

            BukkitTask task = new BukkitRunnable() {

                @Override
                public void run() {
                    June24ArmorSneaks.remove(p.getUniqueId());
                }
            }.runTaskLater(AceItems.getInstance(), 50);

            activeTasks.put(p.getUniqueId(), task);
        }
    }
}
