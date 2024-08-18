package com.Zpazod.antiVPN;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiVPN extends JavaPlugin {

    private PlayerJoinListener playerJoinListener;

    @Override
    public void onEnable() {
        playerJoinListener = new PlayerJoinListener(this);
        Bukkit.getPluginManager().registerEvents(playerJoinListener, this);

        // Enregistre la commande
        this.getCommand("checkvpn").setExecutor(new CheckVPNCommand(this, playerJoinListener));

        getLogger().info("AntiVPN activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("AntiVPN désactivé !");
    }
}
