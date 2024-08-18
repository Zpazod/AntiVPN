package com.Zpazod.antiVPN;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class VPNChecker {

    private final AntiVPN plugin;

    public VPNChecker(AntiVPN plugin) {
        this.plugin = plugin;
    }

    public boolean isUsingVPN(String ip) {
        String apiUrl = "https://vpnapi.io/api/" + ip + "?key=YOUR_API_KEY";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                plugin.getLogger().warning("API VPN a renvoyé un code d'erreur: " + responsecode);
                return false;
            } else {
                Scanner sc = new Scanner(url.openStream());
                StringBuilder inline = new StringBuilder();
                while (sc.hasNext()) {
                    inline.append(sc.nextLine());
                }
                sc.close();

                return inline.toString().contains("\"vpn\":true");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
