package org.springframework.beans.factory.support;

import org.springframework.annotation.Controller;
import org.springframework.annotation.Service;
import org.springframework.beans.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BeanDefinitionReader {

    private Properties config = new Properties();

    private final String DEFAULT_CONFIGLOCATION = "scan.package";

    private final List<String> registerBeanClass = new ArrayList<>();

    private final DefaultListableBeanFactory registry = new DefaultListableBeanFactory();

    public BeanDefinitionReader(String configLocation) {
        configLocation = configLocation.replace("classpath:", "");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configLocation);
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //扫描包下的类
        doScan(config.getProperty(DEFAULT_CONFIGLOCATION));
    }

    //扫描包下的类
    private void doScan(String scanPackage) {
        String scanPackagePath = scanPackage.replaceAll("\\.", "/");
        scanPackagePath = this.getClass().getClassLoader().getResource(scanPackagePath).getFile();
        File file = new File(scanPackagePath);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                //找到下一级文件夹
                doScan(scanPackage + "." + f.getName());
            }
            //保存起来
            registerBeanClass.add(scanPackage + "." + (f.getName().replace(".class","")));
        }
    }

    //创建初始化
    public List<BeanDefinition> loadBeanDefinition() {
        List<BeanDefinition> result = new ArrayList<>();
        for (String beanClass : registerBeanClass) {
            try {
                Class<?> clazz = Class.forName(beanClass);
                //获取名称
                if (clazz.isAnnotationPresent(Controller.class)) {
                    Controller controller = clazz.getAnnotation(Controller.class);
                    String factoryBeanName = controller.value();
                    if ("".equals(factoryBeanName)) {
                        factoryBeanName = clazz.getSimpleName();
                    }
                    result.add(new BeanDefinition(clazz.getName(), toFirstLow(factoryBeanName)));
                }
                if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);
                    String factoryBeanName = service.value();
                    if ("".equals(factoryBeanName)) {
                        factoryBeanName = clazz.getSimpleName();
                    }
                    result.add(new BeanDefinition(clazz.getName(), toFirstLow(factoryBeanName)));
                }
                //如果是接口的话
                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    result.add(new BeanDefinition(clazz.getName(), anInterface.getName()));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String toFirstLow(String beanName) {
        char[] chars = beanName.toCharArray();
        chars[0] += 32;
        return new String(chars);
    }

    public DefaultListableBeanFactory getRegistry() {
        return registry;
    }
}
