package net.toujoustudios.hichatplus.command;

import net.toujoustudios.hichatplus.config.Config;
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

        if(player.hasPermission("hichatplus.command.message")) {

            if(args.length > 1) {

                Player target = Bukkit.getPlayer(args[0]);

                if(target == null) {

                    player.sendMessage(Config.MESSAGE_ERROR_INVALIDPLAYER.replace("{Prefix}", Config.MESSAGE_PREFIX));
                    return false;

                }

                StringBuilder messageBuilder = new StringBuilder();

                for(int i = 1; i < args.length; i++) {

                    messageBuilder.append(args[i]);
                    if(i != (args.length - 1)) messageBuilder.append(" ");

                }

                String message = messageBuilder.toString();

                if(Config.CHAT_COLOR_ENABLED) {

                    if(Config.CHAT_COLOR_USEPERMISSION) {

                        if(player.hasPermission("hichatplus.chat.color")) {

                            message = message.replace(Config.CHAT_COLOR_PREFIX, "§");

                        }

                    } else {

                        message = message.replace(Config.CHAT_COLOR_PREFIX, "§");

                    }

                }

                if(Config.CHAT_EMOJI_ENABLED) {

                    for(String emoji : Config.CHAT_EMOJI_LIST) {

                        String[] strings = emoji.split("/");
                        message = message.replaceAll("(?i):" + strings[0] + ":", strings[1] + Config.CHAT_PRIVATE_COLOR);

                    }

                }

                player.sendMessage(Config.CHAT_PRIVATE_FORMAT_SENDER.replace("{Player}", target.getName()).replace("{Message}", message).replace("{PrivateColor}", Config.CHAT_PRIVATE_COLOR));
                target.sendMessage(Config.CHAT_PRIVATE_FORMAT_TARGET.replace("{Player}", player.getName()).replace("{Message}", message).replace("{PrivateColor}", Config.CHAT_PRIVATE_COLOR));

                if(Config.CHAT_PRIVATE_SOUND_ENABLED) {

                    target.playSound(target.getLocation(), Config.CHAT_PRIVATE_SOUND_TYPE, Config.CHAT_PRIVATE_SOUND_CATEGORY, 100, Config.CHAT_PRIVATE_SOUND_PITCH);

                }

            } else {
                player.sendMessage(Config.MESSAGE_ERROR_SYNTAX.replace("{Prefix}", Config.MESSAGE_PREFIX).replace("{Usage}", command.getUsage()));
            }

        } else {
            player.sendMessage(Config.MESSAGE_ERROR_NOPERMISSION.replace("{Prefix}", Config.MESSAGE_PREFIX));
        }

        return false;

    }

}
