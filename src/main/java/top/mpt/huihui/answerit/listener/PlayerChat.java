package top.mpt.huihui.answerit.listener;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitTask;
import top.mpt.huihui.answerit.Main;
import top.mpt.huihui.answerit.commands.impl.q;
import top.mpt.huihui.answerit.schedule.Timer;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.ConfigUtils;

import static top.mpt.huihui.answerit.Main.*;

public class PlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if (isCheckChat){
            Player target = q.target;
            if (event.getPlayer() == target){
                // 设置聊天发送的格式
                event.setCancelled(true);
                ChatUtils.broadcast("%s#GREEN#%s的回答是： #AQUA#%s", normal, target.getName(), event.getMessage());
                // 设置ClickEvent
                ClickEvent clickEventT = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer vote true");
                ClickEvent clickEventF = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer vote false");
                // 设置文字
                TextComponent componentT = new TextComponent(
                        ChatUtils.translateColor("#GREEN#[正确]  ")
                );
                TextComponent componentF = new TextComponent(
                        ChatUtils.translateColor("#RED#[错误]", normal)
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
                int delaySecond = (int) ConfigUtils.getConfig("Write-wait-time", 30);
                ChatUtils.broadcast("%s#RED#计时#AQUA#%d#RED#秒，开始。", normal, delaySecond);
                ChatUtils.broadcast("%s#RED#请在规定时间内完成投票。", normal);
                new Timer().runTaskTimer(Main.getPlugin(Main.class), delaySecond * 20L, 0);
                // 撤销事件
                isCheckChat = false;
            }
        }
    }
}
