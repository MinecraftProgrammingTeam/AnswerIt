package top.mpt.huihui.answerit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.prize.prize;

import static top.mpt.huihui.answerit.Main.playersOnQuestioning;

public class InvOpen implements Listener {
    /* from prize.prize */
    @EventHandler
    public void onInvClicked(InventoryClickEvent event){
        // 防止过分占用服务器资源，因为有的时候会疯狂报错
        if (prize.canPrize){
            if (event.getInventory().equals(prize.getTargetPlayer().getInventory())){
                if (event.getCurrentItem() != null){
                    Player prizePlayer = prize.getPrizePlayer();
                    Player targetPlayer = prize.getTargetPlayer();
                    // 添加物品
                    prizePlayer.getInventory().addItem(event.getCurrentItem());
                    // 移除物品
                    targetPlayer.getInventory().remove(event.getCurrentItem());
                    // 关闭inventory
                    prizePlayer.closeInventory();
                    // 奖励结束，移除玩家
                    // 防止玩家因为sb腐竹reload而插件报错无法获取奖励
                    if (playersOnQuestioning.contains(prizePlayer) && playersOnQuestioning.contains(targetPlayer)){
                        playersOnQuestioning.remove(prizePlayer);
                        playersOnQuestioning.remove(targetPlayer);
                    }
                    // 结束奖励
                    prize.clearAllPlayer();

                    /* to the end of this(select / write) process */
                }

            }

        }
    }
}
