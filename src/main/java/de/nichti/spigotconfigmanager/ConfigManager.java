package de.nichti.spigotconfigmanager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ConfigManager {

    protected File configFile;
    protected FileConfiguration config;
    protected FileConfiguration defaultConfig;
    protected final JavaPlugin plugin = Plugin.getPlugin();

    public ConfigManager(String pluginName) {
        if(loadConfig(pluginName) != null){
            System.out.println("Error creating config with plugin name " + pluginName);
        }
    }

    public FileConfiguration getConfig(String name){
        return this.config;
    }

    protected void registerDefaultConfig(FileConfiguration defaultConfig){ this.defaultConfig = defaultConfig; }

    protected Boolean saveDefaultConfig(){
        if(defaultConfig.saveToString().length() > 0){
            this.config = this.defaultConfig;
            return saveData();
        }
        else return false;
    }

    protected void overwriteConfig(FileConfiguration newConfig){
        this.config = newConfig;
        saveData();
    }

    public void resetToDefaultConfig(){
        saveDefaultConfig();
    }

    protected FileConfiguration loadConfig(String configName) {
        this.configFile = new File(this.plugin.getDataFolder(), configName);

        if (!configFile.exists()) {
            this.plugin.saveResource(configName, false);
        }

        this.config = new YamlConfiguration();
        try {
            this.config.load(configFile);
            return this.config;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object addData(String path, Object data){
        this.config.set(path, data);

        if(saveData()){
            return data;
        }
        else {
            return null;
        }
    }

    public boolean addLocation(Location location, String path){
        this.config.set(String.format("%s.world", path), location.getWorld().getName());
        this.config.set(String.format("%s.x", path), location.getX());
        this.config.set(String.format("%s.y", path), location.getY());
        this.config.set(String.format("%s.z", path), location.getZ());

        return saveData();
    }

    protected boolean saveData(){
        try {
            this.config.save(configFile);
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Remove if not using placeholderAPI
    public String getString(String path){
        if(this.config.contains(path)){
            return this.config.getString(path);
        }
        return null;
    }
    public Integer getInt(String path){
        if(this.config.contains(path)){
            return this.config.getInt(path);
        }
        return null;
    }

    public Double getDouble(String path){
        if(this.config.contains(path)){
            return this.config.getDouble(path);
        }
        return null;
    }

    public Boolean getBoolean(String path){
        if(this.config.contains(path)){
            return this.config.getBoolean(path);
        }
        return null;
    }

    public List<?> getList(String path){
        if(this.config.contains(path)){
            return this.config.getList(path);
        }
        return null;
    }

    public Location getLocation(String path){
        return new Location(Bukkit.getWorld(getString(String.format("%s.world", path))), getInt(String.format("%s.x", path)), getInt(String.format("%s.y", path)), getInt(String.format("%s.z", path)));
    }

    public Boolean isEmpty(){
        return this.configFile.length() == 0;
    }
}
