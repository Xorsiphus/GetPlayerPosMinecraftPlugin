package ru.xorsiphus.positiongetter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Getter extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("GetPlayerPos Plugin has been enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = null;

        if (sender instanceof Player)
            player = (Player) sender;

        if (args.length == 1) {
            var targetPlayer = getServer().getPlayer(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage("Player not found!");
                return true;
            }

            var targetPlayerLoc = targetPlayer.getLocation();

            if (player != null) {
                if (player.isOp()) {
                    player.sendMessage(
                            targetPlayerLoc.getWorld().getName() +
                                    ": " + targetPlayerLoc.getBlockX() +
                                    " " + targetPlayerLoc.getBlockY() +
                                    " " + targetPlayerLoc.getBlockZ()
                    );

                } else {
                    player.sendMessage("You are not an op!");
                }
                return true;
            } else {
                sender.sendMessage(
                        ChatColor.GREEN +
                                targetPlayerLoc.getWorld().getName() +
                                ": " + targetPlayerLoc.getBlockX() +
                                " " + targetPlayerLoc.getBlockY() +
                                " " + targetPlayerLoc.getBlockZ()
                );

            }
        }
        return false;
    }
}
