package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;

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
                PlayerUtils.send(sender, "#RED#想啥呢？答完题了还想再答是吧？？？给爷爬！！！");
                return true;
            }
            if (args.length != 2){
                PlayerUtils.send(sender, "#AQUA#您输入的格式并不正确！");
            } else if (args[0].equals("答对啦！")){
                PlayerUtils.send(sender, "#GREEN#答对啦！");
                ChatUtils.broadcast("#GOLD#玩家： #AQUA#%s #GOLD#答对了 #BLUE#%s #GOLD#的问题。", sender.getName(), args[1]);
                prize.setPrizePlayer((Player) sender);
                prize.setTargetPlayer(Bukkit.getPlayer(args[1]));
                prize.executePrize();
            } else if (args[0].equals("答错了！")){
                PlayerUtils.send(sender, "#RED#答错了qwq");
                ChatUtils.broadcast("#RED#玩家： #AQUA#%s #RED#答错了 #BLUE#%s #RED#的问题。", sender.getName(), args[1]);
                prize.setTargetPlayer((Player) sender);
                prize.setPrizePlayer(Bukkit.getPlayer(args[1]));
                prize.executePrize();
            } else {
                PlayerUtils.send(sender, "#GREEN#不对啊qwq，肯定是服务器出问题了，这行字按道理来说不会出现的qwq。不要找灰灰好吧");
            }
            /* to prize.prize */
        } else {
            sender.sendMessage("请让玩家执行该指令.");
        }
        return true;
    }


    public String permission(){
        return "answer.question";
    }
}
