package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

public class PlayerSwapHandItemsListener implements Listener {

    public void PlayerSwapHandItemsHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    //Items are after event
    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent e) {
        ItemStack mainHandItem = e.getMainHandItem();
        ItemStack offHandItem = e.getOffHandItem();
        Player p = e.getPlayer();
        //Item going to offhand
        if (offHandItem.hasItemMeta()){
            PersistentDataContainer container = offHandItem.getItemMeta().getPersistentDataContainer();
            String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
            if (itemID != null) {
                switch (itemID) {
                    case
                        "June24Wings" -> {
                        if (!AceItems.disabledWorlds.get("June24Wings").contains(p.getWorld().getName())) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
                        }
                    }
                    case
                        "June24Amulet" -> {
                        if (!AceItems.disabledWorlds.get("June24Amulet").contains(p.getWorld().getName())) {
                            BukkitScheduler scheduler = Bukkit.getScheduler();
                            scheduler.runTaskTimer(AceItems.getInstance(), task -> {
                                if (!p.isOnline())
                                    task.cancel();
                                ItemStack offHand = p.getInventory().getItemInOffHand();
                                if (!offHand.hasItemMeta()) {
                                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                    task.cancel();
                                }
                                if (!offHand.getItemMeta().getPersistentDataContainer().has(AceItems.keyItemID, PersistentDataType.STRING)) {
                                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                    task.cancel();
                                }
                                if (!Objects.equals(offHand.getItemMeta().getPersistentDataContainer().get(AceItems.keyItemID, PersistentDataType.STRING), "June24Amulet")) {
                                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                    task.cancel();
                                }

                                if (p.getWorld().isDayTime()) {
                                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0));
                                } else {
                                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 0));
                                }
                            }, 0L, 100L);
                        }
                    }
                    case
                        "UltraJune24Amulet" -> {
                        if (!AceItems.disabledWorlds.get("UltraJune24Amulet").contains(p.getWorld().getName())) {
                            BukkitScheduler scheduler = Bukkit.getScheduler();
                            scheduler.runTaskTimer(AceItems.getInstance(), task -> {
                                if (!p.isOnline())
                                    task.cancel();
                                ItemStack offHand = p.getInventory().getItemInOffHand();
                                if (!offHand.hasItemMeta()) {
                                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                    task.cancel();
                                }
                                if (!offHand.getItemMeta().getPersistentDataContainer().has(AceItems.keyItemID, PersistentDataType.STRING)) {
                                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                    task.cancel();
                                }
                                if (!Objects.equals(offHand.getItemMeta().getPersistentDataContainer().get(AceItems.keyItemID, PersistentDataType.STRING), "UltraJune24Amulet")) {
                                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                    task.cancel();
                                }

                                if (p.getWorld().isDayTime()) {
                                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 1));
                                } else {
                                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1));
                                }
                            }, 0L, 100L);
                        }
                    }
                    case "July24JobsLantern" -> {
                        if (!AceItems.disabledWorlds.get("July24JobsLantern").contains(p.getWorld().getName()))
                            Utils.addPermission(p.getUniqueId(), JulyItemsConfig.July24JobsLanternIncomeBoostPerm);
                    }
                    case "UltraJuly24JobsLantern" -> {
                        if (!AceItems.disabledWorlds.get("UltraJuly24JobsLantern").contains(p.getWorld().getName())) {Utils.addPermission(p.getUniqueId(), "jobs.boost.Brewer.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Brewer.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Brewer.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Miner.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Miner.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Miner.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Woodcutter.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Woodcutter.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Woodcutter.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Weaponsmith.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Weaponsmith.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Weaponsmith.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Crafter.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Crafter.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Crafter.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Digger.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Digger.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Digger.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Engineer.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Engineer.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Engineer.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Explorer.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Explorer.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Explorer.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Farmer.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Farmer.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Farmer.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Enchanter.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Enchanter.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Enchanter.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Cooker.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Cooker.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Cooker.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Builder.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Builder.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Builder.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Trader.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Trader.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Trader.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Tamer.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Tamer.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Tamer.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Taster.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Taster.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Taster.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Florist.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Florist.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Florist.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Hunter.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Hunter.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Hunter.points.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Smelter.exp.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Smelter.money.2");
                            Utils.addPermission(p.getUniqueId(), "jobs.boost.Smelter.points.2");

                        }
                    }
                    case "July24PocketBeacon" -> {
                        if (!JulyItemsConfig.July24PocketBeaconWhitelistedWorlds.contains(p.getWorld().getName())) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
                        }
                    }
                    case "UltraJuly24PocketBeacon" -> {
                        if (!JulyItemsConfig.UltraJuly24PocketBeaconWhitelistedWorlds.contains(p.getWorld().getName())) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1));
                        }
                    }
                    case "July24Fireball" -> {
                        if (!AceItems.disabledWorlds.get("July24Fireball").contains(p.getWorld().getName()))
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 0));
                    }
                    case "July24MoreOPPickaxe", "UltraJuly24MoreOPPickaxe" -> {
                        p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                        p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                    }
                    case "July24SpeedVoucher" ->
                        Utils.removePermission(p.getUniqueId(), JulyItemsConfig.July24SpeedVoucherPerm);
                }
            }
        }
        //Item going to mainhand
        if (mainHandItem.hasItemMeta()){
            PersistentDataContainer container = mainHandItem.getItemMeta().getPersistentDataContainer();
            if (container.has(AceItems.keyItemID, PersistentDataType.STRING)) {
                String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);
                assert itemID != null;
                switch (itemID) {
                    case
                        "June24Wings" ->
                        p.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    case
                        "June24Amulet",
                        "UltraJune24Amulet" -> {
                        p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    }
                    case "July24JobsLantern" -> {
                        if (!AceItems.disabledWorlds.get("July24JobsLantern").contains(p.getWorld().getName()))
                            Utils.removePermission(p.getUniqueId(), JulyItemsConfig.July24JobsLanternIncomeBoostPerm);
                    }
                    case "UltraJuly24JobsLantern" -> {
                        if (!AceItems.disabledWorlds.get("UltraJuly24JobsLantern").contains(p.getWorld().getName()))Utils.removePermission(p.getUniqueId(), "jobs.boost.Brewer.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Brewer.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Brewer.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Miner.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Miner.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Miner.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Woodcutter.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Woodcutter.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Woodcutter.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Weaponsmith.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Weaponsmith.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Weaponsmith.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Crafter.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Crafter.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Crafter.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Digger.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Digger.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Digger.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Engineer.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Engineer.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Engineer.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Explorer.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Explorer.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Explorer.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Farmer.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Farmer.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Farmer.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Enchanter.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Enchanter.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Enchanter.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Cooker.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Cooker.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Cooker.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Builder.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Builder.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Builder.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Trader.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Trader.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Trader.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Tamer.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Tamer.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Tamer.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Taster.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Taster.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Taster.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Florist.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Florist.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Florist.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Hunter.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Hunter.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Hunter.points.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Smelter.exp.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Smelter.money.2");
                        Utils.removePermission(p.getUniqueId(), "jobs.boost.Smelter.points.2");

                    }
                    case "July24PocketBeacon" -> {
                        if (!JulyItemsConfig.July24PocketBeaconWhitelistedWorlds.contains(p.getWorld().getName())) {
                            p.removePotionEffect(PotionEffectType.SPEED);
                            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                            p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                            p.removePotionEffect(PotionEffectType.JUMP);
                            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                            p.removePotionEffect(PotionEffectType.REGENERATION);
                        }
                    }
                    case "UltraJuly24PocketBeacon" -> {
                        if (!JulyItemsConfig.UltraJuly24PocketBeaconWhitelistedWorlds.contains(p.getWorld().getName())) {
                            p.removePotionEffect(PotionEffectType.SPEED);
                            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                            p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                            p.removePotionEffect(PotionEffectType.JUMP);
                            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                            p.removePotionEffect(PotionEffectType.REGENERATION);
                        }
                    }
                    case
                        "July24MoreOPPickaxe" -> {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, -1, 0));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 0));

                    }
                    case
                        "UltraJuly24MoreOPPickaxe" -> {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, -1, 1));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 0));
                    }
                    case "July24Fireball" -> {
                        if (!AceItems.disabledWorlds.get("July24Fireball").contains(p.getWorld().getName()))
                            p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                    }
                    case "July24SpeedVoucher" ->
                        Utils.addPermission(p.getUniqueId(), JulyItemsConfig.July24SpeedVoucherPerm);
                }
            }
        }

    }


}
