package org.springframework.web.servlet;

import org.springframework.annotation.Controller;
import org.springframework.annotation.RequestMapping;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DispatcherServlet extends HttpServlet {


    private List<HandlerMapping> handlerMappings = new CopyOnWriteArrayList();

    private Map<HandlerMapping, HandlerAdapter> handlerAdapters = new ConcurrentHashMap<>();


    private List<ViewResolver> viewResolvers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        HandlerMapping handler = getHandler(req);

        HandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        ModelAndView modelAndView = handlerAdapter.handle(req, resp, handler);

        try {
            processDispatchResult(req, resp, modelAndView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, ModelAndView modelAndView) throws Exception {

        for (ViewResolver viewResolver : this.viewResolvers) {
            View view = viewResolver.resolveViewName(modelAndView.getView().toString(), null);
            view.render(modelAndView.getModel(), req, resp);
            return;
        }

    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        return handlerAdapters.get(handler);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        //根据请求获取url
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "");
        for (HandlerMapping handlerMapping : handlerMappings) {
            if (handlerMapping.getUrl().equals(url)) {
                return handlerMapping;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化
        ApplicationContext context = new ApplicationContext(config.getInitParameter("configLocation"));

        //加载九大组件
        //初始化file上传处理器
        initMultipartResolver(context);
        //本地语言
        initLocaleResolver(context);
        //主题
        initThemeResolver(context);
        //url和method映射器  实现
        initHandlerMappings(context);
        //参数处理适配器  实现
        initHandlerAdapters(context);
        //异常处理
        initHandlerExceptionResolvers(context);
        //reuqest和view翻译器
        initRequestToViewNameTranslator(context);
        //视图处理器  实现
        initViewResolvers(context);
        //重定向参数传递处理器
        initFlashMapManager(context);
    }


    private void initHandlerMappings(ApplicationContext context) {
        String[] beanDefinitionNames = context.getReader().getRegistry().getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = context.getBean(beanDefinitionName);
            //获取类型 是否是Controller的
            Class<?> clazz = bean.getClass();
            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }
            RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
            String url = "";
            if (requestMapping != null) {
                url = requestMapping.value();
            }
            //循环方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                url = url + "/" + mapping.value();
                handlerMappings.add(new HandlerMapping(url.replaceAll("//+", "/"), method, bean));
            }
        }
    }

    //参数映射
    private void initHandlerAdapters(ApplicationContext context) {
        for (HandlerMapping handlerMapping : this.handlerMappings) {
            handlerAdapters.put(handlerMapping, new HandlerAdapter());
        }
    }


    //视图解析器
    private void initViewResolvers(ApplicationContext context) {
        //获取到文件所在的路径
        String templateRoot = context.getReader().getConfig().getProperty("templateRoot");
        //获取到文件夹下文件
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File file = new File(templateRootPath);
        File[] files = file.listFiles();
        for (File f : files) {
            viewResolvers.add(new ViewResolver(templateRoot));
        }
    }


    private void initFlashMapManager(ApplicationContext context) {
    }


    private void initRequestToViewNameTranslator(ApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(ApplicationContext context) {
    }

    private void initThemeResolver(ApplicationContext context) {
    }

    private void initLocaleResolver(ApplicationContext context) {
    }

    private void initMultipartResolver(ApplicationContext context) {
    }
}