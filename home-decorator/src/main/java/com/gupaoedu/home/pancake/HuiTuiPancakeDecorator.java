package com.gupaoedu.home.pancake;

public class HuiTuiPancakeDecorator extends BasePancakeDecorator {
    public HuiTuiPancakeDecorator(BasePancake basePancake) {
        super(basePancake);
    }

    @Override
    String msg() {
        return super.msg()+" 加火腿";
    }

    @Override
    int price() {
        return super.price()+2;
    }
}
