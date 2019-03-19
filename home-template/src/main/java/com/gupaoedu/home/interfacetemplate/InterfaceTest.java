package com.gupaoedu.home.interfacetemplate;

public class InterfaceTest {

    public static void main(String[] args) {
        System.out.println("===================红烧肉");
        BraisedPork cooking = new BraisedPork();
        cooking.cooking();

        System.out.println("===================清蒸鲈鱼");
        SteamedSquid steamedSquid = new SteamedSquid();
        steamedSquid.cooking();


        System.out.println("===================爆炒辣椒 ");
        Chili chili  = new Chili(false);
        chili.cooking();

    }

}
