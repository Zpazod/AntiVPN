package com.Zpazod.antiVPN;

import org.bukkit.ChatColor;
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
        // Vérifie que la commande est bien utilisée avec un seul argument
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /checkvpn <pseudo>");
            return false;
        }

        String playerName = args[0];
        PlayerInfo playerInfo = playerJoinListener.getPlayerInfo(playerName);

        // Vérifie si des données sont disponibles pour le joueur
        if (playerInfo == null) {
            sender.sendMessage(ChatColor.RED + "Aucune donnée disponible pour le joueur " + playerName + ".");
            return true;
        }

        // Prépare les messages avec les couleurs pour l'affichage en jeu
        String status = playerInfo.isUsingVPN() ? ChatColor.RED + "UTILISE VPN" : ChatColor.GREEN + "PAS DE VPN";
        String playerNameMessage = ChatColor.YELLOW + "Pseudo: " + ChatColor.WHITE + playerInfo.getPlayerName();
        String playerIPMessage = ChatColor.YELLOW + "IP: " + ChatColor.WHITE + playerInfo.getIp();
        String connectionDateMessage = ChatColor.YELLOW + "Dernière Connexion: " + ChatColor.WHITE + playerInfo.getLastConnection();
        String statusMessage = ChatColor.YELLOW + "Statut VPN: " + status;

        // Envoie les messages au CommandSender
        sender.sendMessage(playerNameMessage);
        sender.sendMessage(playerIPMessage);
        sender.sendMessage(connectionDateMessage);
        sender.sendMessage(statusMessage);

        return true;
    }
}
