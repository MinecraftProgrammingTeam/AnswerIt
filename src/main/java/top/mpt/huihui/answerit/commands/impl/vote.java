package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;

import java.util.Objects;

import static top.mpt.huihui.answerit.Main.*;

public class vote extends ICommand {
    public vote(){
        super("vote", "", "no-usage");
    }

    /* from listener.PlayerChat(with Global Player Execute) */
    public boolean onCommand(CommandSender sender, String[] args) {
        /*
         * arg[0] == true/false
         */
        if (sender instanceof Player){
            // 防止重复投票
            for (String player : voteList){
                if (Objects.equals(player, sender.getName())){
                    PlayerUtils.send(sender, "#RED#您已经参与过投票了，请不要重复投票！！！");
                    return true;
                }
            }
            // 避免玩家投票结束后再次投票
            if (!canVote){
                PlayerUtils.send(sender, "#RED#投票时间已过！");
                return true;
            }
            if (args.length != 1){
                PlayerUtils.send(sender, "#RED#格式错误！");
                return true;
            }
            if (args[0].equals("true")){
                ChatUtils.broadcast("#GREEN#玩家： #AQUA#%s #GREEN#投给了： 答案正确", sender.getName());
                voteResult.add(true);
            } else if (args[0].equals("false")){
                ChatUtils.broadcast("#RED#玩家： #AQUA#%s #RED#投给了： 答案错误", sender.getName());
                voteResult.add(false);
            }
            // 投票列表添加玩家
            voteList.add(sender.getName());
            /* influence scheduler.Timer */
        } else {
            sender.sendMessage("请让玩家执行该指令");
        }
        return true;
    }

    public String permission(){
        return "answer.question";
    }
}
