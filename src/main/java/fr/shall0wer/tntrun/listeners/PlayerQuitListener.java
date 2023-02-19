package fr.shall0wer.tntrun.listeners;

import fr.shall0wer.tntrun.game.Game;
import fr.shall0wer.tntrun.manager.PlayerManager;
import fr.shall0wer.tntrun.state.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        GameState state = Game.getInstance().getState();
        int nb = PlayerManager.getInstance().removePlayer(player.getUniqueId());
        if (state == GameState.WAITING || state == GameState.PRESTARTING || state == GameState.STARTING) {
            for (Player players : Bukkit.getOnlinePlayers()){
                players.sendMessage("§6[TnTRun] §b" + player.getName() + "§r vient de quitté la partie. §7(§b" + nb + "§7/§b" + PlayerManager.getInstance().getMaxPlayers() + "§7)");
            }
            if (state != GameState.WAITING && PlayerManager.getInstance().getNbPlayers() < PlayerManager.getInstance().getMinPlayers())
                Game.getInstance().waitingGame();
        } else if (state == GameState.RUNNING) {
            for (Player players : Bukkit.getOnlinePlayers())
                players.sendMessage("§6[TnTRun] §b" + player.getName() + "§7 a quitté la partie.");
            if(PlayerManager.getInstance().getNbPlayers() == 1){
                Game.getInstance().endGame();
            }
        }
    }
}
