package top.mpt.huihui.answerit.utils;

import top.mpt.huihui.answerit.Main;

/**
 * Config工具类
 * @author WindLeaf_qwq & X_huihui
 */
public class ConfigUtils {
    /**
     * 获取Config
     * @param path 名称
     * @return ConfigValue
     */
    public static Object getConfig(String path) {
        Main instance = Main.instance;
        return instance.getConfig().get(path);
    }

    /**
     * 获取Config
     * @param path 名称
     * @param defaultValue 默认值
     * @return ConfigValue
     */
    public static Object getConfig(String path, Object defaultValue) {
        Object result = getConfig(path);
        return result == null ? defaultValue : result;
    }

}
