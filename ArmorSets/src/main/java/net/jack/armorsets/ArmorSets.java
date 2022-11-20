package net.jack.armorsets;

import net.jack.armorsets.commandhandler.AdminCommand;
import net.jack.armorsets.commandhandler.PlayerCommand;
import net.jack.armorsets.guiview.InventoryHandler;
import net.jack.armorsets.itembuilder.Demon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;


public class ArmorSets extends JavaPlugin {


    private static ArmorSets instance;


    public void onEnable() {
        this.equipCheck();
        instance = this;
        this.config();
        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + "§6=== ENABLE START ===");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aLoading §dListeners");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aLoading §dCommands");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aMade by §dKoz");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== ENABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) ===");
        registerCommands();
        registerEvents();


    }

    private void registerEvents() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new InventoryHandler(this), this);
    }

    private void registerCommands() {
        getCommand("adminas").setExecutor(new AdminCommand());
        getCommand("armorsets").setExecutor(new PlayerCommand(this));
    }

    public void onDisable() {
        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + "§6=== DISABLING ===");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aDisabling §dListeners");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aDisabling §dCommands");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aMade by §dKoz");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== DISABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) =");
    }

    private void config() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public static ArmorSets getInstance () {
        return instance;
    }

    public void equipCheck() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player playercheck : Bukkit.getOnlinePlayers()) {
                if (Arrays.equals(playercheck.getInventory().getArmorContents(), Demon.getDemon())) {
                    Demon.DEMON.add(playercheck.getUniqueId());
                    Demon.effectGrant(playercheck);
                } else if (!Arrays.equals(playercheck.getInventory().getArmorContents(), Demon.getDemon())) {
                    Demon.DEMON.remove(playercheck.getUniqueId());
                    Demon.removeEffect(playercheck);
                }
            }
        }, 10, 20);
    }
}

