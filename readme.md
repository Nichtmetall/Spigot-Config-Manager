# Spigot-Config-Manager
1. [Install](#Install Package)
1. [Install](#Install Package)
1. [Example Config](#Example Config)

## Install
Add code below to your `pom.xml` file
```xml
<dependency>
  <groupId>de.nichti</groupId>
  <artifactId>spigotconfigmanager</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
run via command line
```
nvm install
```

## First Steps

Create a **new** class and extend `ConfigManager`. As example `ExampleConfig.java`
```java
public class ExampleConfig extends ConfigManager {
    public ExampleConfig(String pluginName) {
        super(pluginName);
    }
}
```
Then you can create a new Instance of your created class.
I would recommend to make it static.
```java
public static ExampleConfig config = new ExampleConfig("pluginName");
```
## Example Config
```java
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
```

