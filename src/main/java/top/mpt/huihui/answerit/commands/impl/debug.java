package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.utils.PlayerUtils;
import top.mpt.huihui.answerit.utils.i18N;

import static top.mpt.huihui.answerit.Main.Online_Players;

public class debug extends ICommand {
    public debug(){
        super("debug", "", "/answer debug [language_path_single]/getOnline");
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        if (args.length < 1){
            sender.sendMessage(ChatColor.RED + "Error args");
            return true;
        }
        if (args[0].equals("getOnline")){
            sender.sendMessage(ChatColor.AQUA + "Online Players:" + Online_Players);
        }
        else {
            PlayerUtils.send(sender, i18N.getLang(args[0]));
        }
        return true;
    }

    public String permission() {
        return "answerop.debug";
    }
}
