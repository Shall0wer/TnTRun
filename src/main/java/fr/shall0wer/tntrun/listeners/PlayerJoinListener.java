package fr.shall0wer.tntrun.listeners;

import fr.shall0wer.tntrun.game.Game;
import fr.shall0wer.tntrun.manager.PlayerManager;
import fr.shall0wer.tntrun.state.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);

        Player player = event.getPlayer();
        GameState state = Game.getInstance().getState();
        if(state == GameState.WAITING || state == GameState.PRESTARTING){
            initGamePlayer(player);
        } else {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("§7§oVous avez rejoint une partie en cours.");
        }
    }

    private void initGamePlayer(Player player){
        int nb = PlayerManager.getInstance().addPlayer(player.getUniqueId());
        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(new Location(Bukkit.getWorld("world"), 60, 6, 60));
        for(Player players : Bukkit.getOnlinePlayers()){
            players.sendMessage("§6[TnTRun] §b" + player.getName() + " §fvient de rejoindre la partie. §7(§b" + nb + "§7/§b" + PlayerManager.getInstance().getMAX_PLAYERS() +"§7)");
        }
        if(Game.getInstance().getState() == GameState.WAITING && PlayerManager.getInstance().canStart()){
            Game.getInstance().preStartingGame();
        }
    }
}
