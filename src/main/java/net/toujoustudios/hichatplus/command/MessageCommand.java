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
 * Date: 24.10.2020
 * Time: 23:43
 * Project: HiChatPlus
 */
public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;
        PlayerManager playerManager = PlayerManager.getPlayers().get(player.getUniqueId());

        if(player.hasPermission("hichatplus.command.message")) {

            if(args.length > 1) {

                Player target = Bukkit.getPlayer(args[0]);

                if(target == null) {

                    player.sendMessage(Config.MESSAGE_ERROR_INVALIDPLAYER.replace("{Prefix}", Config.MESSAGE_PREFIX));
                    return false;

                }

                StringBuilder message = new StringBuilder();

                for(String part : args) {

                    if(!message.toString().equals("")) message.append(" ");
                    message.append(part);

                }

                player.sendMessage(Config.CHAT_PRIVATE_FORMAT_SENDER.replace("{Player}", target.getName()).replace("{Message}", message.toString()));
                target.sendMessage(Config.CHAT_PRIVATE_FORMAT_TARGET.replace("{Player}", player.getName()).replace("{Message}", message.toString()));

            } else {
                player.sendMessage(Config.MESSAGE_ERROR_SYNTAX.replace("{Prefix}", Config.MESSAGE_PREFIX).replace("{Usage}", command.getUsage()));
            }

        } else {
            player.sendMessage(Config.MESSAGE_ERROR_NOPERMISSION.replace("{Prefix}", Config.MESSAGE_PREFIX));
        }

        return false;

    }

}
