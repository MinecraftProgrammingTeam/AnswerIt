package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import org.bukkit.command.CommandSender;

public class setanswer extends ICommand {
    public setanswer(){
        super("setanswer", "", "设置问题答案");
    }

    public static String answer = "";
    public static String answerText = "";
    public static Player sender = null;
    public static Player target = null;
    public boolean onCommand(CommandSender sender, String[] args){
        /* args[0] = "NameFlying"
         * args[1] = "Answer"
         * args[2] = "fakeAnswer,Answer"
         */
        if (sender instanceof Player){
            setanswer.sender = (Player)sender;
            target = Bukkit.getPlayer(args[0]);
            answer = args[1];
            answerText = args[2];
        } else {
            sender.sendMessage("请让玩家执行该指令！！！");
        }
        return true;
    }

    public String permission(){
        return "answer.question";
    }
}
