package ru.xorsiphus.positiongetter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Getter extends JavaPlugin {
    @Override
    public void onEnable(){
        getLogger().info("GetPlayerPos Plugin has been enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        var player = (Player) sender;

        if (player != null && args.length > 0){

            if (player.isOp()){
                getLogger().info(sender.toString());
                getLogger().info(command.toString());
                getLogger().info(args[0]);

                var targetPlayer = getServer().getPlayer(args[0]);

                if (targetPlayer != null){
                    var loc = targetPlayer.getLocation();

                    player.sendMessage(targetPlayer.getLocation().getWorld().getName() +
                            ": " + loc.getBlockX() +
                            " " + loc.getBlockY() +
                            " " + loc.getBlockZ()
                    );
                } else
                {
                    player.sendMessage("Player not found!");
                }
                return true;
            }else {
                player.sendMessage("You are not an op!");
            }
        }
        return true;
    }
}
