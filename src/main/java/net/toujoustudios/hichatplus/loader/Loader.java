package net.toujoustudios.hichatplus.loader;

import net.toujoustudios.hichatplus.config.Config;
import net.toujoustudios.hichatplus.database.DatabaseManager;
import net.toujoustudios.hichatplus.database.DatabaseTimer;
import net.toujoustudios.hichatplus.log.LogLevel;
import net.toujoustudios.hichatplus.log.Logger;
import net.toujoustudios.hichatplus.main.HiChatPlus;
import net.toujoustudios.hichatplus.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This file was created by IanToujou.
 * Date: 23.10.2020
 * Time: 18:59
 * Project: HiChatPlus
 */
public class Loader {

    private static LoaderState state;
    private static boolean cancelled = false;

    public static void startLoading() {

        preInitialize();
        initialize();
        postInitialize();

    }

    public static void preInitialize() {

        if(cancelled) return;

        state = LoaderState.PRE_INIT;
        Config.initialize();

        if(cancelled) return;

        if(Config.USE_DATABASE) {

            DatabaseManager.connect();
            DatabaseManager.setup();
            DatabaseTimer.start();

        }

        Logger.log(LogLevel.INFORMATION, "Pre initialization completed.");

    }

    public static void initialize() {

        if(cancelled) return;

        state = LoaderState.INIT;
        HiChatPlus.getInstance().registerEvents();
        HiChatPlus.getInstance().registerCommands();
        Logger.log(LogLevel.INFORMATION, "Initialization completed.");

    }

    public static void postInitialize() {

        if(cancelled) return;

        state = LoaderState.POST_INIT;

        for(Player player : Bukkit.getOnlinePlayers()) {

            if(player != null) {

                PlayerManager playerManager = new PlayerManager(player.getUniqueId());
                PlayerManager.getPlayers().put(player.getUniqueId(), playerManager);

            }

        }

        Logger.log(LogLevel.INFORMATION, "Post initialization completed.");
        state = LoaderState.FINISHED;

    }

    public static void cancel() {

        cancelled = true;
        HiChatPlus.getInstance().getServer().getPluginManager().disablePlugin(HiChatPlus.getInstance());

    }

    public static LoaderState getState() {

        return state;

    }

}
