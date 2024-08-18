package com.Zpazod.antiVPN;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiVPN extends JavaPlugin {

    private PlayerJoinListener playerJoinListener;
    private FileManager fileManager;

    @Override
    public void onEnable() {
        fileManager = new FileManager(this);
        fileManager.createPlayerDataFile();
        playerJoinListener = new PlayerJoinListener(this);
        Bukkit.getPluginManager().registerEvents(playerJoinListener, this);
        getCommand("checkvpn").setExecutor(new CheckVPNCommand(this, playerJoinListener));
        playerJoinListener.loadPlayerData();
        getLogger().info("AntiVPN activé !");
    }

    @Override
    public void onDisable() {
        if (playerJoinListener != null) {
            playerJoinListener.savePlayerData();
        } else {
            getLogger().warning("Le PlayerJoinListener est null. Les données des joueurs ne peuvent pas être sauvegardées.");
        }

        getLogger().info("AntiVPN désactivé !");
    }
}
