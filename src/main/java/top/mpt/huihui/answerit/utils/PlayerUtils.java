package top.mpt.huihui.answerit.utils;

import org.bukkit.command.CommandSender;

import java.util.Collections;

/**
 * Player工具类
 * @author WindLeaf_qwq
 */
public class PlayerUtils {

    /**
     * 给玩家发送信息
     * @param sender 玩家或控制台
     * @param message 要发送的消息
     */
    public static void send(CommandSender sender, String message) {
        StringBuilder sb = new StringBuilder();
        Collections.singletonList(message).forEach(it -> sb.append(it).append(" "));
        sender.sendMessage(ChatUtils.translateColor(sb.toString()));
    }

    /**
     * 给玩家发送消息（带占位符)
     * @param sender 玩家或控制台
     * @param message 要发送的信息
     * @param args 占位符替换
     */
    public static void send(CommandSender sender, String message, Object... args) {
        send(sender, String.format(message, args));
    }
}
