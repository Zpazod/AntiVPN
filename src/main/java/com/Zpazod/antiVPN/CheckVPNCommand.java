package com.Zpazod.antiVPN;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CheckVPNCommand implements CommandExecutor {

    private final AntiVPN plugin;
    private final PlayerJoinListener playerJoinListener;

    public CheckVPNCommand(AntiVPN plugin, PlayerJoinListener playerJoinListener) {
        this.plugin = plugin;
        this.playerJoinListener = playerJoinListener;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /checkvpn <pseudo>");
            return false;
        }

        String playerName = args[0];
        PlayerInfo playerInfo = playerJoinListener.getPlayerInfo(playerName);

        if (playerInfo == null) {
            sender.sendMessage("Aucune donnée disponible pour le joueur " + playerName + ".");
            return true;
        }

        String status = playerInfo.isUsingVPN() ? "UTILISE VPN" : "PAS DE VPN";
        sender.sendMessage("Pseudo: " + playerInfo.getPlayerName() + ", IP: " + playerInfo.getIp() + ", Dernière Connexion: " + playerInfo.getLastConnection() + ", Statut VPN: " + status);

        return true;
    }
}
