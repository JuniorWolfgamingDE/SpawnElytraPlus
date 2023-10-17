package de.justin.spawnelytra;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.util.ArrayList;
public class ItemSwapListener implements Listener {
    ArrayList<Player> fly = new ArrayList();

    @EventHandler
    public void onEvent(PlayerSwapHandItemsEvent event) {
        if (isRadiusSpawn(SpawnElytra.radius, event.getPlayer().getLocation(),SpawnElytra.world, SpawnElytra.ignoreYLevel) && !fly.contains(event.getPlayer())) {
            event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().setY(100));
            event.getPlayer().setGliding(true);
            fly.add(event.getPlayer());
            Bukkit.getScheduler().runTaskTimer(SpawnElytra.plugin, bukkitTask -> {
                if (event.getPlayer().isOnGround()) {
                    event.getPlayer().setGliding(false);
                    fly.remove(event.getPlayer());
                    bukkitTask.cancel();
                }
            },60,2);
        }
    }
    public boolean isRadiusSpawn(int radius, Location loc, String world, boolean ignoreYLevel) {
        if (loc.getWorld().getName().equals(world)) {
            int x1 = SpawnElytra.spawnx;
            int z1 = SpawnElytra.spawnz;
            int y1 = SpawnElytra.spawny;
            int x2 = loc.getBlockX();
            int z2 = loc.getBlockZ();
            int y2 = loc.getBlockY();
            if (ignoreYLevel) {
                if (x1-x2 < radius && x1-x2 > -radius && z1-z2 < radius && z1-z2 > -radius) {
                    return true;
                }
            }else {
                if (x1-x2 < radius && x1-x2 > -radius && z1-z2 < radius && z1-z2 > -radius && y1-y2 < radius && y1-y2 > -radius) {
                    return true;
                }
            }

        }
        return false;
    }
    @EventHandler
    public void onGlide(EntityToggleGlideEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (fly.contains(player)) {
                event.setCancelled(true);
            }
        }
    }
}
