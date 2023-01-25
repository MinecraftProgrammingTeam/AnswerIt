package top.mpt.huihui.answerit.schedule;

import org.bukkit.scheduler.BukkitRunnable;
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
        ChatUtils.broadcast("%s#GREEN#投票结束，有%d人投了答案正确，%d人投了答案错误。", normal, trueCount, falseCount);
        if (trueCount == falseCount){
            ChatUtils.broadcast("%s#GOLD#票数相等，没有奖励也没有惩罚(悲", normal);
        } else if (trueCount > falseCount){
            ChatUtils.broadcast("%s#GREEN#答案正确！", normal);
        } else {
            ChatUtils.broadcast("%s#RED#答案错误！", normal);
        }
        // 清空数组
        voteResult.clear();
        // 不可投票
        canVote = false;
        // 关闭Runnable
        this.cancel();
    }
}
