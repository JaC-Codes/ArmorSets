package net.jack.armorsets.nametag;

import net.jack.armorsets.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Nametag implements Listener {

    private Scoreboard scoreboard;

    private void registerScoreboard(){
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    }


    private void registerHealthBar(){
        if (scoreboard.getObjective("health") != null) {
            scoreboard.getObjective("health").unregister();
        }

        Objective objective = scoreboard.registerNewObjective("health", "health");
        objective.setDisplayName(CC.translate("&c❤"));
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    private void registerNameTag(){
        if (scoreboard.getTeam("blue") != null) {
            scoreboard.getTeam("blue").unregister();
        }
        Team team = scoreboard.registerNewTeam("blue");
        team.setPrefix(ChatColor.BLUE + " ");

    }

    public void register(){
        registerScoreboard();
        registerHealthBar();
        registerNameTag();
    }

    @EventHandler
    @Deprecated
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (scoreboard != null) {
            scoreboard.getTeam("blue").addPlayer(player);
        }
    }
}

