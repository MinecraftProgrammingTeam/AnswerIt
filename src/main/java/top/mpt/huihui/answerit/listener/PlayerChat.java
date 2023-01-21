package top.mpt.huihui.answerit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import top.mpt.huihui.answerit.commands.impl.q;

public class PlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        Player target = q.target;
        if (event.getPlayer() == target){
            // 设置聊天发送的格式
            event.setMessage("我的答案是: " + event.getMessage());
        }
    }
}
