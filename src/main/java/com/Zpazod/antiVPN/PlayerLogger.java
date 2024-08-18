package com.Zpazod.antiVPN;

public class PlayerLogger {

    private final AntiVPN plugin;

    public PlayerLogger(AntiVPN plugin) {
        this.plugin = plugin;
    }

    public void logPlayerInfo(String playerName, String playerIP, String lastConnection, boolean isUsingVPN) {
        String status = isUsingVPN ? "UTILISE VPN" : "PAS DE VPN";
        plugin.getLogger().info("Pseudo: " + playerName + ", IP: " + playerIP + ", Dernière Connexion: " + lastConnection + ", Statut VPN: " + status);
    }
}
