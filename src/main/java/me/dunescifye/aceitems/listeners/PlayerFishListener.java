package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.utils.CooldownManager;
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

import java.time.Duration;
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
        ItemStack item = p.getInventory().getItemInMainHand();

        if (!item.hasItemMeta()) return;

        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
        if (itemID == null) return;


        switch (state) {
            case CAUGHT_FISH -> {
                switch (itemID) {
                    case "June24FishingRod" -> {
                        if (!AceItems.disabledWorlds.get("June24FishingRod").contains(p.getWorld().getName()))
                            June24FishingRod(p, June24FishingRodSeaLanternChance, June24FishingRodFishSpawnEggChance, June24FishingRodFroglightChance);
                    }
                    case "UltraJune24FishingRod" -> {
                        if (!AceItems.disabledWorlds.get("UltraJune24FishingRod").contains(p.getWorld().getName())) {
                            June24FishingRod(p, UltraJune24FishingRodSeaLanternChance, UltraJune24FishingRodFishSpawnEggChance, UltraJune24FishingRodFroglightChance);
                            if (ThreadLocalRandom.current().nextInt(UltraJune24FishingRodGuardianSpawnEggChance) == 0)
                                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.GUARDIAN_SPAWN_EGG));
                        }
                    }
                    case "July24FishingRod" -> {
                        if (!AceItems.disabledWorlds.get("July24FishingRod").contains(p.getWorld().getName())) {
                            if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24FishingRodFishKeyChance) == 0)
                                Utils.runConsoleCommand(JulyItemsConfig.July24FishingRodFishKeyCommand.replace("%player%", p.getName()));
                            if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24FishingRodSalmonSpawnEggChance) == 0)
                                Utils.dropItems(p.getLocation(), p.getUniqueId(), new ItemStack(Material.SALMON_SPAWN_EGG));
                            if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24FishingRodAxolotlSpawnEggChance) == 0)
                                Utils.dropItems(p.getLocation(), p.getUniqueId(), new ItemStack(Material.AXOLOTL_SPAWN_EGG));
                            if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24FishingRodTurtleSpawnEggChance) == 0)
                                Utils.dropItems(p.getLocation(), p.getUniqueId(), new ItemStack(Material.TURTLE_SPAWN_EGG));
                            if (ThreadLocalRandom.current().nextInt(JulyItemsConfig.July24FishingRodSquidSpawnEggChance) == 0)
                                Utils.dropItems(p.getLocation(), p.getUniqueId(), new ItemStack(Material.SQUID_SPAWN_EGG));
                        }
                    }
                }
            }
            case IN_GROUND -> {
                switch (itemID) {
                    case "July24GrapplingHook" -> {
                        if (CooldownManager.hasCooldown(CooldownManager.July24GrapplingHookSelfLaunchCooldowns, p.getUniqueId())) {
                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(CooldownManager.July24GrapplingHookSelfLaunchCooldowns, p.getUniqueId()));
                        } else {
                            p.setVelocity(e.getHook().getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(JulyItemsConfig.July24GrapplingHookSelfLaunchStrength));
                            CooldownManager.setCooldown(CooldownManager.July24GrapplingHookSelfLaunchCooldowns, p.getUniqueId(), Duration.ofSeconds(JulyItemsConfig.July24GrapplingHookSelfLaunchCooldown));
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
