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
import java.util.Objects;
import java.util.UUID;

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

                //June Armor
                else if ((Objects.equals(helmetID, "June24Helmet") || Objects.equals(helmetID, "June24CrocHat")) && Objects.equals(chestplateID, "June24Chestplate") && Objects.equals(leggingsID, "June24Leggings") && Objects.equals(bootsID, "June24Boots")
                    && !AceItems.disabledWorlds.get("June24Helmet").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Chestplate").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Leggings").contains(p.getWorld().getName()) && !AceItems.disabledWorlds.get("June24Boots").contains(p.getWorld().getName())) {
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
