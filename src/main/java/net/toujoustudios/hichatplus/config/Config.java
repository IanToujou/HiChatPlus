package net.toujoustudios.hichatplus.config;

import net.toujoustudios.hichatplus.log.LogLevel;
import net.toujoustudios.hichatplus.log.Logger;
import net.toujoustudios.hichatplus.main.HiChatPlus;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
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
    public static boolean CHAT_COLOR_ENABLED;
    public static boolean CHAT_DEFAULT_ENABLED;
    public static boolean CHAT_PRIVATE_ENABLED;
    public static boolean CHAT_EMOJI_ENABLED;

    //Permission
    public static boolean CHAT_COLOR_USEPERMISSION;

    //Formatting
    public static String CHAT_DEFAULT_FORMAT;
    public static String CHAT_DEFAULT_COLOR;
    public static String CHAT_PRIVATE_FORMAT_SENDER;
    public static String CHAT_PRIVATE_FORMAT_TARGET;
    public static String CHAT_PRIVATE_COLOR;
    public static String CHAT_COLOR_PREFIX;

    //Lists
    public static List<String> CHAT_EMOJI_LIST;

    //Message
    public static String MESSAGE_PREFIX;
    public static String MESSAGE_ERROR_NOPERMISSION;
    public static String MESSAGE_ERROR_SYNTAX;
    public static String MESSAGE_ERROR_INVALIDPLAYER;
    public static String MESSAGE_ERROR_MUTED_CHAT;

    public static String MESSAGE_NOTIFICATION_MUTE_SENDER;
    public static String MESSAGE_NOTIFICATION_MUTE_TARGET;
    public static String MESSAGE_NOTIFICATION_UNMUTE_SENDER;
    public static String MESSAGE_NOTIFICATION_UNMUTE_TARGET;

    //Sound
    public static boolean CHAT_PRIVATE_SOUND_ENABLED;
    public static Sound CHAT_PRIVATE_SOUND_TYPE;
    public static float CHAT_PRIVATE_SOUND_PITCH;
    public static SoundCategory CHAT_PRIVATE_SOUND_CATEGORY;

    @SuppressWarnings("all")
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
                if(!configuration.isSet("Chat.Color.Enabled")) {
                    configuration.set("Chat.Color.Enabled", true);
                }
                if(!configuration.isSet("Chat.Color.UsePermission")) {
                    configuration.set("Chat.Color.UsePermission", false);
                }
                if(!configuration.isSet("Chat.Color.Prefix")) {
                    configuration.set("Chat.Color.Prefix", "&");
                }
                if(!configuration.isSet("Chat.Default.Enabled")) {
                    configuration.set("Chat.Default.Enabled", true);
                }
                if(!configuration.isSet("Chat.Default.Color")) {
                    configuration.set("Chat.Default.Color", "§7");
                }
                if(!configuration.isSet("Chat.Default.Format")) {
                    configuration.set("Chat.Default.Format", "§6Chat §8| §e{Player} §8> {DefaultColor}{Message}");
                }
                if(!configuration.isSet("Chat.Private.Enabled")) {
                    configuration.set("Chat.Private.Enabled", true);
                }
                if(!configuration.isSet("Chat.Private.Sound.Enabled")) {
                    configuration.set("Chat.Private.Sound.Enabled", true);
                }
                if(!configuration.isSet("Chat.Private.Sound.Type")) {
                    configuration.set("Chat.Private.Sound.Type", "BLOCK_NOTE_BLOCK_PLING");
                }
                if(!configuration.isSet("Chat.Private.Sound.Pitch")) {
                    configuration.set("Chat.Private.Sound.Pitch", 1.0f);
                }
                if(!configuration.isSet("Chat.Private.Sound.Category")) {
                    configuration.set("Chat.Private.Sound.Category", "MASTER");
                }
                if(!configuration.isSet("Chat.Private.Color")) {
                    configuration.set("Chat.Private.Color", "§a");
                }
                if(!configuration.isSet("Chat.Private.Format.Sender")) {
                    configuration.set("Chat.Private.Format.Sender", "§6Private §8| §7To §e{Player} §8> {PrivateColor}{Message}");
                }
                if(!configuration.isSet("Chat.Private.Format.Target")) {
                    configuration.set("Chat.Private.Format.Target", "§6Private §8| §7From §e{Player} §8> {PrivateColor}{Message}");
                }
                if(!configuration.isSet("Chat.Emoji.Enabled")) {
                    configuration.set("Chat.Emoji.Enabled", true);
                }
                if(!configuration.isSet("Chat.Emoji.List")) {
                    ArrayList<String> emoji = new ArrayList<>();
                    emoji.add("heart/§c❤");
                    emoji.add("green_heart/§a❤");
                    emoji.add("purple_heart/§5❤");
                    emoji.add("skull/§f☠");
                    emoji.add("peace/§d☮");
                    emoji.add("yin_yang/§f☯");
                    emoji.add("spades/§8♠");
                    emoji.add("clubs/§8♣");
                    emoji.add("diamond/§b♦");
                    emoji.add("king/§6♔");
                    emoji.add("queen/§6♕");
                    emoji.add("fleur_de_lis/§b⚜");
                    emoji.add("star/§e★");
                    emoji.add("empty_star/§e☆");
                    emoji.add("comet/§b☄");
                    emoji.add("lightning_bolt/§e⚡");
                    emoji.add("moon/§e☾");
                    emoji.add("moon_inverted/§e☾");
                    emoji.add("sun/§e☀");
                    emoji.add("clouds/§f☁");
                    emoji.add("umbrella/§d☂");
                    emoji.add("snowman/§f☃");
                    emoji.add("smiling/§e☺");
                    emoji.add("frowning/§e☹");
                    emoji.add("telephone/§c☎");
                    emoji.add("point_left/§e☜");
                    emoji.add("point_right/§e☞");
                    emoji.add("point_up/§e☝");
                    emoji.add("point_down/§e☟");
                    emoji.add("writing_hand/§e✍");
                    emoji.add("victory_hand/§e✌");
                    emoji.add("radioactive/§6☢");
                    emoji.add("biohazard/§2☣");
                    emoji.add("hotsprings/§c♨");
                    emoji.add("flower/§d✿");
                    emoji.add("trident/§b♆");
                    emoji.add("star_and_moon/§e☪");
                    emoji.add("eighth_note/§5♪");
                    emoji.add("quarter_note/§5♩");
                    emoji.add("beamed_eighth_note/§5♫");
                    emoji.add("beamed_sixteenth_note/§5♬");
                    emoji.add("scissors/§c✂");
                    emoji.add("phone_sign/§a✆");
                    emoji.add("envelope/§f✉");
                    emoji.add("four_pointed_star/§e✦");
                    emoji.add("west_syriac_cross/§6♰");
                    emoji.add("east_syriac_cross/§6♱");
                    emoji.add("infinity/§b∞");
                    emoji.add("male/§9♂");
                    emoji.add("female/§d♀");
                    emoji.add("mercury/§4☿");
                    emoji.add("trademark/§b™");
                    emoji.add("registered/§b®");
                    emoji.add("copyright/§b©");
                    emoji.add("multiplication/§4✖");
                    emoji.add("cross/§c✗");
                    emoji.add("waves/§e♒");
                    emoji.add("up/§9▲");
                    emoji.add("down/§9▼");
                    emoji.add("dot_empty/§b○");
                    emoji.add("dot_filled/§b●");
                    emoji.add("tsu_smile/§eツ");
                    emoji.add("times/§f回");
                    emoji.add("for_all/§f∀");
                    emoji.add("up_tack/§f⊥");
                    emoji.add("angle/§f∠");
                    emoji.add("logic_or/§f∨");
                    emoji.add("logic_and/§f∧");
                    emoji.add("intersection/§f∩");
                    emoji.add("subset_of/§f⊂");
                    emoji.add("superset_of/§f⊃");
                    emoji.add("union/§f∪");
                    configuration.set("Chat.Emoji.List", emoji);
                }
                if(!configuration.isSet("Message.Prefix")) {
                    configuration.set("Message.Prefix", "§6HiChatPlus §8|");
                }
                if(!configuration.isSet("Message.Error.NoPermission")) {
                    configuration.set("Message.Error.NoPermission", "{Prefix} §cYou do not have the permission to perform this command§8.");
                }
                if(!configuration.isSet("Message.Error.Syntax")) {
                    configuration.set("Message.Error.Syntax", "{Prefix} §cThe command syntax is not correct§8. §cUsage§8: §e{Usage}");
                }
                if(!configuration.isSet("Message.Error.InvalidPlayer")) {
                    configuration.set("Message.Error.InvalidPlayer", "{Prefix} §cThe given player is invalid§8.");
                }
                if(!configuration.isSet("Message.Error.MutedChat")) {
                    configuration.set("Message.Error.MutedChat", "{Prefix} §cYou are currently muted§8. §cReason: §e{Reason}");
                }
                if(!configuration.isSet("Message.Notification.Mute.Sender")) {
                    configuration.set("Message.Notification.Mute.Sender", "{Prefix} §e{Player} §chas been muted §8. §cReason§8: §e{Reason}");
                }
                if(!configuration.isSet("Message.Notification.Mute.Target")) {
                    configuration.set("Message.Notification.Mute.Target", "{Prefix} §cYou have been muted§8. §cReason§8: §e{Reason}");
                }
                if(!configuration.isSet("Message.Notification.Unmute.Sender")) {
                    configuration.set("Message.Notification.Unmute.Sender", "{Prefix} §e{Player} §chas been unmuted§8.");
                }
                if(!configuration.isSet("Message.Notification.Unmute.Target")) {
                    configuration.set("Message.Notification.Unmute.Target", "{Prefix} §cYou have been unmuted§8.");
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
            if(!configuration.isSet("Chat.Color.Enabled")) {
                configuration.set("Chat.Color.Enabled", true);
            }
            if(!configuration.isSet("Chat.Color.UsePermission")) {
                configuration.set("Chat.Color.UsePermission", false);
            }
            if(!configuration.isSet("Chat.Color.Prefix")) {
                configuration.set("Chat.Color.Prefix", "&");
            }
            if(!configuration.isSet("Chat.Default.Enabled")) {
                configuration.set("Chat.Default.Enabled", true);
            }
            if(!configuration.isSet("Chat.Default.Color")) {
                configuration.set("Chat.Default.Color", "§7");
            }
            if(!configuration.isSet("Chat.Default.Format")) {
                configuration.set("Chat.Default.Format", "§6Chat §8| §e{Player} §8> {DefaultColor}{Message}");
            }
            if(!configuration.isSet("Chat.Private.Enabled")) {
                configuration.set("Chat.Private.Enabled", true);
            }
            if(!configuration.isSet("Chat.Private.Sound.Enabled")) {
                configuration.set("Chat.Private.Sound.Enabled", true);
            }
            if(!configuration.isSet("Chat.Private.Sound.Type")) {
                configuration.set("Chat.Private.Sound.Type", "BLOCK_NOTE_BLOCK_PLING");
            }
            if(!configuration.isSet("Chat.Private.Sound.Pitch")) {
                configuration.set("Chat.Private.Sound.Pitch", 1.0f);
            }
            if(!configuration.isSet("Chat.Private.Sound.Category")) {
                configuration.set("Chat.Private.Sound.Category", "MASTER");
            }
            if(!configuration.isSet("Chat.Private.Color")) {
                configuration.set("Chat.Private.Color", "§a");
            }
            if(!configuration.isSet("Chat.Private.Format.Sender")) {
                configuration.set("Chat.Private.Format.Sender", "§6Private §8| §7To §e{Player} §8> {PrivateColor}{Message}");
            }
            if(!configuration.isSet("Chat.Private.Format.Target")) {
                configuration.set("Chat.Private.Format.Target", "§6Private §8| §7From §e{Player} §8> {PrivateColor}{Message}");
            }
            if(!configuration.isSet("Chat.Emoji.Enabled")) {
                configuration.set("Chat.Emoji.Enabled", true);
            }
            if(!configuration.isSet("Chat.Emoji.List")) {
                ArrayList<String> emoji = new ArrayList<>();
                emoji.add("heart/§c❤");
                emoji.add("green_heart/§a❤");
                emoji.add("purple_heart/§5❤");
                emoji.add("skull/§f☠");
                emoji.add("peace/§d☮");
                emoji.add("yin_yang/§f☯");
                emoji.add("spades/§8♠");
                emoji.add("clubs/§8♣");
                emoji.add("diamond/§b♦");
                emoji.add("king/§6♔");
                emoji.add("queen/§6♕");
                emoji.add("fleur_de_lis/§b⚜");
                emoji.add("star/§e★");
                emoji.add("empty_star/§e☆");
                emoji.add("comet/§b☄");
                emoji.add("lightning_bolt/§e⚡");
                emoji.add("moon/§e☾");
                emoji.add("moon_inverted/§e☾");
                emoji.add("sun/§e☀");
                emoji.add("clouds/§f☁");
                emoji.add("umbrella/§d☂");
                emoji.add("snowman/§f☃");
                emoji.add("smiling/§e☺");
                emoji.add("frowning/§e☹");
                emoji.add("telephone/§c☎");
                emoji.add("point_left/§e☜");
                emoji.add("point_right/§e☞");
                emoji.add("point_up/§e☝");
                emoji.add("point_down/§e☟");
                emoji.add("writing_hand/§e✍");
                emoji.add("victory_hand/§e✌");
                emoji.add("radioactive/§6☢");
                emoji.add("biohazard/§2☣");
                emoji.add("hotsprings/§c♨");
                emoji.add("flower/§d✿");
                emoji.add("trident/§b♆");
                emoji.add("star_and_moon/§e☪");
                emoji.add("eighth_note/§5♪");
                emoji.add("quarter_note/§5♩");
                emoji.add("beamed_eighth_note/§5♫");
                emoji.add("beamed_sixteenth_note/§5♬");
                emoji.add("scissors/§c✂");
                emoji.add("phone_sign/§a✆");
                emoji.add("envelope/§f✉");
                emoji.add("four_pointed_star/§e✦");
                emoji.add("west_syriac_cross/§6♰");
                emoji.add("east_syriac_cross/§6♱");
                emoji.add("infinity/§b∞");
                emoji.add("male/§9♂");
                emoji.add("female/§d♀");
                emoji.add("mercury/§4☿");
                emoji.add("trademark/§b™");
                emoji.add("registered/§b®");
                emoji.add("copyright/§b©");
                emoji.add("multiplication/§4✖");
                emoji.add("cross/§c✗");
                emoji.add("waves/§e♒");
                emoji.add("up/§9▲");
                emoji.add("down/§9▼");
                emoji.add("dot_empty/§b○");
                emoji.add("dot_filled/§b●");
                emoji.add("tsu_smile/§eツ");
                emoji.add("times/§f回");
                emoji.add("for_all/§f∀");
                emoji.add("up_tack/§f⊥");
                emoji.add("angle/§f∠");
                emoji.add("logic_or/§f∨");
                emoji.add("logic_and/§f∧");
                emoji.add("intersection/§f∩");
                emoji.add("subset_of/§f⊂");
                emoji.add("superset_of/§f⊃");
                emoji.add("union/§f∪");
                configuration.set("Chat.Emoji.List", emoji);
            }
            if(!configuration.isSet("Message.Prefix")) {
                configuration.set("Message.Prefix", "§6HiChatPlus §8|");
            }
            if(!configuration.isSet("Message.Error.NoPermission")) {
                configuration.set("Message.Error.NoPermission", "{Prefix} §cYou do not have the permission to perform this command§8.");
            }
            if(!configuration.isSet("Message.Error.Syntax")) {
                configuration.set("Message.Error.Syntax", "{Prefix} §cThe command syntax is not correct§8. §cUsage§8: §e{Usage}");
            }
            if(!configuration.isSet("Message.Error.InvalidPlayer")) {
                configuration.set("Message.Error.InvalidPlayer", "{Prefix} §cThe given player is invalid§8.");
            }
            if(!configuration.isSet("Message.Error.MutedChat")) {
                configuration.set("Message.Error.MutedChat", "{Prefix} §cYou are currently muted§8. §cReason: §e{Reason}");
            }
            if(!configuration.isSet("Message.Notification.Mute.Sender")) {
                configuration.set("Message.Notification.Mute.Sender", "{Prefix} §e{Player} §chas been muted §8. §cReason§8: §e{Reason}");
            }
            if(!configuration.isSet("Message.Notification.Mute.Target")) {
                configuration.set("Message.Notification.Mute.Target", "{Prefix} §cYou have been muted§8. §cReason§8: §e{Reason}");
            }
            if(!configuration.isSet("Message.Notification.Unmute.Sender")) {
                configuration.set("Message.Notification.Unmute.Sender", "{Prefix} §e{Player} §chas been unmuted§8.");
            }
            if(!configuration.isSet("Message.Notification.Unmute.Target")) {
                configuration.set("Message.Notification.Unmute.Target", "{Prefix} §cYou have been unmuted§8.");
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

        ENABLED = settingsConfig.getBoolean("Enabled");
        USE_DATABASE = settingsConfig.getBoolean("UseDatabase");

        CHAT_COLOR_ENABLED = settingsConfig.getBoolean("Chat.Color.Enabled");
        CHAT_COLOR_USEPERMISSION = settingsConfig.getBoolean("Chat.Color.UsePermission");
        CHAT_COLOR_PREFIX = settingsConfig.getString("Chat.Color.Prefix");
        CHAT_DEFAULT_ENABLED = settingsConfig.getBoolean("Chat.Default.Enabled");
        CHAT_DEFAULT_FORMAT = settingsConfig.getString("Chat.Default.Format");
        CHAT_DEFAULT_COLOR = settingsConfig.getString("Chat.Default.Color");
        CHAT_PRIVATE_ENABLED = settingsConfig.getBoolean("Chat.Private.Enabled");
        CHAT_PRIVATE_SOUND_ENABLED = settingsConfig.getBoolean("Chat.Private.Sound.Enabled");
        CHAT_PRIVATE_SOUND_TYPE = Sound.valueOf(settingsConfig.getString("Chat.Private.Sound.Type"));
        CHAT_PRIVATE_SOUND_PITCH = (float) settingsConfig.getDouble("Chat.Private.Sound.Pitch");
        CHAT_PRIVATE_SOUND_CATEGORY = SoundCategory.valueOf(settingsConfig.getString("Chat.Private.Sound.Category"));
        CHAT_PRIVATE_COLOR = settingsConfig.getString("Chat.Private.Color");
        CHAT_PRIVATE_FORMAT_SENDER = settingsConfig.getString("Chat.Private.Format.Sender");
        CHAT_PRIVATE_FORMAT_TARGET = settingsConfig.getString("Chat.Private.Format.Target");
        CHAT_EMOJI_ENABLED = settingsConfig.getBoolean("Chat.Emoji.Enabled");
        CHAT_EMOJI_LIST = settingsConfig.getStringList("Chat.Emoji.List");

        MESSAGE_PREFIX = settingsConfig.getString("Message.Prefix");
        MESSAGE_ERROR_NOPERMISSION = settingsConfig.getString("Message.Error.NoPermission").replace("{Prefix}", MESSAGE_PREFIX);
        MESSAGE_ERROR_SYNTAX = settingsConfig.getString("Message.Error.Syntax").replace("{Prefix}", MESSAGE_PREFIX);
        MESSAGE_ERROR_INVALIDPLAYER = settingsConfig.getString("Message.Error.InvalidPlayer").replace("{Prefix}", MESSAGE_PREFIX);
        MESSAGE_ERROR_MUTED_CHAT = settingsConfig.getString("Message.Error.MutedChat").replace("{Prefix}", MESSAGE_PREFIX);
        MESSAGE_NOTIFICATION_MUTE_SENDER = settingsConfig.getString("Message.Notification.Mute.Sender").replace("{Prefix}", MESSAGE_PREFIX);
        MESSAGE_NOTIFICATION_MUTE_TARGET = settingsConfig.getString("Message.Notification.Mute.Target").replace("{Prefix}", MESSAGE_PREFIX);
        MESSAGE_NOTIFICATION_UNMUTE_SENDER = settingsConfig.getString("Message.Notification.Unmute.Sender").replace("{Prefix}", MESSAGE_PREFIX);
        MESSAGE_NOTIFICATION_UNMUTE_TARGET = settingsConfig.getString("Message.Notification.Unmute.Target").replace("{Prefix}", MESSAGE_PREFIX);

        Logger.log(LogLevel.INFORMATION, "Successfully loaded the configuration files.");

    }

    public static YamlConfiguration getConfigFile(String filename) {

        File file = new File("plugins/" + HiChatPlus.PLUGIN_NAME + "/" + filename);
        return YamlConfiguration.loadConfiguration(file);

    }

    public static void saveToFile(YamlConfiguration configuration, String filename) {

        File file = new File("plugins/" + HiChatPlus.PLUGIN_NAME + "/" + filename);

        try {

            configuration.save(file);

        } catch(IOException exception) {

            exception.printStackTrace();

        }

    }

}
