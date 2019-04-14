package com.dy.spring.webmvc;

import com.dy.spring.annotation.Controller;
import com.dy.spring.annotation.RequestMapping;
import com.dy.spring.context.ApplicationContext;
import com.dy.spring.webmvc.servlet.HandlerAdapater;
import com.dy.spring.webmvc.servlet.HandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class DispatchServlet extends HttpServlet {

    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private ApplicationContext applicationContext;

    private List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    private Map<HandlerMapping, HandlerAdapater> HandlerAdapaters = new ConcurrentHashMap<HandlerMapping,HandlerAdapater>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {

        //获取applicationContext
        applicationContext = new ApplicationContext(getServletConfig().getInitParameter(CONTEXT_CONFIG_LOCATION));

        //初始化9大组件
        initStrategies(applicationContext);
    }

    private void initStrategies(ApplicationContext applicationContext) {
        //
        initHandlerMappings(applicationContext);

        initHandlerAdapters(applicationContext);

        initViewResolvers(applicationContext);

    }

    private void initViewResolvers(ApplicationContext applicationContext) {
        Object templateRoot = applicationContext.getConfig().get("templateRoot");
    }

    private void initHandlerAdapters(ApplicationContext applicationContext) {
        for (HandlerMapping handlerMapping : this.handlerMappings) {
            this.HandlerAdapaters.put(handlerMapping,new HandlerAdapater());
        }
    }

    private void initHandlerMappings(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            Class<?> clazz = bean.getClass();
            if(clazz.isAnnotationPresent(Controller.class)){
                continue;
            }

            String baseUrl = "";
            if(clazz.isAnnotationPresent(RequestMapping.class)){
                //获取url
                RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
                String value = annotation.value();
                if("".equals(value)){
                    baseUrl = value;
                }
            }

            //获取method
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                //判断是否有requestMapping
                if(method.isAnnotationPresent(RequestMapping.class)){continue;}

                RequestMapping annotation = method.getAnnotation(RequestMapping.class);

                String value = annotation.value();

                String regex = ("/"+baseUrl+"/"+value.replaceAll("\\*","*")).replaceAll("/+","/");
                Pattern pattern = Pattern.compile(regex);
                HandlerMapping handlerMapping = new HandlerMapping();
                handlerMapping.setController(bean);
                handlerMapping.setMethod(method);
                handlerMapping.setPattern(pattern);
                handlerMappings.add(handlerMapping);
            }

        }


    }


}
