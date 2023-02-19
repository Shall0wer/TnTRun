package fr.shall0wer.tntrun.game;

import fr.shall0wer.tntrun.countdown.AbstractCountdown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LoopScheduler extends AbstractCountdown {

    protected LoopScheduler() {
        super(-1);
    }

    @Override
    public void run() {
        timer++;
        switch (timer){
            case 180:
            case 120:
            case 60:
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.sendMessage("Cela fait " + timer + "secondes que la partie est lancée.");
                }
                break;
            default: break;
        }
    }
}
