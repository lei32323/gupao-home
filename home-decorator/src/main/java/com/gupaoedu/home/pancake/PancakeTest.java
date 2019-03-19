package com.gupaoedu.home.pancake;

public class PancakeTest {

    public static void main(String[] args) {
        BasePancake basePancake = new BasePancake();
        System.out.println(basePancake.msg()+"  "+basePancake.price());

        BasePancake basePancake1 = new EggPancakeDecorator(basePancake);
        System.out.println(basePancake1.msg()+"  "+basePancake1.price());

        BasePancake basePancake2 = new EggPancakeDecorator(basePancake1);
        System.out.println(basePancake2.msg()+"  "+basePancake2.price());

        BasePancake basePancake3 = new EggPancakeDecorator(basePancake2);
        System.out.println(basePancake3.msg()+"  "+basePancake3.price());

        BasePancake huiTuiPancakeDecorator = new HuiTuiPancakeDecorator(basePancake3);
        System.out.println(huiTuiPancakeDecorator.msg()+"  "+huiTuiPancakeDecorator.price());

    }

}
