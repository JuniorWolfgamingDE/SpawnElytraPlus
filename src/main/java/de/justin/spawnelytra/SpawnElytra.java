package de.justin.spawnelytra;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnElytra extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new ItemSwapListener(), this);
        plugin = this;
        saveDefaultConfig();
        radius = getConfig().getInt("radius");
        world = getConfig().getString("world");
        ignoreYLevel = getConfig().getBoolean("ignoreYLevel");
        spawnx = getConfig().getInt("spawnx");
        spawny = getConfig().getInt("spawny");
        spawnz = getConfig().getInt("spawnz");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static SpawnElytra plugin;
    public static int radius;
    public static String world;
    public static boolean ignoreYLevel;
    public static int spawnx;
    public static int spawny;
    public static int spawnz;
}
