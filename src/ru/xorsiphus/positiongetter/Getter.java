package ru.xorsiphus.positiongetter;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.Objects;


public class Getter extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("GetPlayerPos Plugin has been enabled!");
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command,
                             @Nonnull String label, @Nonnull String[] args) {

        Player player = null;

        if (sender instanceof Player)
            player = (Player) sender;

        if (args.length == 1) {
            Location targetPlayerLoc = null;
            String message = null;

            var targetPlayer = getServer().getPlayer(args[0]);

            if (targetPlayer == null) {
                var offlinePlayers = getServer().getOfflinePlayers();

                for (OfflinePlayer offlinePlayer : offlinePlayers) {
                    if (Objects.equals(offlinePlayer.getName(), args[0])) {
                        targetPlayerLoc = offlinePlayer.getBedSpawnLocation();
                        if (targetPlayerLoc == null) {
                            sender.sendMessage(ChatColor.RED + "Player is currently offline and did not spawn by the bed!");
                            return true;
                        } else {
                            message = ChatColor.GREEN +
                                    offlinePlayer.getName() +
                                    "'s bed at: " +
                                    Objects.requireNonNull(targetPlayerLoc.getWorld()).getName() +
                                    " - " + targetPlayerLoc.getBlockX() +
                                    " " + targetPlayerLoc.getBlockY() +
                                    " " + targetPlayerLoc.getBlockZ();
                        }
                    }
                }
            } else {
                targetPlayerLoc = targetPlayer.getLocation();
                message = ChatColor.GREEN +
                        targetPlayer.getName() +
                        " at: " +
                        Objects.requireNonNull(targetPlayerLoc.getWorld()).getName() +
                        " - " + targetPlayerLoc.getBlockX() +
                        " " + targetPlayerLoc.getBlockY() +
                        " " + targetPlayerLoc.getBlockZ();
            }

            if (targetPlayerLoc == null) {
                sender.sendMessage(ChatColor.RED + "Player not found!");
                return true;
            }

            if (player != null) {
                if (player.isOp()) {
                    player.sendMessage(message);

                } else {
                    player.sendMessage(ChatColor.RED + "You are not an op!");
                }
            } else {
                sender.sendMessage(message);
            }
            return true;
        }
        return false;
    }
}
