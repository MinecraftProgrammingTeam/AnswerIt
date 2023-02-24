package top.mpt.huihui.answerit.listener;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import top.mpt.huihui.answerit.Main;
import top.mpt.huihui.answerit.commands.impl.q;
import top.mpt.huihui.answerit.scheduler.Timer;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.ConfigUtils;
import top.mpt.huihui.answerit.utils.i18N;

import static top.mpt.huihui.answerit.Main.*;

public class PlayerChat implements Listener {
    /* form commands.impl.q */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        // 判断是否处于Write问答状态
        if (isCheckChat){
            // 获取target
            Player target = q.target;
            // 如果获取到的玩家为target
            if (event.getPlayer().equals(target)){
                // 设置聊天发送的格式
                event.setCancelled(true);
                ChatUtils.broadcast((String) i18N.getLang("write.target_answer_info"), target.getName(), event.getMessage());
                // 设置ClickEvent
                ClickEvent clickEventT = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer vote true");
                ClickEvent clickEventF = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer vote false");
                // 设置文字
                TextComponent componentT = new TextComponent(
                        ChatUtils.translateColor(i18N.getLang("write.vote_right"))
                );
                TextComponent componentF = new TextComponent(
                        ChatUtils.translateColor(i18N.getLang("write.vote_wrong"))
                );
                // setClickEvent
                componentT.setClickEvent(clickEventT);
                componentF.setClickEvent(clickEventF);
                // 加上去
                componentT.addExtra(componentF);
                // 可投票
                canVote = true;
                // 显示给全体玩家
                Bukkit.spigot().broadcast(componentT);
                // 开始计时
                int delaySecond = (int) ConfigUtils.getConfig(instance.getConfig(), "Write-wait-time", 30);
                ChatUtils.broadcast((String) i18N.getLang("timer.timer_start_info"), delaySecond);
                ChatUtils.broadcast((String) i18N.getLang("timer.timer_start_tip"));
                new Timer().runTaskLater(Main.getPlugin(Main.class), delaySecond * 20L);
                // 撤销事件
                isCheckChat = false;
                /* opened scheduler.Timer  */
                /* wait Global Player vote    */
                /* then to commands.impl.vote */
            }
        }
    }
}
