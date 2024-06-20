package me.dunescifye.aceitems.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dunescifye.aceitems.AceItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.luckperms.api.node.Node;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static me.dunescifye.aceitems.files.Config.changeVariableMessage;
import static me.dunescifye.aceitems.files.Config.getInClaimMessage;

public class Utils {

    public static final ItemStack waterBreathingPotion = new ItemStack(Material.POTION, 1);
    public static final ItemStack seaLantern = new ItemStack(Material.SEA_LANTERN, 1);
    public static final Map<UUID, UUID> UltraJune24MoreOPSwordTagged = new HashMap<>();
    public static final Map<UUID, Player> June24GuardianBeamTagged = new HashMap<>();
    public static final Map<UUID, Integer> June24ArmorSneaks = new HashMap<>();
    public static final Map<UUID, BukkitTask> activeTasks = new HashMap<>();

    private static final HashMap<UUID, Double> arrowDistances = new HashMap<>();

    public static HashMap<UUID, Double> getArrowDistances() {
        return arrowDistances;
    }

    private static final List<PotionEffectType> harmfulEffects = Arrays.asList(
        PotionEffectType.BLINDNESS,
        PotionEffectType.CONFUSION,
        PotionEffectType.HARM,
        PotionEffectType.HUNGER,
        PotionEffectType.POISON,
        PotionEffectType.SLOW,
        PotionEffectType.SLOW_DIGGING,
        PotionEffectType.UNLUCK,
        PotionEffectType.WEAKNESS,
        PotionEffectType.WITHER
    );

    private static final List<PotionEffectType> beneficialEffects = Arrays.asList(
        PotionEffectType.ABSORPTION,
        PotionEffectType.DAMAGE_RESISTANCE,
        PotionEffectType.FAST_DIGGING,
        PotionEffectType.FIRE_RESISTANCE,
        PotionEffectType.HEAL,
        PotionEffectType.HEALTH_BOOST,
        PotionEffectType.INCREASE_DAMAGE,
        PotionEffectType.INVISIBILITY,
        PotionEffectType.JUMP,
        PotionEffectType.NIGHT_VISION,
        PotionEffectType.REGENERATION,
        PotionEffectType.SATURATION,
        PotionEffectType.SPEED,
        PotionEffectType.WATER_BREATHING
    );

    public static boolean isBeneficialEffect(PotionEffectType potion){
        return beneficialEffects.contains(potion);
    }
    public static boolean isHarmfulEffect(PotionEffectType potion){
        return harmfulEffects.contains(potion);
    }

    public static void runConsoleCommand(String... commands){
        Server server = Bukkit.getServer();
        ConsoleCommandSender console = server.getConsoleSender();
        for (String command : commands){
            server.dispatchCommand(console, command);
        }
    }

    public static Object randomObject(Object... objects){
        List<Object> objectList = Arrays.asList(objects);
        return objectList.get(ThreadLocalRandom.current().nextInt(objects.length));
    }

