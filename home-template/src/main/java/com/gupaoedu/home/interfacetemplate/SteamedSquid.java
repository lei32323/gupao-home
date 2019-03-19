package com.gupaoedu.home.interfacetemplate;

public class SteamedSquid implements ICooking {
    @Override
    public boolean needSeasoning() {
        return true;
    }

    @Override
    public void seasoning() {
        System.out.println("放清蒸鲈鱼的佐料");
    }
}
