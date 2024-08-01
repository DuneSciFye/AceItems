package me.dunescifye.aceitems.listeners;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTEntity;
import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.utils.CooldownManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.time.Duration;
import java.util.Objects;

import static me.dunescifye.aceitems.utils.Utils.updateLore;

public class PlayerInteractEntityListener implements Listener {

    public void PlayerInteractEntityHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;
        Player p = e.getPlayer();
        ItemStack heldItem = p.getInventory().getItemInMainHand();
        Entity entity = e.getRightClicked();

        if (!heldItem.hasItemMeta()) return;

        ItemMeta meta = heldItem.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);

        if (itemID == null) return;

        switch (itemID) {
            case "June24GrowthStunter" -> {
                if (!AceItems.disabledWorlds.get("June24GrowthStunter").contains(p.getWorld().getName())) {
                    if (entity instanceof Ageable) {
                        ((Ageable) entity).setBaby();
                        ((Ageable) entity).setAge(-2147483647);
                    }
                }
            }
            case "July24VillagerWand" -> {
                if (entity instanceof Villager villager) {
                    if (!AceItems.disabledWorlds.get("July24VillagerWand").contains(p.getWorld().getName())) {
                        if (CooldownManager.hasCooldown(CooldownManager.July24VillagerWandZombifyCooldowns, p.getUniqueId())) {
                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(CooldownManager.July24VillagerWandZombifyCooldowns, p.getUniqueId()));
                        } else {
                            villager.zombify();
                            CooldownManager.setCooldown(CooldownManager.July24VillagerWandZombifyCooldowns, p.getUniqueId(), Duration.ofSeconds(JulyItemsConfig.July24VillagerWandZombifyCooldown));
                        }
                    }
                }
            }
            case "UltraJuly24VillagerWand" -> {
                if (entity instanceof Villager villager) {
                    if (!AceItems.disabledWorlds.get("UltraJuly24VillagerWand").contains(p.getWorld().getName())) {
                        villager.zombify();
                    }
                }
            }
            //July 24 Saddle right click any mob to ride them
            case "July24Saddle" -> {
                if (!(entity instanceof Player)) {
                    entity.addPassenger(p);
                }
            }
            case "July24GrapplingHook" -> {
                if (entity instanceof Player target) {
                    if (CooldownManager.hasCooldown(CooldownManager.July24GrapplingHookTargetLaunchCooldowns, p.getUniqueId())) {
                        CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(CooldownManager.July24GrapplingHookTargetLaunchCooldowns, p.getUniqueId()));
                    } else {
                        target.setVelocity(new Vector(0, JulyItemsConfig.July24GrapplingHookTargetLaunchStrength, 0));
                        CooldownManager.setCooldown(CooldownManager.July24GrapplingHookTargetLaunchCooldowns, p.getUniqueId(), Duration.ofSeconds(JulyItemsConfig.July24GrapplingHookTargetLaunchCooldown));
                    }
                }
            }
            case "July24AIDisabler" -> {
                ToggleMobAI(p, (LivingEntity) entity, container);
                int uses = container.get(AceItems.keyUses, PersistentDataType.INTEGER);
                if (uses > 1) {
                    container.set(AceItems.keyUses, PersistentDataType.INTEGER, uses - 64);
                    meta.lore(updateLore(heldItem, String.valueOf(uses), String.valueOf(uses - 1)));
                    heldItem.setItemMeta(meta);
                } else {
                    heldItem.setAmount(0);
                }
            }
            case "UltraJuly24AIDisabler" -> {
                ToggleMobAI(p, (LivingEntity) entity, container);
            }
        }
    }

    private void ToggleMobAI(Player p, LivingEntity entity, PersistentDataContainer container) {
        if (Objects.equals(container.get(AceItems.keyState, PersistentDataType.STRING), "enable")) {
            entity.setAI(true);
            p.sendMessage(Component.text("You have enabled this mob's AI.", NamedTextColor.GREEN));
        } else {
            entity.setAI(false);
            p.sendMessage(Component.text("You have disabled this mob's AI.", NamedTextColor.GREEN));
        }
    }

}
