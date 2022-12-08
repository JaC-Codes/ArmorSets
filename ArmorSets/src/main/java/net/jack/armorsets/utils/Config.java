package net.jack.armorsets.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class Config extends YamlConfiguration {

    private final JavaPlugin plugin;
    private final String fileName;

    public Config(JavaPlugin plugin, String fileName) throws IOException, InvalidConfigurationException {
        this.plugin = plugin;
        this.fileName = fileName + ".yml";

        if (!plugin.getDataFolder().exists())
            if (plugin.getDataFolder().mkdir())
                System.out.println("The data folder didn't exist so we created it for you.");

        File file = new File(plugin.getDataFolder(), this.fileName);
        if (!file.exists()) {
            if (plugin.getResource(this.fileName) != null)
                plugin.saveResource(this.fileName, false);
            else save();

            load(file);
            save();
            return;
        }

        load(file);
    }

    @Override
    @SuppressWarnings("ALL")
    public String getString(String path) {
        try {
            if (super.getString(path).contains("&"))
                return ChatColor.translateAlternateColorCodes('&', super.getString(path));

            return super.getString(path);
        } catch (NullPointerException ex) {
            System.out.println("The path " + path + " does not exist.");
            return "Error Read Console.";
        }
    }


    @Override
    public List<String> getStringList(String path) {

        List<String> yes = super.getStringList(path).stream().filter(s -> s.contains("&"))
                .map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());

        yes.addAll(super.getStringList(path).stream()
                .filter(s -> !yes.contains(s) && !s.contains("&")).collect(Collectors.toList()));
        return yes;
    }

    public void save() {
        try {
            this.save(new File(plugin.getDataFolder(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
