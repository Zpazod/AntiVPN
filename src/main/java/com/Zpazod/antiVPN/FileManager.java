package com.Zpazod.antiVPN;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class FileManager {

    private final JavaPlugin plugin;

    public FileManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void createPlayerDataFile() {
        File dataFolder = new File(plugin.getDataFolder(), "AntiVPNConfig");
        if (!dataFolder.exists()) {
            if (dataFolder.mkdirs()) {
                plugin.getLogger().info("Répertoire AntiVPNConfig créé.");
            } else {
                plugin.getLogger().warning("Impossible de créer le répertoire AntiVPNConfig.");
            }
        }

        File playerDataFile = new File(dataFolder, "playerdata.yml");
        if (!playerDataFile.exists()) {
            // Copie le fichier playerdata.yml depuis les ressources du JAR
            plugin.saveResource("AntiVPNConfig/playerdata.yml", false);
            plugin.getLogger().info("Fichier playerdata.yml créé.");
        }
    }
}
