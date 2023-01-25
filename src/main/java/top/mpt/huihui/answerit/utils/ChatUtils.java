package top.mpt.huihui.answerit.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Chat工具类
 * @author WindLeaf_qwq
 */
public class ChatUtils {
    // 颜色List
    private static final ArrayList<ChatColor> colors = new ArrayList<>();
    // 存放颜色代码对应的中文
    /**
     * 将带特殊颜色代码的文本转为带颜色代码的文本
     * <p>换行
     * 例如："#RED#你输了！" -> "§c你输了！"
     * @param string 带特殊颜色代码的文本
     * @return 转换后的文本
     */
    public static String translateColor(String string) {
        // 正则表达式替换
        String result = string; // #RED#[红队]
        Pattern regex = Pattern.compile("#[A-Z_-]+#");
        Matcher matcher = regex.matcher(result);
        while (matcher.find()) {
            String code = matcher.group(); // #RED#
            result = result.replaceFirst(
                    code, // #RED#
                    "§" + ChatColor.valueOf(code.replaceAll("#", "")).getChar() // §c
            );
        }
        return result;
    }

    /**
     * 将带特殊颜色代码的文本转为带颜色代码的文本 (带占位符)
     * <p>
     * 例如："#RED#你输了！" -> "§c你输了！"
     * @param string 带特殊颜色代码的文本
     * @param args 占位符替换
     * @return 转换后的文本
     */
    public static String translateColor(String string, Object... args) {
        return translateColor(String.format(string, args));
    }

    /**
     * 全服公告消息(带占位符)
     * @param message 要公告的消息
     * @param args 占位符替换
     */
    public static void broadcast(String message, Object... args) {
        Bukkit.getOnlinePlayers().forEach(it -> PlayerUtils.send(it, message, args));
    }

}
