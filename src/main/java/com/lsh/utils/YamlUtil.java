package com.lsh.utils;


import com.lsh.Po.YmlConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @Author: lish
 * @Description: yml文件解析
 * @Date:2019/4/29
 */


public class YamlUtil {

    public static YmlConfig getYml(String path){
        YmlConfig ymlConfig = null;
        try{

            Yaml yaml = new Yaml();
            ymlConfig = yaml.loadAs(new FileInputStream(new File(path)),YmlConfig.class);

        }catch (Exception e){
            e.printStackTrace();
        }

        return ymlConfig;


    }

    public static Map<String, Object> getYml1(String path,String key){
        Map<String, Object> map = null;
        try{

            Yaml yaml = new Yaml();
            map = yaml.loadAs(new FileInputStream(new File(path)),Map.class);

        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String, Object> maps = new HashMap<String, Object>();
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            Object obj = entry.getValue();
            if (entry.getKey().equals(key))
                return (Map<String, Object>)obj;

            if (entry.getValue() instanceof Map) {
                maps = getYml1(path, key);
                if (maps == null)
                    continue;
                return maps;
            }
        }
        return null;
    }



}
