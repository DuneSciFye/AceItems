package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

import static me.dunescifye.aceitems.files.JuneItemsConfig.*;
import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.utils.BlockUtils.*;
import static me.dunescifye.aceitems.utils.Utils.*;

public class PlayerBlockBreakListener implements Listener {

    public void PlayerBlockBreakHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    static {
        PotionMeta meta = (PotionMeta) waterBreathingPotion.getItemMeta();
        meta.setBasePotionType(PotionType.WATER_BREATHING);
        waterBreathingPotion.setItemMeta(meta);
    }

    Enchantment[] enchantments = Enchantment.values();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.isCancelled()) return;
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        Block b = e.getBlock();
        if (item.hasItemMeta()){
            PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
            if (container.has(keyItemID)){
                String itemID = container.get(keyItemID, PersistentDataType.STRING);
                if (itemID.equals("June24MoreOPPickaxe")){
                    if (!June24MoreOPPickaxeDisabledWorlds.contains(p.getWorld().getName())) {
                        if (ThreadLocalRandom.current().nextInt(1, June24MoreOPPickaxeWaterBreathingPotionChance) == 1)
                            p.getWorld().dropItemNaturally(b.getLocation(), waterBreathingPotion);
                        if (ThreadLocalRandom.current().nextInt(1, June24MoreOPPickaxeSeaLanternChance) == 1)
                            p.getWorld().dropItemNaturally(b.getLocation(), seaLantern);
                        if (ThreadLocalRandom.current().nextInt(1, June24MoreOPPickaxeFishSpawnerChance) == 1)
                            Utils.runConsoleCommand("silkspawners:ss give " + p.getName() + Utils.randomObject("tropical_fish", "pufferfish", "salmon", "cod")); //1 in 6k
                    }
                } else if (itemID.equals("June24LessOPPickaxe")){
                    if (!June24LessOPPickaxeDisabledWorlds.contains(p.getWorld().getName())) {
                        int randomValue2 = ThreadLocalRandom.current().nextInt(12000);
                        if (ThreadLocalRandom.current().nextInt(1, June24LessOPPickaxeWaterBreathingPotionChance) == 1)
                            p.getWorld().dropItemNaturally(b.getLocation(), waterBreathingPotion);
                        if (ThreadLocalRandom.current().nextInt(1, June24LessOPPickaxeSeaLanternChance) == 1)
                            p.getWorld().dropItemNaturally(b.getLocation(), seaLantern);
                        if (ThreadLocalRandom.current().nextInt(1, June24LessOPPickaxeFishSpawnerChance) == 1)
                            Utils.runConsoleCommand("silkspawners:ss give " + p.getName() + Utils.randomObject("tropical_fish", "pufferfish", "salmon", "cod"));
                        if (ThreadLocalRandom.current().nextInt(1, June24LessOPPickaxeGuardianSpawnerChance) == 1)
                            Utils.runConsoleCommand("silkspawners:ss give " + p.getName() + "guardian");
                    }
                } else if (itemID.equals("June24Axe")){
                    if (!June24AxeDisabledWorlds.contains(p.getWorld().getName())){
                        if (container.has(keyBlockType, PersistentDataType.STRING)){
                            for (Predicate<Block> log : logs){
                                if (log.test(b)){
                                    e.setDropItems(false);
                                    String drop = container.get(keyBlockType, PersistentDataType.STRING);
                                    String material = b.getType().toString();
                                    ItemStack drops;

                                    if (Objects.equals(drop, "")){
                                        drops = new ItemStack(b.getType());
                                    } else {
                                        drops = new ItemStack(Material.getMaterial(material.substring(0, material.length() - 3) + drop), 8);
                                    }
                                    b.getWorld().dropItemNaturally(b.getLocation(), drops);

                                    break;
                                }
                            }
                        }
                    }
                } else if (itemID.equals("UltraJune24Axe")){
                    if (!UltraJune24AxeDisabledWorlds.contains(p.getWorld().getName())) {
                        for (Predicate<Block> log : logs) {
                            if (log.test(b)) {
                                e.setDropItems(false);
                                String drop = container.get(keyBlockType, PersistentDataType.STRING);
                                String material = b.getType().toString();
                                ItemStack drops;

                                if (Objects.equals(drop, "")) {
                                    drops = new ItemStack(b.getType());
                                } else {
                                    drops = new ItemStack(Material.getMaterial(material.substring(0, material.length() - 3) + drop), 16);
                                }
                                b.getWorld().dropItemNaturally(b.getLocation(), drops);

                                break;
                            }
                        }
                    }
                } else if (itemID.equals("June24Shovel")){
                    if (!June24ShovelDisabledWorlds.contains(p.getWorld().getName())) {
                        if (container.get(keyRadius, PersistentDataType.INTEGER) > 0){
                            breakInRadius(b, 1, p, shovelWhitelist);
                        }
                        if (ThreadLocalRandom.current().nextInt(1, June24ShovelGlowstoneChance) == 1)
                            p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GLOWSTONE));
                        if (ThreadLocalRandom.current().nextInt(1, June24ShovelFroglightChance) == 1){
                            int random = ThreadLocalRandom.current().nextInt(0,2);
                            if (random == 0)
                                p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.OCHRE_FROGLIGHT));
                            else if (random == 1)
                                p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.PEARLESCENT_FROGLIGHT));
                            else
                                p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.VERDANT_FROGLIGHT));
                        }
                        if (ThreadLocalRandom.current().nextInt(1, June24ShovelSeaLanternChance) == 1)
                            p.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.SEA_LANTERN));
                    }
                } else if (itemID.equals("June24Hoe")){
                    if (!June24HoeDisabledWorlds.contains(p.getWorld().getName())) {
                        if (b.getBlockData() instanceof Ageable ageable) {
                            if (ageable.getAge() == ageable.getMaximumAge()) {
                                Material material = b.getType();
                                Bukkit.getScheduler().runTask(AceItems.getInstance(), () -> {
                                    b.setType(material);
                                    int random = ThreadLocalRandom.current().nextInt(3000);
                                    if (random == 0) {
                                        Enchantment randomEnchant = enchantments[ThreadLocalRandom.current().nextInt(enchantments.length)];
                                        ItemStack enchantmentBook = new ItemStack(Material.ENCHANTED_BOOK);
                                        EnchantmentStorageMeta enchantmentMeta = (EnchantmentStorageMeta) enchantmentBook.getItemMeta();

                                        enchantmentMeta.addStoredEnchant(randomEnchant, 1 + ThreadLocalRandom.current().nextInt(randomEnchant.getMaxLevel()), true);
                                        enchantmentBook.setItemMeta(enchantmentMeta);

                                        b.getWorld().dropItemNaturally(b.getLocation(), enchantmentBook);
                                    }

                                });
                            }
                        }
                    }
                }

                //3x3 Mining for all tools
                if (container.has(keyRadius)){
                    if (container.get(keyRadius, PersistentDataType.INTEGER) > 0){
                        breakInRadius(b, 1, p, pickaxeWhitelist, pickaxeBlacklist);
                    }
                }
            }
        }
        ItemStack offhandItem = p.getInventory().getItemInOffHand();
        if (offhandItem != null){
            if (offhandItem.hasItemMeta()){
                PersistentDataContainer container = offhandItem.getItemMeta().getPersistentDataContainer();
                if (container.has(keyItemID, PersistentDataType.STRING)){
                    String itemID = container.get(keyItemID, PersistentDataType.STRING);
                    if (itemID.equals("June24TechnologicalAdvancement")){
                        if (!June24TechnologicalAdvancementDisabledWorlds.contains(p.getWorld().getName())) {
                            int random = ThreadLocalRandom.current().nextInt(500);
                            if (random == 0) {
                                PotionEffect potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 2);
                                p.addPotionEffect(potionEffect);
                            }
                        }
                    } else if (itemID.equals("UltraJune24TechnologicalAdvancement")){
                        if (!UltraJune24TechnologicalAdvancementDisabledWorlds.contains(p.getWorld().getName())) {
                            int random = ThreadLocalRandom.current().nextInt(300);
                            if (random == 0) {
                                PotionEffect potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 2);
                                p.addPotionEffect(potionEffect);
                            }
                        }
                    }
                }
            }
        }

    }

}
