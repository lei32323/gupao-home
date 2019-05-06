package com.mybaits.demo.plugins;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

@Intercepts({@Signature(type = Exception.class, method = "update", args = {MappedStatement.class, Object.class})})
public class DyInterceptor implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    public void setProperties(Properties properties) {
        //设置参数
    }
}
