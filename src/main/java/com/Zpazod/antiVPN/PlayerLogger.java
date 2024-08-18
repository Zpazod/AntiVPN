package com.Zpazod.antiVPN;

public class PlayerLogger {

    private final AntiVPN plugin;

    public PlayerLogger(AntiVPN plugin) {
        this.plugin = plugin;
    }

    public void logPlayerInfo(String playerName, String playerIP, String connectionDate, boolean isUsingVPN) {
        // Prépare les messages avec des couleurs pour les logs
        String status = isUsingVPN ? "UTILISE VPN" : "PAS DE VPN";
        String playerNameMessage = "Pseudo: " + playerName;
        String playerIPMessage = "IP: " + playerIP;
        String connectionDateMessage = "Date de Connexion: " + connectionDate;
        String statusMessage = "Statut VPN: " + status;

        // Envoie les messages au log du plugin
        plugin.getLogger().info(playerNameMessage);
        plugin.getLogger().info(playerIPMessage);
        plugin.getLogger().info(connectionDateMessage);
        plugin.getLogger().info(statusMessage);
    }
}
