package net.toujoustudios.hichatplus.command;

import net.toujoustudios.hichatplus.config.Config;
import net.toujoustudios.hichatplus.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This file was created by IanToujou.
 * Date: 04.11.2020
 * Time: 01:47
 * Project: HiChatPlus
 */
public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;
        PlayerManager playerManager = PlayerManager.getPlayers().get(player.getUniqueId());

        if(player.hasPermission("hichatplus.command.mute")) {

            if(args.length > 1) {

                Player target = Bukkit.getPlayer(args[0]);

                if(target != null) {

                    StringBuilder reasonBuilder = new StringBuilder();
                    for(int i = 1; i < args.length; i++) {

                        reasonBuilder.append(args[i]);
                        if(i != (args.length - 1)) reasonBuilder.append(" ");

                    }

                    PlayerManager targetManager = PlayerManager.getPlayers().get(target.getUniqueId());

                    targetManager.setMuted(true);
                    player.sendMessage(Config.MESSAGE_NOTIFICATION_MUTE_SENDER.replace("{Player}", target.getName()).replace("{Reason}", reasonBuilder.toString()));
                    target.sendMessage(Config.MESSAGE_NOTIFICATION_MUTE_TARGET.replace("{Reason}", reasonBuilder.toString()));

                } else {

                    player.sendMessage(Config.MESSAGE_ERROR_INVALIDPLAYER);

                }

            } else {

                player.sendMessage(Config.MESSAGE_ERROR_SYNTAX.replace("{Usage}", command.getUsage()));

            }

        } else {

            player.sendMessage(Config.MESSAGE_ERROR_NOPERMISSION);

        }

        return false;

    }

}
