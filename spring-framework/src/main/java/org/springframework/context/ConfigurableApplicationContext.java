package org.springframework.context;

import org.springframework.beans.factory.BeanFactory;

public interface ConfigurableApplicationContext extends BeanFactory {

    //上下文刷新
    void refresh();

}
