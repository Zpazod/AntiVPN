package com.Zpazod.antiVPN;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckVPNCommand implements CommandExecutor {

    private final AntiVPN plugin;

    public CheckVPNCommand(AntiVPN plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /checkvpn <pseudo>");
            return false;
        }

        String playerName = args[0];
        Player target = Bukkit.getPlayer(playerName);

        if (target == null) {
            sender.sendMessage("Le joueur " + playerName + " n'est pas en ligne.");
            return true;
        }

        String playerIP = target.getAddress().getAddress().getHostAddress();
        String lastConnection = String.valueOf(System.currentTimeMillis());

        VPNChecker vpnChecker = new VPNChecker(plugin);
        boolean isUsingVPN = vpnChecker.isUsingVPN(playerIP);

        PlayerLogger playerLogger = new PlayerLogger(plugin);
        playerLogger.logPlayerInfo(playerName, playerIP, lastConnection, isUsingVPN);

        String status = isUsingVPN ? "UTILISE VPN" : "PAS DE VPN";
        sender.sendMessage("Pseudo: " + playerName + ", IP: " + playerIP + ", Dernière Connexion: " + lastConnection + ", Statut VPN: " + status);

        return true;
    }
}
