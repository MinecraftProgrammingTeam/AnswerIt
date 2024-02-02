package top.mpt.huihui.answerit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.PlayerUtils;
import top.mpt.huihui.answerit.utils.i18N;

import static top.mpt.huihui.answerit.Main.playersOnQuestioning;

public class InvClose implements Listener{
    @EventHandler
    public void onInvClose(InventoryCloseEvent event){
        if (prize.canPrize){
            if (event.getPlayer().equals(prize.getPrizePlayer())){
                Player prizePlayer = prize.getPrizePlayer();
                Player targetPlayer = prize.getTargetPlayer();
                // 奖励结束，移除玩家
                if (playersOnQuestioning.contains(prizePlayer) && playersOnQuestioning.contains(targetPlayer)){
                    playersOnQuestioning.remove(prizePlayer);
                    playersOnQuestioning.remove(targetPlayer);
                }
                PlayerUtils.send(prize.getPrizePlayer(), i18N.getLang("global.inv_closed"));
                prize.clearAllPlayer();
            }
        }
    }
}
