import de.nichti.spigotconfigmanager.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ExampleConfig extends ConfigManager {
    public ExampleConfig(String pluginName) {
        super(pluginName);
        defaultConfig();
    }


    private void defaultConfig(){
        FileConfiguration config = new YamlConfiguration();
        config.set("Test1", "Test");
        config.set("Test2", 1);
        config.set("Test3", 1.5);

        registerDefaultConfig(config); //register new defaultConfig
        if(saveDefaultConfig()){
            //do stuff
        }
    }
}
