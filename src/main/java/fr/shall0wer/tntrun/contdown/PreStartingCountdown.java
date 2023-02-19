package fr.shall0wer.tntrun.contdown;

import fr.shall0wer.tntrun.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PreStartingCountdown extends AbstractCountdown {

    public PreStartingCountdown(){
        super(31);
    }

    @Override
    public void run() {
        timer--;
        switch (timer){
            case 30:
            case 20:
            case 10:
                String msg = "§6[TnTRun] §fLancement de la partie dans §b" + timer + " §fsecondes.";
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.sendMessage(msg);
                    players.playSound(players.getLocation(), Sound.ORB_PICKUP, 1F, 0.1F);
                }
                break;
            case 5:
                Game.getInstance().startingGame();
                break;
        }
    }
}
