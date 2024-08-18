package com.Zpazod.antiVPN;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class FileManager {

    private final JavaPlugin plugin;

    public FileManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void createPlayerDataFile() {
        // Crée le répertoire AntiVPNConfig dans le répertoire de données du plugin
        File dataFolder = new File(plugin.getDataFolder(), "AntiVPNConfig");
        if (!dataFolder.exists()) {
            if (dataFolder.mkdirs()) {
                plugin.getLogger().info("Répertoire AntiVPNConfig créé.");
            } else {
                plugin.getLogger().warning("Impossible de créer le répertoire AntiVPNConfig.");
            }
        }

        // Crée le fichier playerdata.yml dans le répertoire AntiVPNConfig
        File playerDataFile = new File(dataFolder, "playerdata.yml");
        if (!playerDataFile.exists()) {
            plugin.saveResource("playerdata.yml", false); // Copie le fichier par défaut dans le répertoire
            plugin.getLogger().info("Fichier playerdata.yml créé.");
        }
    }

    public File getPlayerDataFile() {
        return new File(plugin.getDataFolder(), "AntiVPNConfig/playerdata.yml");
    }
}
