package net.jack.armorsets.timer;

import net.jack.armorsets.ArmorSets;
import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.UUID;

public class Timer {

    private final ArmorSets armorSets;
    private final HashMap<UUID, Long> doublexp = new HashMap<>();

    public Timer(ArmorSets armorSets) {
        this.armorSets = armorSets;
    }

    public void saveTimer(Player player, TimerType timerType) {
        if (timerType.equals(TimerType.DOUBLEXP) && getTimer(player, TimerType.DOUBLEXP) != 0) {
            long remaining = doublexp.get(player.getUniqueId()) - System.currentTimeMillis();
            this.armorSets.getTimer().set("Player." + player.getDisplayName() + ".doublexp", (remaining / 1000L));
            this.armorSets.getTimer().save();
        }
    }

    public Long clearTimer(UUID key) {
            return doublexp.remove(key);
        }

    public void loadTimers(Player player) {
        if (this.armorSets.getTimer().contains("Player." + player.getUniqueId() + ".doublexp")) {
            doublexp.put(player.getUniqueId(), System.currentTimeMillis() + (this.armorSets.getTimer().getInt("Player." +
                    player.getUniqueId() + ".doublexp") * 1000L));
            this.armorSets.getTimer().set("Player." + player.getUniqueId() + ".doublexp", null);
            this.armorSets.getTimer().save();

        }
    }

    public void removeTimer(Player player) {
        if (this.armorSets.getTimer().getInt("Player." + player.getUniqueId() + ".doublexp") < 0) {
            this.armorSets.getTimer().set("Player." + player.getUniqueId() + ".doublexp", null);
            doublexp.remove(player.getUniqueId());
        }
    }

    public long getTimer(Player player, TimerType timerType) {
        if (timerType.equals(TimerType.DOUBLEXP)) {
            return doublexp.get(player.getUniqueId());
        }
        return 0;
    }

    public boolean checkDoublexp(UUID key) {
        return this.doublexp.containsKey(key);
    }

    public void setDoublexp(UUID key, final long value) {
        this.doublexp.put(key, value);
    }

    public Long getDoublexp(UUID key) {
        return this.doublexp.get(key);
    }

    public void timerStart(Player player) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(armorSets, new Runnable() {
            @Override
            public void run() {
                removeTimer(player);
                player.sendTitle(CC.translate("&a&lArmorEvents"), CC.translate("&b&lMythic Rune &a&lDe-Activated"),
                        30, 150, 30);
            }
        }, 30000);
    }


}
