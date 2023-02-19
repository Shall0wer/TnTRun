package fr.shall0wer.tntrun.contdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EndCountdown extends AbstractCountdown {

    public EndCountdown(){
        super(6);
    }

    @Override
    public void run() {
        timer--;
        switch (timer){
            case 5:
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.sendMessage("§6[TnTRun] §fPartie terminée. Retour au lobby dans §b5 §fsecondes.");
                }
                break;
            case 0:
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.kickPlayer("§cPartie terminée.");
                }
                Bukkit.shutdown();
                break;
            default: break;
        }
    }
}
