package top.mpt.huihui.answerit.prize;

import org.bukkit.entity.Player;

/**
 * 奖罚系统
 * @author X_huihui
 */
public class prize {
    private static Player prizePlayer;
    private static Player targetPlayer;
    /**
     * 现在是否可以奖励(仅select，write不需要这个)
     */
    public static boolean canPrize = false;

    /**
     * 设置被奖励的玩家
     * @param prizePlayer 被奖励的玩家
     */
    public static void setPrizePlayer(Player prizePlayer){
        prize.prizePlayer = prizePlayer;
    }

    /**
     * 设置被惩罚的玩家
     * @param targetPlayer 被惩罚的玩家
     */
    public static void setTargetPlayer(Player targetPlayer){
        prize.targetPlayer = targetPlayer;
    }

    /**
     * 获取被奖励的玩家
     * @return 被奖励的玩家
     */
    public static Player getPrizePlayer(){
        return prizePlayer;
    }

    /**
     * 获取被惩罚的玩家
     * @return 被惩罚的玩家
     */
    public static Player getTargetPlayer() {
        return targetPlayer;
    }

    /**
     * 清理掉成功的Man和失败的Man
     */
    public static void clearAllPlayer(){
        canPrize = false;
        prizePlayer = null;
        targetPlayer = null;
    }

    /* from commands.impl.send */
    /* from scheduler.Timer    */
    /**
     * 执行奖励
     * <p>
     * 若需要更改奖励内容，请在这里修改。
     * <p>
     * 默认为打开对方背包并且可以拿取任意一个物品
     */
    public static void executePrize(){
        // 执行奖励
        prizePlayer.openInventory(targetPlayer.getInventory());
        /* to listener.InvOpen */
    }
}
