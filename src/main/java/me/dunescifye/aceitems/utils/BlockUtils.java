package me.dunescifye.aceitems.utils;

import me.dunescifye.aceitems.AceItems;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.function.Predicate;

import static org.bukkit.Bukkit.getServer;

public class BlockUtils {

    public static List<Predicate<Block>> pickaxeWhitelist = new ArrayList<>();
    static {
        Predicate<Block> pickaxeMineable = block -> Tag.MINEABLE_PICKAXE.isTagged(block.getType());
        pickaxeWhitelist.add(pickaxeMineable);
    }

    public static List<Predicate<Block>> axeWhitelist = List.of(
        block -> Tag.MINEABLE_AXE.isTagged(block.getType()),
        block -> Tag.LEAVES.isTagged(block.getType())
    );
    public static List<Predicate<Block>> axeBlacklist = List.of(
        block -> block.getType().equals(Material.BARREL),
        block -> block.getType().equals(Material.CHEST),
        block -> block.getType().equals(Material.TRAPPED_CHEST),
        block -> Tag.ALL_SIGNS.isTagged(block.getType())
    );

    public static List<Predicate<Block>> shovelWhitelist = List.of(
        block -> Tag.MINEABLE_SHOVEL.isTagged(block.getType())
    );

    public static List<Predicate<Block>> pickaxeBlacklist = new ArrayList<>();
    static {
        Predicate<Block> spawner = block -> block.getType().equals(Material.SPAWNER);
        Predicate<Block> gildedBlackstone = block -> block.getType().equals(Material.GILDED_BLACKSTONE);
        Predicate<Block> dropper = block -> block.getType().equals(Material.DROPPER);
        Predicate<Block> dispenser = block -> block.getType().equals(Material.DISPENSER);
        Predicate<Block> hopper = block -> block.getType().equals(Material.HOPPER);
        Predicate<Block> furnace = block -> block.getType().equals(Material.FURNACE);
        Predicate<Block> blastFurnace = block -> block.getType().equals(Material.BLAST_FURNACE);
        Predicate<Block> smoker = block -> block.getType().equals(Material.SMOKER);
        Predicate<Block> shulkerBoxes = block -> Tag.SHULKER_BOXES.isTagged(block.getType());
        pickaxeBlacklist.add(spawner);
        pickaxeBlacklist.add(gildedBlackstone);
        pickaxeBlacklist.add(dropper);
        pickaxeBlacklist.add(dispenser);
        pickaxeBlacklist.add(hopper);
        pickaxeBlacklist.add(furnace);
        pickaxeBlacklist.add(blastFurnace);
        pickaxeBlacklist.add(smoker);
        pickaxeBlacklist.add(shulkerBoxes);
    }

    public static List<Predicate<Block>> ores = List.of(
        block -> block.getType().equals(Material.GOLD_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_GOLD_ORE),
        block -> block.getType().equals(Material.IRON_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_IRON_ORE),
        block -> block.getType().equals(Material.COAL_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_COAL_ORE),
        block -> block.getType().equals(Material.COPPER_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_COPPER_ORE),
        block -> block.getType().equals(Material.REDSTONE_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_REDSTONE_ORE),
        block -> block.getType().equals(Material.LAPIS_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_LAPIS_ORE),
        block -> block.getType().equals(Material.DIAMOND_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_DIAMOND_ORE),
        block -> block.getType().equals(Material.EMERALD_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_EMERALD_ORE),
        block -> block.getType().equals(Material.NETHER_GOLD_ORE),
        block -> block.getType().equals(Material.NETHER_QUARTZ_ORE),
        block -> block.getType().equals(Material.ANCIENT_DEBRIS)
    );

    public static List<Predicate<Block>> regularOres = List.of(
        block -> block.getType().equals(Material.GOLD_ORE),
        block -> block.getType().equals(Material.IRON_ORE),
        block -> block.getType().equals(Material.COAL_ORE),
        block -> block.getType().equals(Material.COPPER_ORE),
        block -> block.getType().equals(Material.REDSTONE_ORE),
        block -> block.getType().equals(Material.LAPIS_ORE),
        block -> block.getType().equals(Material.DIAMOND_ORE),
        block -> block.getType().equals(Material.EMERALD_ORE)
    );
    public static List<Predicate<Block>> deepslateOres = List.of(
        block -> block.getType().equals(Material.DEEPSLATE_GOLD_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_IRON_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_COAL_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_COPPER_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_REDSTONE_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_LAPIS_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_DIAMOND_ORE),
        block -> block.getType().equals(Material.DEEPSLATE_EMERALD_ORE)
    );

