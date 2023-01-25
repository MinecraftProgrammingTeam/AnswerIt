package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.LogUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;

import java.util.Objects;

import static top.mpt.huihui.answerit.Main.*;

public class vote extends ICommand {
    public vote(){
        super("vote", "", "no-usage");
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player){
            // 防止重复投票
            LogUtils.info(voteList);
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
                ChatUtils.broadcast("%s#GREEN#玩家： #AQUA#%s #GREEN#投给了： 答案正确", normal, sender.getName());
                voteResult.add(true);
                voteList.add(sender.getName());
            } else if (args[0].equals("false")){
                ChatUtils.broadcast("%s#RED#玩家： #AQUA#%s #RED#投给了： 答案错误", normal, sender.getName());
                voteResult.add(false);
                voteList.add(sender.getName());
            }



        } else {
            sender.sendMessage("请让玩家执行该指令");
        }
        return true;
    }

    public String permission(){
        return "answer.question";
    }
}
