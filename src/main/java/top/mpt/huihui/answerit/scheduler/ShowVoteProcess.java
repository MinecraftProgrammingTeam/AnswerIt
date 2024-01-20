package top.mpt.huihui.answerit.scheduler;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import top.mpt.huihui.answerit.Main;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.i18N;

public class ShowVoteProcess extends BukkitRunnable {
    @Override
    public void run() {
        // 计算剩余投票时间
        double EndTime = ((double) (Main.voteEndTime - System.currentTimeMillis()) / 1000);

        Bukkit.getServer().getOnlinePlayers().forEach(p ->{
            if (Main.voteEndTime == 0){
                BaseComponent error = new TextComponent(ChatUtils.translateColor(i18N.getLang("action_bar.error")));
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, error);
                this.cancel();
            }
            else if (EndTime <= 0.0D){
                this.cancel();
            }
            else {
                BaseComponent base = new TextComponent(ChatUtils.translateColor(i18N.getLang("action_bar.base")
                        , String.valueOf(EndTime), Main.voteRight, Main.voteWrong));
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, base);
            }

        });
    }
}