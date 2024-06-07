package me.dunescifye.aceitems.listeners;

import me.dunescifye.aceitems.AceItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.UUID;

import static me.dunescifye.aceitems.utils.Utils.*;

public class ProjectileHitListener implements Listener {

    public void ProjectileHitHandler(AceItems plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow arrow) {
            if (arrow.getShooter() instanceof Player p) {
                UUID arrowId = arrow.getUniqueId();
                if (getArrowDistances().containsKey(arrowId)) {
                    double startDistance = getArrowDistances().get(arrowId);
                    double endDistance = arrow.getLocation().distance(p.getLocation());
                    double totalDistance = startDistance + endDistance;
                    getArrowDistances().put(arrowId, totalDistance);
                }
            }
        }

    }

}
