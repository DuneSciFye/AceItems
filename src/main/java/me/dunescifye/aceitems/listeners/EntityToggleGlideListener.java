package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.files.JuneItemsConfig.*;
import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class EntityToggleGlideListener implements Listener {

    public void EntityToggleGlideHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityToggleGlide(EntityToggleGlideEvent e){
        if(e.getEntity() instanceof Player p){
            ItemStack chestItem = p.getInventory().getChestplate();

            if (chestItem.hasItemMeta()){
                ItemMeta meta = chestItem.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(keyItemID, PersistentDataType.STRING)){
                    String itemID = container.get(keyItemID, PersistentDataType.STRING);
                    if (itemID.equals("June24Elytra")) {
                        if (!June24ElytraDisabledWorlds.contains(p.getWorld().getName())) {
                            Particle.DustOptions aqua = new Particle.DustOptions(Color.AQUA, 2.0F);
                            Particle.DustOptions lime = new Particle.DustOptions(Color.LIME, 2.0F);
                            if (e.isGliding()) {
                                Bukkit.getScheduler().runTaskTimer(AceItems.getInstance(), task -> {
                                    if (!p.isOnline()) {
                                        task.cancel();
                                        return;
                                    }
                                    if (!p.isGliding()) {
                                        task.cancel();
                                        return;
                                    }

                                    if (ThreadLocalRandom.current().nextInt(0, 1) == 0) {
                                        p.spawnParticle(Particle.REDSTONE, p.getLocation(), 5, 0.5, 0.5, 0.5, aqua);
                                    } else {
                                        p.spawnParticle(Particle.REDSTONE, p.getLocation(), 5, 0.5, 0.5, 0.5, lime);
                                    }
                                    if (ThreadLocalRandom.current().nextInt(1, June24ElytraTurtleEggChance) == 1)
                                        p.getInventory().addItem(new ItemStack(Material.TURTLE_EGG));
                                    if (ThreadLocalRandom.current().nextInt(1, June24ElytraAxolotlSpawnEggChance) == 1)
                                        p.getInventory().addItem(new ItemStack(Material.AXOLOTL_SPAWN_EGG));

                                }, 0L, 5L);
                            }
                        }
                    }
                }
            }
        }
    }
}
