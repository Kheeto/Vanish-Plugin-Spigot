package kheeto.vanish;

import com.sun.tools.sjavac.Log;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Vanish extends JavaPlugin {

    public ArrayList<String> vanishedPlayers;

    @Override
    public void onEnable() {
        vanishedPlayers = new ArrayList<String>();

        // Registers the /vanish command
        Bukkit.getPluginCommand(
                "vanish").setExecutor(new VanishCommand(this));

        System.out.println("[Vanish] Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[Vanish] Plugin has been disabled!");
    }
}
