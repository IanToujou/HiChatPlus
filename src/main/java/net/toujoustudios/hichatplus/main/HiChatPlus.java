package net.toujoustudios.hichatplus.main;

import net.toujoustudios.hichatplus.listener.ChatListener;
import net.toujoustudios.hichatplus.listener.JoinListener;
import net.toujoustudios.hichatplus.listener.QuitListener;
import net.toujoustudios.hichatplus.loader.Loader;
import net.toujoustudios.hichatplus.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This file was created by IanToujou.
 * Date: 23.10.2020
 * Time: 21:08
 * Project: HiChatPlus
 */
public class HiChatPlus extends JavaPlugin {

    public static String PLUGIN_NAME = "HiChatPlus";
    public static String PLUGIN_VERSION = "0.1.0";
    public static int CONFIG_VERSION = 1;

    private static HiChatPlus instance;
    private PluginManager pluginManager;

    public void onEnable() {

        instance = this;
        pluginManager = Bukkit.getPluginManager();

        Loader.startLoading();

    }

    public void onDisable() {

        PlayerManager.unloadAll();

    }

    public void registerCommands() {}

    public void registerEvents() {

        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);

    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public static HiChatPlus getInstance() {
        return instance;
    }

}
