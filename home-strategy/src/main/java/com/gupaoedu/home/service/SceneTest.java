package com.gupaoedu.home.service;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.home.service.jsoncheck.Root;
import com.sun.org.apache.bcel.internal.util.ClassPath;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SceneTest {

    public static void main(String[] args) {
       try {
           //传入场景名称
           StringBuffer sb = new StringBuffer();
           Path path = Paths.get(System.getProperty("user.dir")+"/json.txt");
           List<String> lines = Files.readAllLines(path);
           lines.forEach(str -> sb.append(str));
           String json = sb.toString();

           Root root = JSON.parseObject(json, Root.class);
           SceneExec sceneExec = new SceneExec();
           int exec = sceneExec.exec(root);
           System.out.println(exec);
       }catch (Exception e){
           e.printStackTrace();
       }
    }

}
