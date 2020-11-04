package net.toujoustudios.hichatplus.player;

import net.toujoustudios.hichatplus.config.Config;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This file was created by IanToujou.
 * Date: 23.10.2020
 * Time: 21:20
 * Project: HiChatPlus
 */
public class PlayerManager {

    private static final HashMap<UUID, PlayerManager> players = new HashMap<>();
    private static final YamlConfiguration playerConfig = Config.getConfigFile("playerdata.yml");

    private UUID uuid;
    private boolean muted;

    public PlayerManager(UUID uuid) {

        this.uuid = uuid;
        muted = playerConfig.getBoolean("Data." + uuid.toString() + ".Muted");

    }

    public void unload() {

        save();
        destroy();

    }

    public void save() {

        playerConfig.set("Data." + uuid.toString() + ".Muted", muted);
        Config.saveToFile(playerConfig, "playerdata.yml");

    }

    public void destroy() {

        players.remove(uuid);

    }

    public UUID getUUID() {

        return uuid;

    }

    public void setUUID(UUID uuid) {

        this.uuid = uuid;

    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public static void unloadAll() {

        for(Map.Entry<UUID, PlayerManager> entry : players.entrySet()) {

            players.get(entry.getKey()).unload();

        }

    }

    public static HashMap<UUID, PlayerManager> getPlayers() {

        return players;

    }

    public static YamlConfiguration getPlayerConfig() {

        return playerConfig;

    }

}
