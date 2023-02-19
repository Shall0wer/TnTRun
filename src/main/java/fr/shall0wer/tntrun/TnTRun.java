package fr.shall0wer.tntrun;

import fr.shall0wer.tntrun.game.Game;
import fr.shall0wer.tntrun.listeners.PlayerJoinListener;
import fr.shall0wer.tntrun.listeners.PlayerMoveListener;
import fr.shall0wer.tntrun.listeners.PlayerQuitListener;
import fr.shall0wer.tntrun.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TnTRun extends JavaPlugin {

    private static TnTRun instance = null;

    @Override
    public void onEnable() {
        getLogger().info("Initialization...");

        instance = this;
        registerListeners();

        new Game();
        new PlayerManager();

        getLogger().info("Plugin initialized!");
    }

    @Override
    public void onDisable() {
    }

    public static TnTRun getInstance() {
        return instance;
    }

    private void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
    }
}
