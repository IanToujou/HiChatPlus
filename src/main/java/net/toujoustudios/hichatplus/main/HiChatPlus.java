package net.toujoustudios.hichatplus.main;

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

    private static HiChatPlus instance;
    private PluginManager pluginManager;

    public void onEnable() {

        instance = this;
        pluginManager = Bukkit.getPluginManager();

    }

    public void registerCommands() {



    }

    public void registerEvents() {



    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public static HiChatPlus getInstance() {
        return instance;
    }

}
