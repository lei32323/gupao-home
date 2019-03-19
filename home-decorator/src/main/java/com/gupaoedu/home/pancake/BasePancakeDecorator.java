package com.gupaoedu.home.pancake;

/**
 * 基础煎饼的装饰器
 */
public abstract class BasePancakeDecorator extends BasePancake{

    private BasePancake basePancake;

    public BasePancakeDecorator(BasePancake basePancake) {
        this.basePancake = basePancake;
    }

    @Override
    String msg() {
        return this.basePancake.msg();
    }

    @Override
    int price() {
        return this.basePancake.price();
    }
}
