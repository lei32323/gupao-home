package com.wl.demo;

import javax.management.*;

public class Mechine implements MyMBean {
    @Override
    public int getCpuCore() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }

}
