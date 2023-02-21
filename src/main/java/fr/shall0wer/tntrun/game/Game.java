package fr.shall0wer.tntrun.game;

import fr.shall0wer.tntrun.TnTRun;
import fr.shall0wer.tntrun.countdown.AbstractCountdown;
import fr.shall0wer.tntrun.countdown.EndCountdown;
import fr.shall0wer.tntrun.countdown.PreStartingCountdown;
import fr.shall0wer.tntrun.countdown.StartingCountdown;
import fr.shall0wer.tntrun.manager.PlayerManager;
import fr.shall0wer.tntrun.state.GameState;
import lombok.Getter;
import org.bukkit.scheduler.BukkitTask;

@Getter
public class Game {

    private @Getter static Game instance = null;

    private GameState state;

    private BukkitTask gameTask = null;
    private AbstractCountdown currentTask = null;

    public Game(){
        instance = this;
        this.state = GameState.WAITING;
    }

    // Timers and state
    public void waitingGame(){
        if(state == GameState.PRESTARTING || state == GameState.STARTING){
            state = GameState.WAITING;
            cancelTask();
        }
    }

    public void preStartingGame(){
        if(state == GameState.WAITING){
            state = GameState.PRESTARTING;
            cancelTask();
            currentTask = new PreStartingCountdown();
            gameTask = currentTask.runTaskTimer(TnTRun.getInstance(), 0, 20);
        }
    }

    public void startingGame(){
        if(state == GameState.PRESTARTING){
            state = GameState.STARTING;
            cancelTask();
            currentTask = new StartingCountdown();
            gameTask = currentTask.runTaskTimer(TnTRun.getInstance(), 0, 20);
            PlayerManager.getInstance().teleportPlayers();
        }
    }

    public void startGame(){
        if(state == GameState.STARTING){
            state = GameState.RUNNING;
            cancelTask();
            currentTask = new LoopScheduler();
            gameTask = currentTask.runTaskTimerAsynchronously(TnTRun.getInstance(), 0, 20);
        }
    }

    public void endGame(){
        if(state == GameState.RUNNING){
            state = GameState.TERMINATED;
            cancelTask();
            currentTask = new EndCountdown();
            gameTask = currentTask.runTaskTimer(TnTRun.getInstance(), 0, 20);
        }
    }

    public void cancelTask(){
        if(gameTask != null){
            gameTask.cancel();
            gameTask = null;
            currentTask = null;
        }
    }

}
