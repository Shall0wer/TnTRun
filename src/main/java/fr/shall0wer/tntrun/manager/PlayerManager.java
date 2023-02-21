package fr.shall0wer.tntrun.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    private @Getter final int MAX_PLAYERS = 8;
    private @Getter final int MIN_PLAYERS = 2;

    private List<UUID> gamePlayers = new ArrayList<>();

    private static PlayerManager instance = null;

    public PlayerManager(){
        instance = this;
    }

    public static PlayerManager getInstance() {
        return instance;
    }

    public int addPlayer(UUID uuid){
        if(!gamePlayers.contains(uuid)){
            gamePlayers.add(uuid);
        }
        return getNbPlayers();
    }

    public int removePlayer(UUID uuid){
        if(gamePlayers.contains(uuid)){
            gamePlayers.remove(uuid);
        }
        return getNbPlayers();
    }

    public boolean canStart(){
        return gamePlayers.size() >= MIN_PLAYERS;
    }

    // Téléporte les joueurs au centre de la carte
    public void teleportPlayers(){
        for(UUID uuids : gamePlayers){
            Bukkit.getPlayer(uuids).teleport(new Location(Bukkit.getWorld("world"), 60, 6, 60));
        }
    }

    public int getNbPlayers(){
        return gamePlayers.size();
    }
}
