package com.gupaoedu.home.pancake;

public class EggPancakeDecorator extends BasePancakeDecorator {

    public EggPancakeDecorator(BasePancake basePancake) {
      super(basePancake);
    }

    @Override
    String msg() {
        return super.msg()+"鸡蛋";
    }

    @Override
    int price() {
        return super.price()+1;
    }
}
