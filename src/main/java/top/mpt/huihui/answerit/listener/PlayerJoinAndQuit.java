package top.mpt.huihui.answerit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import static top.mpt.huihui.answerit.Main.Online_Players;

public class PlayerJoinAndQuit implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        String PlayerName = event.getPlayer().getName();
        if (!Online_Players.contains(PlayerName)){
            Online_Players.add(PlayerName);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        String PlayerName = event.getPlayer().getName();
        Online_Players.remove(PlayerName);
    }
}
