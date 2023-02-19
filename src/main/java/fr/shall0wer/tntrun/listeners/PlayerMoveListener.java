package fr.shall0wer.tntrun.listeners;

import fr.shall0wer.tntrun.TnTRun;
import fr.shall0wer.tntrun.game.Game;
import fr.shall0wer.tntrun.manager.PlayerManager;
import fr.shall0wer.tntrun.state.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event){
        if(Game.getInstance().getState() == GameState.RUNNING){
            Player player = event.getPlayer();
            if(player.getLocation().getY() <= 4){
                int nb = PlayerManager.getInstance().removePlayer(player.getUniqueId());
                player.setGameMode(GameMode.SPECTATOR);
                for (Player players : Bukkit.getOnlinePlayers()){
                    players.sendMessage("§6[TnTRun] §b" + player.getName() + "§f vient de tomber !");
                }
                if(nb == 1){
                    Game.getInstance().endGame();
                }
            }

            removeBlock(player.getLocation());
        }
    }

    private void removeBlock(Location location){
        Block block = location.getBlock().getRelative(BlockFace.DOWN);
        if(block.getType() == Material.GRAVEL){
            Bukkit.getScheduler().runTaskLater(TnTRun.getInstance(), () -> {
                location.setY(location.getY() - 1);
                location.getBlock().setType(Material.AIR);
                location.setY(location.getY() - 1);
                location.getBlock().setType(Material.AIR);
            }, 10);;
        }
    }
}
