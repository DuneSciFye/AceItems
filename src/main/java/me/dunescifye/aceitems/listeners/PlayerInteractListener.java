package me.dunescifye.aceitems.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dunescifye.aceitems.AceItems;
import me.dunescifye.aceitems.libs.Laser;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static me.dunescifye.aceitems.items.JuneItemsManager.*;
import static me.dunescifye.aceitems.files.Config.*;
import static me.dunescifye.aceitems.utils.BlockUtils.*;
import static me.dunescifye.aceitems.utils.CooldownManager.*;
import static me.dunescifye.aceitems.utils.Utils.*;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

public class PlayerInteractListener implements Listener {


    private static List<Material> blockMaterials = Stream.of(Material.values())
        .filter(Material::isBlock)
        .filter(material -> !getUnobtainableBlocks().contains(material))
        .collect(Collectors.toList());

    public static final Map<UUID, BukkitTask> confirm = new HashMap<>();

    public void PlayerInteractHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        Player p = e.getPlayer();

        if (item != null) {
            if (item.hasItemMeta()){
                ItemMeta meta = e.getItem().getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(keyItemID)) {
                    Action action = e.getAction();
                    String itemID = container.get(keyItemID, PersistentDataType.STRING);
                    if (action.isRightClick()){
                        if (itemID.equals("June24MoreOPPickaxe") ||
                            itemID.equals("June24LessOPPickaxe") ||
                            itemID.equals("June24Shovel")){
                            Integer radius = container.get(keyRadius, PersistentDataType.INTEGER);
                            String oldText = meta.getPersistentDataContainer().get(keyRadiusLore, PersistentDataType.STRING);
                            String newText = inputOutput(radius, 0, "3x3", 1, "1x1");
                            sendPlayerChangeVariableMessage(p, changeVariableMessage, "Mining Mode", newText);
                            meta.getPersistentDataContainer().set(keyRadius, PersistentDataType.INTEGER, Integer.valueOf(inputOutputCycle(radius, 0, 1, 0)));
                            meta.getPersistentDataContainer().set(keyRadiusLore, PersistentDataType.STRING, newText);
                            meta.lore(updateLore(item, oldText, newText));
                            item.setItemMeta(meta);
                        }
                        else if (itemID.equals("UltraJune24MoreOPPickaxe")) {
                            Integer radius = container.get(keyRadius, PersistentDataType.INTEGER);
                            String oldText = meta.getPersistentDataContainer().get(keyRadiusLore, PersistentDataType.STRING);
                            String newText = inputOutput(radius, 0, "3x3", 1, "5x5", 2, "1x1");
                            sendPlayerChangeVariableMessage(p, changeVariableMessage, "Mining Mode", newText);
                            meta.getPersistentDataContainer().set(keyRadius, PersistentDataType.INTEGER, Integer.valueOf(inputOutputCycle(radius, 0, 1, 2, 0)));
                            meta.getPersistentDataContainer().set(keyRadiusLore, PersistentDataType.STRING, newText);
                            meta.lore(updateLore(item, oldText, newText));
                            item.setItemMeta(meta);
                        } else if (itemID.equals("June24QuartzWand") || itemID.equals("UltraJune24QuartzWand")) {
                            if (p.isSneaking()){
                                String currentVariant = container.get(keyBlockType, PersistentDataType.STRING);
                                String oldText = container.get(keyBlockTypeLore, PersistentDataType.STRING);
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
                                meta.getPersistentDataContainer().set(keyBlockTypeLore, PersistentDataType.STRING, newText);
                                meta.getPersistentDataContainer().set(keyBlockType, PersistentDataType.STRING, inputOutputCycle(currentVariant,
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
                        } else if (itemID.equals("June24PropTool")) {
                            if (!June24PropToolDisabledWorlds.contains(p.getWorld().getName())){
                                if (!p.isSneaking()){
                                    if (action == Action.RIGHT_CLICK_BLOCK){
                                        if (hasCooldown(June24PropToolCooldowns, p.getUniqueId())){
                                            sendCooldownMessage(p, getRemainingCooldown(June24PropToolCooldowns, p.getUniqueId()));
                                        } else {
                                            setCooldown(June24PropToolCooldowns, p.getUniqueId(), Duration.ofMinutes(15));
                                            MiscDisguise disguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, Material.getMaterial((String.valueOf(e.getClickedBlock().getType()))));
                                            disguise.setEntity(p);
                                            disguise.startDisguise();
                                        }
                                    }
                                } else {
                                    DisguiseAPI.undisguiseToAll(p);
                                }
                            }
                        } else if (itemID.equals("UltraJune24PropTool")) {
                            if (!UltraJune24PropToolDisabledWorlds.contains(p.getWorld().getName())){
                                if (!p.isSneaking()){
                                    if (action == Action.RIGHT_CLICK_BLOCK){
                                        MiscDisguise disguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, Material.getMaterial((String.valueOf(e.getClickedBlock().getType()))));
                                        disguise.setEntity(p);
                                        disguise.startDisguise();
                                    }
                                } else {
                                    DisguiseAPI.undisguiseToAll(p);
                                }
                            }
                        } else if (itemID.equals("June24Axe") || itemID.equals("UltraJune24Axe")) {
                            if (p.isSneaking()){
                                String oldText = meta.getPersistentDataContainer().get(keyBlockTypeLore, PersistentDataType.STRING);
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
                                meta.getPersistentDataContainer().set(keyBlockType, PersistentDataType.STRING, inputOutput(oldText,
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
                                meta.getPersistentDataContainer().set(keyBlockTypeLore, PersistentDataType.STRING, newText);
                                meta.lore(updateLore(item, oldText, newText));
                                item.setItemMeta(meta);
                            }
                        } else if (itemID.equals("UltraJune24MoreOPSword")){
                            if (!UltraJune24MoreOPSwordDisabledWorlds.contains(p.getWorld().getName())) {
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
                        } else if (itemID.equals("June24GuardianBeam")){
                            if (!June24GuardianBeamDisabledWorlds.contains(p.getWorld().getName())) {
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
                        } else if (itemID.equals("June24BlockWand")){
                            if (!June24BlockWandDisabledWorlds.contains(p.getWorld().getName())) {
                                if (hasCooldown(June24BlockWandCooldowns, p.getUniqueId())) {
                                    sendCooldownMessage(p, getRemainingCooldown(June24BlockWandCooldowns, p.getUniqueId()));
                                } else {
                                    setCooldown(June24BlockWandCooldowns, p.getUniqueId(), Duration.ofMinutes(20));
                                    Material randomBlock = blockMaterials.get(ThreadLocalRandom.current().nextInt(blockMaterials.size()));
                                    Component message = Component.text("You received " + randomBlock.toString().toLowerCase().replace("_", " ") + ".", GREEN);
                                    p.sendMessage(message);
                                    ItemStack itemStack = new ItemStack(randomBlock, ThreadLocalRandom.current().nextInt(1, 129));
                                    dropItem(p, itemStack);
                                }
                            }
                        } else if (itemID.equals("UltraJune24BlockWand")){
                            if (!UltraJune24BlockWandDisabledWorlds.contains(p.getWorld().getName())) {
                                if (hasCooldown(UltraJune24BlockWandCooldowns, p.getUniqueId())) {
                                    sendCooldownMessage(p, getRemainingCooldown(UltraJune24BlockWandCooldowns, p.getUniqueId()));
                                } else {
                                    setCooldown(UltraJune24BlockWandCooldowns, p.getUniqueId(), Duration.ofMinutes(10));
                                    Material randomBlock = blockMaterials.get(ThreadLocalRandom.current().nextInt(blockMaterials.size()));
                                    Component message = Component.text("You received " + randomBlock.toString().toLowerCase().replace("_", " ") + ".", GREEN);
                                    p.sendMessage(message);
                                    ItemStack itemStack = new ItemStack(randomBlock, ThreadLocalRandom.current().nextInt(1, 129));
                                    dropItem(p, itemStack);
                                }
                            }
                        } else if (itemID.equals("June24LeafBlower")){
                            if (!June24LeafBlowerDisabledWorlds.contains(p.getWorld().getName())) {
                                Bukkit.getScheduler().runTask(AceItems.getInstance(), () -> {
                                    String leafType = container.get(keyBlockType, PersistentDataType.STRING);
                                    if (p.isSneaking()) {
                                        sendPlayerChangeVariableMessage(p, changeVariableMessage, "Leaf Type", inputOutput(leafType, "OAK", "Spruce", "SPRUCE", "Jungle", "JUNGLE", "Dark Oak", "DARK_OAK", "Birch", "BIRCH", "Acacia", "ACACIA", "Mangrove", "MANGROVE", "Cherry", "CHERRY", "Azalea", "AZALEA", "Flowering Azalea", "FLOWERING_AZALEA", "Oak"));
                                        meta.getPersistentDataContainer().set(keyBlockType, PersistentDataType.STRING, inputOutputCycle(leafType, "OAK", "SPRUCE", "JUNGLE", "DARK_OAK", "BIRCH", "ACACIA", "MANGROVE", "CHERRY", "AZALEA", "FLOWERING_AZALEA", "OAK"));
                                        meta.getPersistentDataContainer().set(keyBlockTypeLore, PersistentDataType.STRING, inputOutput(leafType, "OAK", "Spruce", "SPRUCE", "Jungle", "JUNGLE", "Dark Oak", "DARK_OAK", "Birch", "BIRCH", "Acacia", "ACACIA", "Mangrove", "MANGROVE", "Cherry", "CHERRY", "Azalea", "AZALEA", "Flowering Azalea", "FLOWERING_AZALEA", "Oak"));
                                        item.setItemMeta(meta);
                                    } else if (action == Action.RIGHT_CLICK_BLOCK) {
                                        Block blockRelative = e.getClickedBlock().getRelative(e.getBlockFace());
                                        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(blockRelative.getLocation(), true, null);
                                        if (claim != null && blockRelative.getType() == Material.AIR) {
                                            if (claim.getOwnerID().equals(p.getUniqueId())) {
                                                blockRelative.setType(Material.valueOf(leafType + "_LEAVES"));
                                            } else {
                                                sendClaimMessage(p);
                                            }
                                        }
                                    }
                                });
                            }
                        } else if (itemID.equals("June24LavaWaterBucket")) {
                            if (action == Action.RIGHT_CLICK_BLOCK) {
                                Block b = e.getClickedBlock();
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
                        } else if (itemID.equals("UltraJune24LavaWaterBucket")) {
                            if (action == Action.RIGHT_CLICK_BLOCK) {
                                Block b = e.getClickedBlock();
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
                    } else if (action.isLeftClick()){
                        if (itemID.equals("UltraJune24MoreOPSword")) {
                            if (!UltraJune24MoreOPSwordDisabledWorlds.contains(p.getWorld().getName())) {
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
                            if (!June24GuardianBeamDisabledWorlds.contains(p.getWorld().getName())) {
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
                            if (!June24LeafBlowerDisabledWorlds.contains(p.getWorld().getName())) {
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
                            if (!June24HoeDisabledWorlds.contains(p.getWorld().getName())){
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
                        else if (action == Action.LEFT_CLICK_BLOCK) {
                            if (itemID.equals("June24QuartzWand")) {
                                if (!June24QuartzWandDisabledWorlds.contains(p.getWorld().getName())) {
                                    Block clickedBlock = e.getClickedBlock();
                                    for (Predicate<Block> quartzBlock : quartzBlocks) {
                                        if (quartzBlock.test(clickedBlock)) {
                                            Duration timeLeft = getRemainingCooldown(June24QuartzWandCooldowns, p.getUniqueId());
                                            if (timeLeft.isZero() || timeLeft.isNegative()) {
                                                int uses = container.get(keyUses, PersistentDataType.INTEGER);
                                                clickedBlock.setType(Material.valueOf(container.get(keyBlockType, PersistentDataType.STRING)));
                                                if (uses < 2) {
                                                    meta.getPersistentDataContainer().set(keyUses, PersistentDataType.INTEGER, 300);
                                                    setCooldown(June24QuartzWandCooldowns, p.getUniqueId(), Duration.ofMinutes(30));
                                                } else {
                                                    meta.getPersistentDataContainer().set(keyUses, PersistentDataType.INTEGER, uses - 1);
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
                            else if (itemID.equals("UltraJune24QuartzWand")){
                                if (!UltraJune24QuartzWandDisabledWorlds.contains(p.getWorld().getName())) {
                                    Block clickedBlock = e.getClickedBlock();
                                    for (Predicate<Block> quartzBlock : quartzBlocks) {
                                        if (quartzBlock.test(clickedBlock)) {
                                            clickedBlock.setType(Material.valueOf(container.get(keyBlockType, PersistentDataType.STRING)));
                                            break;
                                        }
                                    }
                                }
                            } else if (itemID.equals("June24LavaWaterBucket")) {
                                if (action == Action.LEFT_CLICK_BLOCK) {
                                    Block b = e.getClickedBlock();
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
                            } else if (itemID.equals("UltraJune24LavaWaterBucket")) {
                                if (action == Action.LEFT_CLICK_BLOCK) {
                                    Block b = e.getClickedBlock();
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
                            }
                        }
                    }
                }
            }
        }
    }

}
