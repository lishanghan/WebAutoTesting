package com.lsh.utils;


import com.lsh.Po.YmlConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * @Author: lish
 * @Description: yml文件
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



}
