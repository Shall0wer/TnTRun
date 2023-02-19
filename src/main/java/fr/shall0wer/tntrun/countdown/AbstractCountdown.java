package fr.shall0wer.tntrun.countdown;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class AbstractCountdown extends BukkitRunnable {

    protected int timer;

    protected AbstractCountdown(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }
}
