package top.mpt.huihui.answerit.scheduler;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import top.mpt.huihui.answerit.Main;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.i18N;

import static top.mpt.huihui.answerit.Main.*;
import static top.mpt.huihui.answerit.prize.prize.canPrize;

public class Timer extends BukkitRunnable {

    /* be opened on listener.PlayerChat    */
    /* be influenced on commands.impl.vote */
    @Override
    public void run() {
        int trueCount = voteRight;
        int falseCount = voteWrong;
        // 清空数据
        voteRight = 0;
        voteWrong = 0;
        voteList.clear();
        // 设置可以被奖励
        canPrize = true;
        ChatUtils.broadcast((String) i18N.getLang("timer.timer_over_summary"), trueCount, falseCount);
        if (trueCount == falseCount){
            ChatUtils.broadcast((String) i18N.getLang("timer.votes_equal"));
            // 如果票数相等，执行清空操作
            playersOnQuestioning.remove(prize.getPrizePlayer());
            playersOnQuestioning.remove(prize.getTargetPlayer());
            prize.clearAllPlayer();

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