    public static List<Predicate<Block>> quartzBlocks = new ArrayList<>();
    static {
        Predicate<Block> quartzBlock = block -> block.getType().equals(Material.QUARTZ_BLOCK);
        Predicate<Block> quartzStairs = block -> block.getType().equals(Material.QUARTZ_STAIRS);
        Predicate<Block> quartzSlab = block -> block.getType().equals(Material.QUARTZ_SLAB);
        Predicate<Block> chiseledQuartzBlock = block -> block.getType().equals(Material.CHISELED_QUARTZ_BLOCK);
        Predicate<Block> quartzBricks = block -> block.getType().equals(Material.QUARTZ_BRICKS);
        Predicate<Block> quartzPillar = block -> block.getType().equals(Material.QUARTZ_PILLAR);
        Predicate<Block> smoothQuartz = block -> block.getType().equals(Material.SMOOTH_QUARTZ);
        Predicate<Block> smoothQuartzStairs = block -> block.getType().equals(Material.SMOOTH_QUARTZ_STAIRS);
        Predicate<Block> smoothQuartzSlab = block -> block.getType().equals(Material.SMOOTH_QUARTZ_SLAB);
        quartzBlocks.add(quartzBlock);
        quartzBlocks.add(quartzStairs);
        quartzBlocks.add(quartzSlab);
        quartzBlocks.add(chiseledQuartzBlock);
        quartzBlocks.add(quartzPillar);
        quartzBlocks.add(quartzBricks);
        quartzBlocks.add(smoothQuartz);
        quartzBlocks.add(smoothQuartzStairs);
        quartzBlocks.add(smoothQuartzSlab);
    }

    public static final List<Predicate<Block>> logs = List.of(
            block -> block.getType().equals(Material.OAK_LOG),
            block -> block.getType().equals(Material.SPRUCE_LOG),
            block -> block.getType().equals(Material.BIRCH_LOG),
            block -> block.getType().equals(Material.JUNGLE_LOG),
            block -> block.getType().equals(Material.ACACIA_LOG),
            block -> block.getType().equals(Material.DARK_OAK_LOG),
            block -> block.getType().equals(Material.MANGROVE_LOG),
            block -> block.getType().equals(Material.CHERRY_LOG),
            block -> block.getType().equals(Material.CRIMSON_STEM),
            block -> block.getType().equals(Material.WARPED_STEM)
    );
    private static final List<Predicate<Block>> leaves = new ArrayList<>();
    static {
        Predicate<Block> oakLeaves = block -> block.getType().equals(Material.OAK_LEAVES);
        Predicate<Block> spruceLeaves = block -> block.getType().equals(Material.SPRUCE_LEAVES);
        Predicate<Block> birchLeaves = block -> block.getType().equals(Material.BIRCH_LEAVES);
        Predicate<Block> jungleLeaves = block -> block.getType().equals(Material.JUNGLE_LEAVES);
        Predicate<Block> acaciaLeaves = block -> block.getType().equals(Material.ACACIA_LEAVES);
        Predicate<Block> darkOakLeaves = block -> block.getType().equals(Material.DARK_OAK_LEAVES);
        Predicate<Block> mangroveLeaves = block -> block.getType().equals(Material.MANGROVE_LEAVES);
        Predicate<Block> cherryLeaves = block -> block.getType().equals(Material.CHERRY_LEAVES);
        Predicate<Block> azaleaLeaves = block -> block.getType().equals(Material.AZALEA_LEAVES);
        Predicate<Block> floweringAzaleaLeaves = block -> block.getType().equals(Material.FLOWERING_AZALEA_LEAVES);
        leaves.add(oakLeaves);
        leaves.add(spruceLeaves);
        leaves.add(birchLeaves);
        leaves.add(jungleLeaves);
        leaves.add(acaciaLeaves);
        leaves.add(darkOakLeaves);
        leaves.add(mangroveLeaves);
        leaves.add(cherryLeaves);
        leaves.add(azaleaLeaves);
        leaves.add(floweringAzaleaLeaves);
    }

    public static List<Predicate<Block>> getLeaves(){
        return leaves;
    }

