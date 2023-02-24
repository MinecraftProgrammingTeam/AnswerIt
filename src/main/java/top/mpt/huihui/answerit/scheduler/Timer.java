package top.mpt.huihui.answerit.scheduler;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.i18N;

import static top.mpt.huihui.answerit.Main.*;

public class Timer extends BukkitRunnable {

    /* be opened on listener.PlayerChat    */
    /* be influenced on commands.impl.vote */
    @Override
    public void run() {
        int trueCount = 0;
        int falseCount = 0;
        for (Boolean result : voteResult){
            if (result){
                trueCount++;
            } else {
                falseCount++;
            }
        }
        // 清空数组
        voteResult.clear();
        voteList.clear();
        ChatUtils.broadcast((String) i18N.getLang("timer.timer_over_summary"), trueCount, falseCount);
        if (trueCount == falseCount){
            ChatUtils.broadcast((String) i18N.getLang("timer.votes_equal"));
        } else if (trueCount > falseCount){
            ChatUtils.broadcast((String) i18N.getLang("timer.votes_right"));
            prize.executePrize();
        } else {
            ChatUtils.broadcast((String) i18N.getLang("timer.votes_wrong"));
            // 调换顺序
            Player prizePlayer = prize.getPrizePlayer();
            prize.setPrizePlayer(prize.getTargetPlayer());
            prize.setTargetPlayer(prizePlayer);
            // 执行prize
            prize.executePrize();
        }
        // 不可投票
        canVote = false;
        // 关闭Runnable
        this.cancel();
        /* to prize.prize */
    }
}
