package net.toujoustudios.hichatplus.listener;

import net.toujoustudios.hichatplus.loader.Loader;
import net.toujoustudios.hichatplus.loader.LoaderState;
import net.toujoustudios.hichatplus.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 18:02
 * Project: HiChatPlus
 */
public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if(Loader.getState() != LoaderState.FINISHED) {

            player.kickPlayer("§cThe server is still loading. Please wait a moment.");
            return;

        }

        PlayerManager playerManager = new PlayerManager(player.getUniqueId());
        PlayerManager.getPlayers().put(player.getUniqueId(), playerManager);

    }

}
