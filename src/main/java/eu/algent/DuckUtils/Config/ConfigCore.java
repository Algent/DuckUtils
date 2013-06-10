package eu.algent.DuckUtils.Config;

import org.bukkit.configuration.file.FileConfiguration;

import eu.algent.DuckUtils.DuckUtils;

public class ConfigCore {

    private boolean pluginEnabled;
    private boolean giveHeadCommand;

    public ConfigCore(DuckUtils plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        pluginEnabled = config.getBoolean("plugin-enabled", true);
        giveHeadCommand = config.getBoolean("givehead-command-enabled", true);
    }

    public boolean isPluginEnabled() {
        return pluginEnabled;
    }

    public boolean isGiveHeadCommandEnabled() {
        return giveHeadCommand;
    }
}
