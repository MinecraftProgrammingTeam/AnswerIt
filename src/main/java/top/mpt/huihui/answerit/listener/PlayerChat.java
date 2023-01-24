package top.mpt.huihui.answerit.listener;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import top.mpt.huihui.answerit.commands.impl.q;
import top.mpt.huihui.answerit.utils.ChatUtils;

public class PlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        Player target = q.target;

        if (event.getPlayer() == target){
            // 设置聊天发送的格式
            event.setMessage("我的答案是: " + event.getMessage());
            TextComponent component = new TextComponent(
                    ChatUtils.translateColor("%s#GREEN#[正确] #RED#[错误]")
            );
        }
    }
}
