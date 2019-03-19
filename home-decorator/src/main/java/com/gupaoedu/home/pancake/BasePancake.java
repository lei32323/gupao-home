package com.gupaoedu.home.pancake;

public class BasePancake extends AbstractPancake {
    @Override
    String msg() {
        return "煎饼";
    }

    @Override
    int price() {
        return 3;
    }
}
