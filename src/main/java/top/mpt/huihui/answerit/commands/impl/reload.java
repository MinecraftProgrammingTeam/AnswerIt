package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.command.CommandSender;
import top.mpt.huihui.answerit.Main;
import top.mpt.huihui.answerit.commands.ICommand;

public class reload extends ICommand {
    public reload(){
        super("reload", "", "重载config(仅OP)");
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        Main.getPlugin(Main.class).reloadConfig();
        sender.sendMessage("[AnswerIt] Plugin Reload Completed");
        return true;
    }

    public String permission() {
        return "answerop.reload";
    }
}
