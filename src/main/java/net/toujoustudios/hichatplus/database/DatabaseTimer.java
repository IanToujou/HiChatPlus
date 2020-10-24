package net.toujoustudios.hichatplus.database;

import net.toujoustudios.hichatplus.log.LogLevel;
import net.toujoustudios.hichatplus.log.Logger;
import net.toujoustudios.hichatplus.player.PlayerManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 17:42
 * Project: HiChatPlus
 */
public class DatabaseTimer extends TimerTask {

    @Override
    public void run() {

        PlayerManager.unloadAll();
        Logger.log(LogLevel.INFORMATION, "Reconnecting the database...");
        DatabaseManager.disconnect();

        try {

            Thread.sleep(1000);

        } catch(InterruptedException e) {

            e.printStackTrace();

        }

        DatabaseManager.connect();

    }

    public static void start() {

        DatabaseTimer databaseTimer = new DatabaseTimer();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(databaseTimer, 10800000, 10800000);

    }

}
