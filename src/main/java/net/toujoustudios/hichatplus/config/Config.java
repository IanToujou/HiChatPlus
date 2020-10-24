package net.toujoustudios.hichatplus.config;

import net.toujoustudios.hichatplus.log.LogLevel;
import net.toujoustudios.hichatplus.log.Logger;
import net.toujoustudios.hichatplus.main.HiChatPlus;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * This file was created by IanToujou.
 * Date: 23.10.2020
 * Time: 21:18
 * Project: HiChatPlus
 */
public class Config {

    //Database credentials
    public static String DATABASE_HOST;
    public static String DATABASE_PORT;
    public static String DATABASE_NAME;
    public static String DATABASE_USER;
    public static String DATABASE_PASSWORD;

    //Main plugin settings
    public static boolean ENABLED;
    public static boolean USE_DATABASE;

    //Enabled features
    public static boolean CHAT_DEFAULT_ENABLED;
    public static boolean CHAT_PRIVATE_ENABLED;

    //Formatting
    public static String CHAT_DEFAULT_FORMAT;
    public static String CHAT_PRIVATE_FORMAT_SENDER;
    public static String CHAT_PRIVATE_FORMAT_TARGET;

    public static void initialize() {

        Logger.log(LogLevel.INFORMATION, "Loading configuration files...");

        File databaseConfigFile = new File("plugins/" + HiChatPlus.PLUGIN_NAME + "/database.yml");
        File settingsConfigFile = new File("plugins/" + HiChatPlus.PLUGIN_NAME + "/settings.yml");

        if(!databaseConfigFile.exists()) {

            Logger.log(LogLevel.WARNING, "Configuration file database.yml not found. Creating now...");
            YamlConfiguration configuration = new YamlConfiguration();
            configuration.set("Host", "localhost");
            configuration.set("Port", "3306");
            configuration.set("Name", "");
            configuration.set("User", "");
            configuration.set("Password", "");

            try {

                configuration.save(databaseConfigFile);

            } catch(IOException exception) {

                exception.printStackTrace();

            }

        }

        Logger.log(LogLevel.WARNING, "Updating settings.yml if necessary...");
        YamlConfiguration configuration = new YamlConfiguration();

        if(configuration.isSet("Main.Enabled")) {
            configuration.set("Main.Enabled", true);
        }
        if(configuration.isSet("Main.UseDatabase")) {
            configuration.set("Main.UseDatabase", false);
        }
        if(configuration.isSet("Features.Chat.Default.Enabled")) {
            configuration.set("Features.Chat.Default.Enabled", true);
        }
        if(configuration.isSet("Features.Chat.Default.Format")) {
            configuration.set("Features.Chat.Default.Format", "§e{Player} §8| §7{Message}");
        }
        if(configuration.isSet("Features.Chat.Private.Enabled")) {
            configuration.set("Features.Chat.Private.Enabled", true);
        }
        if(configuration.isSet("Features.Chat.Private.FormatSender")) {
            configuration.set("Features.Chat.Private.FormatSender", "§7To §e{Player} §8| §a{Message}");
        }
        if(configuration.isSet("Features.Chat.Private.FormatTarget")) {
            configuration.set("Features.Chat.Private.FormatTarget", "§7From §e{Player} §8| §a{Message}");
        }

        try {

            configuration.save(settingsConfigFile);

        } catch(IOException exception) {

            exception.printStackTrace();

        }

        Logger.log(LogLevel.INFORMATION, "Successfully saved configuration files.");

        YamlConfiguration databaseConfig = YamlConfiguration.loadConfiguration(databaseConfigFile);
        YamlConfiguration settingsConfig = YamlConfiguration.loadConfiguration(databaseConfigFile);

        DATABASE_HOST = databaseConfig.getString("Host");
        DATABASE_PORT = databaseConfig.getString("Port");
        DATABASE_NAME = databaseConfig.getString("Name");
        DATABASE_USER = databaseConfig.getString("User");
        DATABASE_PASSWORD = databaseConfig.getString("Password");

        ENABLED = settingsConfig.getBoolean("Main.Enabled");
        USE_DATABASE = settingsConfig.getBoolean("Main.UseDatabase");

        CHAT_DEFAULT_ENABLED = settingsConfig.getBoolean("Features.Chat.Default.Enabled");
        CHAT_DEFAULT_FORMAT = settingsConfig.getString("Features.Chat.Default.Format");
        CHAT_PRIVATE_ENABLED = settingsConfig.getBoolean("Features.Chat.Private.Enabled");
        CHAT_PRIVATE_FORMAT_SENDER = settingsConfig.getString("Features.Chat.Private.FormatSender");
        CHAT_PRIVATE_FORMAT_TARGET = settingsConfig.getString("Features.Chat.Default.FormatTarget");

        Logger.log(LogLevel.INFORMATION, "Successfully loaded the configuration files.");

    }

}
