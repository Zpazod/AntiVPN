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
        this.getCommand("checkvpn").setExecutor(new CheckVPNCommand(this, playerJoinListener));
        getLogger().info("AntiVPN activé !");
    }

    @Override
    public void onDisable() {
        if (playerJoinListener != null) {
            playerJoinListener.savePlayerData(); // Sauvegarde des données des joueurs
        }
        getLogger().info("AntiVPN désactivé !");
    }
}
