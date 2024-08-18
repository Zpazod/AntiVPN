package com.Zpazod.antiVPN;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PlayerJoinListener implements Listener {

    private final AntiVPN plugin;
    private final Map<String, PlayerInfo> playerData = new HashMap<>();

    public PlayerJoinListener(AntiVPN plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        String playerIP = event.getPlayer().getAddress().getAddress().getHostAddress();
        String connectionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        VPNChecker vpnChecker = new VPNChecker(plugin);
        boolean isUsingVPN = vpnChecker.isUsingVPN(playerIP);

        // Enregistre les informations du joueur dans le HashMap
        playerData.put(playerName, new PlayerInfo(playerName, playerIP, connectionDate, isUsingVPN));

        // Sauvegarde les données dans le fichier YAML
        savePlayerData();

        plugin.getLogger().info("Pseudo: " + playerName + ", IP: " + playerIP + ", Date de Connexion: " + connectionDate + ", Statut VPN: " + (isUsingVPN ? "UTILISE VPN" : "PAS DE VPN"));
    }

    public PlayerInfo getPlayerInfo(String playerName) {
        return playerData.get(playerName);
    }

    public void savePlayerData() {
        FileConfiguration config = plugin.getConfig();
        config.set("players", null); // Clear existing data

        for (Map.Entry<String, PlayerInfo> entry : playerData.entrySet()) {
            String playerName = entry.getKey();
            PlayerInfo info = entry.getValue();
            config.set("players." + playerName + ".ip", info.getIp());
            config.set("players." + playerName + ".lastConnection", info.getLastConnection());
            config.set("players." + playerName + ".isUsingVPN", info.isUsingVPN());
        }
        plugin.saveConfig();
    }

    public void loadPlayerData() {
        FileConfiguration config = plugin.getConfig();
        if (config.contains("players")) {
            for (String playerName : config.getConfigurationSection("players").getKeys(false)) {
                String ip = config.getString("players." + playerName + ".ip");
                String lastConnection = config.getString("players." + playerName + ".lastConnection");
                boolean isUsingVPN = config.getBoolean("players." + playerName + ".isUsingVPN");
                playerData.put(playerName, new PlayerInfo(playerName, ip, lastConnection, isUsingVPN));
            }
        }
    }
}
