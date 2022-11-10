import de.nichti.spigotconfigmanager.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ExampleConfig extends ConfigManager {
    public ExampleConfig(String pluginName) {
        super(pluginName);
    }

    private void test(){
        FileConfiguration config = new YamlConfiguration();
        config.set("Test123", "Test");

        registerDefaultConfig(config);
        setDefaultConfig();
        saveData();
    }
}
