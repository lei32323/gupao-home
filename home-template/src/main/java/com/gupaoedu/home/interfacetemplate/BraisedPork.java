package com.gupaoedu.home.interfacetemplate;

public class BraisedPork implements ICooking{
    @Override
    public boolean needSeasoning() {
        return true;
    }

    @Override
    public void seasoning() {
        System.out.println("放红烧肉的佐料");
    }
}
