package guiexamples.guiexamples;

import guiexamples.guiexamples.GuiEventsMain.Guievents;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class Guiexamples extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new Guievents(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
