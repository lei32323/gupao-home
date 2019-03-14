package com.gupaoedu.home.service.strategy;

import com.gupaoedu.home.service.jsoncheck.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 获取场景的类
 */
public class ScenesStrategy {

    private ScenesStrategy(){}

    //注册场景的集合
    private static final List<AbstractScenes> SCENES_REGISTER = new CopyOnWriteArrayList<>();

    static {
        //初始化场景
        SCENES_REGISTER.add(new NewMasterOrViceScenes());
        SCENES_REGISTER.add(new AlreadyMasterNewSecondaryCard());
    }

    //获取全部
    public static List<AbstractScenes> getScenes(Root root){
        //返回的场景对象集合
        List<AbstractScenes> abstractScenes = new ArrayList<>();

        //找到当前场景编码的场景对象
        SCENES_REGISTER.forEach(scenes->{
            if(scenes.getSale_order_template_id().equals(root.getCont().getPub_info().getSale_order_template_id())){
                scenes.init(root);//初始化
                abstractScenes.add(scenes);
            }
        });
        //返回
        return abstractScenes;
    }


}
