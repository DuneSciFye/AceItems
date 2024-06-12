package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.files.JuneItemsConfig.*;
import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class PlayerFishListener implements Listener {


    public void PlayerFishHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        PlayerFishEvent.State state = e.getState();
        Player p = e.getPlayer();
        if (state == PlayerFishEvent.State.CAUGHT_FISH){
            ItemStack item = p.getInventory().getItemInMainHand();
            if (item.hasItemMeta()){
                PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
                if (container.has(keyItemID, PersistentDataType.STRING)){
                    String itemID = container.get(keyItemID, PersistentDataType.STRING);
                    assert itemID != null;
                    if (itemID.equals("June24FishingRod")){
                        if (!June24FishingRodDisabledWorlds.contains(p.getWorld().getName())) {
                            if (ThreadLocalRandom.current().nextInt(June24FishingRodSeaLanternChance) == 0)
                                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.SEA_LANTERN));
                            if (ThreadLocalRandom.current().nextInt(June24FishingRodFishSpawnEggChance) == 0)
                                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack((Material) randomObject(Material.TROPICAL_FISH_SPAWN_EGG, Material.PUFFERFISH_SPAWN_EGG, Material.SALMON_SPAWN_EGG, Material.COD_SPAWN_EGG)));
                            if (ThreadLocalRandom.current().nextInt(June24FishingRodFroglightChance) == 0)
                                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack((Material) randomObject(Material.OCHRE_FROGLIGHT, Material.PEARLESCENT_FROGLIGHT, Material.VERDANT_FROGLIGHT)));
                        }
                    } else if (itemID.equals("UltraJune24FishingRod")) {
                        if (!UltraJune24FishingRodDisabledWorlds.contains(p.getWorld().getName())) {
                            if (ThreadLocalRandom.current().nextInt(UltraJune24FishingRodSeaLanternChance) == 0)
                                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.SEA_LANTERN));
                            if (ThreadLocalRandom.current().nextInt(UltraJune24FishingRodFishSpawnEggChance) == 0)
                                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack((Material) randomObject(Material.TROPICAL_FISH_SPAWN_EGG, Material.PUFFERFISH_SPAWN_EGG, Material.SALMON_SPAWN_EGG, Material.COD_SPAWN_EGG)));
                            if (ThreadLocalRandom.current().nextInt(UltraJune24FishingRodFroglightChance) == 0)
                                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack((Material) randomObject(Material.OCHRE_FROGLIGHT, Material.PEARLESCENT_FROGLIGHT, Material.VERDANT_FROGLIGHT)));
                            if (ThreadLocalRandom.current().nextInt(UltraJune24FishingRodGuardianSpawnEggChance) == 0)
                                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.GUARDIAN_SPAWN_EGG));
                        }
                    }
                }
            }
        }
    }
}
