package net.jack.armorsets;

import net.jack.armorsets.commandmanager.CommandManager;
import net.jack.armorsets.fallencrate.FallenCrate;
import net.jack.armorsets.itembuilder.MythicRune;
import net.jack.armorsets.nametag.Nametag;
import net.jack.armorsets.subcommands.DoubleXpCommand;
import net.jack.armorsets.subcommands.PlayerCommand;
import net.jack.armorsets.guiview.InventoryHandler;
import net.jack.armorsets.itembuilder.Demon;
import net.jack.armorsets.itembuilder.Warrior;
import net.jack.armorsets.subcommands.RuneDisplayCommand;
import net.jack.armorsets.timer.Timer;
import net.jack.armorsets.timer.TimerImpl;
import net.jack.armorsets.timer.TimerType;
import net.jack.armorsets.utils.CC;
import net.jack.armorsets.utils.Config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


public class ArmorSets extends JavaPlugin {


    private static ArmorSets instance;
    private Config timer;
    private Timer timers;
    private Nametag nametag;
    private CommandManager manager;
    final HashMap<Player, Long> lastLoc = new HashMap<>();


    public Timer getTimers() {
        return timers;
    }

    public CommandManager getManager() {
        return manager;
    }

    public void onEnable() {
        this.equipCheck();
        this.timers = new Timer(this);
        this.manager = new CommandManager(this);
        this.nametag = new Nametag();
        nametag.register();
        instance = this;
        this.runDxpCheck();
        try {
            timer = new Config(instance, "timer");
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        this.config();
        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + "§6=== ENABLE START ===");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aLoading §dListeners");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aLoading §dCommands");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aMade by §dJack");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== ENABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) ===");
        registerCommands();
        registerEvents();


    }

    private void registerEvents() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new InventoryHandler(this), this);
        manager.registerEvents(new MythicRune(this), this);
        manager.registerEvents(new TimerImpl(this), this);
        manager.registerEvents(new FallenCrate(this), this);
    }

    private void registerCommands() {
        (getCommand("adminas")).setExecutor(new CommandManager(this));
        (getCommand("armorsets")).setExecutor(new PlayerCommand(this));
        (getCommand("asevents")).setExecutor(new DoubleXpCommand(this));
        getCommand("runes").setExecutor(new RuneDisplayCommand(this));
    }

    public void onDisable() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (timers.checkDoublexp(online.getUniqueId())) {
                this.timers.saveTimer(online, TimerType.DOUBLEXP);
            }
        }
        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + "§6=== DISABLING ===");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aDisabling §dListeners");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aDisabling §dCommands");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aMade by §dJack");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== DISABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) =");
    }

    private void config() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void equipCheck() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player playercheck : Bukkit.getOnlinePlayers()) {
                if (Arrays.equals(playercheck.getInventory().getArmorContents(), Demon.getDemon())) {
                    Demon.effectGrant(playercheck);
                    return;
                }
                if (Arrays.equals(playercheck.getInventory().getArmorContents(), Warrior.getWarrior())) {
                    Warrior.effectGrant(playercheck);
                }
            }

        }, 20, 20);
    }

    public Config getTimer() {
        return timer;
    }

    public void runDxpCheck() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (timers.checkDoublexp(online.getUniqueId())) {
                long remaining = timers.getDoublexp(online.getUniqueId()) - System.currentTimeMillis();
                long fullRemaining = (remaining / 1000L);
                long minutes = (fullRemaining % 3600) / 60;
                long seconds = fullRemaining % 60;
                if (seconds < 10) {
                    String format = (minutes + ":0" + seconds);
                    online.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(CC.translate("&b&lDouble XP &a&l- " + format)));
                    online.sendMessage(format);
                } else {
                    String format2 = (minutes + ":0" + seconds);
                    online.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(CC.translate("&b&lDouble XP &a&l- " + format2)));
                    online.sendMessage(format2);
                }
            }
        }
    }
}

