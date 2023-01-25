package top.mpt.huihui.answerit;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import top.mpt.huihui.answerit.executor.CommandHandler;
import top.mpt.huihui.answerit.listener.PlayerChat;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.ChatColor.BLUE;

public final class Main extends JavaPlugin {

    // getLogger()用
    public static Main instance;
    // 是否检查玩家聊天
    public static boolean isCheckChat = false;
    // 存票
    public static List<Boolean> voteResult = new ArrayList<>();
    // 存玩家（防止重复投票）
    public static List<String> voteList = new ArrayList<>();
    // 避免玩家投票结束后进行投票
    public static boolean canVote = false;
    public static String normal = BLUE + "[AnswerIt] ";
    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("answer").setExecutor(new CommandHandler());
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        getLogger().info(normal + ChatColor.AQUA + "插件已启用");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
