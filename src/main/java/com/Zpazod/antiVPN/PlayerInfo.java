package com.Zpazod.antiVPN;

public class PlayerInfo {

    private final String playerName;
    private final String ip;
    private final String lastConnection;
    private final boolean isUsingVPN;

    public PlayerInfo(String playerName, String ip, String lastConnection, boolean isUsingVPN) {
        this.playerName = playerName;
        this.ip = ip;
        this.lastConnection = lastConnection;
        this.isUsingVPN = isUsingVPN;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getIp() {
        return ip;
    }

    public String getLastConnection() {
        return lastConnection;
    }

    public boolean isUsingVPN() {
        return isUsingVPN;
    }
}
