package top.mpt.huihui.answerit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import top.mpt.huihui.answerit.executor.CommandHandler;

import static org.bukkit.ChatColor.BLUE;

public final class Main extends JavaPlugin {

    // getLogger()用
    public static Main instance;
    public String normal = BLUE + "[AnswerIt] ";
    @Override
    public void onEnable() {
        instance = this;
        getCommand("answer").setExecutor(new CommandHandler());
        getLogger().info(normal + ChatColor.AQUA + "插件已启用");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