    private static final List<Predicate<Block>> water = List.of(
        block -> block.getType().equals(Material.WATER)
    );

    public static List<Predicate<Block>> getWater(){
        return water;
    }
    private static final List<Predicate<Block>> lava = List.of(
        block -> block.getType().equals(Material.LAVA)
    );

    public static List<Predicate<Block>> getLava(){
        return lava;
    }
    private static final List<Material> dirtBlocks = Arrays.asList(
        Material.PODZOL,
        Material.DIRT,
        Material.COARSE_DIRT,
        Material.FARMLAND,
        Material.DIRT_PATH,
        Material.GRASS_BLOCK
    );

    public static List<Material> getDirtBlocks(){
        return dirtBlocks;
    }

    private static final List<Predicate<Block>> unbreakableBlocks = List.of(
        block -> block.getType().equals(Material.BEDROCK)
    );

    public static List<Predicate<Block>> getUnbreakableBlocks(){
        return unbreakableBlocks;
    }

    public static boolean notInBlacklist(Block b, List<Predicate<Block>> blacklist) {
        for (Predicate<Block> blacklisted : blacklist) {
            if (blacklisted.test(b)) {
                return false;
            }
        }
        return true;
    }

    public static void breakInRadius(Block block, int radius, Player player, List<Predicate<Block>> whitelist, List<Predicate<Block>> blacklist) {

        ItemStack heldItem = player.getInventory().getItemInMainHand();

        Collection<ItemStack> drops = new ArrayList<>();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block b = block.getRelative(x, y, z);
                    for (Predicate<Block> whitelisted : whitelist) {
                        if (whitelisted.test(b)) {
                            if (notInBlacklist(b, blacklist)) {
                                drops.addAll(b.getDrops(heldItem));
                                b.setType(Material.AIR);
                            }
                        }
                    }
                }
            }
        }

        for (ItemStack item : mergeSimilarItemStacks(drops)){
            block.getWorld().dropItemNaturally(block.getLocation(), item);
        }

    }

    public static void breakInRadius(Block block, int radius, Player player, List<Predicate<Block>> whitelist) {

        ItemStack heldItem = player.getInventory().getItemInMainHand();

        Collection<ItemStack> drops = new ArrayList<>();
        player.setMetadata("ignoreBlockBreak", new FixedMetadataValue(AceItems.getInstance(), true));

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block b = block.getRelative(x, y, z);
                    for (Predicate<Block> whitelisted : whitelist) {
                        if (whitelisted.test(b)) {
                            BlockBreakEvent blockBreakEvent = new BlockBreakEvent(b, player);
                            Bukkit.getPluginManager().callEvent(blockBreakEvent);
                            if (blockBreakEvent.isCancelled()) continue;
                            blockBreakEvent.setDropItems(false);

                            drops.addAll(b.getDrops(heldItem));
                        }
                    }
                }
            }
        }

        player.removeMetadata("ignoreBlockBreak", AceItems.getInstance());

        for (ItemStack item : mergeSimilarItemStacks(drops)){
            block.getWorld().dropItemNaturally(block.getLocation(), item);
        }

    }

    public static void breakInRadiusBlacklist(Block block, int radius, Player player, List<Predicate<Block>> blacklist) {

        ItemStack heldItem = player.getInventory().getItemInMainHand();

        Collection<ItemStack> drops = new ArrayList<>();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block b = block.getRelative(x, y, z);
                    if (notInBlacklist(b, blacklist)) {
                        drops.addAll(b.getDrops(heldItem));
                        b.setType(Material.AIR);
                    }
                }
            }
        }

        for (ItemStack item : mergeSimilarItemStacks(drops)){
            block.getWorld().dropItemNaturally(block.getLocation(), item);
        }

    }
    public static void removeInRadius(Block block, int radius, List<Predicate<Block>> whitelist, List<Predicate<Block>> blacklist) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block b = block.getRelative(x, y, z);
                    for (Predicate<Block> whitelisted : whitelist) {
                        if (whitelisted.test(b)) {
                            if (notInBlacklist(b, blacklist))
                                    b.setType(Material.AIR);
                        }
                    }
                }
            }
        }
    }
    public static void removeInRadius(Block block, int radius, List<Predicate<Block>> whitelist) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block b = block.getRelative(x, y, z);
                    for (Predicate<Block> whitelisted : whitelist) {
                        if (whitelisted.test(b)) {
                            b.setType(Material.AIR);
                        }
                    }
                }
            }
        }
    }
    public static void accelerateMelonGrowth(Block block, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block b = block.getRelative(x, y, z);
                    if (b.getType() == Material.MELON_STEM && b.getBlockData() instanceof Ageable ageable) {
                        b.applyBoneMeal(BlockFace.UP);
                        if (ageable.getAge() == ageable.getMaximumAge()) {
                            List<BlockFace> faces = new ArrayList<>(Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST));
                            Collections.shuffle(faces);
                            for (BlockFace face : faces) {
                                Block relative = b.getRelative(face);
                                if (relative.getType() == Material.AIR) {
                                    relative.setType(Material.MELON);
                                    b.setType(Material.ATTACHED_MELON_STEM);
                                    Directional blockData = (Directional) (b.getBlockData());
                                    blockData.setFacing(face);
                                    b.setBlockData(blockData);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isNaturallyGenerated(Block block) {
        List<String[]> lookup = getCoreProtect().blockLookup(block, 2147483647);
        if (lookup != null && !lookup.isEmpty()) {
            CoreProtectAPI.ParseResult parseResult = getCoreProtect().parseResult(lookup.get(0));
            return parseResult.getPlayer().startsWith("#") || parseResult.getActionId() != 1 || parseResult.isRolledBack();
        }
        return true;
    }

    private static CoreProtectAPI getCoreProtect() {
        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");

        // Check that CoreProtect is loaded
        if (!(plugin instanceof CoreProtect)) {
            return null;
        }

        // Check that the API is enabled
        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
        if (!CoreProtect.isEnabled()) {
            return null;
        }

        // Check that a compatible version of the API is loaded
        if (CoreProtect.APIVersion() < 10) {
            return null;
        }

        return CoreProtect;
    }

    public static Collection<ItemStack> mergeSimilarItemStacks(Collection<ItemStack> itemStacks) {
        Map<Material, ItemStack> mergedStacksMap = new HashMap<>();

        for (ItemStack stack : itemStacks) {
            Material material = stack.getType();
            ItemStack existing = mergedStacksMap.get(material);
            if (existing == null) {
                mergedStacksMap.put(material, stack.clone());
            } else {
                existing.setAmount(existing.getAmount() + stack.getAmount());
            }
        }
        return mergedStacksMap.values();
    }
    public static String changeColor(String input, String newColor) {
        String regexColor = "(?i)\\b(WHITE|LIGHT_GRAY|GRAY|BLACK|BROWN|RED|ORANGE|YELLOW|LIME|GREEN|CYAN|LIGHT_BLUE|BLUE|PURPLE|MAGENTA|PINK)(_(\\w+))";
        if (input.matches(regexColor)){
            return input.replaceAll(regexColor, newColor + "$2");
        } else {
            if (input.equalsIgnoreCase("GLASS")) input = "STAINED_" + input;
            return newColor + "_" + input;
        }
    }
    public static void veinMine(Block block, Material blockType, ItemStack heldItem) {
        Set<Block> blocksToBreak = new HashSet<>();
        Collection<ItemStack> drops = new ArrayList<>();
        findVein(block, blockType, blocksToBreak);

        // Break all collected blocks
        for (Block b : blocksToBreak) {
            if (b.equals(block)) continue;
            drops.addAll(b.getDrops(heldItem));
            b.setType(Material.AIR);
        }

        for (ItemStack drop : mergeSimilarItemStacks(drops)){
            Utils.dropItems(block.getLocation(), drop);
        }
    }

    private static void findVein(Block block, Material blockType, Set<Block> blocksToBreak) {
        if (block.getType() != blockType || blocksToBreak.contains(block)) {
            return;
        }

        blocksToBreak.add(block);

        for (Block adjacent : getAdjacentBlocks(block)) {
            findVein(adjacent, blockType, blocksToBreak);
        }
    }

    private static Set<Block> getAdjacentBlocks(Block block) {
        Set<Block> adjacentBlocks = new HashSet<>();
        int[][] directions = {
            {1, 0, 0}, {-1, 0, 0},
            {0, 1, 0}, {0, -1, 0},
            {0, 0, 1}, {0, 0, -1}
        };

        for (int[] dir : directions) {
            Block adjacent = block.getRelative(dir[0], dir[1], dir[2]);
            adjacentBlocks.add(adjacent);
        }

        return adjacentBlocks;
    }
}
