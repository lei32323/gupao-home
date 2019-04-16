package com.dy.spring.webmvc.servlet;

import com.dy.spring.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wanglei
 * @Date: 2019/4/14 19:19
 * @Description:
 */
public class HandlerAdapater {

    public boolean supports(Object handler) {
        return handler instanceof HandlerAdapater;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMapping handlerMapping = (HandlerMapping) handler;
        Map<String, Integer> paramIndexMapping = new HashMap<String, Integer>();
        Annotation[][] parameterAnnotations = handlerMapping.getMethod().getParameterAnnotations();

        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof RequestMapping) {
                    RequestMapping requestMapping = (RequestMapping) annotation;
                    String value = requestMapping.value();
                    if (!"".equals(value.trim())) {
                        paramIndexMapping.put(value, i);
                    }
                }
            }
        }
        Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == HttpServletRequest.class || parameterTypes[i] == HttpServletResponse.class) {
                paramIndexMapping.put(parameterTypes[i].getName(), i);
            }
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        Object[] paramValues = new Object[parameterTypes.length];

        for (Map.Entry<String, String[]> parm : parameterMap.entrySet()) {
            String value = Arrays.toString(parm.getValue()).replaceAll("\\[|\\]", "")
                    .replaceAll("\\s", ",");

            if (!paramIndexMapping.containsKey(parm.getKey())) {
                continue;
            }

            int index = paramIndexMapping.get(parm.getKey());
            paramValues[index] = caseStringValue(value, parameterTypes[index]);
        }

        if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            Integer index = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[index] = request;
        }

        if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            Integer index = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[index] = response;
        }

        try {
            Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramValues);

            if (result instanceof ModelAndView) {
                return (ModelAndView) result;
            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object caseStringValue(String value, Class clazz) {
        if (clazz == String.class) {
            return value;
        }
        if (clazz == Double.class) {
            return Double.parseDouble(value);
        }
        if (clazz == Integer.class) {
            return Integer.parseInt(value);
        }
        return null;
    }

}
