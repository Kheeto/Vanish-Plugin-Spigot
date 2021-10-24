package kheeto.vanish;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private Vanish plugin;

    public VanishCommand(Vanish plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("vanish")) {
            if(sender.hasPermission("vanish")) {
                // If the first argument is the name of another player
                if(args.length > 0 && Bukkit.getPlayer(args[0]) != null) {
                    Player p = Bukkit.getPlayer(args[0]);

                    VanishPlayer(p);

                    return true;
                }

                // If theres no argument and the command sender is a player
                if (sender instanceof Player) {
                    Player p = (Player) sender;

                    VanishPlayer(p);

                    return true;
                }

                // If the command is being executed in the console
                sender.sendMessage(
                        "§cDevi essere un giocatori per mettere in vanish te stesso!");
                return true;
            }
            // If the command sender doesn't have the permission
            sender.sendMessage("§cNon hai il permesso per usare quel comando!");
            return true;
        }

        return false;
    }

    // Vanishes or unvanishes another player
    private void VanishPlayer(Player p) {

        // If the player is vanished, unvanished him
        if(plugin.vanishedPlayers.contains(p.getUniqueId().toString())) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(p);
                plugin.vanishedPlayers.remove(p.getUniqueId().toString());
            }
            return;
        }

        // If the player NOT vanished, vanishes him
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.hidePlayer(p);
            plugin.vanishedPlayers.add(p.getUniqueId().toString());
        }
    }
}
