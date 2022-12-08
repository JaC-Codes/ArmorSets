package net.jack.armorsets.timer;

import net.jack.armorsets.ArmorSets;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TimerImpl implements Listener {

    private final ArmorSets armorSets;
    private final Timer timer;

    public TimerImpl(ArmorSets armorSets) {
        this.armorSets = armorSets;
        this.timer = new Timer(armorSets);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (timer.checkDoublexp(player.getUniqueId())) {
            this.timer.saveTimer(player, TimerType.DOUBLEXP);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        this.timer.loadTimers(event.getPlayer());
    }

}
