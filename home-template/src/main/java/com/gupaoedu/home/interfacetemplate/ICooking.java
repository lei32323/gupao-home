package com.gupaoedu.home.interfacetemplate;

public interface ICooking {

    //刷锅
    default void brushPot(){
        System.out.println("刷锅");
    }

    default void hotPot(){
        System.out.println("热锅");
    }

    default void drain(){
        System.out.println("放油");
    }

    default void stirFry(){
        System.out.println("翻炒");
    }

    boolean needSeasoning();

    default void seasoning(){
        System.out.println("放佐料");
    }

    default void pan(){
        System.out.println("出锅");
    }

    default void cooking(){
        brushPot();
        hotPot();
        drain();
        stirFry();
        if(needSeasoning()){
            seasoning();
        }
        pan();

    }


}
