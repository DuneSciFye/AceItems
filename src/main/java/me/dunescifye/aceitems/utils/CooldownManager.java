package me.dunescifye.aceitems.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static me.dunescifye.aceitems.files.Config.*;

public class CooldownManager {
    public static final Map<UUID, Instant> June24QuartzWandCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> June24PropToolCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> UltraJune24MoreOPSwordCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> June24GuardianBeamCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> June24GuardianBeamGetItemsCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> June24ArmorBeesCooldown = new HashMap<>();
    public static final Map<UUID, Instant> June24BlockWandCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> UltraJune24BlockWandCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> June24LeafBlowerCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> June24HoeCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> June24LavaWaterBucketCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> UltraJune24LavaWaterBucketCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> July24VillagerWandCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> July24SaddleLaunchCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> July24SaddleJumpBoostCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> July24LessOPPickaxeCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> July24MelonWandCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> UltraJuly24MelonWandCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> July24PaintBrushCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> July24GrapplingHookSelfLaunchCooldowns = new HashMap<>();
    public static final Map<UUID, Instant> July24GrapplingHookTargetLaunchCooldowns = new HashMap<>();

    // Set cooldown
    public static void setCooldown(Map<UUID, Instant>map, UUID key, Duration duration) {
        map.put(key, Instant.now().plus(duration));
    }

    // Check if cooldown has expired
    public static boolean hasCooldown(Map<UUID, Instant> map, UUID key) {
        Instant cooldown = map.get(key);
        return cooldown != null && Instant.now().isBefore(cooldown);
    }

    // Remove cooldown
    public static Instant removeCooldown(Map<UUID, Instant> map, UUID key) {
        return map.remove(key);
    }

    // Get remaining cooldown time
    public static Duration getRemainingCooldown(Map<UUID, Instant> map, UUID key) {
        Instant cooldown = map.get(key);
        Instant now = Instant.now();
        if (cooldown != null && now.isBefore(cooldown)) {
            return Duration.between(now, cooldown);
        } else {
            return Duration.ZERO;
        }
    }

    public static void sendCooldownMessage(Player player, Duration duration){
        String message;

        if (duration.compareTo(Duration.ofHours(1)) > 0){
            message = cooldownMessageHours.replace("%hours%", String.valueOf(duration.toHoursPart()))
                .replace("%minutes%", String.valueOf(duration.toMinutesPart()))
                .replace("%seconds%", String.valueOf(duration.toSecondsPart()));
        } else if (duration.compareTo(Duration.ofMinutes(1)) > 0) {
            message = cooldownMessageMinutes.replace("%minutes%", String.valueOf(duration.toMinutesPart()))
                .replace("%seconds%", String.valueOf(duration.toSecondsPart()));
        } else {
            message = cooldownMessageSeconds.replace("%seconds%", String.valueOf(duration.toSecondsPart()));
        }

        player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(player, message)));

    }
}
