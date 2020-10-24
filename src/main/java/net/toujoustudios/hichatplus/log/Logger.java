package net.toujoustudios.hichatplus.log;

import net.toujoustudios.hichatplus.main.HiChatPlus;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This file was created by IanToujou.
 * Date: 15.09.2020
 * Time: 19:00
 * Project: JutsuCraft
 */
public class Logger {

    public static void log(LogLevel level, String message) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", new Locale("de", "DE"));
        SimpleDateFormat fileDate = new SimpleDateFormat("dd-MM-yyyy");
        Bukkit.getConsoleSender().sendMessage(level.getColor() + "[" + HiChatPlus.PLUGIN_NAME + " - " + level + "] " + message);

    }

}
