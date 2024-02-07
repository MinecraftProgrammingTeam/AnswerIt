package top.mpt.huihui.answerit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.i18N;

import static top.mpt.huihui.answerit.Main.Online_Players;
import static top.mpt.huihui.answerit.Main.playersOnQuestioning;

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
        Player player = event.getPlayer();
        Online_Players.remove(player.getName());
        // 如果玩家在提问队列中，就移除玩家
        // TODO:并扣除生命值上限
        if (playersOnQuestioning.contains(player)){
            playersOnQuestioning.remove(prize.getPrizePlayer());
            playersOnQuestioning.remove(prize.getTargetPlayer());
            // 全服公告
            ChatUtils.broadcast(i18N.getLang("global.player_quit").toString(), player.getName());
        }
    }
}
