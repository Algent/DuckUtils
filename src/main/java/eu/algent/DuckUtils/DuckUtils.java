package eu.algent.DuckUtils;

import java.util.logging.Logger;

import eu.algent.DuckUtils.Listeners.AntiSpawnerFarming;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import eu.algent.DuckUtils.Commands.GiveHead;
import eu.algent.DuckUtils.Config.ConfigCore;

public class DuckUtils extends JavaPlugin {

    private PluginManager pm;
    private Logger log;
    private ConfigCore duckConfig;

    @Override
    public void onEnable() {
        pm = this.getServer().getPluginManager();
        log = this.getLogger();
        duckConfig = new ConfigCore(this);

        if (!duckConfig.isPluginEnabled()) {
            log.info("is disabled in configuration.");
            pm.disablePlugin(this);
            return;
        }

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        if(duckConfig.isGiveHeadCommandEnabled())
            getCommand("dugivehead").setExecutor(new GiveHead());
    }

    private void registerEvents() {
        if (duckConfig.isAntiSpawnerFarmingEnabled())
            pm.registerEvents(new AntiSpawnerFarming(), this);
    }
}
