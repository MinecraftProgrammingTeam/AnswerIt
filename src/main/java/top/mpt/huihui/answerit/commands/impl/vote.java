package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;
import top.mpt.huihui.answerit.utils.i18N;

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
                    PlayerUtils.send(sender, i18N.getLang("write.player_cant_vote"));
                    return true;
                }
            }
            // 避免玩家投票结束后再次投票
            if (!canVote){
                PlayerUtils.send(sender, i18N.getLang("write.vote_timeout"));
                return true;
            }
            if (args.length != 1){
                PlayerUtils.send(sender, i18N.getLang("write.vote_timeout"));
                return true;
            }
            if (args[0].equals("true")){
                ChatUtils.broadcast((String) i18N.getLang("write.server_vote_right_info"), sender.getName());
                voteResult.add(true);
            } else if (args[0].equals("false")){
                ChatUtils.broadcast((String) i18N.getLang("write.server_vote_wrong_info"), sender.getName());
                voteResult.add(false);
            }
            // 投票列表添加玩家
            voteList.add(sender.getName());
            /* influence scheduler.Timer */
        } else {
            sender.sendMessage((String) i18N.getLang("sender_err"));
        }
        return true;
    }

    public String permission(){
        return "answer.question";
    }
}
