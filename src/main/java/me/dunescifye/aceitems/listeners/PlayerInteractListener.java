package me.dunescifye.aceitems.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.files.JulyItemsConfig;
import me.dunescifye.aceitems.libs.Laser;
import me.dunescifye.aceitems.utils.BlockUtils;
import me.dunescifye.aceitems.utils.CooldownManager;
import me.dunescifye.aceitems.utils.Utils;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.block.ShulkerBox;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

import static me.dunescifye.aceitems.files.Config.*;
import static me.dunescifye.aceitems.utils.BlockUtils.*;
import static me.dunescifye.aceitems.utils.CooldownManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

public class PlayerInteractListener implements Listener {

    public static final Map<UUID, BukkitTask> confirm = new HashMap<>();

    public void PlayerInteractHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        Player p = e.getPlayer();

        if (item == null || !item.hasItemMeta()) return;

        Block b = e.getClickedBlock();

        ItemMeta meta = e.getItem().getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        String itemID = container.get(AceItems.keyItemID, PersistentDataType.STRING);

        if (itemID == null) return;

        Action action = e.getAction();
        //Right or left click, sneaking
        if (p.isSneaking()) {
            switch (itemID) {
                case "July24PaintBrush", "UltraJuly24PaintBrush" -> Utils.updateKey(p, item, meta, container, AceItems.keyState, AceItems.keyStateLore, "Color", "WHITE", "White", "LIGHT_GRAY", "Light Gray", "GRAY", "Gray", "BLACK", "Black", "BROWN", "Brown", "RED", "Red", "ORANGE", "Orange", "YELLOW", "Yellow", "LIME", "Lime", "GREEN", "Green", "CYAN", "Cyan", "LIGHT_BLUE", "Light Blue", "BLUE", "Blue", "MAGENTA", "Magenta", "PURPLE", "Purple", "PINK", "Pink");
            }
        }
        //Right or left click, not sneaking
        else {
            if (b != null) {
                switch (itemID) {
                    case "July24PaintBrush" -> {
                        if (CooldownManager.hasCooldown(July24PaintBrushCooldowns, p.getUniqueId())) {
                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(July24PaintBrushCooldowns, p.getUniqueId()));
                        } else {
                            if (!(b.getState() instanceof ShulkerBox)) {
                                b.setType(Objects.requireNonNull(Material.getMaterial(changeColor(b.getType().toString(), container.get(AceItems.keyState, PersistentDataType.STRING)))));
                                int uses = container.get(AceItems.keyUses, PersistentDataType.INTEGER);
                                if (uses > 1) {
                                    container.set(AceItems.keyUses, PersistentDataType.INTEGER, uses - 1);
                                } else {
                                    container.set(AceItems.keyUses, PersistentDataType.INTEGER, JulyItemsConfig.July24PaintBrushUses);
                                }
                                item.setItemMeta(meta);
                            }
                        }
                    }
                    case "UltraJuly24PaintBrush" -> {
                        if (!AceItems.disabledWorlds.get("June24PropTool").contains(p.getWorld().getName())) {
                            if (!(b instanceof ShulkerBox)) {
                                b.setType(Objects.requireNonNull(Material.getMaterial(changeColor(b.getType().toString(), container.get(AceItems.keyState, PersistentDataType.STRING)))));
                            }
                        }
                    }
                }
            }
        }
        if (action.isRightClick()){
            switch (itemID) {
                case
                    "June24MoreOPPickaxe",
                    "June24LessOPPickaxe",
                    "June24Shovel" -> {
                    Integer radius = container.get(AceItems.keyRadius, PersistentDataType.INTEGER);
                    String oldText = meta.getPersistentDataContainer().get(AceItems.keyRadiusLore, PersistentDataType.STRING);
                    String newText = inputOutput(radius, 0, "3x3", 1, "1x1");
                    sendPlayerChangeVariableMessage(p, changeVariableMessage, "Mining Mode", newText);
                    meta.getPersistentDataContainer().set(AceItems.keyRadius, PersistentDataType.INTEGER, Integer.valueOf(inputOutputCycle(radius, 0, 1, 0)));
                    meta.getPersistentDataContainer().set(AceItems.keyRadiusLore, PersistentDataType.STRING, newText);
                    meta.lore(updateLore(item, oldText, newText));
                    item.setItemMeta(meta);
                }
                case
                    "UltraJune24MoreOPPickaxe" -> {
                    Integer radius = container.get(AceItems.keyRadius, PersistentDataType.INTEGER);
                    String oldText = meta.getPersistentDataContainer().get(AceItems.keyRadiusLore, PersistentDataType.STRING);
                    String newText = inputOutput(radius, 0, "3x3", 1, "5x5", 2, "1x1");
                    sendPlayerChangeVariableMessage(p, changeVariableMessage, "Mining Mode", newText);
                    meta.getPersistentDataContainer().set(AceItems.keyRadius, PersistentDataType.INTEGER, Integer.valueOf(inputOutputCycle(radius, 0, 1, 2, 0)));
                    meta.getPersistentDataContainer().set(AceItems.keyRadiusLore, PersistentDataType.STRING, newText);
                    meta.lore(updateLore(item, oldText, newText));
                    item.setItemMeta(meta);
                }
                case
                    "June24QuartzWand",
                    "UltraJune24QuartzWand" -> {
                    if (p.isSneaking()) {
                        String currentVariant = container.get(AceItems.keyState, PersistentDataType.STRING);
                        String oldText = container.get(AceItems.keyStateLore, PersistentDataType.STRING);
                        String newText = inputOutputCycle(oldText,
                            "Quartz Block",
                            "Quartz Stairs",
                            "Quartz Slab",
                            "Chiseled Quartz Block",
                            "Quartz Bricks",
                            "Quartz Pillar",
                            "Smooth Quartz",
                            "Smooth Quartz Stairs",
                            "Smooth Quartz Slab",
                            "Quartz Block");
                        sendPlayerChangeVariableMessage(p, changeVariableMessage, "Quartz Type", newText);
                        meta.getPersistentDataContainer().set(AceItems.keyStateLore, PersistentDataType.STRING, newText);
                        meta.getPersistentDataContainer().set(AceItems.keyState, PersistentDataType.STRING, inputOutputCycle(currentVariant,
                            "QUARTZ_BLOCK",
                            "QUARTZ_STAIRS",
                            "QUARTZ_SLAB",
                            "CHISELED_QUARTZ_BLOCK",
                            "QUARTZ_BRICKS",
                            "QUARTZ_PILLAR",
                            "SMOOTH_QUARTZ",
                            "SMOOTH_QUARTZ_STAIRS",
                            "SMOOTH_QUARTZ_SLAB",
                            "QUARTZ_BLOCK"));
                        meta.lore(updateLore(item, oldText, newText));
                        item.setItemMeta(meta);
                    }
                }
                case
                    "June24PropTool" -> {
                    if (!AceItems.disabledWorlds.get("June24PropTool").contains(p.getWorld().getName())) {
                        if (!p.isSneaking()) {
                            if (b != null) {
                                if (hasCooldown(June24PropToolCooldowns, p.getUniqueId())) {
                                    sendCooldownMessage(p, getRemainingCooldown(June24PropToolCooldowns, p.getUniqueId()));
                                } else {
                                    setCooldown(June24PropToolCooldowns, p.getUniqueId(), Duration.ofMinutes(15));
                                    MiscDisguise disguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, Material.getMaterial((String.valueOf(b.getType()))));
                                    disguise.setEntity(p);
                                    disguise.startDisguise();
                                }
                            }
                        } else {
                            DisguiseAPI.undisguiseToAll(p);
                        }
                    }
                }
                case
                    "UltraJune24PropTool" -> {
                    if (!AceItems.disabledWorlds.get("UltraJune24PropTool").contains(p.getWorld().getName())) {
                        if (!p.isSneaking()) {
                            if (b != null) {
                                MiscDisguise disguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, Material.getMaterial((String.valueOf(b.getType()))));
                                disguise.setEntity(p);
                                disguise.startDisguise();
                            }
                        } else {
                            DisguiseAPI.undisguiseToAll(p);
                        }
                    }
                }
                case
                    "June24Axe",
                    "UltraJune24Axe" -> {
                    if (p.isSneaking()) {
                        String oldText = meta.getPersistentDataContainer().get(AceItems.keyStateLore, PersistentDataType.STRING);
                        String newText = inputOutputCycle(oldText,
                            "Original",
                            "Planks",
                            "Stairs",
                            "Slab",
                            "Fence",
                            "Fence Gate",
                            "Door",
                            "Trapdoor",
                            "Pressure Plate",
                            "Button",
                            "Original");
                        sendPlayerChangeVariableMessage(p, changeVariableMessage, "Drop", newText);
                        meta.getPersistentDataContainer().set(AceItems.keyState, PersistentDataType.STRING, inputOutput(oldText,
                            "Original",
                            "PLANKS",
                            "Planks",
                            "STAIRS",
                            "Stairs",
                            "SLAB",
                            "Slab",
                            "FENCE",
                            "Fence",
                            "FENCE_GATE",
                            "Fence Gate",
                            "DOOR",
                            "Door",
                            "TRAPDOOR",
                            "Trapdoor",
                            "PRESSURE_PLATE",
                            "Pressure Plate",
                            "BUTTON",
                            "Button",
                            ""));
                        meta.getPersistentDataContainer().set(AceItems.keyStateLore, PersistentDataType.STRING, newText);
                        meta.lore(updateLore(item, oldText, newText));
                        item.setItemMeta(meta);
                    }
                }
                case "June24MoreOPSword" -> {
                    if (p.isSneaking() && !AceItems.disabledWorlds.get("June24MoreOPSword").contains(p.getWorld().getName())) {
                        int kills = container.get(AceItems.keyKills, PersistentDataType.INTEGER);

                        if (kills > 9) {
                            meta.getPersistentDataContainer().set(AceItems.keyItemID, PersistentDataType.INTEGER, 0);
                            Utils.strikeLightningAroundLocation(p.getLocation(), 3);
                            meta.lore(updateLore(item, String.valueOf(kills), "0"));
                            item.setItemMeta(meta);
                        }

                    }
                }
                case
                    "UltraJune24MoreOPSword" -> {
                    if (!AceItems.disabledWorlds.get("UltraJune24MoreOPSword").contains(p.getWorld().getName())) {
                        if (hasCooldown(UltraJune24MoreOPSwordCooldowns, p.getUniqueId())) {
                            sendCooldownMessage(p, getRemainingCooldown(UltraJune24MoreOPSwordCooldowns, p.getUniqueId()));
                        } else {
                            setCooldown(UltraJune24MoreOPSwordCooldowns, p.getUniqueId(), Duration.ofSeconds(5));
                            Laser laser = null;
                            try {
                                Location pLocation = p.getEyeLocation();
                                Vector direction = pLocation.getDirection();
                                PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, 80, 0);
                                laser = new Laser.GuardianLaser(pLocation, pLocation.clone().add(direction.clone().multiply(8)), 2, 8);
                                laser.start(AceItems.getInstance());
                                for (double i = 0; i < 8; i += 0.5) {
                                    Location checkLocation = pLocation.clone().add(direction.clone().multiply(i));
                                    List<Entity> nearbyEntities = (List<Entity>) checkLocation.getWorld().getNearbyEntities(checkLocation, 0.5, 0.5, 0.5);
                                    for (Entity entity : nearbyEntities) {
                                        if (entity != p && entity.getType() != EntityType.ARMOR_STAND && entity instanceof LivingEntity livingEntity) {
                                            livingEntity.damage(1.5, p);
                                            double health = livingEntity.getHealth();
                                            if (health - 1.5 <= 0) {
                                                livingEntity.damage(Integer.MAX_VALUE);
                                            } else {
                                                livingEntity.setHealth(health - 1.5);
                                            }
                                            livingEntity.addPotionEffect(slowness);
                                            if (entity instanceof Player) {
                                                UltraJune24MoreOPSwordTagged.put(entity.getUniqueId(), p.getUniqueId());
                                                Bukkit.getScheduler().runTaskLater(AceItems.getInstance(), () -> UltraJune24MoreOPSwordTagged.remove(entity.getUniqueId()), 100L);
                                            }
                                        }
                                    }
                                }

                            } catch (
                                ReflectiveOperationException ignored) {
                            }
                            assert laser != null;
                        }
                    }
                }
                case
                    "June24GuardianBeam" -> {
                    if (!AceItems.disabledWorlds.get("June24GuardianBeam").contains(p.getWorld().getName())) {
                        if (p.isSneaking()) {
                            if (hasCooldown(June24GuardianBeamGetItemsCooldowns, p.getUniqueId())) {
                                sendCooldownMessage(p, getRemainingCooldown(June24GuardianBeamGetItemsCooldowns, p.getUniqueId()));
                            } else {
                                setCooldown(June24GuardianBeamGetItemsCooldowns, p.getUniqueId(), Duration.ofMinutes(20));
                                List<ItemStack> possibleItems = new ArrayList<>();

                                possibleItems.add(new ItemStack(Material.PRISMARINE, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.PRISMARINE_STAIRS, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.PRISMARINE_SLAB, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.PRISMARINE_WALL, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.PRISMARINE_BRICKS, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.PRISMARINE_BRICK_STAIRS, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.PRISMARINE_BRICK_SLAB, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.SEA_LANTERN, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.DARK_PRISMARINE, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.DARK_PRISMARINE_STAIRS, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.DARK_PRISMARINE_SLAB, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.PRISMARINE_SHARD, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.PRISMARINE_CRYSTALS, ThreadLocalRandom.current().nextInt(1, 33)));
                                possibleItems.add(new ItemStack(Material.SPONGE, ThreadLocalRandom.current().nextInt(1, 8)));

                                int numberOfDrops = ThreadLocalRandom.current().nextInt(3, 6);

                                for (int i = 0; i < numberOfDrops; i++) {
                                    Item itemToDrop = p.getWorld().dropItemNaturally(p.getLocation(), possibleItems.get(ThreadLocalRandom.current().nextInt(possibleItems.size())));
                                    itemToDrop.setPickupDelay(0);
                                }
                            }
                        } else {
                            if (June24GuardianBeamTagged.containsKey(p.getUniqueId())) {
                                Player tagged = June24GuardianBeamTagged.get(p.getUniqueId());
                                tagged.addPotionEffect(new PotionEffect(ThreadLocalRandom.current().nextDouble() < 0.5 ? PotionEffectType.BLINDNESS : PotionEffectType.SLOW, 80, 0));
                                p.teleport(tagged);
                                June24GuardianBeamTagged.remove(p.getUniqueId());
                            }
                        }
                    }
                }
                case
                    "June24BlockWand" -> {
                    if (!AceItems.disabledWorlds.get("June24BlockWand").contains(p.getWorld().getName())) {
                        if (hasCooldown(June24BlockWandCooldowns, p.getUniqueId())) {
                            sendCooldownMessage(p, getRemainingCooldown(June24BlockWandCooldowns, p.getUniqueId()));
                        } else {
                            setCooldown(June24BlockWandCooldowns, p.getUniqueId(), Duration.ofMinutes(20));
                            Material randomBlock = obtainableBlocks.get(ThreadLocalRandom.current().nextInt(obtainableBlocks.size()));
                            p.sendMessage(Component.text("You received " + randomBlock.toString().toLowerCase().replace("_", " ") + ".", GREEN));
                            ItemStack itemStack = new ItemStack(randomBlock, ThreadLocalRandom.current().nextInt(1, 129));
                            Utils.dropItems(p.getLocation(), itemStack);
                        }
                    }
                }
                case
                    "UltraJune24BlockWand" -> {
                    if (!AceItems.disabledWorlds.get("UltraJune24BlockWand").contains(p.getWorld().getName())) {
                        if (hasCooldown(UltraJune24BlockWandCooldowns, p.getUniqueId())) {
                            sendCooldownMessage(p, getRemainingCooldown(UltraJune24BlockWandCooldowns, p.getUniqueId()));
                        } else {
                            setCooldown(UltraJune24BlockWandCooldowns, p.getUniqueId(), Duration.ofMinutes(10));
                            Material randomBlock = obtainableBlocks.get(ThreadLocalRandom.current().nextInt(obtainableBlocks.size()));
                            p.sendMessage(Component.text("You received " + randomBlock.toString().toLowerCase().replace("_", " ") + ".", GREEN));
                            ItemStack itemStack = new ItemStack(randomBlock, ThreadLocalRandom.current().nextInt(1, 129));
                            Utils.dropItems(p.getLocation(), itemStack);
                        }
                    }
                }
                case
                    "June24LeafBlower" -> {
                    if (!AceItems.disabledWorlds.get("June24LeafBlower").contains(p.getWorld().getName())) {
                        String leafType = container.get(AceItems.keyState, PersistentDataType.STRING);
                        Bukkit.getScheduler().runTask(AceItems.getInstance(), () -> {
                            if (p.isSneaking()) {
                                sendPlayerChangeVariableMessage(p, changeVariableMessage, "Leaf Type", inputOutput(leafType, "OAK", "Spruce", "SPRUCE", "Jungle", "JUNGLE", "Dark Oak", "DARK_OAK", "Birch", "BIRCH", "Acacia", "ACACIA", "Mangrove", "MANGROVE", "Cherry", "CHERRY", "Azalea", "AZALEA", "Flowering Azalea", "FLOWERING_AZALEA", "Oak"));
                                meta.getPersistentDataContainer().set(AceItems.keyState, PersistentDataType.STRING, inputOutputCycle(leafType, "OAK", "SPRUCE", "JUNGLE", "DARK_OAK", "BIRCH", "ACACIA", "MANGROVE", "CHERRY", "AZALEA", "FLOWERING_AZALEA", "OAK"));
                                meta.getPersistentDataContainer().set(AceItems.keyStateLore, PersistentDataType.STRING, inputOutput(leafType, "OAK", "Spruce", "SPRUCE", "Jungle", "JUNGLE", "Dark Oak", "DARK_OAK", "Birch", "BIRCH", "Acacia", "ACACIA", "Mangrove", "MANGROVE", "Cherry", "CHERRY", "Azalea", "AZALEA", "Flowering Azalea", "FLOWERING_AZALEA", "Oak"));
                                item.setItemMeta(meta);
                            } else if (b != null) {
                                Block blockRelative = b.getRelative(e.getBlockFace());
                                Claim claim = GriefPrevention.instance.dataStore.getClaimAt(blockRelative.getLocation(), true, null);
                                if (blockRelative.getType() == Material.AIR && (claim == null || claim.getOwnerID().equals(p.getUniqueId()))) {
                                    blockRelative.setType(Material.valueOf(leafType + "_LEAVES"));
                                    if (blockRelative.getBlockData() instanceof Leaves leaves) {
                                        leaves.setPersistent(true);
                                        blockRelative.setBlockData(leaves);
                                    }
                                } else {
                                    sendClaimMessage(p);
                                }
                            }
                        });
                    }
                }
                case
                    "June24LavaBucket" -> {
                    if (!AceItems.disabledWorlds.get("June24LavaBucket").contains(p.getWorld().getName())) {
                        if (b != null) {
                            if (p.isSneaking()) {
                                if (hasCooldown(June24LavaWaterBucketCooldowns, p.getUniqueId())) {
                                    sendCooldownMessage(p, getRemainingCooldown(June24LavaWaterBucketCooldowns, p.getUniqueId()));
                                } else {
                                    setCooldown(June24LavaWaterBucketCooldowns, p.getUniqueId(), Duration.ofMinutes(5));
                                    removeInRadius(b, 3, getWater());
                                }
                            } else {
                                Block blockRelative = b.getRelative(e.getBlockFace());
                                Claim claim = GriefPrevention.instance.dataStore.getClaimAt(blockRelative.getLocation(), true, null);
                                if (claim != null) {
                                    if (claim.getOwnerID().equals(p.getUniqueId())) {
                                        blockRelative.setType(Material.WATER);
                                    } else {
                                        sendClaimMessage(p);
                                    }
                                } else {
                                    blockRelative.setType(Material.WATER);
                                }
                            }
                        }
                    }
                }
                case
                    "UltraJune24LavaWaterBucket" -> {
                    if (!AceItems.disabledWorlds.get("UltraJune24LavaWaterBucket").contains(p.getWorld().getName())) {
                        if (b != null) {
                            if (p.isSneaking()) {
                                if (hasCooldown(UltraJune24LavaWaterBucketCooldowns, p.getUniqueId())) {
                                    sendCooldownMessage(p, getRemainingCooldown(UltraJune24LavaWaterBucketCooldowns, p.getUniqueId()));
                                } else {
                                    setCooldown(UltraJune24LavaWaterBucketCooldowns, p.getUniqueId(), Duration.ofSeconds(30));
                                    removeInRadius(b, 3, getWater());
                                }
                            } else {
                                Block blockRelative = b.getRelative(e.getBlockFace());
                                Claim claim = GriefPrevention.instance.dataStore.getClaimAt(blockRelative.getLocation(), true, null);
                                if (claim != null) {
                                    if (claim.getOwnerID().equals(p.getUniqueId())) {
                                        blockRelative.setType(Material.WATER);
                                    } else {
                                        sendClaimMessage(p);
                                    }
                                } else {
                                    blockRelative.setType(Material.WATER);
                                }
                            }
                        }
                    }
                }
                case
                    "July24Saddle" -> {
                    if (p.isSneaking()) {
                        if (CooldownManager.hasCooldown(July24SaddleLaunchCooldowns, p.getUniqueId())) {
                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(July24SaddleLaunchCooldowns, p.getUniqueId()));
                        } else {
                            CooldownManager.setCooldown(July24SaddleLaunchCooldowns, p.getUniqueId(), Duration.ofSeconds(JulyItemsConfig.July24SaddleLaunchCooldown));
                            p.setVelocity(new Vector(0, JulyItemsConfig.July24SaddleLaunchStrength, 0));
                        }
                    }
                }
                case
                    "July24SpawnerBundle" -> {
                    if (container.has(AceItems.keyUses, PersistentDataType.INTEGER)) {
                        String spawnerType = randomString(JulyItemsConfig.July24SpawnerBundleMobTypes).toString();
                        Utils.runConsoleCommand(spawnerCommand.replace("%player%", p.getName()).replace("%type%", spawnerType));
                        p.sendMessage(Component.text("You received a " + spawnerType + " spawner!", GREEN));
                        int uses = container.get(AceItems.keyUses, PersistentDataType.INTEGER);
                        if (uses < 2) {
                            container.set(AceItems.keyUses, PersistentDataType.INTEGER, JulyItemsConfig.July24SpawnerBundleUses);
                            item.setItemMeta(meta);
                            item.setAmount(item.getAmount() - 1);
                        } else {
                            container.set(AceItems.keyUses, PersistentDataType.INTEGER, uses - 1);
                            item.setItemMeta(meta);
                        }
                    }
                }
                case
                    "July24MelonWand" -> {
                    if (p.isSneaking()) {
                        if (CooldownManager.hasCooldown(July24MelonWandCooldowns, p.getUniqueId())) {
                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(July24MelonWandCooldowns, p.getUniqueId()));
                        } else {
                            accelerateMelonGrowth(p.getLocation().getBlock(), JulyItemsConfig.July24MelonWandRadius);
                            CooldownManager.setCooldown(July24MelonWandCooldowns, p.getUniqueId(), Duration.ofSeconds(JulyItemsConfig.July24MelonWandCooldown));
                        }
                    } else
                        Utils.updateKey(p, item, meta, container, AceItems.keyState, AceItems.keyStateLore, "Dirt Type", "PODZOL", "Podzol", "DIRT", "Dirt", "COARSE_DIRT", "Coarse Dirt", "FARMLAND", "Farmland", "DIRT_PATH", "Dirt Path", "GRASS_BLOCK", "Grass Block");
                }
                case
                    "UltraJuly24MelonWand" -> {
                    if (p.isSneaking()) {
                        if (CooldownManager.hasCooldown(UltraJuly24MelonWandCooldowns, p.getUniqueId())) {
                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(UltraJuly24MelonWandCooldowns, p.getUniqueId()));
                        } else {
                            accelerateMelonGrowth(p.getLocation().getBlock(), JulyItemsConfig.UltraJuly24MelonWandRadius);
                            CooldownManager.setCooldown(UltraJuly24MelonWandCooldowns, p.getUniqueId(), Duration.ofSeconds(JulyItemsConfig.UltraJuly24MelonWandCooldown));
                        }
                    } else
                        Utils.updateKey(p, item, meta, container, AceItems.keyState, AceItems.keyStateLore, "Dirt Type", "PODZOL", "Podzol", "DIRT", "Dirt", "COARSE_DIRT", "Coarse Dirt", "FARMLAND", "Farmland", "DIRT_PATH", "Dirt Path", "GRASS_BLOCK", "Grass Block");
                }
                case
                    "July24MoreOPPickaxe" -> {
                    if (p.isSneaking())
                        Utils.updateKey(p, item, meta, container, AceItems.keyRadius, AceItems.keyRadiusLore, "Mining Mode", 0, "1x1", 1, "3x3");
                }
                case
                    "UltraJuly24MoreOPPickaxe" -> {
                    if (p.isSneaking())
                        Utils.updateKey(p, item, meta, container, AceItems.keyRadius, AceItems.keyRadiusLore, "Mining Mode", 0, "1x1", 1, "3x3", 2, "5x5");
                }
                case "July24Hoe" -> {
                    if (p.isSneaking())
                        Utils.updateKey(p, item, meta, container, AceItems.keyRadius, AceItems.keyRadiusLore, "Farming Mode", 0, "1x1", 1, "3x3");
                }
                case "UltraJuly24Hoe" -> {
                    if (p.isSneaking())
                        Utils.updateKey(p, item, meta, container, AceItems.keyRadius, AceItems.keyRadiusLore, "Farming Mode", 0, "1x1", 1, "3x3", 2, "5x5");
                }
                case
                    "July24BeachMaker" -> {
                    if (p.isSneaking() && !AceItems.disabledWorlds.get("July24BeachMaker").contains(p.getWorld().getName())) {
                        if (CooldownManager.hasCooldown(July24BeachMakerCooldowns, p.getUniqueId())) {
                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(July24BeachMakerCooldowns, p.getUniqueId()));
                        } else {
                            CooldownManager.setCooldown(July24BeachMakerCooldowns, p.getUniqueId(), Duration.ofSeconds(JulyItemsConfig.July24BeachMakerCooldown));
                            July24BeachMaker(p, 5);
                        }
                    }
                }
                case
                    "UltraJuly24BeachMaker" -> {
                    if (p.isSneaking() && !AceItems.disabledWorlds.get("July24BeachMaker").contains(p.getWorld().getName())) {
                        July24BeachMaker(p, 10);
                    }
                }
                case
                    "July24SlimeWand" -> {
                    if (p.isSneaking()) {
                        int currentUses = container.get(AceItems.keyUses, PersistentDataType.INTEGER);
                        if (currentUses < 64) {
                            p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(JulyItemsConfig.July24SlimeWandOutOfUsesMessage));
                        } else {
                            container.set(AceItems.keyUses, PersistentDataType.INTEGER, currentUses - 64);
                            meta.lore(updateLore(item, String.valueOf(currentUses), String.valueOf(currentUses - 64)));
                            item.setItemMeta(meta);
                            List<Slime> slimes = new ArrayList<>();
                            //Spawn slimes
                            for (int i = 0; i < 5; i++) {
                                slimes.add(p.getWorld().spawn(p.getLocation(), Slime.class));
                            }

                            Bukkit.getScheduler().runTaskLater(AceItems.getInstance(), () -> {
                                for (Entity target : p.getNearbyEntities(20.0, 20.0, 20.0)) {
                                    if (target instanceof Player nearbyPlayer && nearbyPlayer != p) {
                                        for (Slime slime : slimes) {
                                            slime.setTarget(nearbyPlayer);
                                        }
                                        break;
                                    }
                                }
                            }, 20L);

                            Bukkit.getScheduler().runTaskLater(AceItems.getInstance(), () -> {
                                for (Slime slime : slimes) {
                                    slime.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, slime.getLocation(), 3);
                                    for (Entity entity : slime.getNearbyEntities(3, 3, 3)) {
                                        if (entity instanceof LivingEntity livingEntity && livingEntity != p && !(livingEntity instanceof Slime)) {
                                            livingEntity.damage(10, p);
                                        }
                                    }
                                    slime.remove();
                                }
                            }, 100L);
                        }
                    }
                }
                case
                    "July24Shovel" ->
                    Utils.updateKey(p, item, meta, container, AceItems.keyRadius, AceItems.keyRadiusLore, "Mining Mode", 0, "1x1", 1, "3x3");
                case "June24GrowthStunter" -> {
                    e.setCancelled(true);
                }
            }
        }
        //Left click
        else if (action.isLeftClick()){
            switch (itemID) {
                case "July24SlimeWand" -> {
                    int currentUses = container.get(AceItems.keyUses, PersistentDataType.INTEGER);
                    if (p.isSneaking()) {
                        int slimeBalls = Utils.itemCountStrictAndRemove(p, Material.SLIME_BALL, 64);
                        if (slimeBalls > 0) {
                            container.set(AceItems.keyUses, PersistentDataType.INTEGER, currentUses + slimeBalls);
                            p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(JulyItemsConfig.July24SlimeWandAddedSlimeBallsMessage.replace("%amount%", String.valueOf(slimeBalls))));
                            meta.lore(updateLore(item, String.valueOf(currentUses), String.valueOf(currentUses + slimeBalls)));
                            item.setItemMeta(meta);
                        }
                        else {
                            p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(JulyItemsConfig.July24SlimeWandMissingSlimeBallsMessage));
                        }
                    } else {
                        if (currentUses < 1) {
                            p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(JulyItemsConfig.July24SlimeWandOutOfUsesMessage));
                        } else {
                            Snowball snowball = p.launchProjectile(Snowball.class);
                            snowball.setItem(new ItemStack(Material.SLIME_BALL));
                            container.set(AceItems.keyUses, PersistentDataType.INTEGER, currentUses - 1);
                            meta.lore(updateLore(item, String.valueOf(currentUses), String.valueOf(currentUses - 1)));
                            item.setItemMeta(meta);
                        }
                    }
                }
                case "July24JobsLantern", "UltraJuly24JobsLantern" -> {
                    String currentMode = container.get(AceItems.keyString, PersistentDataType.STRING);
                    if (currentMode != null) {
                        String newMode = Utils.inputOutputCycle(currentMode,"xp", "income", "xp");
                        p.sendMessage(Component.text("Switched boost mode to " + newMode, GREEN));
                        container.set(AceItems.keyString, PersistentDataType.STRING, newMode);
                        meta.lore(updateLore(item, currentMode, newMode));
                        item.setItemMeta(meta);
                    }
                }
                case "July24Saddle" -> {
                    if (p.isSneaking()) {
                        if (CooldownManager.hasCooldown(July24SaddleJumpBoostCooldowns, p.getUniqueId())) {
                            CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(July24SaddleJumpBoostCooldowns, p.getUniqueId()));
                        } else {
                            CooldownManager.setCooldown(July24SaddleJumpBoostCooldowns, p.getUniqueId(), Duration.ofSeconds(JulyItemsConfig.July24SaddleJumpBoostCooldown));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 2));
                        }
                    }
                }
                case "July24LessOPPickaxe" -> {
                    if (p.isSneaking()) {
                        if (!JulyItemsConfig.July24LessOPPickaxeExplosionAllowedWorlds.contains(p.getWorld().getName()) || AceItems.disabledWorlds.get("July24LessOPPickaxe").contains(p.getWorld().getName())) {
                            p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(cannotUseHereMessage));
                        } else {
                            if (CooldownManager.hasCooldown(July24LessOPPickaxeCooldowns, p.getUniqueId())) {
                                CooldownManager.sendCooldownMessage(p, CooldownManager.getRemainingCooldown(July24LessOPPickaxeCooldowns, p.getUniqueId()));
                            } else {
                                CooldownManager.setCooldown(July24LessOPPickaxeCooldowns, p.getUniqueId(), Duration.ofSeconds(JulyItemsConfig.July24LessOPPickaxeExplosionCooldown));
                                BlockUtils.breakInRadiusBlacklist(p.getLocation().getBlock(), JulyItemsConfig.July24LessOPPickaxeExplosionRadius, p, BlockUtils.getUnbreakableBlocks());
                            }
                        }
                    }
                }
                case "July24AIDisabler" -> {
                    if (p.isSneaking()) {
                        Utils.updateKey(p, item, meta, container, AceItems.keyState, AceItems.keyStateLore, "AI mode", "enable", "Enable", "disable", "Disable");
                    }
                }
                case "July24Elytra" -> {
                    if (p.isSneaking()) {
                        Utils.updateKey(p, item, meta, container, AceItems.keyState, AceItems.keyStateLore, "Firework Color", "16733525", "Red", "16777215", "White", "5592575", "Blue");
                    }
                }
            }
            if (itemID.equals("UltraJune24MoreOPSword")) {
                if (!AceItems.disabledWorlds.get("UltraJune24MoreOPSword").contains(p.getWorld().getName())) {
                    if (p.isSneaking()) {
                        List<Player> nearbyPlayers = (List<Player>) p.getWorld().getNearbyPlayers(p.getLocation(), 10);
                        for (Player nearbyPlayer : nearbyPlayers) {
                            if (UltraJune24MoreOPSwordTagged.containsKey(nearbyPlayer.getUniqueId())) {
                                if (UltraJune24MoreOPSwordTagged.get(nearbyPlayer.getUniqueId()).equals(p.getUniqueId())) {
                                    nearbyPlayer.setVelocity(p.getLocation().toVector().subtract(nearbyPlayer.getLocation().toVector()).normalize().multiply(2));
                                    UltraJune24MoreOPSwordTagged.remove(nearbyPlayer.getUniqueId());
                                }
                            }
                        }
                    }
                }
            } else if (itemID.equals("June24GuardianBeam")){
                if (!AceItems.disabledWorlds.get("June24GuardianBeam").contains(p.getWorld().getName())) {
                    if (hasCooldown(June24GuardianBeamCooldowns, p.getUniqueId())) {
                        sendCooldownMessage(p, getRemainingCooldown(June24GuardianBeamCooldowns, p.getUniqueId()));
                    } else {
                        setCooldown(June24GuardianBeamCooldowns, p.getUniqueId(), Duration.ofSeconds(5));
                        Location pLocation = p.getEyeLocation();
                        Vector direction = pLocation.getDirection();
                        try {
                            Laser laser = new Laser.GuardianLaser(pLocation, pLocation.clone().add(direction.clone().multiply(8)), 2, 8);
                            laser.start(AceItems.getInstance());
                            loop:
                            for (double i = 0; i < 8; i += 0.5) {
                                Location checkLocation = pLocation.clone().add(direction.clone().multiply(i));
                                List<Entity> nearbyEntities = (List<Entity>) checkLocation.getWorld().getNearbyEntities(checkLocation, 0.5, 0.5, 0.5);
                                for (Entity entity : nearbyEntities) {
                                    if (entity != p && entity.getType() != EntityType.ARMOR_STAND && entity instanceof LivingEntity livingEntity) {
                                        livingEntity.damage(1.5, p);
                                        double health = livingEntity.getHealth();
                                        if (health - 1.5 <= 0) {
                                            livingEntity.damage(Integer.MAX_VALUE);
                                        } else {
                                            livingEntity.setHealth(health - 1.5);
                                        }
                                        if (entity instanceof Player tagged) {
                                            June24GuardianBeamTagged.put(p.getUniqueId(), tagged);
                                            Bukkit.getScheduler().runTaskLater(AceItems.getInstance(), () -> June24GuardianBeamTagged.remove(p.getUniqueId()), 200L);
                                            break loop;
                                        }
                                    }
                                }
                            }
                        } catch (
                            ReflectiveOperationException ignored) {
                        }
                    }
                }
            } else if (itemID.equals("June24LeafBlower")){
                if (!AceItems.disabledWorlds.get("June24LeafBlower").contains(p.getWorld().getName())) {
                    if (p.isSneaking()) {
                        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(p.getLocation(), true, null);
                        if (claim != null) {
                            if (claim.getOwnerID().equals(p.getUniqueId())) {
                                if (hasCooldown(June24LeafBlowerCooldowns, p.getUniqueId())) {
                                    sendCooldownMessage(p, getRemainingCooldown(June24LeafBlowerCooldowns, p.getUniqueId()));
                                } else {
                                    if (confirm.containsKey(p.getUniqueId())) {
                                        setCooldown(June24LeafBlowerCooldowns, p.getUniqueId(), Duration.ofMinutes(5));
                                        removeInRadius(p.getLocation().getBlock(), 15, getLeaves());
                                        confirm.remove(p.getUniqueId());

                                        BukkitTask cancelTask = confirm.remove(p.getUniqueId());
                                        if (cancelTask != null) {
                                            cancelTask.cancel();
                                        }

                                    } else {
                                        p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(p, confirmMessage)));
                                        BukkitTask removeConfirm = new BukkitRunnable() {

                                            @Override
                                            public void run() {
                                                confirm.remove(p.getUniqueId());
                                            }
                                        }.runTaskLater(AceItems.getInstance(), 100);

                                        confirm.put(p.getUniqueId(), removeConfirm);
                                    }
                                }
                            } else {
                                sendClaimMessage(p);
                            }
                        } else {
                            sendClaimMessage(p);
                        }
                    }
                }
            } else if (itemID.equals("June24Hoe")){
                if (!AceItems.disabledWorlds.get("June24Hoe").contains(p.getWorld().getName())){
                    if (p.isSneaking()) {
                        if (hasCooldown(June24HoeCooldowns, p.getUniqueId())) {
                            sendCooldownMessage(p, getRemainingCooldown(June24HoeCooldowns, p.getUniqueId()));
                        } else {
                            setCooldown(June24HoeCooldowns, p.getUniqueId(), Duration.ofMinutes(5));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
                        }
                    }
                }
            }
            //Left clicking a block
            else if (b != null) {
                switch (itemID) {
                    case "June24QuartzWand" -> {
                        if (!AceItems.disabledWorlds.get("June24QuartzWand").contains(p.getWorld().getName())) {
                            for (Predicate<Block> quartzBlock : quartzBlocks) {
                                if (quartzBlock.test(b)) {
                                    Duration timeLeft = getRemainingCooldown(June24QuartzWandCooldowns, p.getUniqueId());
                                    if (timeLeft.isZero() || timeLeft.isNegative()) {
                                        int uses = container.get(AceItems.keyUses, PersistentDataType.INTEGER);
                                        b.setType(Material.valueOf(container.get(AceItems.keyState, PersistentDataType.STRING)));
                                        if (uses < 2) {
                                            meta.getPersistentDataContainer().set(AceItems.keyUses, PersistentDataType.INTEGER, 300);
                                            setCooldown(June24QuartzWandCooldowns, p.getUniqueId(), Duration.ofMinutes(30));
                                        } else {
                                            meta.getPersistentDataContainer().set(AceItems.keyUses, PersistentDataType.INTEGER, uses - 1);
                                        }
                                        item.setItemMeta(meta);

                                    } else {
                                        sendCooldownMessage(p, timeLeft);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    case
                        "UltraJune24QuartzWand" -> {
                        if (!AceItems.disabledWorlds.get("UltraJune24QuartzWand").contains(p.getWorld().getName())) {
                            for (Predicate<Block> quartzBlock : quartzBlocks) {
                                if (quartzBlock.test(b)) {
                                    b.setType(Material.valueOf(container.get(AceItems.keyState, PersistentDataType.STRING)));
                                    break;
                                }
                            }
                        }
                    }
                    case "June24LavaWaterBucket" -> {
                        if (p.isSneaking()) {
                            if (hasCooldown(June24LavaWaterBucketCooldowns, p.getUniqueId())) {
                                sendCooldownMessage(p, getRemainingCooldown(June24LavaWaterBucketCooldowns, p.getUniqueId()));
                            } else {
                                setCooldown(June24LavaWaterBucketCooldowns, p.getUniqueId(), Duration.ofMinutes(5));
                                removeInRadius(b, 3, getLava());
                            }
                        } else {
                            Block blockRelative = b.getRelative(e.getBlockFace());
                            Claim claim = GriefPrevention.instance.dataStore.getClaimAt(blockRelative.getLocation(), true, null);
                            if (claim != null) {
                                if (claim.getOwnerID().equals(p.getUniqueId())) {
                                    blockRelative.setType(Material.LAVA);
                                } else {
                                    sendClaimMessage(p);
                                }
                            } else {
                                blockRelative.setType(Material.LAVA);
                            }
                        }
                    }
                    case "UltraJune24LavaWaterBucket" -> {
                        if (p.isSneaking()) {
                            if (hasCooldown(UltraJune24LavaWaterBucketCooldowns, p.getUniqueId())) {
                                sendCooldownMessage(p, getRemainingCooldown(UltraJune24LavaWaterBucketCooldowns, p.getUniqueId()));
                            } else {
                                setCooldown(UltraJune24LavaWaterBucketCooldowns, p.getUniqueId(), Duration.ofSeconds(30));
                                removeInRadius(b, 3, getWater());
                            }
                        } else {
                            Block blockRelative = b.getRelative(e.getBlockFace());
                            Claim claim = GriefPrevention.instance.dataStore.getClaimAt(blockRelative.getLocation(), true, null);
                            if (claim != null) {
                                if (claim.getOwnerID().equals(p.getUniqueId())) {
                                    blockRelative.setType(Material.LAVA);
                                } else {
                                    sendClaimMessage(p);
                                }
                            } else {
                                blockRelative.setType(Material.LAVA);
                            }
                        }
                    }
                    case "July24MelonWand" -> {
                        if (BlockUtils.getDirtBlocks().contains(b.getType())) {
                            String blockType = container.get(AceItems.keyState, PersistentDataType.STRING);
                            if (blockType != null) {
                                Material material = Material.getMaterial(blockType);
                                if (material != null)
                                    b.setType(material);
                            }
                        }
                    }
                }
            }
        }
    }

    private void July24BeachMaker(Player p, int radius) {
        Block center = p.getLocation().getBlock();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = radius; y >= -radius; y--) {
                    Block relative = center.getRelative(x, y, z);
                    Block blockAbove = relative.getRelative(0, 1, 0);
                    if (relative.getType() != Material.AIR && blockAbove.getType() == Material.AIR) {
                        blockAbove.setType(Material.SAND);
                        break;
                    }
                }
            }
        }
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = radius; y >= -radius; y--) {
                    Block relative = center.getRelative(x, y, z);
                    Material relativeType = relative.getType();
                    if (relativeType == Material.STONE || relativeType == Material.DIRT || relativeType == Material.GRAVEL)
                        relative.setType(ThreadLocalRandom.current().nextInt(2) == 0 ? Material.SAND : Material.SANDSTONE);
                }
            }
        }
    }

}
