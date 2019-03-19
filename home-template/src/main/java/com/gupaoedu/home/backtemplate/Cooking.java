package com.gupaoedu.home.backtemplate;

public class Cooking {

    //刷锅
    public void brushPot(){
        System.out.println("刷锅");
    }

    public void hotPot(){
        System.out.println("热锅");
    }

    public void drain(){
        System.out.println("放油");
    }

    public void stirFry(){
        System.out.println("翻炒");
    }

    public void pan(){
        System.out.println("出锅");
    }

    public void cooking(Seasoning seasoning){
        brushPot();
        hotPot();
        drain();
        stirFry();
        if(seasoning!=null) {
            seasoning.seasoning();
        }
        pan();

    }

    public interface Seasoning{
         void seasoning();
    }



}
