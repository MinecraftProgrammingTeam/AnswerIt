package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.utils.PlayerUtils;

public class vote extends ICommand {
    public vote(){
        super("vote", "", "投票Write类型问题是对是错");
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player){
            if (args.length != 2){
                PlayerUtils.send(sender, "#RED#格式错误！");
            } else if (args[0].equals("正确")){

            } else if (args[0].equals("错误")){

            }
        } else {
            sender.sendMessage("请让玩家执行该指令");
        }
        return true;
    }

    public String permission(){
        return "answer.vote";
    }
}