    public static void sendPlayerChangeVariableMessage(Player player, String message, String variable, String content){
        player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(player, message.replace("%player%", player.getName()).replace("%variable%", variable).replace("%content%", content))));
    }

    public static String inputOutput(Object input, Object... matchers){
        int length = matchers.length;
        for (int i = 0; i < length / 2; i++) {
            if (Objects.equals(input, matchers[i * 2])) {
                return String.valueOf(matchers[1 + i * 2]);
            }
        }
        return length % 2 == 1 ? "" : String.valueOf(matchers[length - 1]);
    }

    public static String inputOutputCycle(Object input, Object... matchers){
        int length = matchers.length;
        for (int i = 0; i < length; i++) {
            if (Objects.equals(input, matchers[i])) {
                return String.valueOf(matchers[1 + i]);
            }
        }
        return String.valueOf(matchers[0]);
    }

    public static void changeMiningMode(Player p, ItemStack item, ItemMeta meta, Integer currentRadius, Integer maxRadius){
        sendPlayerChangeVariableMessage(p, changeVariableMessage, "Mining Mode", String.valueOf(inputOutput(currentRadius, 0, "3x3", 1, "1x1")));
        meta.getPersistentDataContainer().set(AceItems.keyRadius, PersistentDataType.INTEGER, Integer.valueOf(inputOutputCycle(currentRadius, 0, 1, 0)));
        meta.getPersistentDataContainer().set(AceItems.keyRadiusLore, PersistentDataType.STRING, String.valueOf(inputOutput(currentRadius, 0, "3x3", 1, "1x1")));
        item.setItemMeta(meta);


    }

    public static void strikeLightningAroundLocation(Location location, int durationInSeconds) {
        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= durationInSeconds * 20) {
                    cancel();
                    return;
                }

                // Strike lightning at a random location around the player
                double offsetX = (ThreadLocalRandom.current().nextDouble() * 20) - 3;
                double offsetZ = (ThreadLocalRandom.current().nextDouble() * 20) - 3;

                location.getWorld().strikeLightning(location.add(offsetX, 0, offsetZ));

                ticks += 5;
            }
        }.runTaskTimer(AceItems.getInstance(), 0, 5);
    }

    public static void sendClaimMessage(Player p){
        p.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(p, getInClaimMessage())));
    }
    //Drop item at player
    public static void dropItems(Location location, ItemStack... items) {
        for (ItemStack item : items) {
            Item drop = location.getWorld().dropItemNaturally(location, item);
            drop.setPickupDelay(0);
        }
    }

    //Drop item with owner
    public static void dropItems(Location location, UUID uuid, ItemStack... items) {
        for (ItemStack item : items) {
            Item drop = location.getWorld().dropItemNaturally(location, item);
            drop.setPickupDelay(0);
            drop.setOwner(uuid);
        }
    }
    public static int itemCount(Player p, Material item){
        int count = 0;
        PlayerInventory inv = p.getInventory();
        for (ItemStack is : inv.all(item).values()){
            if (is != null && is.getType() == item){
                count += is.getAmount();
            }
        }
        return count;
    }

    public static List<Component> updateLore(ItemStack item, String matcher, String replacement){
        List<Component> loreList = item.lore();

        TextReplacementConfig config = TextReplacementConfig.builder()
            .match(matcher)
            .replacement(replacement)
            .build();

        if (loreList != null)
            loreList.replaceAll(component -> component.replaceText(config));

        return loreList;
    }
    //Update lore and PDC for block type
    public static void updateKeyBlockType(Player p, ItemStack item, ItemMeta meta, PersistentDataContainer container, String variable, Object... matchers){
        //Add first two to the list again, allows for cycling back
        List<Object> newMatchers = new ArrayList<>(Arrays.asList(matchers));
        newMatchers.add(matchers[0]);
        newMatchers.add(matchers[1]);
        //Obtain current keys
        String oldBlock = container.get(AceItems.keyBlockType, PersistentDataType.STRING);
        String oldBlockLore = container.get(AceItems.keyBlockTypeLore, PersistentDataType.STRING);
        //Set new keys to first values in key's get messed up
        String newBlock =  String.valueOf(matchers[0]);
        String newBlockLore = String.valueOf(matchers[1]);
        //Match key to our list
        for (int i = 0; i < newMatchers.size(); i+=2) {
            if (Objects.equals(oldBlock, newMatchers.get(i))) {
                newBlock = String.valueOf(newMatchers.get(i + 2));
                newBlockLore = String.valueOf(newMatchers.get(i + 3));
                break;
            }
        }
        //Send player message
        sendPlayerChangeVariableMessage(p, changeVariableMessage, variable, newBlockLore);
        //Update PDC, lore, and Meta
        container.set(AceItems.keyBlockType, PersistentDataType.STRING, newBlock);
        container.set(AceItems.keyBlockTypeLore, PersistentDataType.STRING, newBlockLore);
        meta.lore(updateLore(item, oldBlockLore, newBlockLore));
        item.setItemMeta(meta);
    }
    //Luckperms add permission node
    public static void addPermission(UUID userUuid, String permission) {
        // Load, modify, then save
        AceItems.luckPerms.getUserManager().modifyUser(userUuid, user -> {
            // Add the permission
            user.data().add(Node.builder(permission).build());
        });
    }
    //Luckperms remove permission node
    public static void removePermission(UUID userUuid, String permission) {
        // Load, modify, then save
        AceItems.luckPerms.getUserManager().modifyUser(userUuid, user -> {
            // Add the permission
            user.data().remove(Node.builder(permission).build());
        });
    }

}
