package org.springframework.context;

import org.springframework.context.support.AbstractApplicationContext;

//注解配置上下文
public class ApplicationContext extends AbstractApplicationContext {

    public ApplicationContext(String configLocation) {
        //初始化父类
        super(configLocation);
        //刷新上下文
        refresh();
    }


}
