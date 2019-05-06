package com.wl.mebatis.v2.session;

import com.wl.mebatis.v2.annotation.Entity;
import com.wl.mebatis.v2.annotation.Select;
import com.wl.mebatis.v2.binding.MapperRegister;
import com.wl.mebatis.v2.executor.CachingExecutor;
import com.wl.mebatis.v2.executor.Executor;
import com.wl.mebatis.v2.executor.SimpleExecutor;
import com.wl.mebatis.v2.plugin.Interceptor;
import com.wl.mebatis.v2.plugin.InterceptorChain;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Configuration {


    public static ResourceBundle sqlMappings;

    public static ResourceBundle configMappings;

    //从mapperRegister中获取
    private MapperRegister mapperRegister = new MapperRegister();

    //保存接口方法和sql的映射关系
    private Map<String, String> mapperStatement = new HashMap<String, String>();

    private InterceptorChain interceptorChain = new InterceptorChain();


    //初始化的时候 解析
    public Configuration() {
        //解析配置文件
        sqlMappings = ResourceBundle.getBundle("sql");
        configMappings = ResourceBundle.getBundle("mysql");

        //加载拦截器
        parseInterceptor(sqlMappings.getString("plugin.path"));

        //解析mapper  顺便解析注解
        parseMapper(configMappings.getString("mapper.path"));

        //解析sql保存起来
        parseSql();
    }

    //加载拦截器
    private void parseInterceptor(String clazz) {
        if (clazz == null || "".equals(clazz)) {
            return;
        }
        try {
            Object interceptor = Class.forName(clazz).newInstance();
            if (interceptor instanceof Interceptor) {
                this.interceptorChain.addInterceptor((Interceptor) interceptor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解析sql
    private void parseSql() {
        try {
            for (String statementId : sqlMappings.keySet()) {
                String sql = sqlMappings.getString(statementId).split("--")[0];
                String pojoClass = sqlMappings.getString(statementId).split("--")[1];


                String mapper = statementId.substring(0, statementId.lastIndexOf("."));

                //保存
                mapperRegister.addMapper(Class.forName(mapper), Class.forName(pojoClass));

                //保存接口方法和sql的映射关系
                mapperStatement.put(statementId, sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解析mapper
    private void parseMapper(String mapperPathScan) {
        String mapperPath = mapperPathScan.replaceAll("\\.", "/");
        //解析
        URL resource = this.getClass().getClassLoader().getResource(mapperPath);
        File file = new File(resource.getFile());
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                parseMapper(mapperPathScan + "." + f.getName());
            } else {
                //保存
                String mapperClassPath = mapperPathScan + "." + (f.getName().replace(".class", ""));
                try {
                    Class<?> clazz = Class.forName(mapperClassPath);
                    //不是接口的话不算
                    if (!clazz.isInterface()) {
                        continue;
                    }
                    //获取entity的注解
                    if (!clazz.isAnnotationPresent(Entity.class)) {
//                        throw new RuntimeException(clazz.getName() + " 未设置@Entity注解");
                    }
                    Entity entity = clazz.getAnnotation(Entity.class);
                    //保存
                    mapperRegister.addMapper(clazz, entity.value());

                    //解析接口的函数
                    for (Method declaredMethod : clazz.getDeclaredMethods()) {
                        if (!declaredMethod.isAnnotationPresent(Select.class)) {
                            continue;
                        }

                        Select select = declaredMethod.getAnnotation(Select.class);
                        String sql = select.value();
                        mapperStatement.put(clazz.getName() + "." + declaredMethod.getName(), sql);
                    }


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //获取mapper
    public <T> T getMapper(Class<T> clazz, DefaultSqlSession sqlSession) {
        return mapperRegister.getMapper(clazz, sqlSession);
    }

    //获取sql
    public String getMapperStatement(String statementId) {
        return this.mapperStatement.get(statementId);
    }

    public Executor newExecutor() {
        Executor executor = null;
        if (configMappings.getString("cache.enabled").equals("true")) {
            //走缓存
            executor = new CachingExecutor(new SimpleExecutor());
        } else {
            //走默认的
            executor = new SimpleExecutor();
        }

        //判断是否有拦截器
        if (interceptorChain.hasPlugin()) {
            //返回拦截器的代理对象
           return (Executor) interceptorChain.pluginAll(executor);
        }

        return executor;
    }
}
