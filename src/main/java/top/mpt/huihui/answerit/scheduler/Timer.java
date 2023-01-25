package top.mpt.huihui.answerit.scheduler;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.ChatUtils;

import static top.mpt.huihui.answerit.Main.*;

public class Timer extends BukkitRunnable {

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
        ChatUtils.broadcast("%s#GREEN#投票结束，有%d人投了答案正确，%d人投了答案错误。", normal, trueCount, falseCount);
        if (trueCount == falseCount){
            ChatUtils.broadcast("%s#GOLD#票数相等，没有奖励也没有惩罚(悲", normal);
        } else if (trueCount > falseCount){
            ChatUtils.broadcast("%s#GREEN#答案正确！", normal);
            prize.executePrize();
        } else {
            ChatUtils.broadcast("%s#RED#答案错误！", normal);
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
    }
}
