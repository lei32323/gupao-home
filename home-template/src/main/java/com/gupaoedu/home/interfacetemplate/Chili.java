package com.gupaoedu.home.interfacetemplate;

public class Chili implements ICooking {

    private boolean needSeasoning;

    public Chili(boolean needSeasoning) {
        this.needSeasoning = needSeasoning;
    }

    @Override
    public boolean needSeasoning() {
        return this.needSeasoning;
    }
}
