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

            if(args.length > 1) {
            
                Player target = Bukkit.getPlayer(args[0]);
                
                if(target != null) {
                
                    String reason = "";
                    
                    for(int i = 1; i < args.length; i++) {
                        
                        reason = reason + args[i] + " ";
                        
                    }
                    
                    PlayerManager targetManager = PlayerManager.getPlayers().get(target.getUniqueId());
                    
                targetManager.setMuted(true);
                player.sendMessage("");
                target.sendMessage("");
                
                } else {
                
                    player.sendMessage(Config.MESSAGE_ERROR_NOPLAYER);
                
                }
            
            } else {
            
            player.sendMessage(Config.MESSAGE_ERROR_SYNTAX.replace("{Prefix}", Config.MESSAGE_PREFIX);

        } else {

            player.sendMessage(Config.MESSAGE_ERROR_NOPERMISSION.replace("{Prefix}", Config.MESSAGE_PREFIX));

        }

        return false;

    }

}
