package net.toujoustudios.hichatplus.listener;

import net.toujoustudios.hichatplus.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 20:39
 * Project: HiChatPlus
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        if(Config.CHAT_DEFAULT_ENABLED) {

            Player player = event.getPlayer();
            String message = Config.CHAT_DEFAULT_FORMAT;

            message = message.replace("{Player}", player.getDisplayName());
            message = message.replace("{DefaultColor}", Config.CHAT_DEFAULT_COLOR);
            message = message.replace("{Message}", event.getMessage());
            message = message.replace(Config.CHAT_COLOR_PREFIX, "§");

            if(Config.CHAT_EMOJI_ENABLED) {

                for(String emoji : Config.CHAT_EMOJI_LIST) {

                    String[] strings = emoji.split("/");
                    message = message.replaceAll("(?i):" + strings[0] + ":", strings[1] + Config.CHAT_DEFAULT_COLOR);

                }

            }

            event.setFormat(message);

        }

    }

}
