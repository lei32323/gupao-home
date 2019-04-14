package com.dy.spring.beans.factory.support;

import com.dy.spring.annotation.Controller;
import com.dy.spring.annotation.Service;
import com.dy.spring.beans.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AbstractBeanDefinitionReader {

    private Properties config = new Properties();

    private List<String> registerBeanClasses = new ArrayList<String>();

    public AbstractBeanDefinitionReader(String configLocation) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(configLocation.replace("classpath:", ""));
        try {
            config.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (config.getProperty("spring.package.scan") == null) {
            throw new RuntimeException("扫描路径不存在");
        }
        //扫描包
        doScan(config.getProperty("spring.package.scan"));
    }

    private void doScan(String scanPackage) {
        String scanPackagePath = scanPackage.replaceAll("\\.", "/");
        //加载配置信息
        URL resource = this.getClass().getClassLoader().getResource(scanPackagePath);
        File scanPackageFile = new File(resource.getPath());
        File[] files = scanPackageFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                doScan(scanPackage + "." + file.getName());
            } else {
                //获取
                String className = (scanPackage + "." + file.getName()).replace(".class", "");
                this.registerBeanClasses.add(className);
            }
        }

    }

    public List<BeanDefinition> loadBeanDefinition() {
        List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();
        try {
            for (String registerBeanClass : registerBeanClasses) {
                //1.获取类
                Class<?> clazz = Class.forName(registerBeanClass);
                //2.判断是否有controllert或者service注解
                if (clazz.getAnnotation(Controller.class) == null && clazz.getAnnotation(Service.class) == null) {
                    continue;
                }
                //2. 如果有接口的话 接口名称保存起来
                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setBeanClassName(anInterface.getName());
                    beanDefinition.setFactoryBeanName(anInterface.getName());
                    beanDefinitions.add(beanDefinition);
                }
                //3.进行实例化保存到beanDefition
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setBeanClassName(registerBeanClass);
                beanDefinition.setFactoryBeanName(toFirstLow(clazz.getSimpleName()));
                beanDefinitions.add(beanDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanDefinitions;
    }

    public static String toFirstLow(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public Properties getConfig(){
        return this.config;
    }
}
