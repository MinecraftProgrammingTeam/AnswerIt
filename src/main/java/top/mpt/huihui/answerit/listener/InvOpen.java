package top.mpt.huihui.answerit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;

import static top.mpt.huihui.answerit.Main.playersOnQuestioning;

public class InvOpen implements Listener {
    /* from prize.prize */
    @EventHandler
    public void onInvClicked(InventoryClickEvent event){
        // 防止过分占用服务器资源，因为有的时候会疯狂报错
        if (prize.canPrize){
            if (event.getInventory().equals(prize.getTargetPlayer().getInventory())){
                Player prizePlayer = prize.getPrizePlayer();
                Player targetPlayer = prize.getTargetPlayer();
                if (event.getCurrentItem() != null){
                    // 添加物品
                    prizePlayer.getInventory().addItem(event.getCurrentItem());
                    // 移除物品
                    targetPlayer.getInventory().remove(event.getCurrentItem());
                    // 关闭inventory
                    prizePlayer.closeInventory();

                    /* to the end of this(select / write) process */
                }
            }
        }
    }
}
