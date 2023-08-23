package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;
import top.mpt.huihui.answerit.utils.i18N;

import static top.mpt.huihui.answerit.Main.*;

public class send extends ICommand {
    public send(){
        super("send", "", "no-usage");
    }

    /* from commands.impl.setAnswer(with Player Execute) */
    public boolean onCommand(CommandSender sender, String[] args){
        // args[0] == 答对啦！
        // args[1] == NameFlying(提问者)
        // args[2] == token
        if (sender instanceof Player){
            if (!prize.canPrize){
                PlayerUtils.send(sender, i18N.getLang("global.player_cant_answer"));
                return true;
            }
            if (args.length != 3){
                PlayerUtils.send(sender, i18N.getLang("global.command_err_format"));
                return true;
            }
            if (!tokens.contains(args[2])){ // 检验token
                PlayerUtils.send(sender, "#RED#Token error.");
            } else if (args[0].equals("答对啦！")){
                // 移除使用过的token
                tokens.remove(args[2]);
                ChatUtils.broadcast((String) i18N.getLang("select.target_choose_right_answer"), sender.getName(), args[1]);
                prize.setPrizePlayer((Player) sender);
                prize.setTargetPlayer(Bukkit.getPlayer(args[1]));
                prize.executePrize();
            } else if (args[0].equals("答错了！")){
                // 移除使用过的token
                tokens.remove(args[2]);
                ChatUtils.broadcast((String) i18N.getLang("select.target_choose_wrong_answer"), sender.getName(), args[1]);
                prize.setTargetPlayer((Player) sender);
                prize.setPrizePlayer(Bukkit.getPlayer(args[1]));
                prize.executePrize();
            } else {
                PlayerUtils.send(sender, "#RED#Error format was executed by players.");
            }
            /* to prize.prize */
        } else {
            sender.sendMessage((String) i18N.getLang("sender_err"));
        }
        return true;
    }


    public String permission(){
        return "answer.question";
    }
}
