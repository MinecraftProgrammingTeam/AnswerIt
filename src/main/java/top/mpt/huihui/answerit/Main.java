package top.mpt.huihui.answerit;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import top.mpt.huihui.answerit.executor.CommandHandler;
import top.mpt.huihui.answerit.listener.InvOpen;
import top.mpt.huihui.answerit.listener.PlayerChat;
import top.mpt.huihui.answerit.utils.i18N;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.ChatColor.BLUE;

public final class Main extends JavaPlugin {

    // log用
    public static Main instance;
    /* Write */
    // 是否检查玩家聊天
    public static boolean isCheckChat = false;
    // 存票
    public static List<Boolean> voteResult = new ArrayList<>();
    // 存玩家（防止重复投票）
    public static List<String> voteList = new ArrayList<>();
    // 避免玩家投票结束后进行投票
    public static boolean canVote = false;
    // 用于lang sys
    public static FileConfiguration config;
    // 设置normal项（用于broadcast）
    public static String normal = BLUE + "[AnswerIt] ";
    @Override
    public void onEnable() {
        instance = this;
        // config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        File file = new File(getDataFolder() + "\\lang\\", getConfig().getString("lang"));
        saveResource("lang/zh_cn.yml", false);
        saveResource("lang/en_us.yml", false);

        config = YamlConfiguration.loadConfiguration(file);
        i18N.setYaml(config);

        // 指令
        getCommand("answer").setExecutor(new CommandHandler());
        // 注册事件
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        getServer().getPluginManager().registerEvents(new InvOpen(), this);
        getLogger().info(normal + ChatColor.AQUA + "Plugin Enabled");
        /* wait player's commands */
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
