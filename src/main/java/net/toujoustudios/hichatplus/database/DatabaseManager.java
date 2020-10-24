package net.toujoustudios.hichatplus.database;

import net.toujoustudios.hichatplus.config.Config;
import net.toujoustudios.hichatplus.loader.Loader;
import net.toujoustudios.hichatplus.log.LogLevel;
import net.toujoustudios.hichatplus.log.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This file was created by IanToujou.
 * Date: 24.10.2020
 * Time: 17:42
 * Project: HiChatPlus
 */
public class DatabaseManager {

    private static Connection connection;

    public static void connect() {

        if(isConnected()) {

            disconnect();

        }

        try {

            String url = "jdbc:mysql://" + Config.DATABASE_HOST + ":" + Config.DATABASE_PORT + "/" + Config.DATABASE_NAME + "?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, Config.DATABASE_USER, Config.DATABASE_PASSWORD);
            Logger.log(LogLevel.INFORMATION, "The connection to the database has been established.");

        } catch(SQLException exception) {

            exception.printStackTrace();
            Logger.log(LogLevel.FATAL, "Failed to connect to database.");
            Loader.cancel();

        }

    }

    public static void disconnect() {

        if(isConnected()) {

            try {

                connection.close();
                Logger.log(LogLevel.INFORMATION, "The database has been disconnected.");

            } catch(Exception exception) {

                exception.printStackTrace();

            }

        }

    }

    public static boolean isConnected() {

        return connection != null;

    }

    public static ResultSet executeQuery(String query) {

        try {

            return connection.prepareStatement(query).executeQuery();

        } catch(SQLException exception) {

            exception.printStackTrace();

        }

        return null;

    }

    public static void setup() {

        if(isConnected()) {

            Logger.log(LogLevel.INFORMATION, "Checking and setting up database...");
            executeUpdate("CREATE TABLE IF NOT EXISTS learned_jutsu (player_uuid varchar(256), jutsu_id varchar(256));");
            Logger.log(LogLevel.INFORMATION, "Database setup completed.");

        }

    }

    public static void executeUpdate(String update) {

        try {

            connection.prepareStatement(update).executeUpdate();

        } catch(SQLException exception) {

            exception.printStackTrace();

        }

    }

    public static Connection getConnection() {

        return connection;

    }

}
