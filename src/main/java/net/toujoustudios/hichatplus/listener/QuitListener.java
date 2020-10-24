package net.toujoustudios.hichatplus.listener;

import net.toujoustudios.hichatplus.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 18:04
 * Project: HiChatPlus
 */
public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        PlayerManager.getPlayers().get(player.getUniqueId()).unload();

    }

}
