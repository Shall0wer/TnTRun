package fr.shall0wer.tntrun.countdown;

import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class AbstractCountdown extends BukkitRunnable {

    protected @Getter int timer;

    protected AbstractCountdown(int timer) {
        this.timer = timer;
    }
}
