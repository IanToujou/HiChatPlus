package net.toujoustudios.hichatplus.player;

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

    private UUID uuid;

    public PlayerManager(UUID uuid) {

        this.uuid = uuid;

    }

    public void unload() {

        save();
        destroy();

    }

    public void save() {



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

    public static void unloadAll() {

        for(Map.Entry<UUID, PlayerManager> entry : players.entrySet()) {

            players.get(entry.getKey()).unload();

        }

    }

    public static HashMap<UUID, PlayerManager> getPlayers() {

        return players;

    }

}
