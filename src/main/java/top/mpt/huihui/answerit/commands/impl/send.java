package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.ConfigUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;
import static top.mpt.huihui.answerit.Main.*;

public class send extends ICommand {
    public send(){
        super("send", "", "no-usage");
    }

    /* from commands.impl.setAnswer(with Player Execute) */
    public boolean onCommand(CommandSender sender, String[] args){
        // args[0] == 答对啦！
        // args[1] == NameFlying(提问者)
        if (sender instanceof Player){
            if (!prize.canPrize){
                PlayerUtils.send(sender, ConfigUtils.getConfig(config, "player_cant_answer"));
                return true;
            }
            if (args.length != 2){
                PlayerUtils.send(sender, ConfigUtils.getConfig(config, "global.command_err_format"));
            } else if (args[0].equals("答对啦！")){
                ChatUtils.broadcast((String) ConfigUtils.getConfig(config, "select.target_choose_right_answer"), sender.getName(), args[1]);
                prize.setPrizePlayer((Player) sender);
                prize.setTargetPlayer(Bukkit.getPlayer(args[1]));
                prize.executePrize();
            } else if (args[0].equals("答错了！")){
                ChatUtils.broadcast((String) ConfigUtils.getConfig(config, "select.target_choose_wrong_answer"), sender.getName(), args[1]);
                prize.setTargetPlayer((Player) sender);
                prize.setPrizePlayer(Bukkit.getPlayer(args[1]));
                prize.executePrize();
            } else {
                PlayerUtils.send(sender, "#GREEN#不对啊qwq，肯定是服务器出问题了，这行字按道理来说不会出现的qwq。不要找灰灰好吧");
            }
            /* to prize.prize */
        } else {
            sender.sendMessage((String) ConfigUtils.getConfig(config, "sender_err"));
        }
        return true;
    }


    public String permission(){
        return "answer.question";
    }
}
