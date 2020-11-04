package net.toujoustudios.hichatplus.command;

import net.toujoustudios.hichatplus.config.Config;
import net.toujoustudios.hichatplus.player.PlayerManager;
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


        } else {

            player.sendMessage(Config.MESSAGE_ERROR_NOPERMISSION.replace("{Prefix}", Config.MESSAGE_PREFIX));

        }

        return false;

    }

}
