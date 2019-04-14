package com.dy.spring2.beans.factory;

import com.dy.spring.annotation.Controller;
import com.dy.spring.annotation.Service;
import com.dy.spring2.beans.config.BeanDefinition;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Auther: wanglei
 * @Date: 2019/4/14 15:36
 * @Description:
 */
public class BeanDefinitionReader {

    private Properties config = new Properties();

    private List<String> registerBeanClazzs = new ArrayList<String>();

    public BeanDefinitionReader(String configLocation) {
        //解析配置中的信息
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(configLocation.replace("classPath:", ""));
        try {
            config.load(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (config.getProperty("spring.package.scan") == null) {
            throw new RuntimeException("扫描包不存在");
        }
        doScan(config.getProperty("spring.package.scan"));

    }
    //扫描类
    private void doScan(String scanPackage) {
          String scanPackagePath = scanPackage.replaceAll("\\.", "/");
          URL resource = this.getClass().getClassLoader().getResource(scanPackagePath);
          File f = new File(resource.getPath());
          //如果是文件的话。循环获取
          if(f.isDirectory()){
              doScan(scanPackage+"."+f.getName());
          }
          //如果是文件的话，保存
          registerBeanClazzs.add(scanPackage+".class");
    }



    public List<BeanDefinition> loadBeanDefinition() {
        List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();
        if(registerBeanClazzs.isEmpty()){return null;}
        for (String registerBeanClazz : registerBeanClazzs) {
            try{
                Class<?> aClass = Class.forName(registerBeanClazz);

                if(aClass.getAnnotation(Controller.class)==null&&aClass.getAnnotation(Service.class)==null){
                    continue;
                }
                beanDefinitions.add(createBeanDefinition(aClass.getName(),aClass.getSimpleName()));
                //如果有接口的话，保存接口类名称
                Class<?>[] interfaces = aClass.getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    beanDefinitions.add(createBeanDefinition(anInterface.getName(),anInterface.getName()));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return beanDefinitions;
    }


    private BeanDefinition createBeanDefinition(String className,String factoryClassName){
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClassName(className);
        beanDefinition.setFactoryClassName(factoryClassName);
        return beanDefinition;
    }


}
