package net.toujoustudios.hichatplus.config;

import net.toujoustudios.hichatplus.log.LogLevel;
import net.toujoustudios.hichatplus.log.Logger;
import net.toujoustudios.hichatplus.main.HiChatPlus;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public static boolean CHAT_EMOJI_ENABLED;

    //Formatting
    public static String CHAT_DEFAULT_FORMAT;
    public static String CHAT_PRIVATE_FORMAT_SENDER;
    public static String CHAT_PRIVATE_FORMAT_TARGET;

    //Lists
    public static List<String> CHAT_EMOJI_LIST;

    //Message
    public static String MESSAGE_PREFIX;
    public static String MESSAGE_ERROR_NOPERMISSION;
    public static String MESSAGE_ERROR_SYNTAX;
    public static String MESSAGE_ERROR_INVALIDPLAYER;

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

        if(settingsConfigFile.exists()) {

            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(settingsConfigFile);

            if(configuration.getInt("Config.Version") < HiChatPlus.CONFIG_VERSION) {

                Logger.log(LogLevel.WARNING, "Configuration file settings.yml is outdated. Updating now...");
                configuration.set("Config.Version", HiChatPlus.CONFIG_VERSION);

                if(!configuration.isSet("UseDatabase")) {
                    configuration.set("UseDatabase", false);
                }
                if(!configuration.isSet("Chat.Default.Enabled")) {
                    configuration.set("Chat.Default.Enabled", true);
                }
                if(!configuration.isSet("Chat.Default.Format")) {
                    configuration.set("Chat.Default.Format", "§6Chat §8| §e{Player} §8> §7{Message}");
                }
                if(!configuration.isSet("Chat.Private.Enabled")) {
                    configuration.set("Chat.Private.Enabled", true);
                }
                if(!configuration.isSet("Chat.Private.FormatSender")) {
                    configuration.set("Chat.Private.FormatSender", "§6Private §8| §7To §e{Player} §8> §a{Message}");
                }
                if(!configuration.isSet("Chat.Private.FormatTarget")) {
                    configuration.set("Chat.Private.FormatTarget", "§6Private §8| §7From §e{Player} §8> §a{Message}");
                }
                if(!configuration.isSet("Chat.Emoji.Enabled")) {
                    configuration.set("Chat.Emoji.Enabled", true);
                }
                if(!configuration.isSet("Chat.Emoji.List")) {
                    ArrayList<String> emoji = new ArrayList<>();
                    emoji.add("heart/§c❤§7");
                    emoji.add("green_heart/§a❤§7");
                    emoji.add("purple_heart/§5❤§7");
                    configuration.set("Chat.Emoji.List", emoji);
                }
                if(!configuration.isSet("Message.Prefix")) {
                    configuration.set("Message.Prefix", "§6HiChatPlus §8|");
                }
                if(!configuration.isSet("Message.Error.NoPermission")) {
                    configuration.set("Message.Error.NoPermission", "{Prefix} §cYou don't have the permission to perform this command§8.");
                }
                if(!configuration.isSet("Message.Error.Syntax")) {
                    configuration.set("Message.Error.Syntax", "{Prefix} §cThe command syntax is not correct§8. §e{Usage}");
                }
                if(!configuration.isSet("Message.Error.InvalidPlayer")) {
                    configuration.set("Message.Error.InvalidPlayer", "{Prefix} §cThe given player is invalid§8.");
                }

                try {
                    configuration.save(settingsConfigFile);
                } catch(IOException exception) {
                    exception.printStackTrace();
                }

            }

        } else {

            Logger.log(LogLevel.WARNING, "Configuration file settings.yml not found. Creating now...");
            YamlConfiguration configuration = new YamlConfiguration();
            configuration.set("Config.Version", HiChatPlus.CONFIG_VERSION);

            if(!configuration.isSet("UseDatabase")) {
                configuration.set("UseDatabase", false);
            }
            if(!configuration.isSet("Chat.Default.Enabled")) {
                configuration.set("Chat.Default.Enabled", true);
            }
            if(!configuration.isSet("Chat.Default.Format")) {
                configuration.set("Chat.Default.Format", "§6Chat §8| §e{Player} §8> §7{Message}");
            }
            if(!configuration.isSet("Chat.Private.Enabled")) {
                configuration.set("Chat.Private.Enabled", true);
            }
            if(!configuration.isSet("Chat.Private.FormatSender")) {
                configuration.set("Chat.Private.FormatSender", "§6Private §8| §7To §e{Player} §8> §a{Message}");
            }
            if(!configuration.isSet("Chat.Private.FormatTarget")) {
                configuration.set("Chat.Private.FormatTarget", "§6Private §8| §7From §e{Player} §8> §a{Message}");
            }
            if(!configuration.isSet("Chat.Emoji.Enabled")) {
                configuration.set("Chat.Emoji.Enabled", true);
            }
            if(!configuration.isSet("Chat.Emoji.List")) {
                ArrayList<String> emoji = new ArrayList<>();
                emoji.add("heart/§c❤§7");
                emoji.add("green_heart/§a❤§7");
                emoji.add("purple_heart/§5❤§7");
                configuration.set("Chat.Emoji.List", emoji);
            }
            if(!configuration.isSet("Message.Prefix")) {
                configuration.set("Message.Prefix", "§6HiChatPlus §8|");
            }
            if(!configuration.isSet("Message.Error.NoPermission")) {
                configuration.set("Message.Error.NoPermission", "{Prefix} §cYou don't have the permission to perform this command§8.");
            }
            if(!configuration.isSet("Message.Error.Syntax")) {
                configuration.set("Message.Error.Syntax", "{Prefix} §cThe command syntax is not correct§8. §e{Usage}");
            }
            if(!configuration.isSet("Message.Error.InvalidPlayer")) {
                configuration.set("Message.Error.InvalidPlayer", "{Prefix} §cThe given player is invalid§8.");
            }

            try {
                configuration.save(settingsConfigFile);
            } catch(IOException exception) {
                exception.printStackTrace();
            }

        }

        YamlConfiguration databaseConfig = YamlConfiguration.loadConfiguration(databaseConfigFile);
        YamlConfiguration settingsConfig = YamlConfiguration.loadConfiguration(settingsConfigFile);

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
        CHAT_EMOJI_ENABLED = settingsConfig.getBoolean("Features.Chat.Emoji.Enabled");
        CHAT_EMOJI_LIST = settingsConfig.getStringList("Features.Chat.Emoji.List");

        MESSAGE_PREFIX = settingsConfig.getString("Message.Format");
        MESSAGE_ERROR_NOPERMISSION = settingsConfig.getString("Message.Error.NoPermission");
        MESSAGE_ERROR_SYNTAX = settingsConfig.getString("Message.Error.Syntax");
        MESSAGE_ERROR_INVALIDPLAYER = settingsConfig.getString("Message.Error.InvalidPlayer");

        Logger.log(LogLevel.INFORMATION, "Successfully loaded the configuration files.");

    }

}
