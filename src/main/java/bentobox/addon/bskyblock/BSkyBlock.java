package bentobox.addon.bskyblock;

import org.bukkit.World;

import bentobox.addon.bskyblock.commands.AdminCommand;
import bentobox.addon.bskyblock.commands.IslandCommand;
import bentobox.addon.bskyblock.generators.BSkyBlockWorld;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.configuration.BBConfig;

/**
 * Main BSkyBlock class - provides an island minigame in the sky
 * @author tastybento
 * @author Poslovitch
 */
public class BSkyBlock extends Addon {

    private static Addon addon;
    // Settings
    private Settings settings;
    private BSkyBlockWorld bsbWorlds;

    @Override
    public void onLoad() {
        addon = this;
        // Save the default config from config.yml
        saveDefaultConfig();
        // Load settings from config.yml. This will check if there are any issues with it too.
        settings = new BBConfig<>(this, Settings.class).loadConfigObject();
        // Load or create worlds
        bsbWorlds = new BSkyBlockWorld(this);
    }

    @Override
    public void onEnable(){
        // Register commands
        new IslandCommand(this);
        new AdminCommand(this);
    }

    @Override
    public void onDisable() {
        // Save settings
        if (settings != null) {
            new BBConfig<>(this, Settings.class).saveConfigObject(settings);
        }
    }

    /**
     * @return the settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * @return the BSkyBlock world
     */
    public World getIslandWorld() {
        return bsbWorlds.getOverWorld();
    }

    public static Addon getInstance() {
        return addon;
    }
}
