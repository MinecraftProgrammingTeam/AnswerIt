package top.mpt.huihui.answerit.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class i18N {
    private static FileConfiguration config;
    public static void setYaml(FileConfiguration configuration){
        config = configuration;
    }
    public static Object getLang(String path){
        return ConfigUtils.getConfig(config, path);
    }
    public static List<?> getLangList(String path){
        return ConfigUtils.getListConfig(config, path);
    }
}
