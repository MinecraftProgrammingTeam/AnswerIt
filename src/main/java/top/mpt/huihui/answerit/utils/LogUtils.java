package top.mpt.huihui.answerit.utils;

import top.mpt.huihui.answerit.Main;

/**
 * 日志工具库
 * @author X_huihui
 */
public class LogUtils {
    // 定义instance
    static Main instance = Main.instance;

    /**
     * LogInfo
     * @param log 要输出的info
     */
    public static void info(Object log){
        instance.getLogger().info(ChatUtils.translateColor(log.toString()));
    }

    /**
     * LogInfo(WithArguments)
     * @param log 要输出的info
     * @param args 占位符
     */
    public static void info(Object log, Object... args){
        instance.getLogger().info(ChatUtils.translateColor(log.toString(), args));
    }

    /**
     * LogWarning
     * @param log 要输出的warning
     */
    public static void warning(Object log){
        instance.getLogger().warning(ChatUtils.translateColor(log.toString()));
    }

    /**
     * LogWarning(WithArguments)
     * @param log 要输出的warning
     * @param args 占位符
     */
    public static void warning(Object log, Object... args){
        instance.getLogger().warning(ChatUtils.translateColor(log.toString(), args));
    }
}
