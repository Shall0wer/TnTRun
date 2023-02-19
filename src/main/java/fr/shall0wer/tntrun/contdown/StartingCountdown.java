package fr.shall0wer.tntrun.contdown;

import fr.shall0wer.tntrun.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StartingCountdown extends AbstractCountdown {

    public StartingCountdown(){
        super(6);
    }

    @Override
    public void run() {
        timer--;
        switch (timer){
            case 0:
                String start_msg = "§6[TnTRun] §fPartie lancée\n§6§m+------------------------------------------+\n§r \n§fDans le TnTRun tu vas devoir courir et sauter sur des blocs de TNT qui explosent sous tes pieds, créant des trous dans le sol. Le but du jeu est d'être le dernier joueur en vie en évitant les trous et les explosions de TNT.\n§r \n§6§m+------------------------------------------+";
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.sendMessage(start_msg);
                    players.playSound(players.getLocation(), Sound.ORB_PICKUP, 1F, 0.1F);
                }
                Game.getInstance().startGame();
                break;
            default:
                String msg = "§6[TnTRun] §fLancement de la partie dans §b" + timer + "§f seconde" + (timer == 1 ? "" : "s");
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.sendMessage(msg);
                    players.playSound(players.getLocation(), Sound.ORB_PICKUP, 1F, 0.1F);
                }
                break;
        }
    }
}
