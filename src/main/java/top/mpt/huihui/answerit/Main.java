package top.mpt.huihui.answerit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import top.mpt.huihui.answerit.executor.CommandHandler;
import top.mpt.huihui.answerit.listener.InvClose;
import top.mpt.huihui.answerit.listener.InvOpen;
import top.mpt.huihui.answerit.listener.PlayerChat;
import top.mpt.huihui.answerit.listener.PlayerJoinAndQuit;
import top.mpt.huihui.answerit.utils.i18N;

import java.io.*;
import java.util.*;

import static org.bukkit.ChatColor.BLUE;

public final class Main extends JavaPlugin {
    // 负责记录正在进行问答的玩家们
    public static List<Player> playersOnQuestioning = new ArrayList<>();
    // 负责记录Tokens
    public static List<String> tokens = new ArrayList<>();
    // log用
    public static Main instance;
    /* Write */
    // 是否检查玩家聊天
    public static boolean isCheckChat = false;
    // 存票(正确的票数和错误的票数)
    public static int voteRight = 0;
    public static int voteWrong = 0;

    // 存玩家（防止重复投票）
    public static List<String> voteList = new ArrayList<>();
    // 避免玩家投票结束后进行投票
    public static boolean canVote = false;
    // 用于lang sys
    public static FileConfiguration config;
    // 新建数组，存放玩家，会在PlayerJoinAndQuit类和q类中被调用
    public static List<String> Online_Players = new ArrayList<>();
    // 设置normal项（用于broadcast）
    public static String normal = BLUE + "[AnswerIt] ";
    // 投票结束时间（用于给玩家显示ActionBar）
    public static long voteEndTime = 0;
    @Override
    public void onEnable() {
        instance = this;
        // config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getOnlinePlayers().forEach(it -> Online_Players.add(it.getName()) );

        File file = new File(getDataFolder() + "/lang/", getConfig().getString("lang"));
        saveResource("lang/zh_cn.yml", false);
        saveResource("lang/en_us.yml", false);
        config = YamlConfiguration.loadConfiguration(file);
        i18N.setYaml(config);

        // 指令
        getCommand("answer").setExecutor(new CommandHandler());
        // 注册事件
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        getServer().getPluginManager().registerEvents(new InvOpen(), this);
        getServer().getPluginManager().registerEvents(new InvClose(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinAndQuit(), this);
        getLogger().info(normal + ChatColor.AQUA + "Plugin Enabled");
        /* wait player's commands */
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
