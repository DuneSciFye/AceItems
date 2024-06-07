package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
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

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.items.JuneItemsManager.June24HelmetDisabledWorlds;
import static me.dunescifye.aceitems.utils.CooldownManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class PlayerToggleSneakListener implements Listener {

    public void PlayerToggleSneakHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
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

        if (e.isSneaking()){
            if (helmetItemID.equals("June24Helmet") &&
                (chestplateItemID.equals("June24Chestplate") || chestplateItemID.equals("June24Elytra")) &&
                leggingsItemID.equals("June24Leggings") &&
                bootsItemID.equals("June24Boots")){
                if (!June24BootsDisabledWorlds.contains(p.getWorld().getName()) &&
                    !June24LeggingsDisabledWorlds.contains(p.getWorld().getName()) &&
                    !June24ChestplateDisabledWorlds.contains(p.getWorld().getName()) &&
                    !June24HelmetDisabledWorlds.contains(p.getWorld().getName())) {
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
                    boots.setItemMeta(bootsMeta);

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

    }
}
