package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
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
                if (container.has(AceItems.keyItemID, PersistentDataType.STRING)){
                    String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
                    assert itemID != null;
                    if (itemID.equals("June24FishingRod")){
                        if (!AceItems.disabledWorlds.get("June24FishingRod").contains(p.getWorld().getName())) {
                            June24FishingRod(p, June24FishingRodSeaLanternChance, June24FishingRodFishSpawnEggChance, June24FishingRodFroglightChance);
                        }
                    } else if (itemID.equals("UltraJune24FishingRod")) {
                        if (!AceItems.disabledWorlds.get("UltraJune24FishingRod").contains(p.getWorld().getName())) {
                            June24FishingRod(p, UltraJune24FishingRodSeaLanternChance, UltraJune24FishingRodFishSpawnEggChance, UltraJune24FishingRodFroglightChance);
                            if (ThreadLocalRandom.current().nextInt(UltraJune24FishingRodGuardianSpawnEggChance) == 0)
                                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.GUARDIAN_SPAWN_EGG));
                        }
                    }
                }
            }
        }
    }

    private void June24FishingRod(Player p, int SeaLanternChance, int FishSpawnEggChance, int FroglightChance) {
        if (ThreadLocalRandom.current().nextInt(SeaLanternChance) == 0)
            p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.SEA_LANTERN));
        if (ThreadLocalRandom.current().nextInt(FishSpawnEggChance) == 0)
            p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack((Material) randomObject(Material.TROPICAL_FISH_SPAWN_EGG, Material.PUFFERFISH_SPAWN_EGG, Material.SALMON_SPAWN_EGG, Material.COD_SPAWN_EGG)));
        if (ThreadLocalRandom.current().nextInt(FroglightChance) == 0)
            p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack((Material) randomObject(Material.OCHRE_FROGLIGHT, Material.PEARLESCENT_FROGLIGHT, Material.VERDANT_FROGLIGHT)));
    }
}
