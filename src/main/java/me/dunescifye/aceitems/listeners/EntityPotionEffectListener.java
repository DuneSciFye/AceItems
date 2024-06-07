package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class EntityPotionEffectListener implements Listener {

    private static final List<UUID> handlingEvent = new ArrayList<>();

    public void EntityPotionEffectHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityPotionEffect(EntityPotionEffectEvent e) {

        if (e.isCancelled()) return;

        EntityPotionEffectEvent.Action action = e.getAction();

        if (e.getEntity() instanceof Player p){
            if (handlingEvent.contains(p.getUniqueId())) return;
            if (action == EntityPotionEffectEvent.Action.ADDED || action == EntityPotionEffectEvent.Action.CHANGED){
                PotionEffectType potionEffectType = e.getModifiedType();
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
                    (chestplateItemID.equals("June24Chestplate") || chestplateItemID.equals("June24Elytra")) &&
                    leggingsItemID.equals("June24Leggings") &&
                    bootsItemID.equals("June24Boots")) {

                    if (!June24BootsDisabledWorlds.contains(p.getWorld().getName()) &&
                        !June24LeggingsDisabledWorlds.contains(p.getWorld().getName()) &&
                        !June24ChestplateDisabledWorlds.contains(p.getWorld().getName()) &&
                        !June24ElytraDisabledWorlds.contains(p.getWorld().getName()) &&
                        !June24HelmetDisabledWorlds.contains(p.getWorld().getName())) {
                        PotionEffect effect = e.getNewEffect();
                        if (effect != null) {
                            if (isHarmfulEffect(potionEffectType)) {
                                int newLevel = effect.getAmplifier() - 1;
                                if (newLevel >= 0) {
                                    PotionEffect newEffect = new PotionEffect(potionEffectType, effect.getDuration(), newLevel, effect.isAmbient(), effect.hasParticles(), effect.hasIcon());
                                    handlingEvent.add(p.getUniqueId());
                                    Bukkit.getScheduler().runTask(AceItems.getInstance(), () -> {
                                        p.removePotionEffect(potionEffectType);
                                        p.addPotionEffect(newEffect);
                                        handlingEvent.remove(p.getUniqueId());
                                    });
                                }
                            } else if (isBeneficialEffect(potionEffectType)) {
                                int newDuration = (int) (effect.getDuration() * 1.3);
                                PotionEffect newEffect = new PotionEffect(potionEffectType, newDuration, effect.getAmplifier(), effect.isAmbient(), effect.hasParticles(), effect.hasIcon());
                                handlingEvent.add(p.getUniqueId());
                                Bukkit.getScheduler().runTask(AceItems.getInstance(), () -> {
                                    p.removePotionEffect(potionEffectType);
                                    p.addPotionEffect(newEffect);
                                    handlingEvent.remove(p.getUniqueId());
                                });
                            }
                        }
                    }
                }
            }
        }


    }
}
