package top.mpt.huihui.answerit.commands.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import top.mpt.huihui.answerit.Main;
import top.mpt.huihui.answerit.commands.ICommand;
import static top.mpt.huihui.answerit.Main.*;

import java.io.File;

public class reload extends ICommand {
    public reload(){
        super("reload", "", "重载config(仅OP)");
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        JavaPlugin main = getPlugin(Main.class);
        main.reloadConfig();
        File file = new File(main.getDataFolder() + "\\lang", main.getConfig().getString("lang"));
        config = YamlConfiguration.loadConfiguration(file);
        sender.sendMessage("[AnswerIt] Plugin Reload Completed");
        return true;
    }

    public String permission() {
        return "answerop.reload";
    }
}
