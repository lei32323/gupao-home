package com.wl.mebatis.v2.plugin;

import java.util.ArrayList;
import java.util.List;

public class InterceptorChain {

    private final List<Interceptor> chains = new ArrayList<Interceptor>();


    public void addInterceptor(Interceptor interceptor) {
        this.chains.add(interceptor);
    }

    public Object pluginAll(Object target) {
        for (Interceptor chain : chains) {
            target = chain.plugin(target);
        }
        return target;
    }

    public boolean hasPlugin() {
        if (chains.size() == 0) {
            return false;
        }
        return true;
    }
}
