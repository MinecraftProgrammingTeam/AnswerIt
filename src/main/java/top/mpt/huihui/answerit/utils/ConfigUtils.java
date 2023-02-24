package top.mpt.huihui.answerit.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * Config工具类
 * @author WindLeaf_qwq & X_huihui
 */
public class ConfigUtils {
    /**
     * 获取Config
     * @param config FileConfiguration
     * @param path 名称
     * @return ConfigValue
     */
    public static Object getConfig(FileConfiguration config, String path) {
        return config.get(path);
    }

    /**
     * 获取Config
     * @param config FileConfiguration
     * @param path 名称
     * @param defaultValue 默认值
     * @return ConfigValue
     */
    public static Object getConfig(FileConfiguration config, String path, Object defaultValue) {
        Object result = getConfig(config, path);
        return result == null ? defaultValue : result;
    }

    public static List<?> getListConfig(FileConfiguration config, String path){
        return config.getList(path);
    }

}
