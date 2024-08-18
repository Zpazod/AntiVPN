package com.Zpazod.antiVPN;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.configuration.file.YamlConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

public class PlayerJoinListener implements Listener {

    private final AntiVPN plugin;
    private final FileManager fileManager;
    private YamlConfiguration playerData;

    public PlayerJoinListener(AntiVPN plugin) {
        this.plugin = plugin;
        this.fileManager = new FileManager(plugin);
        loadPlayerData();
    }

    private void loadPlayerData() {
        File playerDataFile = fileManager.getPlayerDataFile();
        if (playerDataFile.exists()) {
            playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        } else {
            playerData = new YamlConfiguration();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        String playerIP = event.getPlayer().getAddress().getAddress().getHostAddress();
        String connectionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        VPNChecker vpnChecker = new VPNChecker(plugin);
        boolean isUsingVPN = vpnChecker.isUsingVPN(playerIP);

        // Enregistre les informations du joueur dans le fichier YAML
        playerData.set("players." + playerName + ".name", playerName);
        playerData.set("players." + playerName + ".ip", playerIP);
        playerData.set("players." + playerName + ".lastConnection", connectionDate);
        playerData.set("players." + playerName + ".usingVPN", isUsingVPN);
        savePlayerData();

        plugin.getLogger().info("Pseudo: " + playerName + ", IP: " + playerIP + ", Date de Connexion: " + connectionDate + ", Statut VPN: " + (isUsingVPN ? "UTILISE VPN" : "PAS DE VPN"));
    }

    public void savePlayerData() {
        try {
            playerData.save(fileManager.getPlayerDataFile());
        } catch (Exception e) {
            plugin.getLogger().severe("Erreur lors de la sauvegarde des données des joueurs : " + e.getMessage());
        }
    }

    public PlayerInfo getPlayerInfo(String playerName) {
        if (playerData.contains("players." + playerName)) {
            String name = playerData.getString("players." + playerName + ".name");
            String ip = playerData.getString("players." + playerName + ".ip");
            String lastConnection = playerData.getString("players." + playerName + ".lastConnection");
            boolean usingVPN = playerData.getBoolean("players." + playerName + ".usingVPN");
            return new PlayerInfo(name, ip, lastConnection, usingVPN);
        }
        return null;
    }
}
